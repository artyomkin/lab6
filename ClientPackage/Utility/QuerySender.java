package ClientPackage.Utility;

import Common.Query;
import Common.Response;
import Common.Serializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class QuerySender {
    public boolean sendQuery(Query query, SocketChannel socketChannel){
        try{
            byte[] bytes = Serializer.serialize(query);
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

            socketChannel.write(byteBuffer);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
