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
 * Replaces one flat with another if first flat is more than specified
 * **/
public class ReplaceIfLowerCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private ResponseSender responseSender;
    private QueryReader queryReader;
    public ReplaceIfLowerCommand(CollectionManager collectionManager, ResponseSender responseSender, QueryReader queryReader){
        super("replace_if_lower","replace element by key if new element is less than previous one");
        this.collectionManager = collectionManager;
        this.responseSender = responseSender;
        this.queryReader = queryReader;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/

    @Override
    public Response execute(String arg){
        Integer key;
        try{
            key = Integer.parseInt(arg);
        } catch(NumberFormatException e){
            return new Response("Specify a key",true,Instruction.ASK_COMMAND);
        }
        if (!collectionManager.contains(key)){
            return new Response("No flat with speicifed key foung", true, Instruction.ASK_COMMAND);
        }
        responseSender.sendResponse(new Response("",false, Instruction.ASK_FLAT));
        Query query = queryReader.getQuery();
        Flat flat = DTOHandler.convertToFlat(query.getDTOFlat());
        flat.setId(key);
        if (flat.compareTo(collectionManager.getElementByKey(key))<0){
            collectionManager.replace(key,flat);
            return new Response("Flat successfuly replaced",false,Instruction.ASK_COMMAND);
        }
        else {
            return new Response("Flat wasn't replaced",false,Instruction.ASK_COMMAND);
        }
    }
}
