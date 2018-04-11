
public class Room_vip extends Room{

	//Room attributes
	final int rate = 400;
	final boolean wifiEnabled = true;
	final boolean withView = true;
	final boolean withSmoking = true;
	
	//Constructor
	public Room_vip(String roomId, String customerName, BedType bedType, String checkIn, String checkOut, String pax, RoomStatus roomStatus, String guestIC) {
		super(roomId, customerName, bedType, checkIn, checkOut, pax, roomStatus, guestIC);
	}
	
	//Constructor overload, when room is vacant, there is no customer name
	public Room_vip(String roomId, BedType bedType, RoomStatus roomStatus) {
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
