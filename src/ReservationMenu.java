import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
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
                        GuestFileIO gfio = new GuestFileIO();
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
					refio.exportAll(reservationList);
					rfio.exportAll(roomList);
					break;
			case 4: printReservation(reservationList);
					break;
			case 5: System.out.println("Returning to main menu...");
					refio.exportAll(reservationList);
					rfio.exportAll(roomList);
					gfio.exportAll(guestList);
					break;
			case 6: System.out.println("Exiting...");
					refio.exportAll(reservationList);
					rfio.exportAll(roomList);
					gfio.exportAll(guestList);
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
		System.out.println(" * 4. Print All Reservation                *");
		System.out.println(" * 5. Previous                             *");
		System.out.println(" * 6. Quit                                 *");
	}

	public static void makeReservation(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) throws FileNotFoundException{
		try {
			String IC = readString("Please enter the guest IC Number: ");
			//Get the index where the IC matches the guestList
			int gindex = genericSearch("getIc",IC,guestList);
			//int resIndex = genericSearch("getGuestIC",IC,reservationList);
			// if gindex is -1 mean there are no index in the array list which has this guest
			if (gindex == -1) {
				System.out.println("Guest with IC: " + IC + " not found!");
				System.out.println("Please create guest first");
			} else {	
				System.out.println("Guest with IC: " + IC + " found!");
				String guestName = guestList.get(gindex).getName();
				Date checkIn;
				Date checkOut;
				Date today = new Date();

				/*Verify that the check in date is after today. 
				  Earliest reservation must be made 1 day before the check in date*/
				boolean inputCheck = true; 
				do {
					if (!inputCheck)
						System.out.println("Please enter a valid date");
					checkIn = readDate("Please enter the check in date [DD/MM/YYYY]: ");
					inputCheck = false;
				}while(!checkIn.after(today));

				inputCheck = true;

				/*Verify that the check out date is after check in date. 
				  Assume that the user will stay at least a minimum of one day*/
				do {
					if (!inputCheck)
						System.out.println("Please enter a valid date");
					checkOut = readDate("Please enter the check out date [DD/MM/YYYY]: ");
					inputCheck = false;
				}while(!checkOut.after(checkIn));

				int paxInt = readInt("Please enter the number of pax staying: ");
				String pax = Integer.toString(paxInt);

				String roomID = "-";
				int roomIndex = 0;

				// Obtain user preferrence of room type
				boolean input = false;
				String roomType = readNonEmptyString("Please Enter the type of room:"
						+ "you would like to enquiry [(S)ingle/d(O)uble/d(E)luxe/(V)ip] :");
				do {
					switch (roomType.toUpperCase()) {
					case "S":	roomType = "SINGLE";
								input = true;
								break;
					case "O":	roomType = "DOUBLE";
								input = true;
								break;
					case "E":	roomType = "DELUXE";
								input = true;
								break;
					case "V":	roomType = "VIP";
								input = true;
								break;
					default:	roomType = readNonEmptyString("Please Enter the correct type of room which "
								+ "you would like to enquiry [(S)ingle/d(O)uble/d(E)luxe/(V)ip] :");
								input = false;
								break;
					}             
				}while(!input);
				
				//Generating a reservation ID and making sure it there isn't a same reservation ID  
				int index;
				int reservationID;
				do {
					Random random = new Random(System.currentTimeMillis());
					reservationID = 10000+random.nextInt(20000);
					index = genericSearch("getReservationId",String.valueOf(reservationID),reservationList);
				} while (index != -1);
				
				//Retrieve the arrayList of Room class which contains all possible reservation choice
				ArrayList<Room> roomReservationList = new ArrayList<Room>();
				roomReservationList = searchRoomType(roomType, reservationList, roomList, checkIn, checkOut);

				
				// if roomReservationList has 0 element, that means all room with room type of the user's choice is unavailable
				
				if (roomReservationList.size() == 0) {
					System.out.println("Room of Room type " + roomType + " is unavailable at the moment!");
					input = false;	
					// if there are no available room type of the user's choice, he can be placed in waitlist
					String response = readNonEmptyString("Would you like to be placed in the wait list? Y/N");
					do {
						switch (response.toUpperCase()) {
						// generate a reservation ID here also
						// if yes, we create a reservation object and store it into the reservation list
						case "Y":	Reservation re = new Reservation(reservationID,"null", guestName, guestList.get(gindex).getCreditDetails(),
									checkIn, checkOut, pax, Reservation.ReservationStatus.IN_WAITLIST, IC );
									reservationList.add(re);
									input = true;
									break;
						case "N":	input = true;
									break;
						default:	System.out.println("Please Enter Y/N!");
									input = false;
									break;
						}             
					}while(!input);
					
				} else {
					boolean foundRoom = true;
					input = false;
					do {
						//check if there is such a room
						do {
							if (!foundRoom)
								System.out.println("Room : " + roomID + " does not exist!");
							roomID = readNonEmptyString("Please enter the room ID: ");
							roomIndex = genericSearch("getRoomId",roomID,roomList);
							foundRoom = false;
						}while(roomIndex == -1);

						//check if this room is in the list of rooms that are available 
						for(Room r : roomReservationList) {
							if (roomID.equals(r.getRoomId())) {
								input = true;
								break;
							}
						}
					} while(!input);
				

				//setting room status 
				if(roomList.get(roomIndex).getRoomStatus().equals(Room.RoomStatus.VACANT)) {
					roomList.get(roomIndex).setRoomStatus(Room.RoomStatus.RESERVED);
				} 
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
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	        

	public static void searchReservation(ArrayList<Reservation>reservationList) {
		int resID = readInt("Please enter the reservation ID you would like to search: ");
		int index;
		try {
			index = genericSearch("getReservationId",Integer.toString(resID),reservationList); //might have error

			if (index == -1) {
				System.out.println("Reservation ID: " + resID + " not found!");
			} else {
				System.out.println("Reservation ID: " + resID + " found!");

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
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateReservation(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) {
		int resID = readInt("Please enter the reservation ID you would like to update: ");
		int roomIndex = 0;
		int index;
		try {
			index = genericSearch("getReservationId",Integer.toString(resID),reservationList);
			if (index == -1) {
				System.out.println("Reservation ID: " + resID + " not found!");
			} else {
				System.out.println("Reservation ID: " + resID + " found!");

				System.out.println(" -------------------------------------------");
				System.out.println("Please enter new reservation details ('Enter' key to skip)");

				String name = readString("Enter new guest name: ");
				String IC;
				boolean found=false;
				String roomID = reservationList.get(index).getRoomId();

				roomIndex = genericSearch("getRoomId",roomID,roomList);

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
				if (roomIndex != -1) {
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
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printReservation(ArrayList<Reservation>reservationList) {
		int index = 0;
		if (!reservationList.isEmpty()) {
			if(!reservationList.get(index).getReserveStatus().equals(Reservation.ReservationStatus.EXPIRED)) {
				for (Reservation re: reservationList) {
					System.out.println("Guest Name: " + reservationList.get(index).getGuestName() + 
							"\nRoom ID: " + reservationList.get(index).getRoomId() + 
							"\nCredit Card: " + reservationList.get(index).getCreditCard() + 
							"\nCheck In Date: " + reservationList.get(index).getCheckInDate() + 
							"\nCheck Out Date: " + reservationList.get(index).getCheckOutDate() + 
							"\nPax:" + reservationList.get(index).getPax() +
							"\nReservation Status: " + reservationList.get(index).getReserveStatus() +
							"\nGuest IC: " + reservationList.get(index).getGuestIC() +
							"\nReservation ID: " + reservationList.get(index).getReservationId());
					System.out.println(" -------------------------------------------");	
					index++;
				}
			}
		} else 
			System.out.println("There is no reservation at the moment. ");
	}
}
