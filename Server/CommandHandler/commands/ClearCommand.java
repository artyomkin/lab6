package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Response;
import Server.CommandHandler.utility.CollectionManager;
/**
 * Clear whole collection by removing all elements
 * **/
public class ClearCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager){
        super("clear", "clear the collection");
        this.collectionManager = collectionManager;
    }
    /**
     *  Executes the command
     * @return exit status of command
     * **/
    @Override
    public Response execute(String arg) {
        collectionManager.clear();
        collectionManager.updateAfterClear();
        return new Response("Collection is cleared",false,Instruction.ASK_COMMAND);
    }
}
