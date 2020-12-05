package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import objects.Item;

public class ShoppingScreen extends JFrame{
	
	//arrayList that stores items
	ArrayList<Item> itemlist = null;
	//comboBox for the items matching the type selected
	private JComboBox selectedItemCombo = new JComboBox();
	//list of items the user has chosen
	private ArrayList<Item> chosenItems = new ArrayList<Item>();
	
	public ShoppingScreen(ArrayList<Item> itemList) {
		//copy the elements of the original list to the declared list
		itemlist = itemList;
		
		// create elements
		JPanel panel = new JPanel();
		JLabel comboLabel = new JLabel("What type of item would you like to buy?");
		JComboBox itemCombo = new JComboBox();
		itemCombo.addItem("Luxury");
		itemCombo.addItem("Essential");
		itemCombo.addItem("Gift");
		JButton okButton = new JButton("ok");
		JLabel itemComboLabel = new JLabel("Select the item you want to buy:");
		JButton okButton2 = new JButton("ok");
		
		//use the MigLayout manager
		panel.setLayout(new MigLayout());
		
		//when a user has selected the type of item do the following
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the combo box (if the user selects another item of a different type not to show items from before)
				selectedItemCombo.removeAllItems();
				//check what type they have selected
				String selectedType = (String) itemCombo.getSelectedItem();
				boolean found = false;
						
				//for each item in the list of items
				for(Item item : itemlist) {
					//if the item type is the same as the one entered by the user and the stock is not empty
					if(item.getType().equals(selectedType) && item.getQuantity() != 0) {
						//add to the combo box the item
						selectedItemCombo.addItem(item.getName());
						found = true;
					}
				}
						
				//if items of that type have not been found
				if(found == false) {
					//display message that there are no items of that type
					JOptionPane.showMessageDialog(null, "There are no items with this type available", "Alert", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		//when a user has selected the item do the following
		okButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
			}
		});
		
		// add elements to the panel and add panel to the JFrame
		panel.add(comboLabel);
		panel.add(itemCombo);
		panel.add(okButton, "wrap");
		panel.add(itemComboLabel);
		panel.add(selectedItemCombo);
		panel.add(okButton2);
		add(panel);
		
		// set frame properties
		setTitle("Shopping Basket Screen");
		setSize(580, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
