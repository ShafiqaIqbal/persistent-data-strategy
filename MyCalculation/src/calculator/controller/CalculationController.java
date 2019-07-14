package calculator.controller;

import calculator.SerializedDataCollection;
import calculator.model.Addition;
import calculator.model.Calculation;
import calculator.model.CalculationList;
import calculator.model.CalculatorTableModel;
import calculator.model.Division;
import calculator.model.Multiplication;
import calculator.model.Subtract;
import calculator.view.CalculationDetailView;
import calculator.view.CalculationView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.corba.se.impl.io.OptionalDataException;

public class CalculationController extends JFrame implements ActionListener {

    /**
 * 
 */
private static final long serialVersionUID = 1L;
private  ArrayList<Calculation> calculationList;
private final CalculationView calculationView;
private final CalculationDetailView calculationDetailView;
private final CalculatorTableModel calculatorTableModel;
private final SerializedDataCollection serializedDataCollection;

public CalculationController() throws IOException {
	calculationList = new ArrayList<Calculation>();

	calculationList.add(new Addition(10, 50));
	calculationList.add(new Subtract(50, 30));
	calculationList.add(new Multiplication(3, 4));
	calculationList.add(new Division(10, 2));
	serializedDataCollection=new SerializedDataCollection(calculationList);
	
	String filename = "file.ser"; 
    
    // Serialization
	FileOutputStream file = null;
	file = new FileOutputStream(filename);
    
        ObjectOutputStream out = new ObjectOutputStream(file); 
          
        // Method for serialization of object 
        out.writeObject(serializedDataCollection); 
          
        out.close(); 
        file.close(); 

    SerializedDataCollection serializedDataCollection2=null;
    
  // Deserialization 
    try
    {    
        // Reading the object from a file 
        FileInputStream file2 = new FileInputStream(filename); 
        ObjectInputStream in = new ObjectInputStream(file2); 
          
        // Method for deserialization of object
        List<Object> results = new ArrayList<Object>();
       
        try {
        		serializedDataCollection2=(SerializedDataCollection)in.readObject();
        }
        catch (EOFException  e) {
            
        }
        catch(IOException ex)
        {
        	System.out.println("IOException is caught");
        }
        finally {
        	in.close();
        	if(file2!=null)
        	{
        		file2.close();
        	}                
        }          
          
        System.out.println("Object has been deserialized "); 
       
        calculationList=serializedDataCollection2.calculationList;
        serializedDataCollection.calculationList = calculationList;
        for(int i=0;i<calculationList.size();i++) {
        	System.out.println(calculationList.get(i).getOperator());
        }
    } 
      
    catch(IOException ex) 
    { 
        System.out.println("IOException is caught"); 
    } 
      
    catch(ClassNotFoundException ex) 
    { 
        System.out.println("ClassNotFoundException is caught"); 
    } 
    
    calculatorTableModel = new CalculatorTableModel(serializedDataCollection.calculationList);
    calculationView = new CalculationView(this);
    add(calculationView, BorderLayout.CENTER);
    calculationDetailView = new CalculationDetailView(this);
}

@Override
public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    if (command.equals("Done")) {
        dispose();
    } else if (command.equals("New")) {
        remove(calculationView);
        add(calculationDetailView, BorderLayout.CENTER);
        calculationDetailView.updateData(serializedDataCollection.calculationList, -1);
    } else if (command.equals("Show Details")) {
        int index = calculationView.getTable().getSelectedRow();
        if (index >= 0) {
            remove(calculationView);
            add(calculationDetailView, BorderLayout.CENTER);
            calculationDetailView.updateData(serializedDataCollection.calculationList, index);
        }else{
            JOptionPane.showMessageDialog(this, "Please choice record!");
        }
    } else if (command.equals("Save")) {
        int x = calculationDetailView.getXValue();
        int y = calculationDetailView.getYValue();
        String operator = calculationDetailView.getOperator();
        if (operator != null) {
            Calculation calculation = null;
            if (calculationDetailView.getIndex() == -1) {
                if (operator.equals("+")) {
                    calculation = new Addition(x, y);
                } else if (operator.equals("-")) {
                    calculation = new Subtract(x, y);
                } else if (operator.equals("*")) {
                    calculation = new Multiplication(x, y);
                } else if (operator.equals("/")) {
                    calculation = new Division(x, y);
                }
                serializedDataCollection.calculationList.add(calculation);
            } else {
                calculation = serializedDataCollection.calculationList.get(calculationDetailView.getIndex());
                calculation.setX(x);
                calculation.setY(y);
            }
            calculatorTableModel.fireTableDataChanged();
            remove(calculationDetailView);
            add(calculationView, BorderLayout.CENTER);
        }else{
            JOptionPane.showMessageDialog(this, "Please choice operator!");
        }
    } else if (command.equals("Close")) {
        remove(calculationDetailView);
        add(calculationView, BorderLayout.CENTER);
    } else if (command.equals("Delete")) {
        if (JOptionPane.showConfirmDialog(this, "Are you sure delete?", "Conformation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            	serializedDataCollection.calculationList.remove(calculationDetailView.getIndex());
                calculatorTableModel.fireTableDataChanged();
                remove(calculationDetailView);
                add(calculationView, BorderLayout.CENTER);
            }
        }
        validate();
        repaint();
    }

    public CalculatorTableModel getCalculatorTableModel() {
        return calculatorTableModel;
    }
}
