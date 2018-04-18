import java.util.Date;

/**
 * 
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu,Kok Jia Hui
 * @version 1.0
 */
public abstract class Room {

	public enum RoomStatus {
		VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE
	}
	public enum BedType {
		SINGLE_SIZE, DOUBLE_SIZE, KING_SIZE
	}

	//Attributes
	/**
	 * 
	 */
	private String roomId, guestIC;
	private String customerName;
	private Date checkInDate, checkOutDate;
	private String pax;
	private RoomStatus roomStatus;
	private BedType bedType;

	//abstract attribute
	/**
	 * 
	 * @return
	 */
	abstract boolean isWifiEnabled();
	
	//Constructor
	/**
	 * 
	 * @param roomId
	 * @param customerName
	 * @param bedType
	 * @param checkInDate
	 * @param checkOutDate
	 * @param pax
	 * @param roomStatus
	 * @param guestIC
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

	//Constructor overload, when room is vacant, there is no customer name
	/**
	 * 
	 * @param roomId
	 * @param bedType
	 * @param roomStatus
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
	 * 
	 * @param status
	 * @return
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
	 * 
	 * @param type
	 * @return
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
	 * Get the Room number.
	 * @return The room number.
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * Set the Room Number.
	 * @param roomId This room's number.
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
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
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	
	/**
	 * 
	 * @return
	 */
	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	/**
	 * 
	 * @param roomStatus
	 */
	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	/**
	 * 
	 * @return
	 */
	public BedType getBedType() {
		return bedType;
	}

	/**
	 * 
	 * @param bedType
	 */
	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}

}
