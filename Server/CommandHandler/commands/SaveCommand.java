package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Response;
import Server.CommandHandler.utility.CollectionManager;
/**
 * Saves the collection to file
 * **/
public class SaveCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private String filePath;
    public SaveCommand(CollectionManager collectionManager, String filePath){
        super("save","save collection to file");
        this.collectionManager = collectionManager;
        this.filePath = filePath;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    public Response execute(String arg){
        collectionManager.save(filePath);
        return new Response("",false, Instruction.EXIT);
    }
}
