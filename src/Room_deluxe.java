
public class Room_deluxe extends Room{

	//Room attributes
	final int rate = 300;
	final boolean wifiEnabled = true;
	final boolean withView = true;
	final boolean withSmoking = false;
	
	//Constructor
	public Room_deluxe(int roomId, int customerName, String bedType) {
		super(roomId, customerName, bedType);
	}

	//Getter and setters
	public int getRate() {
		return rate;
	}
	
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}
	
	public boolean isWithView() {
		return withView;
	}

	public boolean isWithSmoking() {
		return withSmoking;
	}
}
