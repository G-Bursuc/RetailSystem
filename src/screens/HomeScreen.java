package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import objects.Item;
import objects.Order;

public class HomeScreen extends JFrame{
	// array list to store the stock items
	ArrayList<Item> itemList = new ArrayList<Item>();
	// global order to store the last order from the shopping basket, to be used for change calculator
	Order shoppingBasket = null;
	
	public HomeScreen() {
		// create elements
		JPanel panel = new JPanel();
		
		/*Test button to test if the interface is showing properly can be DELETED when merging code*/
		JButton testButton = new JButton("ADD ITEM");
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddItemScreen(itemList);
			}
		});
		
		
		// add elements to the panel, configure miglayout and add panel to the JFrame
		panel.setLayout(new MigLayout());
		add(panel);
		add(testButton);
		
		
		
		// set frame properties
		setTitle("Home Screen");
		setSize(580, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// constructor for the main home screen
	public static void main(String[] args) {
		new HomeScreen();
	}

}
