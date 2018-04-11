
public abstract class Room {
	
		public enum RoomStatus {
			VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE
		}
		public enum BedType {
			SINGLE_SIZE, DOUBLE_SIZE, KING_SIZE
		}
		
		//Attributes
		private String roomId, guestIC;
		private String customerName;
		private String checkIn, checkOut, pax;
		private RoomStatus roomStatus;
		private BedType bedType;
		
		//Constructor
		public Room(String roomId, String customerName, BedType bedType, String checkIn, 
				String checkOut, String pax, RoomStatus roomStatus, String guestIC){
			this.roomId = roomId;
			this.customerName = customerName;
			this.bedType = bedType;
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			this.pax = pax;
			this.roomStatus = roomStatus;
			this.guestIC = guestIC;
		}
		
		//Constructor overload, when room is vacant, there is no customer name
		public Room(String roomId, BedType bedType, RoomStatus roomStatus) {
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
				case "VACANT": 			  roomStatus = Room.RoomStatus.VACANT;
										  break;
				case "OCCUPIED": 		  roomStatus = Room.RoomStatus.OCCUPIED;
				 				 		  break;
				case "RESERVED": 		  roomStatus = Room.RoomStatus.RESERVED;
				 				 		  break;
				case "UNDER_MAINTENANCE": roomStatus = Room.RoomStatus.UNDER_MAINTENANCE;
				 						  break;
			}
			
			return roomStatus;
		}
		
		public static BedType strToBedType (String type) {
			BedType bedType = Room.BedType.SINGLE_SIZE;
			
			switch (type) {
				case "SINGLE_SIZE": bedType = Room.BedType.SINGLE_SIZE;
									break;
				case "DOUBLE_SIZE": bedType = Room.BedType.DOUBLE_SIZE;
				 				 	break;
				case "KING_SIZE": 	bedType = Room.BedType.KING_SIZE;
				 				 	break;
			}
			
			return bedType;
		}
		
		//Getter and setters
		public String getRoomId() {	
			return roomId;
		}
		
		public String getCustomerName() {
			return customerName;
		}
		
		public BedType getBedType() {
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

				public String getGuestIC() {
					return guestIC;
				}
                
		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public void setBedType(BedType bedType) {
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
               
      public void setGuestIC(String guestIC) {
    	  this.guestIC = guestIC;
      }
                
}
