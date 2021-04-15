package Server.ConnectionReciever;

import Server.CommandHandler.utility.ServerOutput;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ConnectionReciever {

    private int port;

    public ConnectionReciever(int port){
        this.port = port;
    }

    public SocketChannel getSocketChannel(){

        try{
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            InetSocketAddress address = new InetSocketAddress(port);
            serverSocketChannel.bind(address);
            SocketChannel socketChannel = serverSocketChannel.accept();
            ServerOutput.info("Socket channel opened");
            return socketChannel;
        }catch(IOException e){
            ServerOutput.warning("Getting socket channel failed");
            return null;
        }

    }

}
