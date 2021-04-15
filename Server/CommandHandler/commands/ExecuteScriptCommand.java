package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Response;

/**
 * Execute the script from file
 * **/
public class ExecuteScriptCommand extends AbstractCommand{

    public ExecuteScriptCommand(){
        super("execute_script","read and execute script from specified file. " +
                "Script contains same Server.CommandHandler.commands as user uses in interactive mode");
    }
    /**
     * Executes the command
     * @param filePath
     * **/
    @Override
    public Response execute(String filePath) {
        return new Response(filePath,false, Instruction.SCRIPT);
    }
}
