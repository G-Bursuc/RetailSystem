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
<<<<<<< HEAD
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
=======
>>>>>>> 4e9ec35271b4ca8d3656706a38b17d184e14fbbf
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import objects.Item;
import objects.Order;
import objects.ShoppingBasket;

public class ShoppingScreen extends JFrame{
	
	//arrayList that stores items
	ArrayList<Item> itemlist = null;
	//the final order after all items have been added to the basket
	Order order;
	//the type of item selected
	private String selectedType;
	//the item chosen
	private Item chosenItem;
	//total cost of each item
	private double totalAfterVat = 0;
	//total cost for the order
	private double totalCost = 0;
	
	public ShoppingScreen(ArrayList<Item> itemList, Order shoppingBasket) {
		//copy the elements of the original list to the declared list of items
		itemlist = itemList;
		//the order
		order = shoppingBasket;
		
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
		JButton viewButton = new JButton("View Basket");
		JButton totalCost = new JButton("Total Cost");
		JLabel showVat = new JLabel();
		JLabel showPrice = new JLabel();
		JLabel showTotal = new JLabel();
		JButton addToBasket = new JButton("Add to basket");
		JButton backToMenu = new JButton("back to menu");
		JTextArea itemArea = new JTextArea(20, 40);
		JScrollPane bottomScrollPane = new JScrollPane(itemArea);
		
		
		//constraints
		//for scroll pane
		bottomScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		bottomScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//arrayList of the shopping basket in which the added items are kept
		ArrayList<ShoppingBasket> itemsInBasket = new ArrayList<ShoppingBasket>();
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
					if(item.getTypeOfItem().equalsIgnoreCase(selectedType) && item.getItemQuantity() != 0) {
						//add to the combo box the item
						selectedItemCombo.addItem(item.displayInCombo());
						found = true;
					}
				}
						
				//if items of that type have not been found
				if(found == false) {
					//display message that there are no items of that type
					JOptionPane.showMessageDialog(null, "There are no items with this type available", "Alert", JOptionPane.WARNING_MESSAGE);
					selectedItemCombo.removeAllItems();
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
				//variables for the VAT
				double price = 0;
				double rate = 0;
				double vatAmount = 0;
				double totalAfterVat = 0;
				boolean found = false;
				
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
								found = true;
							}
						}
					}
					
					//if everything above is a success assign appropriate values for each type
					if(found == true) {
						if(selectedType.equals("Luxury")) {
							//if the selected type is luxury assign appropriate values
							rate = 20;
							price = 50 * Integer.parseInt(quantity.getText());
						}
						else if(selectedType.equals("Essential")) {
							//if the selected type is essential assign appropriate value
							rate = 10;
							price = 30 * Integer.parseInt(quantity.getText());
						}
						else if(selectedType.equals("Gift")) {
							//if the selected type is gift assign appropriate values
							rate = 5;
							price = 20 * Integer.parseInt(quantity.getText());
						}
						
						//calculate the VAT
						vatAmount = rate * (price / 100);
						totalAfterVat = price + vatAmount;
						
						//display the VAT
						showPrice.setText("Price for this item (without VAT): €" + price);
						showVat.setText("VAT(" + rate + "%): €" + vatAmount);
						showTotal.setText("Total (with VAT): €" + totalAfterVat);
					}
				}
			}
		});
		
		//actionListener for the adding to basket button
		addToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//for each item in the list of items
				for(Item item : itemlist) {
					//if the item matches the chosen item
					if(item == chosenItem) {
						//add the item so the basket
						ShoppingBasket basket= new ShoppingBasket(item, Integer.parseInt(quantity.getText()), totalAfterVat);
						itemsInBasket.add(basket);	
						//update the stock numbers of that item
						item.setItemQuantity(item.getItemQuantity() - Integer.parseInt(quantity.getText()));
						}
				}
				//create the order with the items in the basket
				order = new Order(itemsInBasket, totalCost);
				//clear all fields so the user can buy more items if they want
				selectedItemCombo.removeAllItems();
				quantity.setText("");
				showVat.setText("");
				showPrice.setText("");
				showTotal.setText("");
				vatRate.setText("");
			}
		});
		
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		}
			
		});
		
		totalCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double totalCost = 0;
								
				
		}
			
		});
		//actionListener for the back to menu button
		backToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close the shopping screen page
				dispose();
			}
		});
		
		// add elements to the panel and add panel to the JFrame
		itemArea.setEditable(false);
		
		panel.add(comboLabel);
		panel.add(itemCombo, "split 2");
		panel.add(okButton, "wrap");
		panel.add(itemComboLabel);
		panel.add(selectedItemCombo, "split 2");
		panel.add(okButton2, "wrap");
		panel.add(vatRate, "wrap");
		panel.add(quantityLabel, "wrap");
		panel.add(quantity, "split 2");
		panel.add(calculate, "wrap");
		panel.add(showPrice, "wrap");
		panel.add(showVat, "wrap");
		panel.add(showTotal, "wrap");
		panel.add(addToBasket, "split 2");
		panel.add(viewButton, "split 2");
		panel.add(totalCost, "split 2");
		panel.add(backToMenu, "wrap");
		panel.add(bottomScrollPane, "span");
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