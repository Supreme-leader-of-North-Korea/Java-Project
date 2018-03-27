
public class Room_single extends Room {
	
	//Room attributes
	final int rate = 100;
	final boolean wifiEnabled = true;
	final boolean withView = false;
	final boolean withSmoking = false;
	
	//Constructor
	public Room_single(int roomId, int customerName, String bedType) {
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
