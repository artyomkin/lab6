package Server.CommandHandler.exceptions;

public class KeyAlreadyExistsException extends Exception{
    public KeyAlreadyExistsException(String message){
        super(message);
    }
}
