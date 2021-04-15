package Server.CommandHandler.commands;

import Common.Instruction;
import Common.Response;
import Server.CommandHandler.task_classes.Flat;
import Server.CommandHandler.utility.CollectionManager;

import java.util.Comparator;

/**
 * Prints the flat with the least value of area
 * **/
public class MinByAreaCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private final long INF;
    public MinByAreaCommand(CollectionManager collectionManager){
        super("min_by_area","print the element with minimal value of area");
        this.collectionManager = collectionManager;
        this.INF = 10000000;
    }
    /**
     * Executes the command
     * @return exit status of command
     * **/

    @Override
    public Response execute(String arg) {
        if (collectionManager.getLength()==0){
            return new Response("Collection is empty",true, Instruction.ASK_COMMAND);
        }

        String content = collectionManager
                .getStream()
                .min(Comparator.comparingLong(Flat::getArea))
                .get().toString();

        return new Response(content,false,Instruction.ASK_COMMAND);

    }
}
