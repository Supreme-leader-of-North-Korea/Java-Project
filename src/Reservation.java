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
	/**
	 * 
	 * @param reservationId
	 * @param roomId
	 * @param guestName
	 * @param creditCard
	 * @param checkInDate
	 * @param checkOutDate
	 * @param pax
	 * @param reserveStatus
	 * @param guestIC
	 */
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

	/**
	 * 
	 * @param status
	 * @return
	 */
	public static Reservation.ReservationStatus strToReservationStatus (String status) {
		Reservation.ReservationStatus reservationStatus = Reservation.ReservationStatus.IN_WAITLIST;

		switch (status) {
		case "CONFIRMED":	reservationStatus = Reservation.ReservationStatus.CONFIRMED;
							break;
		case "IN_WAITLIST":	reservationStatus = Reservation.ReservationStatus.IN_WAITLIST;
							break;
		case "CHECKED_IN":	reservationStatus = Reservation.ReservationStatus.CHECKED_IN;
							break;
		case "EXPIRED":		reservationStatus = Reservation.ReservationStatus.EXPIRED;
							break;
		}
		return reservationStatus;
	}

	/**
	 * 
	 * @return
	 */
	public int getReservationId() {
		return reservationId;
	}

	/**
	 * 
	 * @param reservationId
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	/**
	 * 
	 * @return
	 */
	public String getGuestName() {
		return guestName;
	}

	/**
	 * 
	 * @param guestName
	 */
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	/**
	 * 
	 * @return
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * 
	 * @param roomId
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * 
	 * @return
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * 
	 * @param creditCard
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * 
	 * @return
	 */
	public String getGuestIC() {
		return guestIC;
	}

	/**
	 * 
	 * @param guestIC
	 */
	public void setGuestIC(String guestIC) {
		this.guestIC = guestIC;
	}

	/**
	 * 
	 * @return
	 */
	public ReservationStatus getReserveStatus() {
		return reserveStatus;
	}

	/**
	 * 
	 * @param reserveStatus
	 */
	public void setReserveStatus(ReservationStatus reserveStatus) {
		this.reserveStatus = reserveStatus;
	}

	/**
	 * 
	 * @return
	 */
	public Date getCheckInDate() {
		return checkInDate;
	}

	/**
	 * 
	 * @param checkInDate
	 */
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	/**
	 * 
	 * @return
	 */
	public Date getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * 
	 * @param checkOutDate
	 */
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getPax() {
		return pax;
	}

	/**
	 * 
	 * @param pax
	 */
	public void setPax(String pax) {
		this.pax = pax;
	}

}
