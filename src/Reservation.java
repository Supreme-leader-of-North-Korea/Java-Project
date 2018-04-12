import java.util.Date;

public class Reservation {
		public enum ReservationStatus {
			CONFIRMED, IN_WAITLIST, CHECKED_IN, EXPIRED
		}
		
		//Attributes
		private int reservationId;
		private String guestName, roomId, creditCard, guestIC, pax;
		private ReservationStatus reserveStatus;
        private Date checkInDate, checkOutDate;
		
		//Methods
		public Reservation(int reservationId, String roomId, String guestName, String creditCard, 
				Date checkInDate, Date checkOutDate, String pax, ReservationStatus reserveStatus, String guestIC){
			this.reservationId = reservationId;
            this.roomId = roomId;
			this.guestName = guestName;
            this.creditCard = creditCard;
			this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
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

		public String getGuestName() {
			return guestName;
		}

		public void setGuestName(String guestName) {
			this.guestName = guestName;
		}

		public String getRoomId() {
			return roomId;
		}

		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}

		public String getCreditCard() {
			return creditCard;
		}

		public void setCreditCard(String creditCard) {
			this.creditCard = creditCard;
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

		public Date getCheckInDate() {
			return checkInDate;
		}

		public void setCheckInDate(Date checkInDate) {
			this.checkInDate = checkInDate;
		}

		public Date getCheckOutDate() {
			return checkOutDate;
		}

		public void setCheckOutDate(Date checkOutDate) {
			this.checkOutDate = checkOutDate;
		}

		public String getPax() {
			return pax;
		}

		public void setPax(String pax) {
			this.pax = pax;
		}
        
}
