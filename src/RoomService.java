
public class RoomService {
    public enum Status{
        CONFIRMED, PREPARING, DELIVERED
    }
    private String roomId, remarks, menuItem;
    private int quantity;
    private Status status;
    private double price;
    
    public RoomService(String no, String item, int quantity, String remarks, double price, Status status) {
        menuItem = item;
        roomId = no;
        this.quantity = quantity;
        this.remarks = remarks;
        this.status = status;
        this.price = price;
    }
    public double getPrice() {
		return price;
	}
	public int getQuantity() {
        return quantity;
    }
    public String getMenuItem() {
        return menuItem;
    }
    public String getRoomId (){
        return roomId;
    }
    public String getRemarks(){
        return remarks;
    }
    public Status getStatus() {
        return status;
    }
    public void setQuantity(int quan) {
        quantity = quan;
    }
    public void setMenuItem(String item) {
        menuItem = item;
    }
    public void setRoomId (String no){
        roomId = no;
    }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public void setStatus(Status s) {
        status = s;
    }
    public void setPrice(double price) {
		this.price = price;
	}
    
    public static Status strToStatus (String status) {
			Status roomStatus = RoomService.Status.PREPARING;
			
			switch (status) {
				case "PREPARING": 			  roomStatus = RoomService.Status.PREPARING;;
										  break;
				case "CONFIRMED": 		  roomStatus = RoomService.Status.CONFIRMED;;
				 				 		  break;
				case "DELIVERED": 		  roomStatus = RoomService.Status.DELIVERED;
				 				 		  break;
				
			}
			
			return roomStatus;
		}
}
