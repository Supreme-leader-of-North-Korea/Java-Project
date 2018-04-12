
public class Reservation {
		public enum ReservationStatus {
			CONFIRMED, IN_WAITLIST, CHECKED_IN, EXPIRED
		}
		
		//Attributes
		private int reservationId;
		private String guestName, roomId, creditCard, guestIC;
		private ReservationStatus reserveStatus;
        private String checkInDate, checkOut, pax;
		
		//Methods
		public Reservation(int reservationId, String roomId, String guestName, String creditCard, 
				String checkInDate, String checkOut, String pax, ReservationStatus reserveStatus, String guestIC){
			this.reservationId = reservationId;
            this.roomId = roomId;
			this.guestName = guestName;
            this.creditCard = creditCard;
			this.checkInDate = checkInDate;
            this.checkOut = checkOut;
            this.pax = pax;
            this.reserveStatus = reserveStatus;
            this.guestIC = guestIC;
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
		
        public void setReservationId(int reservationId) {
			this.reservationId = reservationId;
		}
        
        public String getRoomId() {
			return roomId;
		}

		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}
		
		public String getGuestName() {
			return guestName;
		}
		
		public void setGuestName(String guestName) {
			this.guestName = guestName;
		}
		
		public String getCreditCard() {
			return creditCard;
		}

		public void setCreditCard(String credit) {
			this.creditCard = credit;
		}

        public String getCheckIn() {
			return checkInDate;
		}
        
        public void setCheckIn(String checkIn) {
			checkInDate = checkIn;
		}
        
        public String getCheckOut() {
			return checkOut;
		}
        
        public void setCheckOut(String checkOut) {
			this.checkOut = checkOut;
		}
       
        public String getPax() {
        	return pax;
        }

        public void setPax(String pax) {
        	this.pax = pax;
        }
		
		public ReservationStatus getReservationStatus() {
			return reserveStatus;
		}
		
		public void setReservationStatus(ReservationStatus reserveStatus) {
			this.reserveStatus = reserveStatus;
		}

		public String getGuestIC() {
			return guestIC;
		}

		public void setGuestIC(String guestIC) {
			this.guestIC = guestIC;
		}

		public ReservationStatus getReserveStatus() {
			return reserveStatus;
		}

		public void setReserveStatus(ReservationStatus reserveStatus) {
			this.reserveStatus = reserveStatus;
		}

		public String getCheckInDate() {
			return checkInDate;
		}

		public void setCheckInDate(String checkInDate) {
			this.checkInDate = checkInDate;
		}
}
