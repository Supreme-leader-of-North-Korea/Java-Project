
public class RoomService {
    public enum Status{
        CONFIRMED, PREPARING, DELIVERED
    }
    private String roomId, remarks; 
    private int menuItemNo, quantity;
    private Status status;
    private double price;
    
    public RoomService(String no, int menuItemNo, int quantity, String remarks, double price, Status status) {
    	roomId = no;
        this.menuItemNo = menuItemNo;
        this.quantity = quantity;
        this.remarks = remarks;
        this.status = status;
        this.price = price;
    }
    public static Status strToStatus (String status) {
    	Status roomStatus = RoomService.Status.PREPARING;
    	switch (status) {
    		case "PREPARING": roomStatus = RoomService.Status.PREPARING;;
    						  break;
    		case "CONFIRMED": roomStatus = RoomService.Status.CONFIRMED;;
    					 	  break;
    		case "DELIVERED": roomStatus = RoomService.Status.DELIVERED;
    					 	  break;
    					
    	}
    	return roomStatus;
    }
    
	//Getter and setters
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getMenuItemNo() {
		return menuItemNo;
	}

	public void setMenuItemNo(int menuItemNo) {
		this.menuItemNo = menuItemNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
