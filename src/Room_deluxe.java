import java.util.Date;
/**
 * Represents the Room: Deluxe.
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu,Kok Jia Hui
 * @version 1.0
 */
public class Room_deluxe extends Room{


	/**	The rate charged for Deluxe Room. */
	private double rate;
	
	/**	If the room enables Wifi. */
	private boolean wifiEnabled;
	
	/**	If the room has view. */
	private boolean withView;
	
	/**	If the room allows smoking. */
	private boolean withSmoking;
	
	/**
	 * Room_deluxe's Constructor
	 * 
	 * @param roomId - The room number that the guest is staying
	 * @param customerName - The name of guest staying in this room
	 * @param bedType - The type of bed this room has
	 * @param checkIn - When this guest is checking in
	 * @param checkOut - When this guest will be checking out
	 * @param pax - The amount of people staying in this room
	 * @param roomStatus - The current status of the room
	 * @param guestIC - This guest's identity card number
	 */
	public Room_deluxe(String roomId, String customerName, BedType bedType, Date checkIn, Date checkOut, String pax, RoomStatus roomStatus, String guestIC) {
		super(roomId, customerName, bedType, checkIn, checkOut, pax, roomStatus, guestIC);
	}

	/**
	 * Room_deluxe's Constructor
	 * When room is vacant, there is no customer name
	 * 
	 * @param roomId - The room number that the guest is staying
	 * @param bedType - The type of bed this room has
	 * @param roomStatus - The current status of the room
	 */
	public Room_deluxe(String roomId, BedType bedType, RoomStatus roomStatus) {
		super(roomId, bedType, roomStatus);
	}

	//Getter and setters
	/**
	 * Get the rate of the room.
	 * @return The rate of the room.
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Set the rate of the room.
	 * @param rate - The rate that user wants to set to.
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * Get boolean if the room has Wifi.
	 * @return true or false if Wifi is enabled.
	 */
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}

	/**
	 * Set true, if the room enables Wifi. Otherwise, false.
	 * @param wifiEnabled - true or false.
	 */
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}

	/**
	 * Get boolean if the room has view.
	 * @return true or false if the room has view.
	 */
	public boolean isWithView() {
		return withView;
	}

	/**
	 * Set true, if the room has view. Otherwise, false.
	 * @param withView - true or false.
	 */
	public void setWithView(boolean withView) {
		this.withView = withView;
	}

	/**
	 * Get boolean if the room allows smoking.
	 * @return true or false if the room allows smoking.
	 */
	public boolean isWithSmoking() {
		return withSmoking;
	}

	/**
	 * Set true, if the room allows smoking. Otherwise, false.
	 * @param withSmoking - true or false.
	 */
	public void setWithSmoking(boolean withSmoking) {
		this.withSmoking = withSmoking;
	}
}

