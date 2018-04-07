import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {
	
	public static void mainMenu(){
        System.out.println(" ===========================================");
        System.out.println(" *         Hotel Management System         *");
        System.out.println(" ===========================================");
        System.out.println(" * 1. About Guest                          *");
        System.out.println(" * 2. About Room                           *");
        System.out.println(" * 3. About Reservation                    *");
        System.out.println(" * 4. About Room Service                   *");
        System.out.println(" * 5. About Payment                        *");
        System.out.println(" * 6. Quit                                 *");
    } 
	
//Guest Menu ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void guestMenu(ArrayList<Guest>guestList, ArrayList<Room>roomList) throws FileNotFoundException {
		
		int choice = 0;
        GuestFileIO gfio = new GuestFileIO();
        RoomFileIO rfio = new RoomFileIO();
        //Select menu
        do {
        	//Print guest menu
        	printGuestMenu();
        	
        	//Get user's choice
        	System.out.println(" -------------------------------------------");
            choice = Menu.readInt(" Please enter your choice: ");
            System.out.println(" -------------------------------------------");
            
            switch(choice) {
                case 1: System.out.println("Creating new guest");
                		createNewGuest(guestList);
                		break;
                		
                case 2: System.out.println("Updating guest details");
                		updateGuest(guestList);
                		gfio.exportAll(guestList);
                                rfio.exportAll(roomList);
                		break;
                case 3: System.out.println("Searching guest details");
                		searchGuest(guestList);
                		break;			
                case 4: System.out.println("Returning to main menu...");
                		gfio.exportAll(guestList);
                                rfio.exportAll(roomList);
                		break;
				case 5: System.out.println("Exiting...");
						gfio.exportAll(guestList);
                                rfio.exportAll(roomList);
                		System.exit(0);
						break;		
				default: System.out.println("Wrong Input. Please input from 1 - 5.");
						 break;
            }
            
        } while (choice != 4);  
	}
	
	public static void printGuestMenu() {
			System.out.println(" ===========================================");
        	System.out.println(" *                  Guest                  *");
        	System.out.println(" ===========================================");
        	System.out.println(" * 1. New Guest                            *");
        	System.out.println(" * 2. Update Guest Details                 *");
        	System.out.println(" * 3. Search Guest Details                 *");
        	System.out.println(" * 4. Previous                             *");
        	System.out.println(" * 5. Quit                                 *");
    }
	
	public static void createNewGuest(ArrayList<Guest>guestList) {
		
		String name = Menu.readString("Enter guest name: ");
		String addr = Menu.readString("Enter guest address: ");
		String country = Menu.readString("Enter guest country: ");
		String gender = Menu.readString("Enter guest gender: ");
		String nat = Menu.readString("Enter guest nationality: ");
		String idt = Menu.readString("Enter guest identity[(D)riving License/(P)assport]: ");
                String ic = Menu.readString("Enter IC Number: ");
		String ccd = Menu.readString("Enter guest credit card detail: ");
		String contact = Menu.readString("Enter guest contact number: ");
		
		Guest g = new Guest(name, addr, country, gender, nat, idt, ic, ccd, contact);
		guestList.add(g);
	}
	
	public static void updateGuest(ArrayList<Guest>guestList) {
		//Ask for guest identity as primary key
		String identifier = Menu.readString("Please enter identity of guest to update: ");
		
		boolean found = false;
		int index = 0;
		
		for (Guest g: guestList) {
			if (identifier.equals(g.getIdentity())) {
				found = true;
				break;
			}
			index++;
		}
		
		if (!found) {
			System.out.println("Guest with identity: " + identifier + " not found!");
		} else {
			System.out.println("Guest with identity: " + identifier + " found!");
			
			System.out.println(" -------------------------------------------");
			System.out.println("Please enter new guest details ('Enter' key to skip)");
			
			String name = Menu.readString("Enter new guest name: ");
			if (!name.equals("")) 
				guestList.get(index).setName(name);
			
			String addr = Menu.readString("Enter new guest address: ");
			if (!addr.equals("")) 
				guestList.get(index).setAddress(addr);
			
			String country = Menu.readString("Enter new guest country: ");
			if (!country.equals("")) 
				guestList.get(index).setCountry(country);
			
			String gender = Menu.readString("Enter new guest gender: ");
			if (!gender.equals("")) 
				guestList.get(index).setGender(gender);
			
			String nat = Menu.readString("Enter new guest nationality: ");
			if (!nat.equals("")) 
				guestList.get(index).setNationality(nat);
			
			String ccd = Menu.readString("Enter new guest credit card detail: ");
			if (!ccd.equals("")) 
				guestList.get(index).setCreditDetails(ccd);;
			
			String contact = Menu.readString("Enter new guest contact number: ");
			if (!contact.equals("")) 
				guestList.get(index).setContact(contact);
					
			System.out.println(" -------------------------------------------");
			System.out.println(" Guest updated!");
		}
	}
	
	public static void searchGuest(ArrayList<Guest>guestList) {
		//Ask for guest name as primary key
		String identifier = Menu.readString("Please enter the name of guest you would like to search: ");
		
		boolean found = false;
		int index = 0;
		
		for (Guest g: guestList) {
			if (identifier.equals(g.getName())) {
				found = true;
				break;
			}
			index++;
		}
		
		if (!found) {
			System.out.println("Guest with name: " + identifier + " not found!");
		} else {
			System.out.println("Guest with name: " + identifier + " found!");
			
			System.out.println(" -------------------------------------------");
			System.out.println("Name: " + guestList.get(index).getName() + 
							   "\nAddress: " + guestList.get(index).getAddress() + 
							   "\nCountry: " + guestList.get(index).getCountry() + 
							   "\nGender: " + guestList.get(index).getGender() + 
							   "\nNationality: " + guestList.get(index).getNationality() + 
							   "\nIdentity: " + guestList.get(index).getIdentity() +
							   "\nCredit Card Details: " + guestList.get(index).getCreditDetails() +
							   "\nContact: " + guestList.get(index).getContact());
			System.out.println(" -------------------------------------------");
			
		}
	}
