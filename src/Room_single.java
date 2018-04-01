
public class Room_single extends Room {
	
	//Room attributes
	final int rate = 100;
	final boolean wifiEnabled = true;
	final boolean withView = false;
	final boolean withSmoking = false;
	
	//Constructor
	public Room_single(String roomId, int customerName, String bedType, RoomStatus roomStatus) {
		super(roomId, customerName, bedType, roomStatus);
	}
	
	//Constructor overload, when room is vacant, there is no customer name
	public Room_single(String roomId, String bedType, RoomStatus roomStatus) {
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
