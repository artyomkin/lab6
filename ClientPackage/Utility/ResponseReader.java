package ClientPackage.Utility;

import ClientPackage.Console.UserInput;
import ClientPackage.Console.UserOutput;
import ClientPackage.InputHandler.Asker;
import Common.Serializer;
import ClientPackage.InputHandler.Validator;
import Common.DataTransferObjects.CommandTransferObject;
import Common.DataTransferObjects.CoordinatesTransferObject;
import Common.DataTransferObjects.FlatTransferObject;
import Common.DataTransferObjects.HouseTransferObject;
import Common.Query;
import Common.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ResponseReader {

    public Response getResponse(SocketChannel socketChannel){

        byte[] bytes = new byte[1024];
        int numberOfBytesRead = 1;
        byte[] resultBytes = new byte[0];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        Response response = null;
        while(true){
            try{
                numberOfBytesRead = socketChannel.read(byteBuffer);
                byte[] tempBytes = new byte[resultBytes.length + numberOfBytesRead];
                System.arraycopy(resultBytes, 0, tempBytes, 0, resultBytes.length);
                System.arraycopy(bytes, 0, tempBytes, resultBytes.length, numberOfBytesRead);
                resultBytes = tempBytes;
                byteBuffer.clear();
                try{
                    response = (Response) Serializer.deserialize(resultBytes);
                    return response;
                } catch (IOException | ClassNotFoundException | ClassCastException e){
                    continue;
                }
            } catch (IOException e){
                e.printStackTrace();
                UserOutput.println("Getting response error");
                break;
            }
        }

        return response;
    }

}
