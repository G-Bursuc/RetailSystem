package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import objects.Item;

public class InputItem extends JFrame{
	ArrayList<Item> iList = null;
	
	public InputItem(ArrayList<Item> itemList) {
		iList = itemList;
		
		// create elements
		JPanel panel = new JPanel();
	
		//label and textfields for user input
		JLabel label = new JLabel("Enter itemID: ");
		JTextField itemID = new JTextField(20);
		JLabel label2 = new JLabel("Enter quantity: ");
		JTextField quantity = new JTextField(20);
		
		//create buttons
		JButton enterButton = new JButton("Enter Item");
		JButton exit = new JButton("Exit");

		//reads data from textfields 
			enterButton.addActionListener(new ActionListener() {
				boolean itemFound = false;
					public void actionPerformed(ActionEvent e) {
						// checks if itemID exists
						for (Item item: iList)	{
							if(item.getItemID() == Integer.parseInt(itemID.getText())) {
								itemFound = true;
							}
						}
						
						if (itemFound == false) {
							JOptionPane.showMessageDialog(null, "This item does not exist", "Alert", JOptionPane.WARNING_MESSAGE);
						}
		
						else if (itemFound == true)
						{
							int itemQuantity = Integer.parseInt(quantity.getText());
							// checks item quanity and quantity entered
							for (Item item : iList) {
								if (itemQuantity > item.getItemQuantity()) {
									JOptionPane.showMessageDialog(null, "There are not enough items in stock", "Alert", JOptionPane.WARNING_MESSAGE);
								}
								
								else {
									JOptionPane.showMessageDialog(null, "Item can be packed into box", "Information", JOptionPane.INFORMATION_MESSAGE);
								}
							}
							
						}
				}
			});
			
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}				
			});
			
		// add elements to the panel and add panel to the JFrame
		panel.add(label);
		panel.add(itemID);
		panel.add(label2);
		panel.add(quantity);
		panel.add(enterButton);
		panel.add(exit);
		add(panel);
		panel.setLayout(new MigLayout ("center center, wrap, gapy 20"));
				
		// set frame properties
		setTitle("Item Boxing Screen - Enter Items");
		setSize(580, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}