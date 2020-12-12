package objects;

public class Item {
	
	private String itemName;
	private String typeOfItem;
	private int itemQuantity;
	private int itemID;
	private static int uniqueID = 1;
	
	public Item() {
		
	}
	
	public Item(String name, String type, int quantity) {
		this.itemName = name;
		this.typeOfItem = type;
		this.itemQuantity = quantity;
		this.itemID = createID();
	}
	
	public static int createID() {
		return uniqueID++;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTypeOfItem() {
		return typeOfItem;
	}

	public void setTypeOfItem(String typeOfItem) {
		this.typeOfItem = typeOfItem;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String toString() {
		return
				"\nItem ID: " + this.itemID
				+ "\nItem Name: " + this.itemName
				+ "\nItem Type: " + this.typeOfItem 
				+ "\nItem Quantity: " + this.itemQuantity;
	}
	
	//display in the shopping screen combo box item info
		public String displayInCombo() {
			return "Item ID: " + this.itemID + " Item Name: " + this.itemName;
		}

}
