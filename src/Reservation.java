
public class Reservation {
  public enum ReservationStatus {
			CONFIRM, IN_WAITLIST, CHECK_IN, EXPIRED
				}
		
		//Attributes
		private int roomId;
		private int customerName;
		private RoomStatus roomStatus;
    		private int checkInDate;
		
		//Methods
		public Reservation(int roomId, int customerName, String checkInDate){
			this.roomId = roomId;
			this.customerName = customerName;
			this.checkInDate = checkInDate;
		}
		
		//Getter and setters
		public int getRoomId() {	
			return roomId;
		}
		
		public int getCustomerName() {
			return customerName;
		}
		
		public RoomStatus getRoomStatus() {
			return roomStatus;
		}

		public void setRoomId(int roomId) {
			this.roomId = roomId;
		}

		public void setCustomerName(int customerName) {
			this.customerName = customerName;
		}

		public void setRoomStatus(RoomStatus roomStatus) {
			this.roomStatus = roomStatus;
		}	
}
