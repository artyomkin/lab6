package Common;

import java.io.Serializable;

public enum Instruction implements Serializable {
    EXIT,
    SCRIPT,
    ASK_HOUSE,
    ASK_COORDINATES,
    ASK_FLAT,
    ASK_COMMAND
}
