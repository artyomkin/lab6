package Server.CommandHandler.exceptions;
/**
 * Is thrown when console tries to apply to command manager that is null
 * **/
public class NoCommandManagerFoundException extends Exception{
    public NoCommandManagerFoundException(String message){
        super(message);
    }
}
