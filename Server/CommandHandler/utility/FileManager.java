package Server.CommandHandler.utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Server.CommandHandler.task_classes.Coordinates;
import Server.CommandHandler.task_classes.Flat;
import Server.CommandHandler.task_classes.House;
import Server.CommandHandler.task_classes.Transport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * Reads the JSON file and returns ready-made collection
 * **/
public class FileManager {
    private String filePath;
    public FileManager(String filePath){
        this.filePath = filePath;
    }
    /**
     * Returns the array of JSON objects from .json file
     * @return JSONArray
     * **/
    public JSONArray getJSON(){
        JSONParser parser = new JSONParser();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))){
            JSONArray result = (JSONArray) parser.parse(fileReader);
            return result;
        } catch (IOException e){
            ServerOutput.warning("Failed to open a file");
            return null;
        } catch (ParseException e){
            ServerOutput.warning("Failed to parse file");
            return null;
        }
    }
    /**
     * Reads JSON and returns the collection
     * @return HashMap
     * **/
    public HashMap<Integer, Flat> loadHashMap(){
        HashMap<Integer,Flat> result = new HashMap<Integer,Flat>();
        JSONArray arr = getJSON();
        try{
            if (arr == null) throw new NullPointerException();
        } catch (NullPointerException e){
            ServerOutput.warning("Failed to load collection from file");
            return null;
        }
        for (Object objFlat : arr){
            JSONObject JSONFlat = (JSONObject) objFlat;
            JSONObject JSONCoordinates = (JSONObject) JSONFlat.get("coordinates");
            JSONObject JSONHouse = (JSONObject) JSONFlat.get("house");

            Coordinates coordinates = new Coordinates(
                    (Double)JSONCoordinates.get("x"),
                    (Long)JSONCoordinates.get("y")
            );
            House house = new House(
                    (String)JSONHouse.get("name"),
                    Convertor.longToInteger((Long)JSONHouse.get("year")),
                    Convertor.longToInteger((Long)JSONHouse.get("numberOfFloors")),
                    (long)JSONHouse.get("numberOfFlatsOnFloor"),
                    Convertor.longToInteger((Long)JSONHouse.get("numberOfLifts"))
            );
            Transport transport = Transport.valueOf((String)JSONFlat.get("transport"));
            Flat flat = new Flat(
                    (String)JSONFlat.get("name"),
                    coordinates,
                    (long)JSONFlat.get("area"),
                    Convertor.longToInteger((Long)JSONFlat.get("numberOfRooms")),
                    (Double)JSONFlat.get("price"),
                    Convertor.longToInteger((Long)JSONFlat.get("livingSpace")),
                    transport,
                    house
            );
            result.put(flat.getID(),flat);

        }
        return result;
    }
}
