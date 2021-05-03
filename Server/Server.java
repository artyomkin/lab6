package Server;

import Common.DataTransferObjects.CommandTransferObject;
import Common.Pair;
import Common.Query;
import Common.Response;
import Server.CommandHandler.commands.*;
import Server.CommandHandler.utility.CommandManager;
import Server.CommandHandler.utility.FileManager;
import Server.CommandHandler.utility.CollectionManager;
import Server.CommandHandler.utility.ServerOutput;
import Server.ConnectionReciever.ConnectionReciever;
import Server.QueryReader.QueryReader;
import Server.ResponseSender.ResponseSender;

import java.io.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    private int port;
    public Server(int port){
        this.port = port;
    }

    public void run() {
        ConnectionReciever connectionReciever = new ConnectionReciever(port);
        Pair<SocketChannel, ServerSocketChannel> pair = connectionReciever.getConnection();
        SocketChannel socketChannel = pair.first;
        ServerSocketChannel serverSocketChannel = pair.second;

        CollectionManager collectionManager = new CollectionManager(new FileManager("input.json"));
        InputStream in;
        OutputStream out;
        try {
            in = socketChannel.socket().getInputStream();
            out = socketChannel.socket().getOutputStream();
        } catch (IOException e) {
            ServerOutput.warning("Setting response sender and query reader error");
            return;
        }
        QueryReader queryReader = new QueryReader(in);
        ResponseSender responseSender = new ResponseSender(out);
        CommandManager commandManager = new CommandManager(
                new ShowCommand(collectionManager),
                new ReplaceIfLowerCommand(collectionManager,responseSender,queryReader),
                new ClearCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(collectionManager,"input.json"),
                new FilterByTransportCommand(collectionManager),
                new InfoCommand(collectionManager),
                new InsertCommand(collectionManager,responseSender,queryReader),
                new MinByAreaCommand(collectionManager),
                new RemoveAllByHouseCommand(collectionManager,responseSender,queryReader),
                new RemoveGreaterCommand(collectionManager,responseSender,queryReader),
                new RemoveKeyCommand(collectionManager),
                new UpdateCommand(collectionManager,queryReader,responseSender)
        );
        commandManager.addCommand(new HelpCommand(commandManager));
        commandManager.addCommand(new HistoryCommand(commandManager));

        while(socketChannel!=null){
            Query query = queryReader.getQuery();
            if(query==null) break;

            Response response = handleCommand(query.getDTOCommand(),commandManager);

            responseSender.sendResponse(response);
        }
        try {
            serverSocketChannel.close();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public Response handleCommand(CommandTransferObject command, CommandManager commandManager){
        return commandManager.executeCommand(command.getCommand(), command.getArgument());
    }
}
