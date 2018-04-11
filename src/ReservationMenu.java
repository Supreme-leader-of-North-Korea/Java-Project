import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
			choice = Menu.readInt(" Please enter your choice: ");
	            
	            switch(choice) {
	                case 1: makeReservation(guestList,roomList,reservationList);
	                        refio.exportAll(reservationList);
	                        rfio.exportAll(roomList);
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
	             String identifier = Menu.readString("Please enter the guest IC Number: ");
	             boolean found = false;
	                int index = 0;
	                int roomIndex = 0;
			for (Guest g: guestList) {
				if (identifier.equals(g.getIC())) {
					found = true;
					break;
				}
				index++;
			}
			
			if (!found) {
				System.out.println("Guest with IC: " + identifier + " not found!");
			} else {
				System.out.println("Guest with IC: " + identifier + " found!");
				
				String roomID = Menu.readString("Please enter the room ID: ");
	                        String checkIn = Menu.readString("Please enter the check in date [DD/MM/YYYY]: ");
	                        String checkOut = Menu.readString("Please enter the check out date [DD/MM/YYYY]: ");
	                        String pax = Menu.readString("Please enter the number of pax staying: ");
				
	                        for (Room r: roomList) {
	                            if (roomID.equals(r.getRoomId())) {
					found = true;
					break;
				}
	                            roomIndex++;
	                        }
	                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                        Date date = new Date();
	                        roomList.get(roomIndex).setCustomerName(guestList.get(index).getName());
	                        roomList.get(roomIndex).setCheckIn(checkIn);
	                        roomList.get(roomIndex).setCheckOut(checkOut);
	                        roomList.get(roomIndex).setPax(pax);
	                        roomList.get(roomIndex).setRoomStatus(Room.RoomStatus.RESERVED);
	                        Random random = new Random( System.currentTimeMillis() );
	                        int id = 10000+random.nextInt(20000);
	                        Reservation reservation = new Reservation(id, roomID, guestList.get(index).getName(), guestList.get(index).getCreditDetails(), checkIn, checkOut, pax, Reservation.ReservationStatus.CONFIRMED);
	                        reservationList.add(reservation);
							System.out.println("Room with room ID: " + roomID + " reserved!");
							System.out.println("Reservation ID: " + id);
	                        System.out.println("Please present this ID during check in !");
				
			
			}
	        }
}
