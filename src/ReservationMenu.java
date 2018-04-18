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
			case 5: removeReservation(reservationList, roomList);
					refio.exportAll(reservationList);
					rfio.exportAll(roomList);
					break;
			case 6: System.out.println("Returning to main menu...");
					refio.exportAll(reservationList);
					rfio.exportAll(roomList);
					gfio.exportAll(guestList);
					break;
			case 7: System.out.println("Exiting...");
					refio.exportAll(reservationList);
					rfio.exportAll(roomList);
					gfio.exportAll(guestList);
					System.exit(0);
					break;				
			default:System.out.println("Wrong Input. Please input from 1 - 6.");
					break;
			}
		} while (choice != 6);

	}

	//Print reservation menu
	public static void printReservationMenu() {
		System.out.println(" ===========================================");
		System.out.println(" *               Reservation               *");
		System.out.println(" ===========================================");
		System.out.println(" * 1. Make Reservation                     *");
		System.out.println(" * 2. Check Reservation Details            *");
		System.out.println(" * 3. Update Reservation Details           *");
		System.out.println(" * 4. Print All Reservation                *");
		System.out.println(" * 5. Remove Reservation                   *");
		System.out.println(" * 6. Previous                             *");
		System.out.println(" * 7. Quit                                 *");
	}

	//Make reservation from reservation list
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
				
				Guest g = guestList.get(gindex);
				
				String guestName = g.getName();
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

				// Obtain user preference of room type
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
					// if there are no available room type of the user's choice, he can be placed in wait list
					String response = readNonEmptyString("Would you like to be placed in the wait list? Y/N");
					do {
						switch (response.toUpperCase()) {
						// generate a reservation ID here also
						// if yes, we create a reservation object and store it into the reservation list
						case "Y":	Reservation re = new Reservation(reservationID,"null", guestName, g.getCreditDetails(),
									checkIn, checkOut, pax, Reservation.ReservationStatus.IN_WAITLIST, IC);
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
				Reservation reservation = new Reservation(reservationID, roomID, g.getName(), g.getCreditDetails(), 
						checkIn, checkOut, pax, Reservation.ReservationStatus.CONFIRMED,g.getIc());
				reservationList.add(reservation);

				System.out.println("Room with room ID: " + roomID + " reserved!");
				System.out.println("Reservation ID: " + reservationID);
				System.out.println("Please present this ID during check in !");	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	        

	//Search reservation from reservation list
	public static void searchReservation(ArrayList<Reservation>reservationList) {
		int resID = readInt("Please enter the reservation ID you would like to search: ");
		int index;
		try {
			index = genericSearch("getReservationId",Integer.toString(resID),reservationList); 

			if (index == -1) {
				System.out.println("Reservation ID: " + resID + " not found!");
			} else {
				System.out.println("Reservation ID: " + resID + " found!");

				Reservation re = reservationList.get(index);
				
				System.out.println(" -------------------------------------------");
				System.out.println("Reservation ID: " + re.getReservationId() + 
						"\nCustomer Name: " + re.getGuestName() + 
						"\nRoom ID: " + re.getRoomId() + 
						"\nCredit Card Information: " + re.getCreditCard() + 
						"\nCheck In Date: " + re.getCheckInDate() + 
						"\nCheck Out Date: " + re.getCheckOutDate() +
						"\nNumber of pax(s) staying: " + re.getPax() +
						"\nReservation Status: " + re.getReserveStatus());
				System.out.println(" -------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Update reservation from reservation list
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
				boolean input = false;
				
				Reservation re = reservationList.get(index);
				
				do {
					String resStatus = readNonEmptyString("Please enter the new Status for the reservation! (C)onfirmed/(E)xpired");
					switch(resStatus.toUpperCase()) {
					case "C":	if (re.getReserveStatus().equals(Reservation.ReservationStatus.IN_WAITLIST)) {
									re.setReserveStatus(Reservation.ReservationStatus.CONFIRMED);	
									input = false;
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
									
									Date checkIn = re.getCheckInDate() ;
									Date checkOut = re.getCheckOutDate();
									
									ArrayList<Room> updateList = searchRoomType(roomType, reservationList, roomList, checkIn, checkOut);
									
									input = false;
									String roomID = "";

									boolean foundRoom = true;
									
									if(updateList.isEmpty()) {
										System.out.println("Room Type:" + roomType + " is not available!");
									} else {
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
											for(Room r : updateList) {
												if (roomID.equals(r.getRoomId())) {
													input = true;
													break;
												}
											}
										} while(!input);
									}
								} else 
									System.out.println("Reservation is not in waiting list.");
								break;
					case "E": 	re.setReserveStatus(Reservation.ReservationStatus.EXPIRED);
								input = true;
								break;
					default:	System.out.println("Please Enter the Correct Status for the reservation!");
								input = false; 
								break;
					}
				}while(!input);
				System.out.println(" -------------------------------------------");
				System.out.println(" Reservation updated!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Print reservation from reservation list
	public static void printReservation(ArrayList<Reservation>reservationList) {
		if (!reservationList.isEmpty()) {
			for (Reservation re: reservationList) {
				if (!re.getReserveStatus().equals(Reservation.ReservationStatus.EXPIRED)) {
					System.out.println("Guest Name: " + re.getGuestName() + 
							"\nRoom ID: " + re.getRoomId() + 
							"\nCredit Card: " + re.getCreditCard() + 
							"\nCheck In Date: " + re.getCheckInDate() + 
							"\nCheck Out Date: " + re.getCheckOutDate() + 
							"\nPax:" + re.getPax() +
							"\nReservation Status: " + re.getReserveStatus() +
							"\nGuest IC: " + re.getGuestIC() +
							"\nReservation ID: " + re.getReservationId());
					System.out.println(" -------------------------------------------");	
				}
			}		
		} else 
			System.out.println("There is no reservation at the moment. ");
	}
		
	//Remove reservation from reservation list
	public static void removeReservation(ArrayList<Reservation>reservationList, ArrayList<Room>roomList) {
		int resID = readInt("Please enter the reservation ID you would like to remove: ");
		int index, roomIndex, resIndex;
		try {
			index = genericSearch("getReservationId", String.valueOf(resID) , reservationList);
			String roomId;
			roomId = reservationList.get(index).getRoomId();
			reservationList.remove(index);
			roomIndex = genericSearch("getRoomId", roomId, roomList);
			
			if (!roomId.equals("null")) {
				if(roomList.get(roomIndex).getRoomStatus().equals(Room.RoomStatus.RESERVED)){
					resIndex = genericSearch("getRoomId", roomId, reservationList);
					if (resIndex == -1)
						roomList.get(roomIndex).setRoomStatus(Room.RoomStatus.VACANT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
