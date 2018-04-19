import java.util.Date;
/**
 * Represents a Reservation added to the System.  
 * A Guest can make 0 to multiple Reservations.
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class Reservation {
	
	/** The list of Status for Reservation. */
	public enum ReservationStatus {
		CONFIRMED, IN_WAITLIST, CHECKED_IN, EXPIRED
	}

	/** The ID of the reservation. */
	private int reservationId;

	/** The full name of this guest. */
	private String guestName;

	/** The ID of the reserved room. */
	private String roomId;

	/** The credit card number of this guest. */
	private String creditCard;

	/** The identity card number of this guest based on the type of identity the guest holds. */
	private String guestIC;

	/** The number of people staying. */
	private String pax;

	/** The Status of the Reservation */
	private ReservationStatus reserveStatus;

	/** The check in date */
	private Date checkInDate;

	/** The check out date */
	private Date checkOutDate;

	/**
	 * Reservation Constructor.
	 * 
	 * @param reservationId This Reservation ID.
	 * @param roomId This room ID.
	 * @param guestName This Guest's name.
	 * @param creditCard This Guest's credit card detail
	 * @param checkInDate This Guest's intended check in date.
	 * @param checkOutDate This Guest's intended check out date.
	 * @param pax The number of people staying in the Guest's room.
	 * @param reserveStatus The Reservation Status of the Guest's reservation.
	 * @param guestIC The Guest's Identity Card number.
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
	 * This method converts String input to ReservationStatus.
	 * 
	 * @param status The Status given by the user.
	 * @return The Status given by the user in ReservationStatus type.
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
	 * Get the Reservation ID of the reservation made by this Guest.
	 * @return the Reservation ID of the reservation made by this Guest.
	 */
	public int getReservationId() {
		return reservationId;
	}

	/**
	 * Set the Reservation ID of the reservation made by this Guest.
	 * @param reservationId the Reservation ID of the reservation made by this Guest.
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	/**
	 * Get the name of the Guest.
	 * @return the name of the Guest.
	 */
	public String getGuestName() {
		return guestName;
	}

	/**
	 * Set the name of the Guest.
	 * @param guestName the name of the Guest.
	 */
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	/**
	 * Get the room number which is reserved by the guest.
	 * @return the room number which is reserved by the guest.
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * Set the room number which the guest reserved.
	 * @param roomId the room number which the guest wants to reserved.
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * Get the credit card number of this Guest.
	 * @return This Guest's credit card number.
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * Set the credit card number of this Guest.
	 * @param creditCard This Guest's credit card number.
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * Get the Identity Card number of this Guest.
	 * @return The Identity Card number of this Guest.
	 */
	public String getGuestIC() {
		return guestIC;
	}

	/**
	 * Set the Identity Card number of this Guest.
	 * @param guestIC The Identity Card number of this Guest.
	 */
	public void setGuestIC(String guestIC) {
		this.guestIC = guestIC;
	}

	/**
	 * Get the Reservation Status of the Reservation.
	 * @return The Reservation Status of the Reservation.
	 */
	public ReservationStatus getReserveStatus() {
		return reserveStatus;
	}

	/**
	 * Set the Reservation Status of the Reservation.
	 * @param reserveStatus The Reservation Status of the Reservation.
	 */
	public void setReserveStatus(ReservationStatus reserveStatus) {
		this.reserveStatus = reserveStatus;
	}

	/**
	 * Get the date which the Guest is checking in.
	 * @return the date which the Guest is checking in.
	 */
	public Date getCheckInDate() {
		return checkInDate;
	}

	/**
	 * Set the date which the Guest is checking in.
	 * @param checkInDate the date which the Guest is checking in.
	 */
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	/**
	 * Get the date which the Guest is checking out.
	 * @return the date which the Guest is checking out
	 */
	public Date getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * Set the date which the Guest is checking out.
	 * @param checkOutDate the date which the Guest is checking out.
	 */
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	/**
	 * Get the number of people staying in the room.
	 * @return The number of people staying in the room.
	 */
	public String getPax() {
		return pax;
	}

	/**
	 * Set the number of people staying in the room.
	 * @param pax The number of people staying in the room. 
	 */
	public void setPax(String pax) {
		this.pax = pax;
	}

}
