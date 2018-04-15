import java.util.Date;

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
	private Date checkInDate, checkOutDate;
	private String pax;
	private RoomStatus roomStatus;
	private BedType bedType;

	//abstract attribute
	abstract boolean isWifiEnabled();
	
	//Constructor
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
	public Room(String roomId, BedType bedType, RoomStatus roomStatus) {
		this.roomId = roomId;
		this.customerName = "-";
		this.bedType = bedType;
		this.checkInDate = null;
		this.checkOutDate = null;
		this.pax = "-";
		this.roomStatus = roomStatus;
	}

	//Methods
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

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getGuestIC() {
		return guestIC;
	}

	public void setGuestIC(String guestIC) {
		this.guestIC = guestIC;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public BedType getBedType() {
		return bedType;
	}

	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}


}