//Room Menu ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void roomMenu(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) throws FileNotFoundException{
		
		int choice = 0;
                GuestFileIO gfio = new GuestFileIO();
                RoomFileIO rfio = new RoomFileIO();
        //Select menu
        do {
        	//Print room menu
        	printRoomMenu();
        	
        	//Get user's choice
        	System.out.println(" -------------------------------------------");
            choice = Menu.readInt(" Please enter your choice: ");
            
            switch(choice) {
                case 1: searchRoomByName(roomList);
                		break;
                case 2: searchRoomByNo(roomList);
                		break;
                case 3: updateRoom(roomList);
                        rfio.exportAll(roomList);
                		break;
                case 4: checkIn(guestList,roomList,reservationList);
                        rfio.exportAll(roomList);
                		break;
                case 5: 
                		break;
                case 6: System.out.println("Printing Room Status statistic report");
                		printRoomReport(roomList);
                		break;
                case 7: System.out.println("Returning to main menu...");
                        rfio.exportAll(roomList);
						break;
                case 8: System.out.println("Exiting...");
                        rfio.exportAll(roomList);
                		System.exit(0);
                		break;		
                default:System.out.println("Wrong Input. Please input from 1 - 8.");
                		break;
            }
            
        } while (choice != 7);  
	}
	
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
	public static void searchRoomByName(ArrayList<Room>roomList) {
		//Ask for guest name as primary key
		String identifier = Menu.readString("Please enter the guest name you would like to search: ");
		
		boolean found = false;
		int index = 0;
		
		for (Room r: roomList) {
			if (identifier.equals(r.getCustomerName())) {
				found = true;
				break;
			}
			index++;
		}
		
		if (!found) {
			System.out.println("Room with customer name: " + identifier + " not found!");
		} else {
			System.out.println("Room with customer name: " + identifier + " found!");
			
			System.out.println(" -------------------------------------------");
                        System.out.println("Room No: " + roomList.get(index).getRoomId() +
                                                "\nBed Type: " + roomList.get(index).getBedType() +
                                                "\nRoom Status: " + roomList.get(index).getRoomStatus() +
                                                "\nCustomer Name: " + roomList.get(index).getCustomerName() +
                                            "\nCheck In: " + roomList.get(index).getCheckIn() +
                                            "\nCheck Out: " + roomList.get(index).getCheckOut() +
                                            "\nNumber of pax staying: " + roomList.get(index).getPax());
			System.out.println(" -------------------------------------------");
			
		}
	}
        
        public static void searchRoomByNo(ArrayList<Room>roomList) {
		//Ask for guest name as primary key
		String identifier = Menu.readString("Please enter the room number you would like to search: ");
		
		boolean found = false;
		int index = 0;
		
		for (Room r: roomList) {
			if (identifier.equals(r.getRoomId())) {
				found = true;
				break;
			}
			index++;
		}
		
		if (!found) {
			System.out.println("Room with room ID: " + identifier + " not found!");
		} else {
			System.out.println("Room with room ID: " + identifier + " found!");
			
			System.out.println(" -------------------------------------------");
                        System.out.println("Room No: " + roomList.get(index).getRoomId() +
                                                "\nBed Type: " + roomList.get(index).getBedType() +
                                                "\nRoom Status: " + roomList.get(index).getRoomStatus() +
                                                "\nCustomer Name: " + roomList.get(index).getCustomerName() +
                                            "\nCheck In: " + roomList.get(index).getCheckIn() +
                                            "\nCheck Out: " + roomList.get(index).getCheckOut() +
                                            "\nNumber of pax staying: " + roomList.get(index).getPax());
			System.out.println(" -------------------------------------------");
			
		}
	}
        
        public static void updateRoom(ArrayList<Room>roomList) {
		//Ask for guest identity as primary key
		String identifier = Menu.readString("Please enter the room ID to update it's details: ");
		boolean input = true;
		boolean found = false;
		int index = 0;
		
		for (Room r: roomList) {
			if (identifier.equals(r.getRoomId())) {
				found = true;
				break;
			}
			index++;
		}
		
		if (!found) {
			System.out.println("Room with ID: " + identifier + " not found!");
		} else {
			System.out.println("Room with ID: " + identifier + " found!");
			
			System.out.println(" -------------------------------------------");
			System.out.println("Please enter new room details ('Enter' key to skip)");
			
			String type = Menu.readString("Enter new bed type: ");
			if (!type.equals("")) 
				roomList.get(index).setBedType(type);
			
			String status = Menu.readString("Enter new room status: [(V)acant/(O)ccupied/(R)eserved/(U)nder Maintenance]");
				
                        switch(status) {
                            case "V": roomList.get(index).setRoomStatus(Room.RoomStatus.VACANT);
                                      break;
                            case "O": roomList.get(index).setRoomStatus(Room.RoomStatus.OCCUPIED);
                                      break;
                            case "R": roomList.get(index).setRoomStatus(Room.RoomStatus.RESERVED);
                                      break;
                            case "U": roomList.get(index).setRoomStatus(Room.RoomStatus.UNDER_MAINTENANCE);
                                      break;
                            case "": break;
                            default: System.out.println("Please enter (V)acant/(O)ccupied/(R)eserved/(U)nder Maintenance");
                                     input = false; 
                                     break;
                        }
			
					
			System.out.println(" -------------------------------------------");
                        if(input)
                            System.out.println(" Room updated!");
                        else 
                            System.out.println(" Please try again with the correct input !");
		}
	}
        
        public static void checkIn(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) {
            System.out.println(" ===========================================");
            System.out.println(" 1. Walk In                                *");
            System.out.println(" 2. Reservation                            *");
            System.out.println(" ===========================================");
            int choice = Menu.readInt(" Please enter the check in method: ");
            
            String identifier;
            boolean found = false;
            int index = 0;
            int roomIndex = 0;
            
            if (choice == 1) {
                identifier = Menu.readString("Please enter the guest IC Number: ");

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
                        roomList.get(roomIndex).setCheckIn(dateFormat.format(date));
                        roomList.get(roomIndex).setCheckOut(checkOut);
                        roomList.get(roomIndex).setPax(pax);
                        roomList.get(roomIndex).setRoomStatus(Room.RoomStatus.OCCUPIED);
		
			System.out.println("Room with room ID: " + roomID + " occupied!");
			
			
		
		}
            } else if (choice == 2) {
                identifier = Menu.readString(" Please enter the reservation number: ");
		
		for (Reservation r: reservationList) {
			if (identifier.equals(r.getReservationId())) {
				found = true;
				break;
			}
			index++;
		}
		
		if (!found) {
			System.out.println("Reservation number: " + identifier + " not found!");
		} else {
			System.out.println("Reservation number: " + identifier + " found!");
			
			
			
		}
            } else 
                System.out.println("Wrong input, please enter 1 or 2.");
        }
	public static void printRoomReport(ArrayList<Room>roomList) {
		
		//Print room occupancy rate
		System.out.println("Printing occupancy rate");
		System.out.println(" -------------------------------------------");
		
		//Get single room occupancy rate
		int single_occuCount = 0;
		int single_totalCount = 0;
		ArrayList<Room>single_occuList = new ArrayList<Room>();
		
		for (Room r: roomList) {
			if (r instanceof Room_single) {
				if (r.getRoomStatus() == Room.RoomStatus.OCCUPIED) {
					single_occuCount++;
					single_occuList.add(r);
				}
				single_totalCount++;
			}
		}
		
		//Get double room occupancy rate
		int double_occuCount = 0;
		int double_totalCount = 0;
		ArrayList<Room>double_occuList = new ArrayList<Room>();
		
		for (Room r: roomList) {
			if (r instanceof Room_double) {
				if (r.getRoomStatus() == Room.RoomStatus.OCCUPIED) {
					double_occuCount++;
					double_occuList.add(r);
				}
				double_totalCount++;
			}
		}
		
		//Get deluxe room occupancy rate
		int deluxe_occuCount = 0;
		int deluxe_totalCount = 0;
		ArrayList<Room>deluxe_occuList = new ArrayList<Room>();
		
		for (Room r: roomList) {
			if (r instanceof Room_deluxe) {
				if (r.getRoomStatus() == Room.RoomStatus.OCCUPIED) {
					deluxe_occuCount++;
					deluxe_occuList.add(r);
				}
				deluxe_totalCount++;
			}
		}
		
		//Get deluxe room occupancy rate
		int vip_occuCount = 0;
		int vip_totalCount = 0;
		ArrayList<Room>vip_occuList = new ArrayList<Room>();
		
		for (Room r: roomList) {
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
		
		for (Room r: roomList) {
			if (r.getRoomStatus() == Room.RoomStatus.VACANT) {
				vacantList.add(r);
			}
		}
		
		//Get occupied rooms
		ArrayList<Room>occupiedList = new ArrayList<Room>();
		
		for (Room r: roomList) {
			if (r.getRoomStatus() == Room.RoomStatus.OCCUPIED) {
				occupiedList.add(r);
			}
		}
		
		//Get reserved rooms
		ArrayList<Room>resList = new ArrayList<Room>();
		
		for (Room r: roomList) {
			if (r.getRoomStatus() == Room.RoomStatus.RESERVED) {
				resList.add(r);
			}
		}
		
		//Get maintenance rooms
		ArrayList<Room>maintList = new ArrayList<Room>();
		
		for (Room r: roomList) {
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

//Room Service Menu -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void roomServiceMenu(ArrayList<RoomService>serviceList, ArrayList<MenuItem>menuList) throws FileNotFoundException{
		
		int choice = 0;
                MenuFileIO mfio = new MenuFileIO();
                ServiceFileIO sfio = new ServiceFileIO();
        //Select menu
        do {
        	//Print room service menu
        	printRoomServiceMenu();
        	
        	//Get user's choice
        	System.out.println(" -------------------------------------------");
            choice = Menu.readInt(" Please enter your choice: ");
            
            switch(choice) {
                case 1: System.out.println("Creating new menu item...");
                        createNewMenu(menuList);
                	break;
                case 2: System.out.println("Updating menu details...");
                	updateMenu(menuList);
                	mfio.exportAll(menuList);
                	break;
                case 3: System.out.println("Removing menu details...");
                	removeMenu(menuList);
                	mfio.exportAll(menuList);
                	break;
                case 4: createNewRS(menuList, serviceList);
                        sfio.exportAll(serviceList);
                        break;
                case 5: System.out.println("Returning to main menu...");
                        mfio.exportAll(menuList);
			break;
		case 6: System.out.println("Exiting...");
                        mfio.exportAll(menuList);
			System.exit(0);
			break;				
		default:System.out.println("Wrong Input. Please input from 1 - 5.");
			break;
            }
            
        } while (choice != 5);
	}
	
	public static void printRoomServiceMenu() {
    	System.out.println(" ===========================================");
        System.out.println(" *              Room Service               *");
        System.out.println(" ===========================================");        
        System.out.println(" * 1. New Room Service Menu Item           *");
        System.out.println(" * 2. Update Room Service Menu Item        *");
        System.out.println(" * 3. Remove Room Service Menu Item        *");
        System.out.println(" * 4. Create Room Service Order	           *");
        System.out.println(" * 5. Previous          			       *");
        System.out.println(" * 6. Quit                                 *");
    }
        
        public static void createNewMenu(ArrayList<MenuItem>menuList) {
		
		String name = Menu.readString("Enter menu name: ");
		String desc = Menu.readString("Preparation method: ");
		String price = Menu.readString("Enter the item price: ");
		
		MenuItem m = new MenuItem(name, desc, price);
		menuList.add(m);
	}
        
        public static void updateMenu(ArrayList<MenuItem>menuList) {
		//Ask for guest identity as primary key
		String identifier = Menu.readString("Please enter the name of menu item to update: ");
		
		boolean found = false;
		int index = 0;
		
		for (MenuItem m: menuList) {
			System.out.println(m.getName());
			if (identifier.equals(m.getName())) {
				found = true;
				break;
			}
			index++;
		}
		
		if (!found) {
			System.out.println("Menu with name: " + identifier + " not found!");
		} else {
			System.out.println("Menu with name: " + identifier + " found!");
			
			System.out.println(" -------------------------------------------");
			System.out.println("Please enter new menu details ('Enter' key to skip)");
			
			String name = Menu.readString("Enter new menu name: ");
			if (!name.equals("")) 
				menuList.get(index).setName(name);
			
			String desc = Menu.readString("Enter new menu preparation method: ");
			if (!desc.equals("")) 
				menuList.get(index).setDescription(desc);
			
			String price = Menu.readString("Enter new menu price: ");
			if (!price.equals("")) 
				menuList.get(index).setPrice(price);
			
			System.out.println(" -------------------------------------------");
			System.out.println(" Menu item updated!");
		}
	}
        
        public static void removeMenu(ArrayList<MenuItem>menuList) {
		//Ask for guest name as primary key
		String identifier = Menu.readString("Please enter the name of guest you would like to search: ");
		
		boolean found = false;
		int index = 0;
		
		for (MenuItem m: menuList) {
			System.out.println(m.getName());
			if (identifier.equals(m.getName())) {
				found = true;
				break;
			}
			index++;
		}
		
		if (!found) {
			System.out.println("Menu with name: " + identifier + " not found!");
		} else {
			System.out.println("Menu with name: " + identifier + " found!");
			
			System.out.println(" -------------------------------------------");
			menuList.remove(index);
			System.out.println(" -------------------------------------------");
			
		}
	}
        
        public static void createNewRS (ArrayList<MenuItem>menuList, ArrayList<RoomService>serviceList) {
            
		String no = Menu.readString("Enter your room no: ");
                
		if (menuList.isEmpty())
			System.out.print("None");
		else {	
			for (MenuItem m: menuList) {
				String name = m.getName();
                                String desc = m.getDescription();
                                String price = m.getPrice();
				System.out.println(name + " | " + desc + " | " + price);
			}
		}
		String order = Menu.readString("Enter your order: ");
                String quantity = Menu.readString("How many would you like to order ?: ");
                String remark = Menu.readString("Any remark(s) for your order ? ('Enter' key to skip)");
                if (remark.equals(""))
                    remark = "-";
		RoomService rs = new RoomService(no, order, quantity, remark);
                serviceList.add(rs);
	}
	
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
                        Reservation reservation = new Reservation(id, roomID, guestList.get(index).getName(), guestList.get(index).getCreditDetails(), checkIn, checkOut, pax);
                        reservationList.add(reservation);
			System.out.println("Room with room ID: " + roomID + " reserved!");
			System.out.println("Reservation ID: " + id);
                        System.out.println("Please present this ID during check in !");
			
		
		}
        }
	
