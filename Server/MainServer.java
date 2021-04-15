package Server;

public class MainServer {
    public static void main(String[] args){
        Server server = new Server(2231);
        server.run();
    }
}
