
public class Room_double extends Room{

	//Room attributes
	final int rate = 200;
	final boolean wifiEnabled = true;
	final boolean withView = false;
	final boolean withSmoking = false;
	
	//Constructor
	public Room_double(String roomId, String customerName, String bedType, String checkIn, String checkOut, String pax, RoomStatus roomStatus) {
		super(roomId, customerName, bedType, checkIn, checkOut, pax, roomStatus);
	}
	
	//Constructor overload, when room is vacant, there is no customer name
	public Room_double(String roomId, String bedType, RoomStatus roomStatus) {
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
