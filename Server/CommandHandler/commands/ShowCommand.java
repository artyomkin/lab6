package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Response;
import Server.CommandHandler.utility.CollectionManager;

/**
 * Prints all elements in collection
 * **/
public class ShowCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private String content;
    public ShowCommand(CollectionManager collectionManager){
        super("show","prints all elements");
        this.collectionManager = collectionManager;
        this.content = "";
    }

    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public Response execute(String arg) {
        if (collectionManager.getLength()==0){
            return new Response("Collection is empty", false, Instruction.ASK_COMMAND);
        } else{
            collectionManager
                    .getStream()
                    .forEach(flat->content+=flat.toString()+"____________________\n");

            return new Response(content, false, Instruction.ASK_COMMAND);
        }
    }

}
