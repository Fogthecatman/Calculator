package interfaces;

import interfaces.Function;
import interfaces.NumberBuilder;

public interface FunctionBuilder {
	float calcFunction();
	NumberBuilder getFirst();
	NumberBuilder getSecond();
	void clearNumBuilders();
	void append(int num);
	void setFunction(Function f);
	void setOpChosen(boolean bool);
}