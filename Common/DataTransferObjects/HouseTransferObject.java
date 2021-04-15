package Common.DataTransferObjects;

import java.io.Serializable;

public class HouseTransferObject implements Serializable {
    private String name;
    private Integer year;
    private Integer numberOfFloors;
    private long numberOfFlatsOnFloor;
    private Integer numberOfLifts;
    public HouseTransferObject(){
        name = "";
        year = 0;
        numberOfFloors = 0;
        numberOfFlatsOnFloor = 0;
        numberOfLifts = 0;
    }

    public long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getNumberOfLifts() {
        return numberOfLifts;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    public String getName() {
        return name;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setNumberOfLifts(Integer numberOfLifts) {
        this.numberOfLifts = numberOfLifts;
    }

    public void setNumberOfFloors(Integer numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public void setNumberOfFlatsOnFloor(long numberOfFlatsOnFloor) {
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public void setName(String name) {
        this.name = name;
    }
}
