import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu,Kok Jia Hui
 * @version 1.0
 */
public class RoomMenu extends Menu {
	
	/**
	 * 
	 * @param guestList
	 * @param roomList
	 * @param reservationList
	 * @param serviceList
	 * @param paymentList
	 * @param menuList
	 * @throws FileNotFoundException
	 */
	public static void roomMenu(ArrayList<Guest>guestList, ArrayList<Room>roomList, 
			ArrayList<Reservation>reservationList, ArrayList<RoomService>serviceList,
			ArrayList<Payment>paymentList, ArrayList<MenuItem>menuList) throws FileNotFoundException{

		int choice = 0;
		ReservationFileIO refio = new ReservationFileIO();
		RoomFileIO rfio = new RoomFileIO();
		PaymentFileIO pfio = new PaymentFileIO();
		ServiceFileIO sfio = new ServiceFileIO();
		//Select menu
		do {
			//Print room menu
			printRoomMenu();
			System.out.println(" -------------------------------------------");
			//Get user's choice
			choice = readInt(" Please enter your choice: ");

			switch(choice) {
			case 1: searchRoomByName(roomList,guestList);
					break;
			case 2: searchRoomByNo(roomList);
					break;
			case 3: updateRoom(roomList);
					rfio.exportAll(roomList);
					break;
			case 4: checkIn(guestList,roomList,reservationList);
					rfio.exportAll(roomList);
					refio.exportAll(reservationList);
					break;
			case 5: checkOut(guestList ,roomList ,reservationList ,serviceList ,paymentList, menuList );
					rfio.exportAll(roomList);
					refio.exportAll(reservationList);
					pfio.exportAll(paymentList);
					sfio.exportAll(serviceList);
					break;
			case 6: System.out.println("Printing Room Status statistic report");
					printRoomReport(roomList);
					break;
			case 7: System.out.println("Returning to main menu...");
					rfio.exportAll(roomList);
					refio.exportAll(reservationList);
					break;
			case 8: System.out.println("Exiting...");
					rfio.exportAll(roomList);
					refio.exportAll(reservationList);
					System.exit(0);
					break;		
			default:System.out.println("Wrong Input. Please input from 1 - 8.");
					break;
			}
		} while (choice != 7);  
	}	        	

	/**
	 * This method prints the Room Menu to inform the users of the options.
	 */
	public static void printRoomMenu() {
		System.out.println(" ===========================================");
		System.out.println(" *                  Room                   *");
		System.out.println(" ===========================================");
		System.out.println(" * 1. Check Room Status by Guest Name      *");
		System.out.println(" * 2. Check Room Status by Room	Number     *");
		System.out.println(" * 3. Update Room Details                  *");
		System.out.println(" * 4. Room Check-in                        *");
		System.out.println(" * 5. Room Check-out                       *");
		System.out.println(" * 6. Print Room Status statistic report   *");
		System.out.println(" * 7. Previous                             *");
		System.out.println(" * 8. Quit                                 *");
	}	

