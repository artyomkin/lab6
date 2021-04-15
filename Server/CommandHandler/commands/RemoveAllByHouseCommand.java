package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Query;
import Common.Response;
import Server.CommandHandler.task_classes.Flat;
import Server.CommandHandler.task_classes.House;
import Server.CommandHandler.utility.CollectionManager;
import Server.QueryReader.DTOHandler;
import Server.QueryReader.QueryReader;
import Server.ResponseSender.ResponseSender;

import java.util.Iterator;

/**
 * Removes all flats that have the same house as specified one
 * **/
public class RemoveAllByHouseCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private ResponseSender responseSender;
    private QueryReader queryReader;
    public RemoveAllByHouseCommand(CollectionManager collectionManager,ResponseSender responseSender, QueryReader queryReader){
        super("remove_all_by_house","remove all elements house of which equals to specified one");
        this.collectionManager = collectionManager;
        this.queryReader = queryReader;
        this.responseSender = responseSender;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/
    public Response execute(String key){
        if(!responseSender.sendResponse(new Response("",false,Instruction.ASK_HOUSE))){
            return new Response("Serializing error",true, Instruction.ASK_COMMAND);
        }
        Query query = queryReader.getQuery();
        Iterator it = collectionManager.getIterator();
        while(it.hasNext()){
            Flat flat = (Flat) it.next();
            House house = flat.getHouse();
            if(DTOHandler.convertToHouse(query.getDTOHouse()).equals(house)) {
                it.remove();
                collectionManager.updateAfterRemove();
            }
        }
        return new Response("Removing successful",false,Instruction.ASK_COMMAND);
    }
}
