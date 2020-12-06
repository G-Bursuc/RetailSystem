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
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import objects.Item;

public class ShoppingScreen extends JFrame{
	
	//arrayList that stores items
	ArrayList<Item> itemlist = null;
	//the type of item selected
	private String selectedType;
	//the item chosen
	private Item chosenItem;
	
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
		JComboBox selectedItemCombo = new JComboBox();
		JButton okButton2 = new JButton("ok");
		JLabel vatRate = new JLabel();
		JLabel quantityLabel = new JLabel("How many?");
		JTextField quantity = new JTextField();
		quantity.setColumns(10);
		JButton calculate = new JButton("calculate");
		
		JButton backToMenu = new JButton("back to menu");
		
		//use the MigLayout manager
		panel.setLayout(new MigLayout());
		
		//actionListener for the first ok button - after a user selects a type
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the combo box (if the user selects another item of a different type not to show items from before)
				selectedItemCombo.removeAllItems();
				//check what type they have selected
				selectedType = (String) itemCombo.getSelectedItem();
				boolean found = false;
						
				//for each item in the list of items
				for(Item item : itemlist) {
					//if the item type is the same as the one entered by the user and the stock is not empty
					if(item.getTypeOfItem().equals(selectedType) && item.getItemQuantity() != 0) {
						//add to the combo box the item
						selectedItemCombo.addItem(item.displayInCombo());
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
		
		//actionListener for the second ok button - after a user selects the item
		okButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the item combo box is blank
				if(selectedItemCombo.getItemCount() == 0) {	
					//display message that there is no item chosen
					JOptionPane.showMessageDialog(null, "No item chosen", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else {
					//if the user has selected an item
					//display the appropriate VAT rate for the type
					if(selectedType.equals("Luxury")) {
						vatRate.setText("VAT rate: 20%");
					}
					else if(selectedType.equals("Essential")) {
						vatRate.setText("VAT rate: 10%");
					}
					else if(selectedType.equals("Gift")) {
						vatRate.setText("VAT rate: 5%");
					}
				}
			}
		});
		
		//actionListener for the calculate button - after a user has entered a quantity number
		calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if there is no item selected display message
				if(selectedItemCombo.getItemCount() == 0) {
					vatRate.setText("");
					JOptionPane.showMessageDialog(null, "No item chosen", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else if(quantity.getText().equals("")) {
					//if there is no quantity entered display message
					JOptionPane.showMessageDialog(null, "No quantity has been entered", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else {
					//if there is an item selected and a quantity entered
					//for each item in the list of items
					for(Item item : itemlist) {
						//if the item matches the item displayed in the combo box
						if(item.displayInCombo().equals(selectedItemCombo.getSelectedItem())) {
							//if the quantity entered is more than the number in stock
							if(Integer.parseInt(quantity.getText()) > item.getItemQuantity()) {
								//display message that there isnt enough in stock
								JOptionPane.showMessageDialog(null, "We dont have that many items in stock", "Alert", JOptionPane.WARNING_MESSAGE);
							}
							else {
								//otherwise set the chosen item equal to the item the loop is on
								chosenItem = item;
							}
						}
					}
				}
			}
		});
		
		//actionListener for the back to menu button
		backToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// add elements to the panel and add panel to the JFrame
		panel.add(comboLabel);
		panel.add(itemCombo);
		panel.add(okButton, "wrap");
		panel.add(itemComboLabel);
		panel.add(selectedItemCombo);
		panel.add(okButton2, "wrap");
		panel.add(vatRate, "wrap");
		panel.add(quantityLabel, "wrap");
		panel.add(quantity, "wrap");
		panel.add(calculate, "wrap");
		panel.add(backToMenu);
		add(panel);
		
		// set frame properties
		setTitle("Shopping Basket Screen");
		setSize(580, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
