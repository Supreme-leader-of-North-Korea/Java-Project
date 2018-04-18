public class RoomService {
	public enum Status{
		CONFIRMED, PREPARING, DELIVERED
	}
	private String roomId, remarks; 
	private int menuItemNo, quantity;
	private Status status;
	private double price;

	/**
	 * 
	 * @param no
	 * @param menuItemNo
	 * @param quantity
	 * @param remarks
	 * @param price
	 * @param status
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
	 * 
	 * @param status
	 * @return
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
	 * 
	 * @return
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * 
	 * @param roomId
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * 
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 
	 * @param remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 
	 * @return
	 */
	public int getMenuItemNo() {
		return menuItemNo;
	}

	/**
	 * 
	 * @param menuItemNo
	 */
	public void setMenuItemNo(int menuItemNo) {
		this.menuItemNo = menuItemNo;
	}

	/**
	 * 
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
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
