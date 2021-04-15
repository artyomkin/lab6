package Server.ResponseSender;

import Common.Response;
import Common.Serializer;
import Server.CommandHandler.utility.ServerOutput;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseSender {

    private OutputStream out;

    public ResponseSender(OutputStream out){
        this.out = out;
    }

    public boolean sendResponse(Response response){
        try{
            byte[] bytes = Serializer.serialize(response);
            out.write(bytes);
            ServerOutput.info("Sent new response");
            return true;
        }catch(IOException e){
            ServerOutput.warning("Failed to serialize response");
            return false;
        }
    }
}
