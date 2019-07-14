package calculator.model;

/**
 *
 * @author jhuan
 */
public class Subtract extends Calculation  {

    public Subtract(int x, int y) {
        super(x, y);
    }

    @Override
    public void execute() {
        setZ(getX() - getY());
    }

    @Override
    public char getOperator() {
        return '-';
    }
}
