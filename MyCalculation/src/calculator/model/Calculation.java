package calculator.model;

import java.io.Serializable;

/**
 *
 * @author jhuan
 */
public abstract class Calculation implements Serializable{

    private int x;
    private int y;
    private int z;

    Calculation(int x, int y) // constructor
    {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public abstract void execute();
    public abstract char getOperator();
    
}
