package functions;

import interfaces.Function;
import interfaces.NumberBuilder;

public class Multiply implements Function {

	@Override
	public float calculate(NumberBuilder first, NumberBuilder second) {
		return first.getNum() * second.getNum(); 
	}


	public String toString(){
		return "*";
	}
}
