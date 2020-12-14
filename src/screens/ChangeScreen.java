package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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
	JTextField amountField = null;
	HashMap<Double, Integer> coinage = new HashMap<Double, Integer>();

	public ChangeScreen(Order shoppingBasket) {
		basket = shoppingBasket;
		boolean emptyBasket = true;

		// create elements
		JPanel panel = new JPanel();
		JLabel amountLabel= new JLabel("Amount Due in Basket in €: (blank if no basket has been made)");
		JLabel paymentLabel= new JLabel("Amount Paid in €: ");
		JTextField paymentField = new JTextField(20);
		JButton calculateBttn = new JButton("Calculate Change");
		JButton exitBttn = new JButton("Exit");
		JButton clear = new JButton("clear");

		// check if an order item (shopping basket) exists in the system yet
		if (basket == null) // if empty, then leave amountField empty
			amountField = new JTextField("", 20);
		else { // if an order exists then fill in the order total into amountField
			amountField = new JTextField("30", 20);
			//amountField = new JTextField(basket.getTotal());
			emptyBasket = false;
		}

		// button that calculates the change
		calculateBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the values from the text fields
				double amount = Double.parseDouble(amountField.getText());
				double payment = Double.parseDouble(paymentField.getText());
				// make sure the payment is enough to pay for the amount due
				if (payment > amount) {
					String amountDue;
					amountDue = calculate(amount, payment);
					JOptionPane.showMessageDialog(null, "Change Due: " + amountDue, "Information", JOptionPane.WARNING_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Error - Payment didn't exceed the amount due.", "Information", JOptionPane.WARNING_MESSAGE);
			}
		});

		// button that clears the calculator fields
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amountField.setText("");
				paymentField.setText("");
			}
		});
				
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
		panel.add(calculateBttn, "split 2");
		panel.add(clear, "wrap");
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

	public String calculate(double amountDue, double payment) {
		// setup array of all notes and coins for change
		Double[] notesAndChange = {500.0, 100.0, 50.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01};
		double paymentDue = 0;
		
		// create a hashmap to store how many of each note/coin to give back
		coinage.put(notesAndChange[0], 0);
		coinage.put(notesAndChange[1], 0);
		coinage.put(notesAndChange[2], 0);
		coinage.put(notesAndChange[3], 0);
		coinage.put(notesAndChange[4], 0);
		coinage.put(notesAndChange[5], 0);
		coinage.put(notesAndChange[6], 0);
		coinage.put(notesAndChange[7], 0);
		coinage.put(notesAndChange[8], 0);
		coinage.put(notesAndChange[9], 0);
		coinage.put(notesAndChange[10], 0);
		coinage.put(notesAndChange[11], 0);
		coinage.put(notesAndChange[12], 0);

		// going through each type of change starting at 500 euro note
		for (int x = 0; x < notesAndChange.length; x++) {
			// check if a note/coin can be taken away from the amount due
			if (amountDue >= notesAndChange[x]) {
				// keep taking away value of change selected and increment in hashmap
				while(amountDue >= notesAndChange[x]) {
					amountDue -= notesAndChange[x];
					coinage.put(notesAndChange[x], coinage.get(notesAndChange[x]) + 1);
				}
			}
		}
		
		return null;
	}
}
