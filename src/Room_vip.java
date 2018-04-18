import java.util.Date;

public class Room_vip extends Room{

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
	public Room_vip(String roomId, String customerName, BedType bedType, Date checkIn, Date checkOut, String pax, RoomStatus roomStatus, String guestIC) {
		super(roomId, customerName, bedType, checkIn, checkOut, pax, roomStatus, guestIC);
	}

	//Constructor overload, when room is vacant, there is no customer name
	/**
	 * 
	 * @param roomId
	 * @param bedType
	 * @param roomStatus
	 */
	public Room_vip(String roomId, BedType bedType, RoomStatus roomStatus) {
		super(roomId, bedType, roomStatus);
	}

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
	 * @param wifiEnabled
	 */
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
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
	 * @param withSmoking
	 */
	public void setWithSmoking(boolean withSmoking) {
		this.withSmoking = withSmoking;
	}

	/**
	 * 
	 */
	public boolean isWifiEnabled() {
		return wifiEnabled;
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
	 * @return
	 */
	public boolean isWithSmoking() {
		return withSmoking;
	}
}
