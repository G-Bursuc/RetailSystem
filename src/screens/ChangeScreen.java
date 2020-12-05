package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import objects.Order;

public class ChangeScreen extends JFrame{
	Order basket = null;
	
	public ChangeScreen(Order shoppingBasket) {
		basket = shoppingBasket;
		boolean emptyBasket = true;
		
		// create elements
		JPanel panel = new JPanel();
		JLabel amountLabel= new JLabel("Amount Due in Basket in €: (blank if no basket has been made)");
		JLabel paymentLabel= new JLabel("Amount Paid in €: ");
		JTextField amountField = null;
		JTextField paymentField = new JTextField(20);
		JButton calculateBttn = new JButton("Calculate Change");
		JButton exitBttn = new JButton("Exit");
		
		// check if an order item (shopping basket) exists in the system yet
		if (basket == null) // if empty, then leave amountField empty
			amountField = new JTextField("", 20);
		else { // if an order exists then fill in the order total into amountField
			amountField = new JTextField("30", 20);
			//amountField = new JTextField(basket.getTotal());
			emptyBasket = false;
		}
		
		// button that closes the change calculator window
		exitBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// add elements to the panel and add panel to the JFrame
		panel.setLayout(new MigLayout("", "275!"));
		panel.add(amountLabel, "wrap");
		panel.add(amountField, "wrap");
		panel.add(paymentLabel, "wrap");
		panel.add(paymentField, "wrap");
		panel.add(calculateBttn, "wrap");
		panel.add(exitBttn);
		add(panel);
		
		// set frame properties
		setTitle("Change Calculator");
		setSize(450, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// show pop up window to inform user that the amount field has taken the total value from the shopping basket
		if (!emptyBasket)
			JOptionPane.showMessageDialog(null, "Since a shopping basket has been made recently, the total \n"
			+ "from it has been automatically filled into the calculator.", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
