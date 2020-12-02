package screens;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Order;

public class ChangeScreen extends JFrame{
	
	public ChangeScreen(Order shoppingBasket) {
		// create elements
		JPanel panel = new JPanel();
		JButton calculateBttn = new JButton("Calculate");
		
		// add elements to the panel and add panel to the JFrame
		add(panel);
		
		// set frame properties
		setTitle("Change Calculator");
		setSize(580, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
