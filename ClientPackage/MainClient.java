package ClientPackage;

public class MainClient {
    public static void main(String[] args){
        Client client = new Client("127.0.0.1",2231);
        client.run();
    }
}
