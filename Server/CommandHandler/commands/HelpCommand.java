package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Response;
import Server.CommandHandler.utility.CommandManager;

/**
 * Prints the information about available Server.CommandHandler.commands that command manager contains
 * **/
public class HelpCommand extends AbstractCommand{

    private CommandManager commandManager;

    public HelpCommand(CommandManager commandManager){
        super("help","prints the information about available commands");
        this.commandManager = commandManager;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    public Response execute(String arg){
        String res = "";
        commandManager.getStream()
                .forEach(command->updateResult(res,command.getName() + " " + command.getDescription() + "\n"));
        return new Response(res,false, Instruction.ASK_COMMAND);
    }
    private String updateResult(String res,String add){
        return res+add;
    }
}
