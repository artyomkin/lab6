package Server.QueryReader;

import Common.DataTransferObjects.CoordinatesTransferObject;
import Common.DataTransferObjects.FlatTransferObject;
import Common.DataTransferObjects.HouseTransferObject;
import Server.CommandHandler.task_classes.Coordinates;
import Server.CommandHandler.task_classes.Flat;
import Server.CommandHandler.task_classes.House;

public class DTOHandler {

    public static Flat convertToFlat(FlatTransferObject DTOflat){
        return new Flat(DTOflat);
    }
    public static Coordinates convertToCoordinates(CoordinatesTransferObject DTOcoordinates){
        return new Coordinates(DTOcoordinates);
    }
    public static House convertToHouse(HouseTransferObject DTOhouse){
        return new House(DTOhouse);
    }
}
