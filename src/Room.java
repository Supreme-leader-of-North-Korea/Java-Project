
public abstract class Room {
	
		public enum RoomStatus {
			VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE
		}
		
		//Attributes
		private String roomId;
		private int customerName;
		private String bedType;
		private RoomStatus roomStatus;
		
		//Constructor
		public Room(String roomId, int customerName, String bedType, RoomStatus roomStatus){
			this.roomId = roomId;
			this.customerName = customerName;
			this.bedType = bedType;
			this.roomStatus = roomStatus;
		}
		
		//Constructor overload, when room is vacant, there is no customer name
		public Room(String roomId, String bedType, RoomStatus roomStatus) {
			this.roomId = roomId;
			this.customerName = 0;
			this.bedType = bedType;
			this.roomStatus = roomStatus;
		}
		
		//Methods
		public static RoomStatus strToRoomStatus (String status) {
			RoomStatus roomStatus = Room.RoomStatus.VACANT;
			
			switch (status) {
				case "VACANT": roomStatus = Room.RoomStatus.VACANT;
							   break;
				case "OCCUPIED": roomStatus = Room.RoomStatus.OCCUPIED;
				 				 break;
				case "RESERVED": roomStatus = Room.RoomStatus.RESERVED;
				 				 break;
				case "UNDER_MAINTENANCE": roomStatus = Room.RoomStatus.UNDER_MAINTENANCE;
				 						  break;
			}
			
			return roomStatus;
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
