
public class Reservation {
		public enum ReservationStatus {
			CONFIRMED, IN_WAITLIST, CHECKED_IN, EXPIRED
		}
		
		//Attributes
		private int reservationId;
		private String customerName, roomId, creditCard;
		private ReservationStatus reserveStatus;
                private String checkInDate, checkOut, pax;
		
		//Methods
		public Reservation(int reservationId, String roomId, String customerName, String creditCard, String checkInDate, String checkOut, String pax, ReservationStatus reserveStatus){
			this.reservationId = reservationId;
                        this.roomId = roomId;
			this.customerName = customerName;
                        this.creditCard = creditCard;
			this.checkInDate = checkInDate;
                        this.checkOut = checkOut;
                        this.pax = pax;
                        this.reserveStatus = reserveStatus;
		}
		
                public static Reservation.ReservationStatus strToReservationStatus (String status) {
			Reservation.ReservationStatus reservationStatus = Reservation.ReservationStatus.IN_WAITLIST;
			
			switch (status) {
				case "CONFIRMED": reservationStatus = Reservation.ReservationStatus.CONFIRMED;
							   break;
				case "IN_WAITLIST": reservationStatus = Reservation.ReservationStatus.IN_WAITLIST;
				 				 break;
				case "CHECKED_IN": reservationStatus = Reservation.ReservationStatus.CHECKED_IN;
				 				 break;
				case "EXPIRED": reservationStatus = Reservation.ReservationStatus.EXPIRED;
				 						  break;
			}
			
			return reservationStatus;
		}
		//Getter and setters
		public int getReservationId() {	
			return reservationId;
		}
		
		public String getCustomerName() {
			return customerName;
		}
		public String getRoomId() {
			return roomId;
		}
                public String getCreditCard() {
			return creditCard;
		}
		public ReservationStatus getReservationStatus() {
			return reserveStatus;
		}
                public String getCheckIn() {
			return checkInDate;
		}
                public String getCheckOut() {
			return checkOut;
		}
                public String getPax() {
			return pax;
		}
                
                public void setReservationId(int reservationId) {
			this.reservationId = reservationId;
		}
		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
                public void setCreditCard(String credit) {
			this.creditCard = credit;
		}
		public void setReservationStatus(ReservationStatus reserveStatus) {
			this.reserveStatus = reserveStatus;
		}
                public void setCheckIn(String checkIn) {
			checkInDate = checkIn;
		}
                public void setCheckOut(String checkOut) {
			this.checkOut = checkOut;
		}
                public void setPax(String pax) {
			this.pax = pax;
		}
}
