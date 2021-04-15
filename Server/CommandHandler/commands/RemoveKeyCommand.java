package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Response;
import Server.CommandHandler.utility.CollectionManager;
/**
 * Removes the flat from collections by its key
 * **/
public class RemoveKeyCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public RemoveKeyCommand(CollectionManager collectionManager){
        super("remove_key","remove the element from collection by its key");
        this.collectionManager = collectionManager;
    }
    /**
     * Executes the command
     * @return exit status of command
     * @param arg that will be used to perform the remove
     * **/
    @Override
    public Response execute(String arg) {
        Integer key;
        try{
            key = Integer.parseInt(arg);
        } catch(NumberFormatException e){
            return new Response("Specify a key",true,Instruction.ASK_COMMAND);
        }
        if (collectionManager.getLength() == 0 || !collectionManager.contains(Integer.parseInt(arg))){
            return new Response("No such flat with specified ID found",true, Instruction.ASK_COMMAND);
        } else {

            collectionManager.remove(key);
            collectionManager.updateAfterRemove();
            return new Response("Flat with ID " + key + " is removed",false,Instruction.ASK_COMMAND);
        }

    }
}
