package ClientPackage;

import ClientPackage.Console.UserInput;
import ClientPackage.Console.UserOutput;
import ClientPackage.Exceptions.EndOfFileException;
import ClientPackage.InputHandler.Asker;
import ClientPackage.InputHandler.Validator;
import ClientPackage.Utility.ConnectionReciever;
import ClientPackage.Utility.QuerySender;
import ClientPackage.Utility.ResponseReader;
import Common.*;
import Common.DataTransferObjects.CommandTransferObject;
import Common.DataTransferObjects.CoordinatesTransferObject;
import Common.DataTransferObjects.FlatTransferObject;
import Common.DataTransferObjects.HouseTransferObject;

import java.io.*;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import static Common.Instruction.SCRIPT;

public class Client {

    private Validator validator;
    private ConnectionReciever connectionReciever;
    private Asker asker;
    private boolean isRunning;
    private Stack<String> callStack;

    public Client(String host, int port){
        this.connectionReciever = new ConnectionReciever(host, port);
        this.validator = new Validator();
        this.isRunning = true;
        this.callStack = new Stack();
    }

    public void run(){
        int maxConnectionAttempts = 10;
        int connectionAttempts = 0;
        Pair<Selector,SocketChannel> connection;
        while(true){
            try{
                connectionAttempts++;
                if(connectionAttempts==1) UserOutput.println("Connecting to server...");
                connection = connectionReciever.connectToServer();
                run("", connection);
                return;
            } catch (IOException e){
                if(connectionAttempts>=maxConnectionAttempts){
                    UserOutput.println("Failed to connect to server");
                    return;
                }
                UserOutput.println("Trying to reconnect to server...");
            }
        }
    }
    private boolean run(String filepath, Pair<Selector,SocketChannel> p){
        UserInput userInput;
        if(filepath == ""){
            userInput = new UserInput(new Scanner(System.in));
            this.asker = new Asker(userInput, validator);
        } else {
            try{
                userInput = new UserInput(new BufferedReader(new FileReader(new File(filepath))));
                this.asker = new Asker(userInput, validator);
            } catch(FileNotFoundException e){
                UserOutput.println("File "+filepath+" not found");
                return true;
            }
        }

        ResponseReader responseReader = new ResponseReader();
        QuerySender querySender = new QuerySender();

        Selector selector = p.first;
        SocketChannel socketChannel = p.second;

        Query query = null;
        try{
            while(isRunning){
                if(selector.select() == 0){
                    continue;
                }
                Set keySet = selector.selectedKeys();
                Iterator it = keySet.iterator();

                while(it.hasNext()){
                    SelectionKey key = (SelectionKey) it.next();
                    it.remove();
                    if(key.isWritable()){
                        if(query==null){
                            query = new Query();
                            query.setDTOCommand(asker.askValidatedCommand());
                        }
                        querySender.sendQuery(query,socketChannel);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    }
                    if(key.isReadable()){
                        Response response = responseReader.getResponse(socketChannel);
                        if (response.getInstruction()!=SCRIPT && !response.getContent().isEmpty()){
                            UserOutput.println(response.getContent());
                        }
                        query = handleResponse(response,socketChannel,selector);
                        if (!isRunning){
                            if (asker.isInteractive()) {
                                selector.close();
                                socketChannel.close();
                            }
                            return false;
                        }
                        socketChannel.register(selector,SelectionKey.OP_WRITE);
                    }
                }
            }
        } catch (IOException e) {
            UserOutput.println("Selector exception");
            return false;
        } catch (EndOfFileException e){
            return true;
        }
        return true;
    }

    public Query handleResponse (Response response, SocketChannel socketChannel, Selector selector) throws EndOfFileException {

        switch (response.getInstruction()){
            case ASK_COMMAND: {
                Query query = new Query();
                CommandTransferObject command = asker.askValidatedCommand();
                if(command!=null) return query.setDTOCommand(command);
                else return null;
            }
            case ASK_FLAT: {
                Query query = new Query();
                FlatTransferObject flat = asker.askValidatedFlat();
                if(flat!=null) return query.setDTOFlat(flat);
                else return null;
            }
            case ASK_COORDINATES: {
                Query query = new Query();
                CoordinatesTransferObject coords = asker.askValidatedCoordinates();
                if(coords!=null) return query.setDTOCoordinates(coords);
                else return null;
            }
            case ASK_HOUSE: {
                Query query = new Query();
                HouseTransferObject house = asker.askValidatedHouse();
                if(house!=null) return query.setDTOHouse(house);
                else return null;
            }
            case SCRIPT:{
                boolean recursion =
                        callStack
                        .stream()
                        .anyMatch(filepath -> filepath.equals(response.getContent()));
                if (recursion){
                    UserOutput.println("Failed to execute because of recursion");
                    return null;
                }
                callStack.push(response.getContent());
                try {
                    socketChannel.register(selector,SelectionKey.OP_WRITE);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
                Asker saveAsker = asker;
                if(!run(response.getContent(),new Pair(selector,socketChannel))){
                    isRunning = false;
                }
                callStack.pop();
                Query query = new Query();
                asker = saveAsker;
                if(isRunning){
                    return query.setDTOCommand(asker.askValidatedCommand());
                } else {
                    return null;
                }
            }
            case EXIT:{
                isRunning = false;
                return new Query();
            }
            default: return null;
        }

    }

}
