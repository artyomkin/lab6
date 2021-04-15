package ClientPackage.Utility;

import ClientPackage.Console.UserOutput;
import Common.Pair;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ConnectionReciever {

    private String host;
    private int port;

    public ConnectionReciever(String host, int port){
        this.host = host;
        this.port = port;
    }

    public Pair<Selector,SocketChannel> connectToServer() throws IOException{
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(host,port));
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector,SelectionKey.OP_WRITE);
            return new Pair(selector,socketChannel);

    }

}
