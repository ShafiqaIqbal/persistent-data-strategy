
package calculator.model;

import java.io.Serializable;

public class Addition extends Calculation implements Serializable {

    public Addition(int x, int y) {
        super(x, y);
    }

    @Override
    public void execute() {
        setZ(getX() + getY());
    }

    @Override
    public char getOperator() {
        return '+';
    }

}
