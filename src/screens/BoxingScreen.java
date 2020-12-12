package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import objects.Box;
import objects.Item;

public class BoxingScreen extends JFrame{
		ArrayList<Box> bList = null;
		ArrayList<Item> iList = null;
	public BoxingScreen(ArrayList<Box> boxList, ArrayList<Item> itemList) {
		bList = boxList;
		iList = itemList;
		
		JPanel panel = new JPanel();
		JButton enterBox = new JButton("Enter Box");
		JButton enterItem = new JButton("Enter Item");
		
	//action listener for when the total button is clicked
		enterBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InputBox(bList);
			}
		});
		
		enterItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InputItem(iList);
			}
		});
		
		panel.add(enterBox);
		panel.add(enterItem);
		add(panel);
		setTitle("Boxing Screen");
		setSize(280, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
