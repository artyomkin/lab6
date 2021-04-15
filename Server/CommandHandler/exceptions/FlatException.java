package Server.CommandHandler.exceptions;

/**
 * Is thrown when flat constructor got inappropriate arguments
 * **/
public class FlatException extends Exception{
    public FlatException(String message) {
        super(message);
    }
}
