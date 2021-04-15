package Server.CommandHandler.exceptions;

/**
 * Is thrown when house constructor got inappropriate arguments
 * **/
public class HouseException extends Exception{
    public HouseException(String message) {
        super(message);
    }
}

