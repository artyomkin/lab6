package Server.CommandHandler.commands;

import Common.Response;
import Server.CommandHandler.utility.CollectionManager;

/**
 * Interrupt the programm
 * **/
public class ExitCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private String filepath;
    public ExitCommand(CollectionManager collectionManager, String filepath){
        super("exit","stop the programm");
        this.collectionManager = collectionManager;
        this.filepath = filepath;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public Response execute(String arg) {
        SaveCommand save = new SaveCommand(collectionManager,filepath);
        return save.execute("");
    }
}
