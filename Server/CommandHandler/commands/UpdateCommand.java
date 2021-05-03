package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Query;
import Common.Response;
import Server.CommandHandler.task_classes.Flat;
import Server.CommandHandler.utility.CollectionManager;
import Server.QueryReader.DTOHandler;
import Server.QueryReader.QueryReader;
import Server.ResponseSender.ResponseSender;

/**
 * Updates the element by its key
 * **/
public class UpdateCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private ResponseSender responseSender;
    private QueryReader queryReader;
    public UpdateCommand(CollectionManager collectionManager, QueryReader queryReader, ResponseSender responseSender){
        super("update","updates the element with specified id");
        this.collectionManager = collectionManager;
        this.queryReader = queryReader;
        this.responseSender = responseSender;
    }

    /**
     * Executes the command
     * @return exit status of command
     * @param id that will be used to perform the update
     * **/
    @Override
    public Response execute(String id){
        Integer key = Integer.parseInt(id);
        if (!collectionManager.contains(key))
            return new Response("No flat with specified key found",true,Instruction.ASK_COMMAND);
        responseSender.sendResponse(new Response("",false,Instruction.ASK_FLAT));
        Query query = queryReader.getQuery();
        Flat flat = DTOHandler.convertToFlat(query.getDTOFlat());
        flat.setId(key);
        collectionManager.replace(key, flat);
        return new Response("Replacing successful",false,Instruction.ASK_COMMAND);
    }
}
