package Server.ConnectionReciever;

import Common.Pair;
import Server.CommandHandler.utility.ServerOutput;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ConnectionReciever {

    private int port;

    public ConnectionReciever(int port){
        this.port = port;
    }

    public Pair<SocketChannel, ServerSocketChannel> getConnection(){

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress address = new InetSocketAddress(port);
            serverSocketChannel.bind(address);
            SocketChannel socketChannel = serverSocketChannel.accept();
            ServerOutput.info("Socket channel opened");
            return new Pair<>(socketChannel, serverSocketChannel);
        }catch (AlreadyBoundException e){
            ServerOutput.warning("Already bound");
            return null;
        }catch(IOException e){
            ServerOutput.warning("Getting socket channel failed");
            return null;
        }

    }

}
