package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Response;
import Server.CommandHandler.utility.CommandManager;

/**
 * Prints 13 last executed Server.CommandHandler.commands
 * **/
public class HistoryCommand extends AbstractCommand{
    private String content = "";
    private CommandManager commandManager;
    public HistoryCommand(CommandManager commandManager){
        super("history","print 13 last Server.CommandHandler.commands without arguments");
        this.commandManager = commandManager;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public Response execute(String arg) {
        commandManager.getHistory().stream()
                .forEach(command->content+=command+"\n");
        return new Response(content,false,Instruction.ASK_COMMAND);
    }
}
