package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import builders.FuncBuilder;
import functions.*;
import interfaces.Function;
import interfaces.FunctionBuilder;
import interfaces.IOperButton;

import java.util.ArrayList;

public class GUI {
	public GUI(){
		Builder builder = new Builder();
	}
}

class DisplayPanel extends JPanel{
	JTextArea display;
	JScrollPane scroll;
	
	public DisplayPanel(){
		super();
		setup();
	}
	
	private void setup(){
		this.setLayout(new GridLayout(1,1));
		display = new JTextArea(3, 20);
		display.setEditable(false);
		scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
	}
	
	public void appendText(String text){
		display.setText(display.getText() + text);
	}
}

class NumberPanel extends JPanel{
	
	public NumberPanel(){
		this.setLayout(new GridLayout(4, 3));
	}
}

class OperandPanel extends JPanel{
	public OperandPanel(){
		this.setLayout(new GridLayout(5, 1));
	}
}

//decorator
class EqualsButton extends JButton {
	
	
	
	public EqualsButton(String toDisplay){
		super(toDisplay);
		this.addMouseListener(new MsListener());
	}
	
	class MsListener extends MouseAdapter{
		
		public void mouseEntered(MouseEvent evt) {
	        ((JButton) evt.getSource()).setBackground(Color.BLUE);
	    }

	    public void mouseExited(MouseEvent evt) {
	    	 ((JButton) evt.getSource()).setBackground(Color.WHITE);
	    }
		
	}
}

class Builder{
	
	JPanel display;
	
	public Builder(){
		guiSetup();
	}
	
	private void guiSetup(){
		JFrame frame = new JFrame("Simple Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setLayout(new BorderLayout());
		
		frame.setSize(500,  500);
		frame.add(DisplayPnlSetup(), BorderLayout.NORTH);
		frame.add(NumPnlSetup(), BorderLayout.CENTER);
		frame.add(OperandPnlSetup(), BorderLayout.EAST);
		
		frame.setVisible(true);
	}
	
	
	private JPanel DisplayPnlSetup(){
		display = new DisplayPanel();
		
		return display;
	}
	
	private JPanel OperandPnlSetup(){
		JPanel pnl = new OperandPanel();
		
		JButton[] buttons = new JButton[5];
		
		JButton add = new JButton("+");
		add.addActionListener(new OperListener(new Add(), (DisplayPanel) display ));
		buttons[0] = add;
		
		JButton sub = new JButton("-");
		sub.addActionListener(new OperListener(new Subtract(), (DisplayPanel) display));
		buttons[1] = sub;
		
		JButton mult = new JButton("*");
		mult.addActionListener(new OperListener(new Multiply(), (DisplayPanel) display ));
		buttons[2] = mult;
		
		JButton divide = new JButton("/");
		divide.addActionListener(new OperListener(new Divide(), (DisplayPanel) display));
		buttons[3] = divide;
		
		JButton equals = new EqualsButton("=");
		equals.addActionListener(new EqualsListener((DisplayPanel) display));
		buttons[4] = equals;
		
		addElements(pnl, buttons);
		
		return pnl;		
	}
	
	
	private JPanel NumPnlSetup(){
		JPanel pnl = new NumberPanel();
		JButton[] buttons = new JButton[9];
		
		for(int i = 9; i >= 1; i--){
			
			JButton b = new JButton("" + i);	
			b.addActionListener(new NumListener(i, (DisplayPanel) display));
			buttons[buttons.length - i] = b;
			
		}	
		
		
		addElements(pnl, buttons);
		JButton zero = new JButton("" + 0);	
		zero.addActionListener(new NumListener(0, (DisplayPanel) display));
		pnl.add(new JButton());
		pnl.add(zero);
		pnl.add(new JButton());
		return pnl;
	}
	
	
	
	private void addElements(JPanel panel, JButton[] elements){
		for(JButton b : elements){
			panel.add(b);
		}
	}
}



class NumListener implements ActionListener{
	
	int value;
	FunctionBuilder fb;
	
	DisplayPanel display;
	
	public NumListener(int value, DisplayPanel display){
		this.value = value;
		fb = FuncBuilder.getInstance();
		this.display = display;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fb.append(value);
		display.appendText("" + value);
		
	}
	
}

class OperListener implements ActionListener{
	String op;
	Function f;
	FunctionBuilder fb;
	DisplayPanel display;
	
	public OperListener(Function f, DisplayPanel d){
		this.f = f;
		fb = FuncBuilder.getInstance();
		display = d;
		op = f.toString();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fb.setFunction(f);
		fb.setOpChosen(true);
		display.appendText(op);
		
	}
}

class EqualsListener implements ActionListener {
	
	FunctionBuilder fb;
	DisplayPanel display;
	
	
	public EqualsListener(DisplayPanel display){
		fb = FuncBuilder.getInstance();
		this.display = display;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		display.appendText("=" + fb.calcFunction() + "\n");	
		fb.clearNumBuilders();
	}
	
}