//Payment Menu ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void paymentMenu() {
		
		int choice = 0;
        
        //Select menu
        do {
        	//Print payment menu
        	printPaymentMenu();
        	
        	//Get user's choice
        	System.out.println(" -------------------------------------------");
            choice = Menu.readInt(" Please enter your choice: ");
            
            switch(choice) {
                case 1: System.out.println("Option 1");
                		break;
                		
                case 4: System.out.println("Returning to main menu...");
						break;
				case 5: System.out.println("Exiting...");
						System.exit(0);
						break;						
				default:System.out.println("Wrong Input. Please input from 1 - 5.");
						break;
            }
            
        } while (choice != 4);
	}
    
    public static void printPaymentMenu() {
    	System.out.println(" ===========================================");
        System.out.println(" *                 Payment                 *");
        System.out.println(" ===========================================");        
        System.out.println(" * 1. Check Payment Details                *");
        System.out.println(" * 2. Update Payment Details               *");
        System.out.println(" * 3. Make Payment                         *");
        System.out.println(" * 4. Previous          			       *");
        System.out.println(" * 5. Quit                                 *");
    }

//Misc

	@SuppressWarnings("resource")
	public static String readString(String prompt) {
		System.out.print(prompt);
		return new java.util.Scanner(System.in).nextLine();
	}
	
	public static int readInt(String prompt) {
		int input = 0;
		boolean valid = false;
		
		while (!valid) {
			try {
				input = Integer.parseInt(readString(prompt));
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("*** Please enter an integer ***");
			}
		}
		return input;
	}
}
