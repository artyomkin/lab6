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
 * Inserts new element with specified key into collection
 * **/
public class InsertCommand extends AbstractCommand{

    private CollectionManager collectionManager;
    private ResponseSender responseSender;
    private QueryReader queryReader;
    public InsertCommand(CollectionManager collectionManager, ResponseSender responseSender, QueryReader queryReader){
        super("insert", "insert new element with specified key");
        this.collectionManager = collectionManager;
        this.responseSender = responseSender;
        this.queryReader = queryReader;
    }
    /**
     * Executes the command
     * @return exit status of command
     * @param key that will be used to perform the insert
     * **/
    @Override
    public Response execute(String key){
        if (collectionManager.contains(Integer.parseInt(key)))
            return new Response("Flat with specified key adlready exists", true, Instruction.ASK_COMMAND);
        if (!responseSender.sendResponse(new Response("",false, Instruction.ASK_FLAT))){
            return new Response("Serializing error",true,Instruction.ASK_COMMAND);
        }
        Query query = queryReader.getQuery();
        Flat flat = DTOHandler.convertToFlat(query.getDTOFlat());
        flat.setId(Integer.parseInt(key));
        collectionManager.insert(Integer.parseInt(key),DTOHandler.convertToFlat(query.getDTOFlat()));
        return new Response("Insertion successful",false,Instruction.ASK_COMMAND);
    }
}
