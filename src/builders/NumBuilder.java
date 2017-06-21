package builders;

import interfaces.NumberBuilder;

public class NumBuilder implements NumberBuilder{
	
	int number;
	
	public NumBuilder(){
		number = 0;
	}

	@Override
	public void append(int num) {
		number = (number * 10) + num;	
	}

	@Override
	public int getNum() {
		return number;
	}

	@Override
	public void clear() {
		number = 0;
		
	}

}
