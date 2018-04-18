import java.util.Date;

public class Room_deluxe extends Room{

	//Room attributes
	private double rate;
	private boolean wifiEnabled, withView, withSmoking;

	//Constructor
	/**
	 * 
	 * @param roomId
	 * @param customerName
	 * @param bedType
	 * @param checkIn
	 * @param checkOut
	 * @param pax
	 * @param roomStatus
	 * @param guestIC
	 */
	public Room_deluxe(String roomId, String customerName, BedType bedType, Date checkIn, Date checkOut, String pax, RoomStatus roomStatus, String guestIC) {
		super(roomId, customerName, bedType, checkIn, checkOut, pax, roomStatus, guestIC);
	}

	//Constructor overload, when room is vacant, there is no customer name
	/**
	 * 
	 * @param roomId
	 * @param bedType
	 * @param roomStatus
	 */
	public Room_deluxe(String roomId, BedType bedType, RoomStatus roomStatus) {
		super(roomId, bedType, roomStatus);
	}

	//Getter and setters
	/**
	 * 
	 * @return
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * 
	 * @param rate
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * 
	 */
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}

	/**
	 * 
	 * @param wifiEnabled
	 */
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isWithView() {
		return withView;
	}

	/**
	 * 
	 * @param withView
	 */
	public void setWithView(boolean withView) {
		this.withView = withView;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isWithSmoking() {
		return withSmoking;
	}

	/**
	 * 
	 * @param withSmoking
	 */
	public void setWithSmoking(boolean withSmoking) {
		this.withSmoking = withSmoking;
	}
}

