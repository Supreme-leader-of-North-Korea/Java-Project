
public abstract class Room {
	
		public enum RoomStatus {
			VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE
		}
		
		//Attributes
		private String roomId;
		private int customerName;
		private String bedType;
		private RoomStatus roomStatus;
		
		//Methods
		public Room(String roomId, int customerName, String bedType){
			this.roomId = roomId;
			this.customerName = customerName;
			this.bedType = bedType;
			roomStatus = RoomStatus.VACANT;
		}
		
		//Getter and setters
		public String getRoomId() {	
			return roomId;
		}
		
		public int getCustomerName() {
			return customerName;
		}
		
		public String getBedType() {
			return bedType;
		}
		
		public RoomStatus getRoomStatus() {
			return roomStatus;
		}

		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}

		public void setCustomerName(int customerName) {
			this.customerName = customerName;
		}

		public void setBedType(String bedType) {
			this.bedType = bedType;
		}

		public void setRoomStatus(RoomStatus roomStatus) {
			this.roomStatus = roomStatus;
		}	
}
