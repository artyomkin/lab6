package Common;

import Common.DataTransferObjects.CommandTransferObject;
import Common.DataTransferObjects.CoordinatesTransferObject;
import Common.DataTransferObjects.FlatTransferObject;
import Common.DataTransferObjects.HouseTransferObject;

import java.io.Serializable;

public class Query implements Serializable {

    private FlatTransferObject DTOFlat;
    private HouseTransferObject DTOHouse;
    private CoordinatesTransferObject DTOCoordinates;
    private CommandTransferObject DTOCommand;

    public CommandTransferObject getDTOCommand() {
        return DTOCommand;
    }


    public CoordinatesTransferObject getDTOCoordinates() {
        return DTOCoordinates;
    }

    public FlatTransferObject getDTOFlat() {
        return DTOFlat;
    }

    public HouseTransferObject getDTOHouse() {
        return DTOHouse;
    }

    public Query setDTOCommand(CommandTransferObject DTOCommand) {
        this.DTOCommand = DTOCommand;
        return this;
    }

    public Query setDTOCoordinates(CoordinatesTransferObject DTOCoordinates) {
        this.DTOCoordinates = DTOCoordinates;
        return this;
    }

    public Query setDTOFlat(FlatTransferObject DTOFlat) {
        this.DTOFlat = DTOFlat;
        return this;
    }

    public Query setDTOHouse(HouseTransferObject DTOHouse) {
        this.DTOHouse = DTOHouse;
        return this;
    }
}
