package Server.CommandHandler.exceptions;
/**
 * Is thrown when constructor of coordinates class got inappropriate arguments
 * **/
public class CoordinatesException extends Exception{
    public CoordinatesException(String message){
        super(message);
    }
}
