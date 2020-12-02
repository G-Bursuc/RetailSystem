package screens;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShoppingScreen extends JFrame{
	
	public ShoppingScreen() {
		// create elements
		JPanel panel = new JPanel();
		
		// add elements to the panel and add panel to the JFrame
		add(panel);
		
		// set frame properties
		setTitle("Shopping Basket Screen");
		setSize(580, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
