/**
 * Represents the Room Service added to the Menu.
 * This Menu item can be ordered by guest.
 * 
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class RoomService {
	public enum Status{
		CONFIRMED, PREPARING, DELIVERED
	}
	
	/**	The room No of the room which the MenuItem order will be delivered to */
	private String roomId;
	
	/**	Any special instruction for the MenuItem order */
	private String remarks; 
	
	/**	The number of MenuItem ordered */
	private int menuItemNo;
	
	/**	The quantity of the MenuItem ordered */
	private int quantity;
	
	/**	The Status of the MenuItem ordered */
	private Status status;

	/** The price of the MenuItem ordered*/
	private double price;

	/**
	 * RoomService Constructor.
	 * 
	 * @param no The room No of the room which the MenuItem order will be delivered to 
	 * @param menuItemNo The number of MenuItem ordered
	 * @param quantity The quantity of the MenuItem ordered
	 * @param remarks Any special instruction for the MenuItem order
	 * @param price The price of the MenuItem ordered
	 * @param status The Status of the MenuItem ordered
	 */
	public RoomService(String no, int menuItemNo, int quantity, String remarks, double price, Status status) {
		roomId = no;
		this.menuItemNo = menuItemNo;
		this.quantity = quantity;
		this.remarks = remarks;
		this.status = status;
		this.price = price;
	}

	/**
	 * This method converts String into RoomService Status. 
	 * 
	 * @param status Status entered by the user.
	 * @return Status of the room.
	 */
	public static Status strToStatus (String status) {
		Status roomStatus = RoomService.Status.PREPARING;
		switch (status) {
			case "PREPARING":	roomStatus = RoomService.Status.PREPARING;;
								break;
			case "CONFIRMED":	roomStatus = RoomService.Status.CONFIRMED;;
								break;
			case "DELIVERED": 	roomStatus = RoomService.Status.DELIVERED;
								break;
		}
		return roomStatus;
	}

	/**
	 * Get the room number.
	 * 
	 * @return The room number.
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * Set the room number.
	 * 
	 * @param roomId This room's number.
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * Get the remarks made by the Guest for the Menu Item when the Guest order.
	 * 
	 * @return the remarks made by the Guest for the Menu Item when the Guest order.
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Set the remarks made by the Guest for the Menu Item when the Guest order.
	 * 
	 * @param remarks Any special instruction for the MenuItem order.
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * Get the MenuItem number 
	 * @return The MenuItem number
	 */
	public int getMenuItemNo() {
		return menuItemNo;
	}

	/**
	 * Set the MenuItem number 
	 * @param menuItemNo This MenuItem number
	 */
	public void setMenuItemNo(int menuItemNo) {
		this.menuItemNo = menuItemNo;
	}

	/**
	 * Get the quantity of the MenuItem ordered.
	 * @return The quantity of the MenuItem ordered.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set the quantity of the MenuItem ordered.
	 * @param quantity This quantity.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Get the Status of the RoomService order.
	 * @return The Status of the RoomService order.
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Set the Status of the RoomService order.
	 * @param status This Status.
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Get the price of the RoomService order.
	 * @return The price of the RoomService order.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the price of the RoomService order.
	 * @param price This.price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