	//Print details of room base on guest names
	/**
	 * 
	 * @param roomList
	 * @param guestList
	 */
	public static void searchRoomByName(ArrayList<Room>roomList,ArrayList<Guest>guestList) {
		//Ask for guest name as primary key, IC is being return as the identifier
		try {
			String identifier = readString("Please enter the guest name you would like to search ('Enter' key to display all result): ");
			String IC =	guestNameSearch(guestList, identifier);

			if (!IC.equals("")) {
				int index = genericSearch("getGuestIC",IC,roomList);
				if (index == -1){
					System.out.println("Guest is not assigned to any room");
				} else {
					
					Room r = roomList.get(index);
					
					System.out.println(" -------------------------------------------");
					System.out.println("Room No: " + r.getRoomId() +
							"\nBed Type: " + r.getBedType() +
							"\nRoom Status: " + r.getRoomStatus() +
							"\nCustomer Name: " + r.getCustomerName() +
							"\nCheck In: " + dateToStr(r.getCheckInDate()) +
							"\nCheck Out: " + dateToStr(r.getCheckOutDate()) +
							"\nNumber of pax staying: " + r.getPax());
					System.out.println(" -------------------------------------------");
				}
			} else {
				System.out.println("Guest is not register in the System!");
				System.out.println("Please create a new guest!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	//Print details of room base on room ID
	/**
	 * 
	 * @param roomList
	 */
	public static void searchRoomByNo(ArrayList<Room>roomList) {
		//Ask for room ID
		String roomID = readString("Please enter the room number you would like to search: ");
		int index;
		try {
			index = genericSearch("getRoomId",roomID,roomList);
			if (index == -1) {
				System.out.println("Room with room ID: " + roomID + " not found!");
			} else {
				
				Room r = roomList.get(index);
				
				System.out.println("Room with room ID: " + roomID + " found!");
				System.out.println(" -------------------------------------------");
				System.out.println("Room No: " + r.getRoomId() +
						"\nBed Type: " + r.getBedType() +
						"\nRoom Status: " + r.getRoomStatus() +
						"\nCustomer Name: " + r.getCustomerName() +
						"\nCheck In: " + r.getCheckInDate() +
						"\nCheck Out: " + r.getCheckOutDate() +
						"\nNumber of pax staying: " + r.getPax());            
				System.out.println(" -------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}        

	//Update details of room 
	/**
	 * 
	 * @param roomList
	 */
	public static void updateRoom(ArrayList<Room>roomList) {
		//Ask for room ID
		String roomID = readNonEmptyString("Please enter the room ID to update it's details: ");
		boolean input = false;
		int index;
		try {
			index = genericSearch("getRoomId",roomID,roomList);
			if (index == -1) {
				System.out.println("Room with ID: " + roomID + " not found!");
			} else {
				Room r = roomList.get(index);
				
				System.out.println("Room with ID: " + roomID + " found!");
				System.out.println(" -------------------------------------------");
				System.out.println("Please enter new room details ('Enter' key to skip)");
				do {
					String type = readNonEmptyString("Enter new bed type [(S)ingle/(D)ouble/(K)ing]: ");
					switch(type.toUpperCase()) {
					case "S":	r.setBedType(Room.BedType.SINGLE_SIZE);
								input = true;
								break;
					case "D": 	r.setBedType(Room.BedType.DOUBLE_SIZE);
								input = true;
								break;
					case "K": 	r.setBedType(Room.BedType.KING_SIZE);
								input = true;		  
								break;
					case "":  	input = true;
								break;
					default:	System.out.println("Please enter [(S)ingle/(D)ouble/(K)ing]: ");
								System.out.println(" Please try again with the correct input !"); 
								input = false; 
								break;
					}
				}while(!input);
				
				Date maintStart;
				Date maintEnd;
				Date today = new Date();
				boolean inputCheck = true; 
				do {
					String status = readString("Enter new room status: [(V)acant/(U)nder Maintenance]");
					switch(status.toUpperCase()) {
					case "V":	r.setRoomStatus(Room.RoomStatus.VACANT);
								r.setCustomerName("-");
								r.setCheckInDate(null);
								r.setCheckOutDate(null);
								r.setPax("-");
								input = true;
								break;
					case "U":	r.setRoomStatus(Room.RoomStatus.UNDER_MAINTENANCE);
								r.setCustomerName("-");
								do {
									if (!inputCheck)
										System.out.println("Please enter a valid date");
									maintStart = readDate("Please enter the starting date of maintenance [DD/MM/YYYY]: ");
									inputCheck = false;
								}while(maintStart.before(today));
								r.setCheckInDate(maintStart);
								inputCheck = true;
								do {
									if (!inputCheck)
										System.out.println("Please enter a valid date");
									maintEnd = readDate("Please enter the ending date of maintenance [DD/MM/YYYY]: ");
									inputCheck = false;
								}while(maintEnd.before(maintStart));
								r.setCheckOutDate(maintEnd);
								r.setPax("-");
								input = true;
								break;
					case "":	input = true;
								break;
					default:	System.out.println("Please enter (V)acant/(U)nder Maintenance");
								System.out.println("Please try again with the correct input !"); 
								input = false; 
								break;
					}
				}while(!input);   
				System.out.println("-------------------------------------------");
				System.out.println("Room updated!");                  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}        

	//New check CheckIn
	/**
	 * 
	 * @param guestList
	 * @param roomList
	 * @param reservationList
	 */
	public static void checkIn(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) {
		System.out.println(" ===========================================");
		System.out.println(" 1. Walk In                                *");
		System.out.println(" 2. Reservation                            *");
		System.out.println(" ===========================================");
		int choice = readInt(" Please enter the check in method: ");

		if (choice == 1) {
			walkInCheckIn(roomList, guestList, reservationList);
		} else if (choice == 2) {
			reservationCheckIn(roomList, guestList, reservationList);
		} else {
			System.out.println("Wrong input, please enter 1 or 2.");
		}
	}        

	//Check in by walk in
	/**
	 * 
	 * @param roomList
	 * @param guestList
	 * @param reservationList
	 */
	public static void walkInCheckIn(ArrayList<Room>roomList, ArrayList<Guest>guestList, ArrayList<Reservation>reservationList) {
		String identifier = readNonEmptyString("Please enter the guest IC Number: ");
		int index;
		ArrayList<Room> walkinList = new ArrayList<Room>();
		try {
			index = genericSearch("getIc",identifier,guestList);

			if (index == -1) {
				System.out.println("Guest with IC: " + identifier + " not found!");
			} else {

				Date checkIn = new Date();
				Date checkOut;
				do {
					checkOut = readDate("Please enter the check out date [DD/MM/YYYY]: ");
				}while(checkIn.after(checkOut));
				boolean input = false;		
				String roomType = readNonEmptyString("Please Enter the type of room "
						+ "you would like to enquiry [(S)ingle/d(O)uble/d(E)luxe/(V)ip]:");
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
					default:	roomType = readNonEmptyString("Please Enter the correct type of room "
									+ "you would like to enquiry [(S)ingle/d(O)uble/d(E)luxe/(V)ip]:");
								input = false;
								break;
					}             
				}while(!input);
				//This method retrieve the arrayList of Room class which contains all possible reservation choice
				walkinList = searchRoomType(roomType, reservationList, roomList, checkIn, checkOut);

				String roomID;
				int roomIndex;
				input = false;
				if (walkinList.size() != 0) {
					do {
						//check if there is such a room
						do {
							roomID = readNonEmptyString("Please enter the room ID: ");
							roomIndex = genericSearch("getRoomId",roomID,roomList);
						}while(roomIndex == -1);
						
						//check if this room is in the list of rooms that are available 
						for(Room r : walkinList) {
							if (roomID.equals(r.getRoomId())) {
								input = true;
								break;
							}
						}
					} while(!input);

					int pax = readInt("Please enter the number of pax staying: ");

					Room r = roomList.get(roomIndex);
					
					r.setGuestIC(guestList.get(index).getIc());
					r.setCustomerName(guestList.get(index).getName());
					r.setCheckInDate(checkIn);
					r.setCheckOutDate(checkOut);
					r.setPax(Integer.toString(pax));
					r.setRoomStatus(Room.RoomStatus.OCCUPIED);
					System.out.println("Guest " + guestList.get(index).getName() + " have successfully checked in room " + roomID);
				} else {
					System.out.println("There are no room available");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Check in by reservation
	/**
	 * 
	 * @param roomList
	 * @param guestList
	 * @param reservationList
	 */
	public static void reservationCheckIn(ArrayList<Room>roomList, ArrayList<Guest>guestList, ArrayList<Reservation>reservationList) {
		int resID = readInt(" Please enter the reservation number: ");
		int index;
		try {
			index = genericSearch("getReservationId", Integer.toString(resID), reservationList);
			if (index == -1) {
				System.out.println("Reservation number: " + resID + " not found!");
			} else {
				System.out.println("Reservation number: " + resID + " found!");
				
				Reservation re = reservationList.get(index);
				
				String roomID = re.getRoomId();
				Reservation.ReservationStatus res = re.getReserveStatus();
				
				int roomIndex = genericSearch("getRoomId",roomID,roomList);
				if (roomIndex == -1) {
					System.out.println("Room ID: " + re.getRoomId() + " not found!");
				//Make sure check in is only possible when reservation status is confirmed 
				} else if (res.equals((Reservation.ReservationStatus.CONFIRMED))){
					
					Room r = roomList.get(roomIndex);
					
					r.setRoomStatus(Room.RoomStatus.OCCUPIED);
					r.setCustomerName(re.getGuestName());
					r.setCheckInDate(re.getCheckInDate());
					r.setCheckOutDate(re.getCheckOutDate());
					r.setPax(re.getPax());
					r.setGuestIC(re.getGuestIC());
					
					re.setReserveStatus(Reservation.ReservationStatus.CHECKED_IN);
					System.out.println("Successfully Check In to Room : " + re.getRoomId());
					
				}else if (!re.getReserveStatus().equals((Reservation.ReservationStatus.CONFIRMED))){
					System.out.println("Reservation Status: " + res);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param guestList
	 * @param roomList
	 * @param reservationList
	 * @param serviceList
	 * @param paymentList
	 * @param menuList
	 */
	public static void checkOut(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList, 
			ArrayList<RoomService>serviceList, ArrayList<Payment>paymentList, ArrayList<MenuItem>menuList) {
		String roomID = readString("Please enter the room ID: ");

		int index;
		try {
			index = genericSearch("getRoomId",roomID,roomList);

			if (index == -1) {
				System.out.println("Room with ID: " + roomID + " not found!");
			} else {
				
				Room r = roomList.get(index);
				
				if (r.getRoomStatus().equals(Room.RoomStatus.OCCUPIED)) {
					System.out.println("Room with ID: " + roomID + " found!");
					int reservationIndex = genericSearch("getRoomId",roomID,reservationList);
					PaymentMenu.printInvoice(paymentList, serviceList, roomList, index);
					if (reservationIndex == -1) {					
						r.setCustomerName("-");
						r.setCheckInDate(null);
						r.setCheckOutDate(null);
						r.setPax("-");
						r.setGuestIC("-");
						r.setRoomStatus(Room.RoomStatus.VACANT);
					} else {
						if (r.getCheckOutDate().compareTo(reservationList.get(reservationIndex).getCheckOutDate()) == 0) {
							System.out.println("Reservation with room ID: " + roomID + " and check out date: " + reservationList.get(reservationIndex).getCheckOutDate() + " found!");
							reservationList.remove(reservationIndex);
							r.setCustomerName("-");
							r.setCheckInDate(null);
							r.setCheckOutDate(null);
							r.setPax("-");
							r.setGuestIC("-");
							r.setRoomStatus(Room.RoomStatus.VACANT);
						} else {
							r.setRoomStatus(Room.RoomStatus.RESERVED);
						}	
					}
					//In this check out we make use of this method to remove what the user has ordered
					roomServiceSearch(serviceList, menuList, roomList, roomID);
				} else 
					System.out.println("Room is not occupied.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param roomList
	 */
	public static void printRoomReport(ArrayList<Room>roomList) {

		//Print room occupancy rate
		System.out.println("Printing occupancy rate");
		System.out.println(" -------------------------------------------");

		//Get single room occupancy rate
		int single_occuCount = 0;
		int single_totalCount = 0;
		//Get double room occupancy rate
		int double_occuCount = 0;
		int double_totalCount = 0;
		//Get deluxe room occupancy rate
		int deluxe_occuCount = 0;
		int deluxe_totalCount = 0;
		//Get VIP room occupancy rate
		int vip_occuCount = 0;
		int vip_totalCount = 0;


		ArrayList<Room>single_occuList = new ArrayList<Room>();
		ArrayList<Room>double_occuList = new ArrayList<Room>();
		ArrayList<Room>deluxe_occuList = new ArrayList<Room>();
		ArrayList<Room>vip_occuList = new ArrayList<Room>();

		for (Room r: roomList) {
			if (r instanceof Room_single) {
				if (r.getRoomStatus() == Room.RoomStatus.OCCUPIED) {
					single_occuCount++;
					single_occuList.add(r);
				}
				single_totalCount++;
			}
			if (r instanceof Room_double) {
				if (r.getRoomStatus() == Room.RoomStatus.OCCUPIED) {
					double_occuCount++;
					double_occuList.add(r);
				}
				double_totalCount++;
			}
			if (r instanceof Room_deluxe) {
				if (r.getRoomStatus() == Room.RoomStatus.OCCUPIED) {
					deluxe_occuCount++;
					deluxe_occuList.add(r);
				}
				deluxe_totalCount++;
			}

			if (r instanceof Room_vip) {
				if (r.getRoomStatus() == Room.RoomStatus.OCCUPIED) {
					vip_occuCount++;
					vip_occuList.add(r);
				}
				vip_totalCount++;
			}
		}

		System.out.println("Single:  Number: " + single_occuCount + " out of " + single_totalCount);
		System.out.print("         Rooms: ");
		printRooms (single_occuList);

		System.out.println("Double:  Number: " + double_occuCount + " out of " + double_totalCount);
		System.out.print("         Rooms: ");
		printRooms (double_occuList);

		System.out.println("Deluxe:  Number: " + deluxe_occuCount + " out of " + deluxe_totalCount);
		System.out.print("         Rooms: ");
		printRooms (deluxe_occuList);

		System.out.println("VIP:     Number: " + vip_occuCount + " out of " + vip_totalCount);
		System.out.print("         Rooms: ");
		printRooms (vip_occuList);

		//Print room status
		System.out.println("Printing room status");
		System.out.println(" -------------------------------------------");

		//Get vacant rooms
		ArrayList<Room>vacantList = new ArrayList<Room>();
		//Get occupied rooms
		ArrayList<Room>occupiedList = new ArrayList<Room>();
		//Get reserved rooms
		ArrayList<Room>resList = new ArrayList<Room>();
		//Get maintenance rooms
		ArrayList<Room>maintList = new ArrayList<Room>();

		for (Room r: roomList) {
			if (r.getRoomStatus() == Room.RoomStatus.VACANT) {
				vacantList.add(r);
			}			
			if (r.getRoomStatus() == Room.RoomStatus.OCCUPIED) {	
				occupiedList.add(r);
			}
			if (r.getRoomStatus() == Room.RoomStatus.RESERVED) {
				resList.add(r);
			}
			if (r.getRoomStatus() == Room.RoomStatus.UNDER_MAINTENANCE) {
				maintList.add(r);
			}
		}

		System.out.println("Vacant: ");
		System.out.print("         Rooms: ");
		printRooms (vacantList);

		System.out.println("Occupied: ");
		System.out.print("         Rooms: ");
		printRooms (occupiedList);

		System.out.println("Reserved: ");
		System.out.print("         Rooms: ");
		printRooms (resList);

		System.out.println("Under maintenance: ");
		System.out.print("         Rooms: ");
		printRooms (maintList);
	}

	/**
	 * 
	 * @param occuList
	 */
	public static void printRooms (ArrayList<Room>occuList) {

		if (occuList.isEmpty())
			System.out.print("None");
		else {	
			for (Room r: occuList) {
				String roomId = r.getRoomId();
				roomId = roomId.substring(0, 2) + "-" + roomId.substring(2, roomId.length());
				System.out.print(roomId + " ");
			}
		}

		System.out.println("\n");
	}

}