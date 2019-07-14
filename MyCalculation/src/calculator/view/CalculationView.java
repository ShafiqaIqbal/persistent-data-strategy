package calculator.view;

import calculator.controller.CalculationController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CalculationView extends JPanel {

    private final JTable table;
    private final JButton btnDone=new JButton("Done");
    private final JButton btnDetail=new JButton("Show Details");
    private final JButton btnNew=new JButton("New");


    public CalculationView(CalculationController controller) {
        super(new BorderLayout());
        table=new JTable(controller.getCalculatorTableModel());
        add(new JScrollPane(table));
        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        buttonPanel.add(btnNew);
        btnNew.addActionListener(controller);
        buttonPanel.add(btnDetail);
        btnDetail.addActionListener(controller);
        buttonPanel.add(btnDone);
        btnDone.addActionListener(controller);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTable getTable(){
        return table;
    }
            
}
