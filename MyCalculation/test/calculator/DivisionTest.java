package calculator;

import calculator.model.Division;
import org.junit.Test;
import static org.junit.Assert.*;

public class DivisionTest {

    @Test
    public void testGetOperator() {
        Division calc = new Division(100,50);
        assertEquals('/', calc.getOperator());
    }
    
    @Test
    public void testExecute() {
        Division calc = new Division(100,50);
        calc.execute();
        assertEquals(2, calc.getZ());
    }
    
    @Test
    public void testExecuteDivideByZero() {
        Division calc = new Division(100,0);
        try{
            calc.execute();
            fail("can't throw divide zero!");
        }catch(RuntimeException e){
        }
    }
    
}
