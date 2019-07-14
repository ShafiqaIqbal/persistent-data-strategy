package calculator;

import calculator.model.Subtract;
import org.junit.Test;
import static org.junit.Assert.*;

public class SubtractTest {
    
    @Test
    public void testGetOperator() {
        Subtract calc = new Subtract(100, 20);
        assertEquals('-', calc.getOperator());
    }
    
    @Test
    public void testExecute() {
        Subtract calc = new Subtract(100, 20);
        calc.execute();
        assertEquals(80, calc.getZ());
    }
    
}
