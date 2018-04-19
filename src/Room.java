import java.util.Date;

/**
 * Represents the Room.
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public abstract class Room {

	public enum RoomStatus {
		VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE
	}
	public enum BedType {
		SINGLE_SIZE, DOUBLE_SIZE, KING_SIZE
	}

	/**The room number this guest is located to*/
	private String roomId;
	
	/**The identity card number of this guest*/
	private String guestIC;

	/**The name of this guest*/
	private String customerName;
	
	/**The date when this guest check in*/
	private Date checkInDate;

	/**The date when this guest check out*/
	private Date checkOutDate;
	
	/**The total number of people staying in the room*/
	private String pax;
	
	/**The current status of the room*/
	private RoomStatus roomStatus;
	
	/**The type of bed in the room*/
	private BedType bedType;

	/**
	 * This is an abstract method which the subclass had to implement
	 * @return The subclass will have to return true or false.
	 */
	abstract boolean isWifiEnabled();
	
	/**
	 *  Room's Constructor
	 * 
	 * @param roomId The room number that the guest is staying
	 * @param customerName The name of guest staying in this room
	 * @param bedType The type of bed this room has
	 * @param checkInDate When this guest is checking in
	 * @param checkOutDate When this guest will be checking out
	 * @param pax The amount of people staying in this room
	 * @param roomStatus The current status of the room
	 * @param guestIC This guest's identity card number
	 */
	public Room(String roomId, String customerName, BedType bedType, Date checkInDate, 
			Date checkOutDate, String pax, RoomStatus roomStatus, String guestIC){
		this.roomId = roomId;
		this.customerName = customerName;
		this.bedType = bedType;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.pax = pax;
		this.roomStatus = roomStatus;
		this.guestIC = guestIC;
	}

	/**
	 * Room's Constructor, used when we do not have the customer name.
	 * 
	 * @param roomId The room number that the guest is staying
	 * @param bedType The type of bed this room has
	 * @param roomStatus The current status of the room
	 */
	public Room(String roomId, BedType bedType, RoomStatus roomStatus) {
		this.roomId = roomId;
		this.customerName = "-";
		this.bedType = bedType;
		this.checkInDate = null;
		this.checkOutDate = null;
		this.pax = "-";
		this.roomStatus = roomStatus;
		this.guestIC = "-";
	}

	//Methods
	/**
	 * This method converts String to RoomStatus.
	 * @param status The Status given by user.
	 * @return The Status given by user in RoomStatus type.
	 */
	public static RoomStatus strToRoomStatus (String status) {
		RoomStatus roomStatus = Room.RoomStatus.VACANT;
		switch (status) {
		case "VACANT":				roomStatus = Room.RoomStatus.VACANT;
									break;
		case "OCCUPIED":			roomStatus = Room.RoomStatus.OCCUPIED;
									break;
		case "RESERVED":			roomStatus = Room.RoomStatus.RESERVED;
									break;
		case "UNDER_MAINTENANCE":	roomStatus = Room.RoomStatus.UNDER_MAINTENANCE;
									break;
		}
		return roomStatus;
	}

	/**
	 * This method converts String to BedType.
	 * @param type The type given by user.
	 * @return The type given by user in BedType type.
	 */
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

	/**
	 * Get the room number.
	 * @return The room number.
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * Set the room number.
	 * @param roomId This room's number.
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * Get the Guest IC.
	 * @return The Guest IC.
	 */
	public String getGuestIC() {
		return guestIC;
	}
	
	/**
	 * Set the Guest IC.
	 * @param guestIC This Guest IC 
	 */
	public void setGuestIC(String guestIC) {
		this.guestIC = guestIC;
	}

	/**
	 * Get the customer name.
	 * @return The Customer Name.
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Set the customer name.
	 * @param customerName This customer name.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	
	/**
	 * Get the Room Status of the room.
	 * @return The Room Status of the room.
	 */
	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	/**
	 * Set the Room Status of the room.
	 * @param roomStatus This Room Status.
	 */
	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	/**
	 * Get the Bed Type of the room.
	 * @return The Bed Type of the room.
	 */
	public BedType getBedType() {
		return bedType;
	}

	/**
	 * Set the Bed Type of the room.
	 * @param bedType This Bed Type.
	 */
	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}

}
