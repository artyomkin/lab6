package ClientPackage.Utility;

import ClientPackage.Console.UserOutput;
import ClientPackage.Exceptions.ConnectionDisruptedException;
import Common.Serializer;
import Common.Response;
import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class ResponseReader {

    public Response getResponse(SocketChannel socketChannel) throws ConnectionDisruptedException {

        byte[] bytes = new byte[1024];
        int numberOfBytesRead = 1;
        byte[] resultBytes = new byte[0];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        Response response = null;
        while(true){
            try {
                numberOfBytesRead = socketChannel.read(byteBuffer);
                if (numberOfBytesRead<0) throw new SocketException();
                byte[] tempBytes = new byte[resultBytes.length + numberOfBytesRead];
                System.arraycopy(resultBytes, 0, tempBytes, 0, resultBytes.length);
                System.arraycopy(bytes, 0, tempBytes, resultBytes.length, numberOfBytesRead);
                resultBytes = tempBytes;
                byteBuffer.clear();
                try {
                    response = (Response) Serializer.deserialize(resultBytes);
                    return response;
                } catch (IOException | ClassNotFoundException | ClassCastException e) {
                    continue;
                }
            } catch (SocketException e) {
                UserOutput.println("Connection with server disrupted");
                break;
            } catch (IOException e){
                throw new ConnectionDisruptedException("Connection disrupted");
            }
        }

        return response;
    }

}
