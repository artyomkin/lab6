package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Response;
import Server.CommandHandler.task_classes.Transport;
import Server.CommandHandler.utility.CollectionManager;

/**
 * Prints all elements that have the same transport as specified one has
 * **/
public class FilterByTransportCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private String content;
    public FilterByTransportCommand(CollectionManager collectionManager){
        super("filter_by_transport", "print all elements value transport of wich equals to specified one");
        this.collectionManager = collectionManager;
        this.content = "";
    }
    public void concatenateToContent(final String add){
        this.content+=add;
    }

    /**
     * Executes the command
     * @return exit status of command
     * **/
    @Override
    public Response execute(String transportArg) {
        Transport validatedTransport;
        try{
            validatedTransport = Transport.valueOf(transportArg);
        } catch (IllegalArgumentException e){
            return new Response("Incorrect transport name", true, Instruction.ASK_COMMAND);
        }
        if(collectionManager.getStream().anyMatch(flat->flat.getTransport().equals(Transport.valueOf(transportArg)))){
            collectionManager
                    .getStream()
                    .filter(flat->flat.getTransport().equals(Transport.valueOf(transportArg)))
                    .forEachOrdered(flat->concatenateToContent(flat.toString()+"____________________\n"));
            return new Response(content, false, Instruction.ASK_COMMAND);
        } else {
            return new Response("No flat with specified transport found", true, Instruction.ASK_COMMAND);
        }

    }
}
