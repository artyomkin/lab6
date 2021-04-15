package Server.CommandHandler.exceptions;
/**
 * Is thrown when command manager fails to find a command with specified name
 * **/
public class NoSuchCommandException extends Exception{
    public NoSuchCommandException(String message){
        super(message);
    }
}
