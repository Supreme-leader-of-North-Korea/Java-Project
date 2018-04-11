
public class Room_single extends Room {
	
	//Room attributes
	final int rate = 100;
	final boolean wifiEnabled = true;
	final boolean withView = false;
	final boolean withSmoking = false;
	
	//Constructor
	public Room_single(String roomId, String customerName, BedType bedType, String checkIn, String checkOut, String pax, RoomStatus roomStatus, String guestIC) {
		super(roomId, customerName, bedType, checkIn, checkOut, pax, roomStatus, guestIC);
	}
	
	//Constructor overload, when room is vacant, there is no customer name
	public Room_single(String roomId, BedType bedType, RoomStatus roomStatus) {
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
