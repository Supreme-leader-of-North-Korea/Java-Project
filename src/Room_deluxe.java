
public class Room_deluxe extends Room{

	//Room attributes
	final int rate = 300;
	final boolean wifiEnabled = true;
	final boolean withView = true;
	final boolean withSmoking = false;
	
	//Constructor
	public Room_deluxe(String roomId, int customerName, String bedType, RoomStatus roomStatus) {
		super(roomId, customerName, bedType, roomStatus);
	}
	
	//Constructor overload, when room is vacant, there is no customer name
	public Room_deluxe(String roomId, String bedType, RoomStatus roomStatus) {
		super(roomId, bedType, roomStatus);
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
