package Common;

import Common.DataTransferObjects.CommandTransferObject;

import java.io.Serializable;

public class Response implements Serializable {
    private String content;
    private boolean executionFailed;
    private Instruction instruction;
    private CommandTransferObject command;

    public Response (){
        this.content = "";
        this.executionFailed = false;
        this.instruction = Instruction.ASK_COMMAND;
    }
    public Response (String content, boolean failed, Instruction instruction){
        this.content = content;
        this.executionFailed = failed;
        this.instruction = instruction;
        this.command = command;
    }
    public Response (String content, boolean failed, Instruction instruction, CommandTransferObject command){
        this.content = content;
        this.executionFailed = failed;
        this.instruction = instruction;
        this.command = command;
    }
    public Instruction getInstruction() {
        return instruction;
    }

    public String getContent() {
        return content;
    }

    public CommandTransferObject getCommandTransferObject() {
        return command;
    }

    public boolean failed() {
        return executionFailed;
    }

    public Response setCommandTransferObject(CommandTransferObject commandTransferObject) {
        this.command = commandTransferObject;
        return this;
    }

    public Response setContent(String content){
        this.content = content;
        return this;
    }
    public Response setExecutionFailed(boolean flag){
        this.executionFailed = flag;
        return this;
    }
    public Response setInstruction(Instruction instruction){
        this.instruction = instruction;
        return this;
    }
}
