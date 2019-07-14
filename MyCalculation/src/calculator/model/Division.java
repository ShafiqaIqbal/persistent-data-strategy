package calculator.model;

import java.io.Serializable;

/**
 *
 * @author jhuan
 */
public class Division extends Calculation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Division(int x, int y) {
        super(x, y);
    }

    @Override
    public void execute() {
        if (getY() == 0) {
            throw new RuntimeException("divide zero!");
        }
        setZ(getX() / getY());
    }

    @Override
    public char getOperator() {
        return '/';
    }

}
