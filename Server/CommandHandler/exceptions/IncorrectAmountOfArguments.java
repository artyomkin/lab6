package Server.CommandHandler.exceptions;
/**
 * Is thrown when method got inappropriate amount of arguments
 * **/
public class IncorrectAmountOfArguments extends Exception{
    public IncorrectAmountOfArguments(String message){
        super(message);
    }
}
