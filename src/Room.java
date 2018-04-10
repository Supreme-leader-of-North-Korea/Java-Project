
public abstract class Room {
	
		public enum RoomStatus {
			VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE
		}
		
		//Attributes
		private String roomId;
		private String customerName;
		private String bedType, checkIn, checkOut, pax;
		private RoomStatus roomStatus;
		
		//Constructor
		public Room(String roomId, String customerName, String bedType, String checkIn, String checkOut, String pax, RoomStatus roomStatus){
			this.roomId = roomId;
			this.customerName = customerName;
			this.bedType = bedType;
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			this.pax = pax;
			this.roomStatus = roomStatus;
		}
		
		//Constructor overload, when room is vacant, there is no customer name
		public Room(String roomId, String bedType, RoomStatus roomStatus) {
			this.roomId = roomId;
			this.customerName = "-";
			this.bedType = bedType;
                        this.checkIn = "-";
                        this.checkOut = "-";
                        this.pax = "-";
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
		
		public String getCustomerName() {
			return customerName;
		}
		
		public String getBedType() {
			return bedType;
		}
		
		public RoomStatus getRoomStatus() {
			return roomStatus;
		}
                public String getCheckIn() {
                    return checkIn;
                }
                public String getCheckOut(){ 
                    return checkOut;
                }
                public String getPax(){
                    return pax;
                }
                
		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public void setBedType(String bedType) {
			this.bedType = bedType;
		}

		public void setRoomStatus(RoomStatus roomStatus) {
			this.roomStatus = roomStatus;
		}
                public void setCheckIn(String in) {
                    checkIn = in;
                }
                public void setCheckOut(String out) {
                    checkOut = out;
                }
                public void setPax(String pax) {
                    this.pax = pax;
                }
}
