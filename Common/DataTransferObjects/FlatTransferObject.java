package Common.DataTransferObjects;


import java.io.Serializable;

public class FlatTransferObject implements Serializable {
    private String name;
    private CoordinatesTransferObject coordinates;
    private long area;
    private Integer numberOfRooms;
    private double price;
    private Integer livingSpace;
    private TransportTransferObject transport;
    private HouseTransferObject house;

    public FlatTransferObject(){
        name = "";
        coordinates = new CoordinatesTransferObject();
        area = 0;
        numberOfRooms = 0;
        price = 0;
        livingSpace = 0;
        transport = null;
        house = new HouseTransferObject();
    }

    public CoordinatesTransferObject getCoordinates() {
        return coordinates;
    }

    public long getArea() {
        return area;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public double getPrice() {
        return price;
    }

    public Integer getLivingSpace() {
        return livingSpace;
    }

    public TransportTransferObject getTransport() {
        return transport;
    }

    public HouseTransferObject getHouse() {
        return house;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(CoordinatesTransferObject coordinates) {
        this.coordinates = coordinates;
    }

    public void setArea(long area) {
        this.area = area;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLivingSpace(Integer livingSpace) {
        this.livingSpace = livingSpace;
    }

    public void setTransport(TransportTransferObject transport) {
        this.transport = transport;
    }

    public void setHouse(HouseTransferObject house) {
        this.house = house;
    }

    public String getName() {
        return name;
    }
}
