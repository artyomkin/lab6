package Server.CommandHandler.exceptions;
/**
 * Is thrown when method got empty key string that can lead to null pointer result
 * **/
public class EmptyStringException extends Exception{
    public EmptyStringException(String message){
        super(message);
    }
}
