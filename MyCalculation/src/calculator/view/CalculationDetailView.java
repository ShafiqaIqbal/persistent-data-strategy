package calculator.view;

import calculator.controller.CalculationController;
import calculator.model.Calculation;
import calculator.model.CalculationList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculationDetailView extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField tfX = new JTextField(6);
    private final JTextField tfY = new JTextField(6);
    private final JButton btnSave = new JButton("Save");
    private final JButton btnDelete = new JButton("Delete");
    private final JButton btnClose = new JButton("Close");
    private final JComboBox<String> cbOperator = new JComboBox<>(new String[]{"+", "-", "*", "/"});
    private final JLabel labOperator=new JLabel();
    private int index;

    public CalculationDetailView(CalculationController controller) {
        super(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
        topPanel.add(tfX);
        topPanel.add(cbOperator);
        topPanel.add(labOperator);
        topPanel.add(tfY);
        add(topPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(btnSave);
        btnSave.addActionListener(controller);
        buttonPanel.add(btnDelete);
        btnDelete.addActionListener(controller);
        buttonPanel.add(btnClose);
        btnClose.addActionListener(controller);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public String getOperator() {
        Object obj=cbOperator.getSelectedItem();
        if(obj==null){
            return null;
        }else{
            return obj.toString();
        }
    }

    public int getXValue() {
        try {
            return Integer.parseInt(tfX.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getYValue() {
        try {
            return Integer.parseInt(tfY.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getIndex() {
        return index;
    }

    public void updateData(ArrayList<Calculation> calculationList, int index) {
        this.index = index;
        if (index == -1) {
            tfX.setText("");
            tfY.setText("");
            cbOperator.setSelectedIndex(-1);
            cbOperator.setVisible(true);
            labOperator.setVisible(false);
            btnDelete.setVisible(false);
        } else {
            Calculation calculation = calculationList.get(index);
            tfX.setText(String.valueOf(calculation.getX()));
            tfY.setText(String.valueOf(calculation.getY()));
            labOperator.setText(" " + calculation.getOperator()+" ");
            cbOperator.setVisible(false);
            labOperator.setVisible(true);
            btnDelete.setVisible(true);
        }
    }

}
