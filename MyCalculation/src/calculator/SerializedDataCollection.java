package calculator;

import java.io.Serializable;
import java.util.ArrayList;

import calculator.model.Calculation;

public class SerializedDataCollection implements Serializable{

	public ArrayList<Calculation> calculationList;

	public SerializedDataCollection(ArrayList<Calculation> calculationList) {
		this.calculationList =calculationList;
	}
}
