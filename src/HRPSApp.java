import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HRPSApp {
	
	public static void main(String[] args) throws FileNotFoundException{

		//List of data
		ArrayList<Guest>guestList = new ArrayList<Guest>();
		ArrayList<Room>roomList = new ArrayList<Room>();
		ArrayList<MenuItem>menuList = new ArrayList<MenuItem>();
		ArrayList<RoomService>serviceList = new ArrayList<RoomService>();
		ArrayList<Payment>paymentList = new ArrayList<Payment>();
		ArrayList<Reservation>reservationList = new ArrayList<Reservation>();         

		//Parse and populate lists from data files
		GuestFileIO gfio = new GuestFileIO();
		ServiceFileIO sfio = new ServiceFileIO();
		MenuFileIO mfio = new MenuFileIO();
		RoomFileIO rfio = new RoomFileIO();
		PaymentFileIO pfio = new PaymentFileIO();
		ReservationFileIO refio = new ReservationFileIO();

		rfio.parseList(roomList);
		gfio.parseList(guestList);
		sfio.parseList(serviceList);
		mfio.parseList(menuList);
		pfio.parseList(paymentList);
		refio.parseList(reservationList);
		
		int choice = 0;

		//Select menu
		do {
			//Print main menu
			Menu.mainMenu();

			//Get user's choice
			System.out.println(" -------------------------------------------");
			choice = Menu.readInt(" Please enter your choice: ");

			switch(choice) {
			case 1: GuestMenu.guestMenu(guestList, roomList, reservationList);
					break;
			case 2: RoomMenu.roomMenu(guestList, roomList, reservationList, serviceList, paymentList, menuList);		
					break;
			case 3: ReservationMenu.reservationMenu(guestList, roomList, reservationList);	
					break;
			case 4: RoomServiceMenu.roomServiceMenu(serviceList, menuList, roomList);		
					break;
			case 5: PaymentMenu.paymentMenu(guestList, roomList, reservationList, serviceList, paymentList);		
					break;
			case 6: System.out.println("Exiting. Goodbye!");
					gfio.exportAll(guestList);
					rfio.exportAll(roomList);
					sfio.exportAll(serviceList);
					mfio.exportAll(menuList);
					break;
			default:System.out.println("Wrong Input. Please input from 1 - 6.");
					break;
			}
		} while (choice != 6);
	}
}