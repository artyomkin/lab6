package Server.CommandHandler.utility;

/**
 * Auxiliary class that helps to convert one type to another while reading JSON
 * **/
public class Convertor {
    public static Integer longToInteger(Long x){
        if (x == null) {
            return 0;
        } else {
            return x.intValue();
        }

    }
}
