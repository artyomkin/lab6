package Common.DataTransferObjects;

import java.io.Serializable;

public class CoordinatesTransferObject  implements Serializable {
    private Double x;
    private Long y;

    public CoordinatesTransferObject(){
        x = 0d;
        y = 0l;
    }

    public Long getY() {
        return y;
    }

    public Double getX() {
        return x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public void setX(Double x) {
        this.x = x;
    }
}
