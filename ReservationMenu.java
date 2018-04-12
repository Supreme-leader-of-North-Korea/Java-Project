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

            int index = 0;
            int roomIndex = 0;	            
		
			index = guestICSearch(guestList, identifier);
			
			if (index == -1) {
				System.out.println("Guest with IC: " + identifier + " not found!");
			} else {
				System.out.println("Guest with IC: " + identifier + " found!");
				String roomID = Menu.readString("Please enter the room ID: ");
				Date checkIn = Menu.readDate("Please enter the check in date [DD/MM/YYYY]: ");
				Date checkOut = Menu.readDate("Please enter the check out date [DD/MM/YYYY]: ");
				String pax = Menu.readString("Please enter the number of pax staying: ");
	
				roomIndex = roomIDSearch(roomList,roomID);
               
				roomList.get(roomIndex).setCustomerName(guestList.get(index).getName());
				roomList.get(roomIndex).setCheckInDate(checkIn);
				roomList.get(roomIndex).setCheckOutDate(checkOut);
				roomList.get(roomIndex).setPax(pax);
				roomList.get(roomIndex).setRoomStatus(Room.RoomStatus.RESERVED);
				Random random = new Random( System.currentTimeMillis() );
				int id = 10000+random.nextInt(20000);
				Reservation reservation = new Reservation(id, roomID, guestList.get(index).getName(), 
               					guestList.get(index).getCreditDetails(), checkIn, checkOut, pax, 
               					Reservation.ReservationStatus.CONFIRMED,guestList.get(index).getIc());
				reservationList.add(reservation);
				System.out.println("Room with room ID: " + roomID + " reserved!");
				System.out.println("Reservation ID: " + id);
				System.out.println("Please present this ID during check in !");					
			}
       }	        
	
		public static void searchReservation(ArrayList<Reservation>reservationList) {
		int identifier = Menu.readInt("Please enter the reservation ID you would like to search: ");
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
			int identifier = Menu.readInt("Please enter the reservation ID you would like to update: ");
	        int roomIndex = 0;
	        int index = reservationSearch(reservationList, identifier);
			
			if (index == -1) {
				System.out.println("Reservation ID: " + identifier + " not found!");
			} else {
				System.out.println("Reservation ID: " + identifier + " found!");
				
				System.out.println(" -------------------------------------------");
				System.out.println("Please enter new reservation details ('Enter' key to skip)");
				
				String name = Menu.readString("Enter new guest name: ");
				String IC;
				boolean found=false;
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
	                Date in = Menu.readDate("Enter new Check In Date: ");
	                if (in != null) {
	                    reservationList.get(index).setCheckInDate(in);
	                    roomList.get(roomIndex).setCheckInDate(in);
	                }
	                Date out = Menu.readDate("Enter new Check Out Date: ");
	                if (out != null) {
	                    reservationList.get(index).setCheckOutDate(out);
	                    roomList.get(roomIndex).setCheckOutDate(out);
	                }
	                String pax = Menu.readString("Enter new number of pax staying: ");
	                if (!pax.equals("")) {
	                    reservationList.get(index).setPax(pax);
	                    roomList.get(roomIndex).setPax(pax);
	                }
	                System.out.println(" -------------------------------------------");
	                System.out.println(" Reservation updated!");
				}
			}
        }
}
            