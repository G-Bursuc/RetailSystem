package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Item;

public class StockScreen extends JFrame{
	ArrayList<Item> itemList = new ArrayList<Item>();
	public StockScreen() {
		// create elements
		JPanel panel = new JPanel();
		JButton addStockBtn = new JButton("ADD STOCK");
		JButton updateStockBtn = new JButton("UPDATE STOCK");
		JButton deleteStockBtn = new JButton("DELETE STOCK");
		
		addStockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddStockScreen(itemList);
			}
		});
		
		updateStockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateStockScreen(itemList);
			}
		});
		
		deleteStockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteStockScreen(itemList);
			}
		});
		
		
		// add elements to the panel and add panel to the JFrame
		add(panel);
		panel.add(addStockBtn);
		panel.add(updateStockBtn);
		panel.add(deleteStockBtn);
		
		// set frame properties
		setTitle("Stock Screen");
		setSize(580, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
}
