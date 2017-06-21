package functions;

import interfaces.Function;
import interfaces.NumberBuilder;

public class Divide implements Function {

	@Override
	public float calculate(NumberBuilder first, NumberBuilder second) {		
		float num = 0.0f;
		try {
			num = first.getNum() / (float) second.getNum();
		}catch(Exception e){}
		
		return num;
	}
	
	
	public String toString(){
		return "/";
	}
}
