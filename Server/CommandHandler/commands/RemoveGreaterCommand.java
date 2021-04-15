package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Query;
import Common.Response;
import Server.CommandHandler.task_classes.Flat;
import Server.CommandHandler.utility.CollectionManager;
import Server.QueryReader.DTOHandler;
import Server.QueryReader.QueryReader;
import Server.ResponseSender.ResponseSender;

import java.util.Iterator;

/**
 * Removes all flats that are more than specified one
 * **/
public class RemoveGreaterCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private ResponseSender responseSender;
    private QueryReader queryReader;
    public RemoveGreaterCommand(CollectionManager collectionManager, ResponseSender responseSender, QueryReader queryReader){
        super("remove_greater","removes all elements that more than specified");
        this.collectionManager = collectionManager;
        this.queryReader = queryReader;
        this.responseSender = responseSender;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public Response execute(String key){
        if(!responseSender.sendResponse(new Response("",false,Instruction.ASK_FLAT))){
            return new Response("Serializing error",true,Instruction.ASK_COMMAND);
        }
        Query query = queryReader.getQuery();
        Iterator it = collectionManager.getIterator();
        while(it.hasNext()){
            Flat flat = (Flat) it.next();
            if (flat.compareTo(DTOHandler.convertToFlat(query.getDTOFlat()))>0){
                it.remove();
                collectionManager.updateAfterRemove();
            }
        }
        return new Response("Removing successful",false,Instruction.ASK_COMMAND);
    }
}
