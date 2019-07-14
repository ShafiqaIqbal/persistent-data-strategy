package calculator.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import calculator.SerializedDataCollection;

public class CalculatorTableModel extends AbstractTableModel {

    private final String[] columnNames = {"X", "Y", "Operator", "Z"};
    //private final CalculationList calculationList;
    private final ArrayList<Calculation> calculationList;

    public CalculatorTableModel(ArrayList<Calculation> calculationList) {
        this.calculationList = calculationList;
    }

    @Override
    public int getRowCount() {
        return calculationList.size();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Calculation calc = calculationList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return calc.getX();
            case 1:
                return calc.getOperator();
            case 2:
                return calc.getY();
            case 3:
                try{
                    calc.execute();
                    return calc.getZ();
                }catch(Exception e){
                    return e.getMessage();
                }
        }
        return "";
    }

}
