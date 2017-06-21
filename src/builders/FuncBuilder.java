package builders;

import interfaces.Function;
import interfaces.FunctionBuilder;
import interfaces.NumberBuilder;

public class FuncBuilder implements FunctionBuilder {

	NumberBuilder first, second;
	Function f;
	
	static FunctionBuilder instance;
	
	boolean opChosen = false;
	
	private FuncBuilder(){
		first =  new NumBuilder();
		second = new NumBuilder();
	}
	
	public static FunctionBuilder getInstance(){
		if(instance == null){
			instance = new FuncBuilder();
		}
		
		return instance;
	}
	@Override
	public NumberBuilder getFirst() {
		return first;
	}

	@Override
	public NumberBuilder getSecond() {
		return second;
	}

	public void append(int num){
		if(!opChosen)
			first.append(num);
		else
			second.append(num);
	}
	
	public void setOpChosen(boolean in){
		opChosen = in;
	}
	
	@Override
	public float calcFunction() {
		return f.calculate(first, second);
	}

	@Override
	public void clearNumBuilders() {
		first.clear();
		second.clear();
		opChosen = false;
	}
	
	public void setFunction(Function f){
		this.f = f;
	}

}
