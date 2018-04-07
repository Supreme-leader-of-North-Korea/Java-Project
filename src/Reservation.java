
public class Reservation {
		public enum ReservationStatus {
			CONFIRMED, IN_WAITLIST, CHECK_IN, EXPIRED
		}
		
		//Attributes
		private int reservationId;
		private String customerName;
		private ReservationStatus reserveStatus;
                private String checkInDate;
		
		//Methods
		public Reservation(int reservationId, String customerName, String checkInDate){
			this.reservationId = reservationId;
			this.customerName = customerName;
			this.checkInDate = checkInDate;
		}
		
		//Getter and setters
		public int getReservationId() {	
			return reservationId;
		}
		
		public String getCustomerName() {
			return customerName;
		}
		
		public ReservationStatus getReservationStatus() {
			return reserveStatus;
		}

		public void setRoomId(int reservationId) {
			this.reservationId = reservationId;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public void setReservationStatus(ReservationStatus reserveStatus) {
			this.reserveStatus = reserveStatus;
		}	
}
