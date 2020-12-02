package screens;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Order;

public class ChangeScreen extends JFrame{
	
	public ChangeScreen(Order shoppingBasket) {
		// create elements
		JPanel panel = new JPanel();
		
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
