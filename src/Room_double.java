
public class Room_double extends Room{

	//Room attributes
	private double rate;
	private boolean wifiEnabled, withView, withSmoking;
	
	//Constructor
	public Room_double(String roomId, String customerName, BedType bedType, String checkIn, String checkOut, String pax, RoomStatus roomStatus, String guestIC) {
		super(roomId, customerName, bedType, checkIn, checkOut, pax, roomStatus, guestIC);
	}
	
	//Constructor overload, when room is vacant, there is no customer name
	public Room_double(String roomId, BedType bedType, RoomStatus roomStatus) {
		super(roomId, bedType, roomStatus);
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public boolean isWifiEnabled() {
		return wifiEnabled;
	}

	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}

	public boolean isWithView() {
		return withView;
	}

	public void setWithView(boolean withView) {
		this.withView = withView;
	}

	public boolean isWithSmoking() {
		return withSmoking;
	}

	public void setWithSmoking(boolean withSmoking) {
		this.withSmoking = withSmoking;
	}
	
	//Getter and setters
	
}
