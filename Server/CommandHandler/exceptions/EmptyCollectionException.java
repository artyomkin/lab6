package Server.CommandHandler.exceptions;
/**
 * Is thrown when user tries to find or delete something from empty collection
 * **/
public class EmptyCollectionException extends Exception{
    public EmptyCollectionException(String message){
        super(message);
    }
}
