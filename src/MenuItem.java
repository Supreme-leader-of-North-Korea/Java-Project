public class MenuItem {
	private String name, description;
	private double price;

	/**
	 * 
	 * @param name
	 * @param description
	 * @param price
	 */
	public MenuItem(String name, String description, double price){
		this.name = name;
		this.description = description;
		this.price = price;
	}


	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}


}