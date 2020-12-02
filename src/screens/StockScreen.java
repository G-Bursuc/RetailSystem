package screens;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StockScreen extends JFrame{

	public StockScreen() {
		// create elements
		JPanel panel = new JPanel();
		
		// add elements to the panel and add panel to the JFrame
		add(panel);
		
		// set frame properties
		setTitle("Stock Screen");
		setSize(580, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
