
public class Room {
	//attributes
		private int roomId;
		private boolean assigned;
		
		//methods
		
		public Room(int room_Id){
			roomId = room_Id;
		}
		public int getRoomID() {	
			return roomId;
		}
		 public int getCustomerName(){
			 return customerName;
		 }
		 public boolean isOccupied() {
			 return assigned;
		 }
		 public void assign(int cust_name) {
			 customerName = cust_name;
			 assigned = true;
		 }
		 public void unAssign() {
			 assigned = false;
		 }

}
