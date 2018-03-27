
public class Reservation {
		public enum ReservationStatus {
			CONFIRM, IN_WAITLIST, CHECK_IN, EXPIRED
		}
		
		//Attributes
		private int reservationId;
		private int customerName;
		private ReservationStatus reserveStatus;
    	private String checkInDate;
		
		//Methods
		public Reservation(int reservationId, int customerName, String checkInDate){
			this.reservationId = reservationId;
			this.customerName = customerName;
			this.checkInDate = checkInDate;
		}
		
		//Getter and setters
		public int getReservationId() {	
			return reservationId;
		}
		
		public int getCustomerName() {
			return customerName;
		}
		
		public ReservationStatus getReservationStatus() {
			return reserveStatus;
		}

		public void setRoomId(int reservationId) {
			this.reservationId = reservationId;
		}

		public void setCustomerName(int customerName) {
			this.customerName = customerName;
		}

		public void setReservationStatus(ReservationStatus reserveStatus) {
			this.reserveStatus = reserveStatus;
		}	
}
