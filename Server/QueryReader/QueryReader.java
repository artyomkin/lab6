package Server.QueryReader;

import Common.Serializer;
import Common.Query;
import Server.CommandHandler.utility.ServerOutput;

import java.io.IOException;
import java.io.InputStream;

public class QueryReader {

    private InputStream in;
    public QueryReader(InputStream in){
        this.in = in;
    }

    public Query getQuery(){

        byte[] bytes = new byte[1024];
        byte[] resultBytes = new byte[0];
        int numberOfBytesRead = 0;
        Query query;
        while(true){
            try{
                numberOfBytesRead = in.read(bytes);
                if(numberOfBytesRead<0){
                    return null;
                }
                byte[] tempBytes = new byte[resultBytes.length + numberOfBytesRead];
                System.arraycopy(resultBytes, 0, tempBytes, 0, resultBytes.length);
                System.arraycopy(bytes, 0, tempBytes, resultBytes.length, numberOfBytesRead);
                resultBytes = tempBytes;
                try{
                    query = (Query) Serializer.deserialize(resultBytes);
                    ServerOutput.info("Recieved new query");
                    return query;
                } catch (IOException | ClassNotFoundException | ClassCastException e){
                    continue;
                }
            } catch (IOException e){
                ServerOutput.warning("Reading query error");
                return null;
            }
        }
    }



}
