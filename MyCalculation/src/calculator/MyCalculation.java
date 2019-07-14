package calculator;

import calculator.controller.CalculationController;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author jhuan
 */
public class MyCalculation{

    public static void main(String args[]) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                CalculationController controller = null;
				try {
					controller = new CalculationController();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                controller.setTitle("Calculation");
                controller.pack();
                controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                controller.setLocation(100, 100);
                controller.setVisible(true);
            }
        });
    }
}
