/**
 * Represents a Menu item added to the Menu.
 * This Menu item can be ordered by guest.
 * 
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class MenuItem {
	
	
	/**	The Name of the item in the Menu */
	private String name;
	
	/**	A brief description of the item in the Menu */
	private String description;
	
	/**	The price that the hotel charge for this item in the Menu */
	private double price;

	/**
	 * Creates a new Menu item with the given name, description and price
	 * 
	 * @param name This Menu item's name
	 * @param description This Menu item's description
	 * @param price This Menu item's price
	 */
	public MenuItem(String name, String description, double price){
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/**
	 * Get the name of this Menu item.
	 * @return This Menu item's name. 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name for the Menu item.
	 * @param name This Menu item's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the description of the Menu item.
	 * @return The description for this Menu item's 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of the Menu item.
	 * @param description A description for this Menu item's
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the price for the Menu item.
	 * @return This Menu item's price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the price for the Menu item.
	 * @param price This Menu item's price
	 */
	public void setPrice(double price) {
		this.price = price;
	}


}