
public class RoomService {
    public enum Status{
        CONFIRMED, PREPARING, DELIVERED
    }
    private String roomNo, remarks, menuItem, quantity;
    private Status status;
    
    public RoomService(String no, String item, String quantity, String remarks) {
        menuItem = item;
        roomNo = no;
        this.quantity = quantity;
        this.remarks = remarks;
        status = Status.PREPARING;
    }
    public String getQuantity() {
        return quantity;
    }
    public String getMenuItem() {
        return menuItem;
    }
    public String getRoomNo (){
        return roomNo;
    }
    public String getRemarks(){
        return remarks;
    }
    public Status getStatus() {
        return status;
    }
    public void setQuantity(String quan) {
        quantity = quan;
    }
    public void setMenuItem(String item) {
        menuItem = item;
    }
    public void setRoomNo (String no){
        roomNo = no;
    }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public void setStatus(Status s) {
        status = s;
    }
    
}
