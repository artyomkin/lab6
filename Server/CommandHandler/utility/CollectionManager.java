package Server.CommandHandler.utility;

import Server.CommandHandler.exceptions.IncorrectInputException;
import org.json.simple.JSONObject;
import Server.CommandHandler.task_classes.Coordinates;
import Server.CommandHandler.task_classes.Flat;
import Server.CommandHandler.task_classes.House;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Manages the main collection
 * **/
public class CollectionManager {

    private HashMap<Integer, Flat> hashMap;
    private Date date;
    private int length;
    public CollectionManager(FileManager fileManager){
        hashMap = fileManager.loadHashMap();
        date = new Date();
        if (hashMap!=null) length = hashMap.size();
        else length = 0;
    }
    /**
     * Inserts specified element with specified key into collection
     * @return exit status of insertion
     * @param flat
     * @param key
     * **/
    public boolean insert(Integer key,Flat flat){
        hashMap.put(key,flat);
        updateAfterInsertion();
        return true;
    }
    public boolean contains(Integer key){
        return getHashMap().keySet().contains(key);
    }
    /**
     * Removes the element by its key
     * @return exit status of removal
     * @param key
     * **/
    public boolean remove(Integer key){
        try{
            if(hashMap.remove(key) == null) throw new IncorrectInputException("Such key does not exist");
            return true;
        } catch (IncorrectInputException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    /**
     * Saves the collection to file with specified path
     * @return exit status of saving
     * @param filePath
     * **/
    public boolean save(String filePath){
        String result = "[";
        Iterator it = hashMap.entrySet().iterator();
        while(it.hasNext()) {
            JSONObject JSONFlat = new JSONObject();
            JSONObject JSONCoordinates = new JSONObject();
            JSONObject JSONHouse = new JSONObject();
            HashMap.Entry<Integer, Flat> entry = (HashMap.Entry) it.next();
            Flat flat = entry.getValue();

            Coordinates coordinates = flat.getCoordinates();
            JSONCoordinates.put("x", coordinates.getX());
            JSONCoordinates.put("y", coordinates.getY());

            House house = flat.getHouse();
            JSONHouse.put("name", house.getName());
            JSONHouse.put("year", house.getYear());
            JSONHouse.put("numberOfFloors", house.getNumberOfFloors());
            JSONHouse.put("numberOfFlatsOnFloor", house.getNumberOfFlatsOnFloor());
            JSONHouse.put("numberOfLifts", house.getNumberOfLifts());


            JSONFlat.put("id", flat.getID());
            JSONFlat.put("name", flat.getName());
            JSONFlat.put("coordinates", JSONCoordinates);
            JSONFlat.put("area", flat.getArea());
            JSONFlat.put("numberOfRooms", flat.getNumberOfRooms());
            JSONFlat.put("price", flat.getPrice());
            JSONFlat.put("livingSpace", flat.getLivingSpace());
            JSONFlat.put("transport", flat.getTransport().toString());
            JSONFlat.put("house", JSONHouse);
            result += JSONFlat.toJSONString();
            if (it.hasNext()) result += ",";
        }
        result += "]";
        try{
            FileOutputStream fout= new FileOutputStream(filePath);
            fout.write(result.getBytes());
            return true;
        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Replaces replacable flat with substituable flat in collection
     * @return exit status of replacing
     * @param ID
     * @param substitutable
     * **/
    public boolean replace(Integer ID, Flat substitutable){
        return(insert(ID, substitutable));

    }
    /**
     * Returns flat by its key from collection
     * @return flat
     * @param key
     * **/
    public Flat getElementByKey(Integer key){
        return hashMap.get(key);
    }
    /**
     * Removes every element from collection
     * **/
    public void clear(){
        hashMap.clear();
    }
    /**
     * Returns the collection
     * @return HashMap
     * **/
    public HashMap<Integer,Flat> getHashMap(){
        return hashMap;
    }
    public Class getCollectionClass(){
        return this.hashMap.getClass();
    }
    /**
     * Returns the date when collection was created
     * @return creation date
     * **/
    public Date getInitializationDate(){ return date; };
    /**
     * Returns the lenght of collection
     * @return length
     * **/
    public int getLength(){ return length; }
    /**
     * Decrements the length of collection after removing 1 element
     * @return exit status of update
     * **/

    public boolean updateAfterRemove(){
        if (this.length>0){
            length -= 1;
            return true;
        } else {
            return false;
        }
    }
    /**
     * Sets the length of collection to 0 after clearing the collection
     * **/
    public void updateAfterClear(){
        this.length = 0;
    }
    /**
     * Increments the length of collection after inserting 1 element
     * **/
    public void updateAfterInsertion(){
        length += 1;
    }
    public Stream<Flat> getStream(){
        List<Flat> flats = hashMap.values().stream()
                .collect(Collectors.toList());
        flats.sort(Flat::compareTo);
        return flats.stream();
    };
    public Iterator getIterator(){
        return this.hashMap.values().iterator();
    }
}
