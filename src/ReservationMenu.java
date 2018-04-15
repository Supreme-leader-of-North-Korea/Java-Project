import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ReservationMenu extends Menu {
	//Reservation Menu ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void reservationMenu(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) throws FileNotFoundException{

		int choice = 0;

		//Select menu
		do {
			//Print reservation menu
			printReservationMenu();
			RoomFileIO rfio = new RoomFileIO();
			ReservationFileIO refio = new ReservationFileIO();
			//Get user's choice
			System.out.println(" -------------------------------------------");
			choice = readInt(" Please enter your choice: ");

			switch(choice) {
			case 1: makeReservation(guestList,roomList,reservationList);
					refio.exportAll(reservationList);
					rfio.exportAll(roomList);
					break;
			case 2: searchReservation(reservationList);
					break;
			case 3: updateReservation(guestList, roomList, reservationList);
					break;
			case 4: printReservation(reservationList);
					break;
			case 5: System.out.println("Returning to main menu...");
					refio.exportAll(reservationList);
					rfio.exportAll(roomList);
					break;
			case 6: System.out.println("Exiting...");
					refio.exportAll(reservationList);
					rfio.exportAll(roomList);
					System.exit(0);
					break;				
			default:System.out.println("Wrong Input. Please input from 1 - 6.");
					break;
			}
		} while (choice != 5);

	}

	public static void printReservationMenu() {
		System.out.println(" ===========================================");
		System.out.println(" *               Reservation               *");
		System.out.println(" ===========================================");
		System.out.println(" * 1. Make Reservation                     *");
		System.out.println(" * 2. Check Reservation Details            *");
		System.out.println(" * 3. Update Reservation Details           *");
		System.out.println(" * 4. Print Reservation                    *");
		System.out.println(" * 5. Previous                             *");
		System.out.println(" * 6. Quit                                 *");
	}

	public static void makeReservation(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) throws FileNotFoundException{
		String identifier = readString("Please enter the guest IC Number: ");

		int gindex = guestICSearch(guestList, identifier);
		int resIndex = reservationICSearch(reservationList, identifier);
		
		if (gindex == -1) {
			System.out.println("Guest with IC: " + identifier + " not found!");
			System.out.println("Please create guest first");
			GuestMenu.createNewGuest(guestList);
		} else if (resIndex != -1) {
			System.out.println("Guest : " + guestList.get(gindex).getName() + " has already made a reservation!");
		} else {	
			System.out.println("Guest with IC: " + identifier + " found!");
			Date checkIn;
			Date checkOut;
			Date today = new Date();
			do {
				checkIn = readDate("Please enter the check in date [DD/MM/YYYY]: ");
				checkOut = readDate("Please enter the check out date [DD/MM/YYYY]: ");
			}while(checkIn.after(checkOut) || checkIn.before(today));
			
			//This method display all room types according to what the user choose 
			//and return the list of room id that are available
			String []roomIdarr = searchRoomType(roomList, checkIn, checkOut);

			String roomID;
			int roomIndex;	         
			boolean input = false;
			do {
				//check if there is such a room
				do {
					roomID = readNonEmptyString("Please enter the room ID: ");
					roomIndex = roomIDSearch(roomList,roomID);
				}while(roomIndex == -1);
				
				//check if this room is in the list of rooms that are available 
				for(int i = 0; roomIdarr.length >= i ;i++) {
					if (roomID.equals(roomIdarr[i])) {
						input = true;
						break;
					}
				}
			} while(!input);

			
			int paxInt = readInt("Please enter the number of pax staying: ");
			String pax = Integer.toString(paxInt);
			

			//setting room status 
			roomList.get(roomIndex).setCustomerName(guestList.get(gindex).getName());
			roomList.get(roomIndex).setCheckInDate(checkIn);
			roomList.get(roomIndex).setCheckOutDate(checkOut);
			roomList.get(roomIndex).setPax(pax);
			roomList.get(roomIndex).setRoomStatus(Room.RoomStatus.RESERVED);
			Random random = new Random( System.currentTimeMillis() );
			int reservationID = 10000+random.nextInt(20000);

			//adding reservation
			Reservation reservation = new Reservation(reservationID, roomID, guestList.get(gindex).getName(), 
					guestList.get(gindex).getCreditDetails(), checkIn, checkOut, pax, 
					Reservation.ReservationStatus.CONFIRMED,guestList.get(gindex).getIc());
			reservationList.add(reservation);

			System.out.println("Room with room ID: " + roomID + " reserved!");
			System.out.println("Reservation ID: " + reservationID);
			System.out.println("Please present this ID during check in !");					
		}
	}	        

	public static void searchReservation(ArrayList<Reservation>reservationList) {
		int identifier = readInt("Please enter the reservation ID you would like to search: ");
		int index = reservationSearch(reservationList, identifier);

		if (index == -1) {
			System.out.println("Reservation ID: " + identifier + " not found!");
		} else {
			System.out.println("Reservation ID: " + identifier + " found!");

			System.out.println(" -------------------------------------------");
			System.out.println("Reservation ID: " + reservationList.get(index).getReservationId() + 
					"\nCustomer Name: " + reservationList.get(index).getGuestName() + 
					"\nRoom ID: " + reservationList.get(index).getRoomId() + 
					"\nCredit Card Information: " + reservationList.get(index).getCreditCard() + 
					"\nCheck In Date: " + reservationList.get(index).getCheckInDate() + 
					"\nCheck Out Date: " + reservationList.get(index).getCheckOutDate() +
					"\nNumber of pax(s) staying: " + reservationList.get(index).getPax() +
					"\nReservation Status: " + reservationList.get(index).getReserveStatus());
			System.out.println(" -------------------------------------------");

		}
	}

	public static void updateReservation(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) {
		int identifier = readInt("Please enter the reservation ID you would like to update: ");
		int roomIndex = 0;
		int index = reservationSearch(reservationList, identifier);

		if (index == -1) {
			System.out.println("Reservation ID: " + identifier + " not found!");
		} else {
			System.out.println("Reservation ID: " + identifier + " found!");

			System.out.println(" -------------------------------------------");
			System.out.println("Please enter new reservation details ('Enter' key to skip)");

			String name = readString("Enter new guest name: ");
			String IC;
			boolean found=false;
			roomIndex = roomIDSearch(roomList, reservationList.get(index).getRoomId());
			if (!name.equals("")) {
				IC = guestNameSearch(guestList, name);
				if(IC != null) { 
					found = true;
				}
			} else {
				name = reservationList.get(index).getGuestName();
				IC = guestNameSearch(guestList, name);
				if (IC != null) {
					found = true;
				}           
			}
			if (found) {
				roomList.get(roomIndex).setCustomerName(name);
				Date in = readDate("Enter new Check In Date: ");
				if (in != null) {
					reservationList.get(index).setCheckInDate(in);
					roomList.get(roomIndex).setCheckInDate(in);
				}
				Date out = readDate("Enter new Check Out Date: ");
				if (out != null) {
					reservationList.get(index).setCheckOutDate(out);
					roomList.get(roomIndex).setCheckOutDate(out);
				}
				int paxInt = readInt("Enter new number of pax staying: ");
				String pax = Integer.toString(paxInt);
				if (!pax.equals("")) {
					reservationList.get(index).setPax(pax);
					roomList.get(roomIndex).setPax(pax);
				}
				System.out.println(" -------------------------------------------");
				System.out.println(" Reservation updated!");
			}
		}
	}

	public static void printReservation(ArrayList<Reservation>reservationList) {
		int index = 0;
		if(!reservationList.get(index).getReserveStatus().equals(Reservation.ReservationStatus.EXPIRED)) {
			System.out.println("Guest Name: " + reservationList.get(index).getGuestName() + 
					"\nRoom ID: " + reservationList.get(index).getRoomId() + 
					"\nCredit Card: " + reservationList.get(index).getCreditCard() + 
					"\nCheck In Date: " + reservationList.get(index).getCheckInDate() + 
					"\nCheck Out Date: " + reservationList.get(index).getCheckOutDate() + 
					"\nPax:" + reservationList.get(index).getPax() +
					"\nReservation Status: " + reservationList.get(index).getReserveStatus() +
					"\nGuest IC: " + reservationList.get(index).getGuestIC() +
					"\nReservation ID: " + reservationList.get(index).getReserveStatus());
			System.out.println(" -------------------------------------------");	
		}
	}
}
