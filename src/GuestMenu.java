import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * GuestMenu is a sub class of Menu class, which handles all the Guests registered in the hotel.
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class GuestMenu extends Menu {

	/**
	 * This method directs the user to the Guest Menu based on user input.
	 * 
	 * @param guestList This list contains the details of all the guest registered in the system.
	 * @param roomList This list contains the details of all the room in the system.
	 * @param reservationList This list contains all the reservations details.
	 * @throws FileNotFoundException
	 */
	public static void guestMenu(ArrayList<Guest>guestList, ArrayList<Room>roomList, 
			ArrayList<Reservation>reservationList) throws FileNotFoundException {
		int choice = 0;
		GuestFileIO gfio = new GuestFileIO();
		RoomFileIO rfio = new RoomFileIO();
		ReservationFileIO refio = new ReservationFileIO();
		//Select menu
		do {
			//Print guest menu
			printGuestMenu();

			//Get user's choice
			System.out.println(" -------------------------------------------");
			choice = readInt(" Please enter your choice: ");
			System.out.println(" -------------------------------------------");

			switch(choice) {
			case 1: System.out.println("Creating new guest");
					createNewGuest(guestList);
					gfio.exportAll(guestList);
					break;
			case 2: System.out.println("Updating guest details");
					updateGuest(guestList,roomList,reservationList);
					gfio.exportAll(guestList);
					rfio.exportAll(roomList);
					refio.exportAll(reservationList);
					break;
			case 3: System.out.println("Searching guest details");
					searchGuest(guestList);
					break;			
			case 4: System.out.println("Returning to main menu...");
					gfio.exportAll(guestList);
					rfio.exportAll(roomList);
					refio.exportAll(reservationList);
					break;
			case 5: System.out.println("Exiting...");
					gfio.exportAll(guestList);
					rfio.exportAll(roomList);
					refio.exportAll(reservationList);
					System.exit(0);
					break;		
			default:System.out.println("Wrong Input. Please input from 1 - 5.");
					break;
			}

		} while (choice != 4);  
	}

	/**
	 * This method prints the Guest Menu to inform the users of the options.
	 */
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

	/**
	 * This method creates a new Guest into the system.
	 * 
	 * @param guestList This list contains the details of all the guest registered in the system.
	 */
	public static void createNewGuest(ArrayList<Guest>guestList) {
		boolean input = false;
		System.out.println("All fields are mandatory");
		String ic = readNonEmptyString("Enter IC Number: ");
		int index;
		try {
			index = genericSearch("getIc",ic,guestList);

			//Ensure that no guest with the input IC is already in the system
			if (index == -1) {
				String name 	= readNonEmptyString("Enter guest name: ");
				String addr 	= readNonEmptyString("Enter guest address: ");
				String country 	= readNonEmptyString("Enter guest country: ");
				String nat 		= readNonEmptyString("Enter guest nationality: ");
				String gender;
				do {
					gender 	= readNonEmptyString("Enter guest gender[(M)ale/(F)emale]: ");		
					switch(gender.toUpperCase()) {
					case "M": 	gender = "Male";
								input = true;
								break;
					case "F": 	gender = "Female";
								input = true;
								break;
					default:  	System.out.println("Please try again with the correct input !"); 
								input = false; 
								break;
					}
				}while(!input);

				String identity;
				do {
					identity = readNonEmptyString("Enter guest identity[(D)riving License/(P)assport]: ");
					switch(identity.toUpperCase()) {
					case "D":	identity = "Driving License";
								input = true;
								break;
					case "P":	identity = "Passport";
								input = true;
								break;
					default:  	System.out.println(" Please try again with the correct input !"); 
								input = false; 
								break;
					}
				}while(!input);

				String ccd		= String.valueOf(readLong("Enter guest credit card detail: "));
				String contact 	= String.valueOf(readInt("Enter guest contact number: "));
				Guest g = new Guest(name, addr, country, gender, nat, identity, ic, ccd, contact);
				guestList.add(g);
				System.out.println("Guest " + name + " is successfully created !");
			} else {
				System.out.println("Guest with identity: " + ic + " has already existed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method updates an existing Guest details which is inside guestList
	 * Updating Guest will also update roomList and reservationList accordingly
	 * 
	 * @param guestList This list contains the details of all the guest registered in the system.
	 * @param roomList This list contains the details of all the room in the system.
	 * @param reservationList This list contains all the reservations details.
	 */
	public static void updateGuest(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) {
		//Ask for guest identity(IC) to update guest

		/*Assumption: identity(IC) and (Passport/Driving License) 
		 cannot be changed as they will be verify upon Guest creation*/

		String IC = readString("Please enter identity of guest to update: ");
		try {
			int index = genericSearch("getIc",IC,guestList);
			boolean input = false;

			//index = -1 if no such guest with the input IC exist in the system
			if (index == -1) {
				System.out.println("Guest with identity: " + IC + " not found!");
			} else {
				System.out.println("Guest with identity: " + IC + " found!");

				Guest g = guestList.get(index);
				
				System.out.println(" -------------------------------------------");
				System.out.println("Please enter new guest details ('Enter' key to skip)");

				String name = readString("Enter new guest name: ");
				if (!name.equals("")) 
					g.setName(name);

				String addr = readString("Enter new guest address: ");
				if (!addr.equals("")) 
					g.setAddress(addr);

				String country = readString("Enter new guest country: ");
				if (!country.equals("")) 
					g.setCountry(country);

				String gender = readString("Enter new guest gender: ");
				if (!gender.equals("")) { 
					do {
						gender 	= readNonEmptyString("Enter guest gender[(M)ale/(F)emale]: ");		
						switch(gender.toUpperCase()) {
						case "M": 	gender = "Male";
									input = true;
									break;
						case "F":	gender = "Female";
									input = true;
									break;
						default:	System.out.println(" Please try again with the correct input !"); 
									input = false; 
									break;
						}
					}while(!input);
					g.setGender(gender);
				}

				String nat = readString("Enter new guest nationality: ");
				if (!nat.equals("")) 
					g.setNationality(nat);

				long ccd = readLong("Enter new guest credit card detail: ");
					g.setCreditDetails(String.valueOf(ccd));

				//Assuming they can put country code
				String contact = readString("Enter new guest contact number: ");
				if (!contact.equals("")) 
					g.setContact(contact);

				//Update reservation and room accordingly
				int roomIndex = genericSearch("getGuestIC", IC, roomList);
				//Guest is currently staying in a room
				if(roomIndex != -1) {
					if (!name.equals("")) 
						roomList.get(roomIndex).setCustomerName(name);
				}
				int reservationIndex = genericSearch("getGuestIC", IC, reservationList);
				//Guest has a reservation
				if (reservationIndex != -1) {
					if (!name.equals("")) 
						reservationList.get(reservationIndex).setGuestName(name);
					if (ccd != -1)
						reservationList.get(reservationIndex).setCreditCard(String.valueOf(ccd));
				}

				System.out.println(" -------------------------------------------");
				System.out.println(" Guest updated!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method allows user to search for a Guest details.
	 * 
	 * @param guestList This list contains the details of all the guest registered in the system.
	 */
	public static void searchGuest(ArrayList<Guest>guestList) {
		//Ask for guest name to search
		String identifier = readString("Please enter name of guest to search: ");

		String IC = guestNameSearch(guestList, identifier);
		if (!IC.equals("")) {
			int index;
			try {
				index = genericSearch("getIc",IC,guestList);
				System.out.println(" -------------------------------------------");
				System.out.println("Name: " + guestList.get(index).getName() + 
						"\nAddress: " + guestList.get(index).getAddress() + 
						"\nCountry: " + guestList.get(index).getCountry() + 
						"\nGender: " + guestList.get(index).getGender() + 
						"\nNationality: " + guestList.get(index).getNationality() + 
						"\nIC:" + guestList.get(index).getIc() +
						"\nIdentity Type: " + guestList.get(index).getIdentity() +
						"\nCredit Card Details: " + guestList.get(index).getCreditDetails() +
						"\nContact: " + guestList.get(index).getContact());
				System.out.println(" -------------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

