package calculator.model;

import java.io.Serializable;

/**
 *
 * @author jhuan
 */
public class Multiplication extends Calculation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Multiplication(int x, int y) {
        super(x, y);
    }

    @Override
    public void execute() {
        setZ(getX() * getY());
    }

    @Override
    public char getOperator() {
        return '*';
    }

}
