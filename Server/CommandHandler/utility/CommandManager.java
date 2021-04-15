package Server.CommandHandler.utility;

import Common.Instruction;
import Common.Response;
import Server.CommandHandler.commands.*;

import java.util.*;
import java.util.stream.Stream;

/**
 * Stores and manages the Server.CommandHandler.commands
 * **/
public class CommandManager {

    private HashMap<String,Executable> commands = new HashMap<String,Executable>();
    private ArrayDeque<String> history = new ArrayDeque<String>(13);
    public CommandManager(Executable... commandsParam){
        for (Executable c : commandsParam){
            commands.put(c.getName(),c);
        }
        history.clear();
    }
    /**
     * Executes the command with specified name
     * @return exit status of executable command
     * **/
    public Response executeCommand(String command, String key){
        key = key.trim();
        history.add(command);
        ServerOutput.info("Recieved "+command+" "+key);
        Response response;
        if (commands.containsKey(command)){
            response = commands.get(command).execute(key);
            if (response.failed()){
                ServerOutput.warning("Failed to execute command "+command+" "+key);
            }
        }
        else{
            ServerOutput.warning("No such command "+command+" found");
            response = new Response("No such command "+command+" found",true,Instruction.ASK_COMMAND);
        }
        return response;
    }

    public void addCommand(Executable command){
        commands.put(command.getName(),command);
    }
    public Stream<Executable> getStream(){
        return this.commands.values().stream();
    }
    public ArrayDeque<String> getHistory(){return this.history;};


}
