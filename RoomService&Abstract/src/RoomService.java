
public class RoomService {
    public enum Status{
        CONFIRMED, PREPARING, DELIVERED
    }
    private String roomNo;
    private String remarks;
    private Status status;
    
    public RoomService(String no, String remarks) {
        roomNo = no;
        this.remarks = remarks;
        status = Status.PREPARING;
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
