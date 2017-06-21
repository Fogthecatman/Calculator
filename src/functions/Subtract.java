package functions;

import interfaces.Function;
import interfaces.NumberBuilder;

public class Subtract implements Function {

	@Override
	public float calculate(NumberBuilder first, NumberBuilder second) {
		return first.getNum() - second.getNum();
	}

	public String toString(){
		return "-";
	}
}
