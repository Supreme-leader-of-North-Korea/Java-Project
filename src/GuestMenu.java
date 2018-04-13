import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GuestMenu extends Menu {
//Guest Menu ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
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
            choice = Menu.readInt(" Please enter your choice: ");
            System.out.println(" -------------------------------------------");
            
            switch(choice) {
                case 1: System.out.println("Creating new guest");
                		createNewGuest(guestList);
                		gfio.exportAll(guestList);
                		break;
                case 2: System.out.println("Updating guest details");
                		updateGuest(guestList,roomList,reservationList);
                		gfio.exportAll(guestList);
                        rfio.exportAll(roomList);;
                        refio.exportAll(reservationList);
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
		boolean input = false;
		
		System.out.println("All fields are mandatory");
			
		String name 	= Menu.readNonEmptyString("Enter guest name: "									);
		String addr 	= Menu.readNonEmptyString("Enter guest address: "								);
		String country 	= Menu.readNonEmptyString("Enter guest country: "								);
		String nat 		= Menu.readNonEmptyString("Enter guest nationality: "							);
		String gender;
		do {
			gender 	= Menu.readNonEmptyString("Enter guest gender: [(M)ale/(F)emale]"			);		
			switch(gender.toUpperCase()) {
		    	case "M": gender = "Male";
			  			  input = true;
			  			  break;
		        case "F": gender = "Female";
			  			  input = true;
			  			  break;
		        default:  System.out.println(" Please try again with the correct input !"); 
		           		  input = false; 
		           		  break;
			}
		}while(!input);
		String idt;
		do {
			idt 	= Menu.readNonEmptyString("Enter guest identity[(D)riving License/(P)assport]: ");
			switch(idt.toUpperCase()) {
		    	case "D": idt = "Driving License";
		    			  input = true;
		    			  break;
		        case "P": idt = "Passport";
		        		  input = true;
		                  break;
		        default:  System.out.println(" Please try again with the correct input !"); 
		           		  input = false; 
		           		  break;
			}
		}while(!input);
        String ic 		= Menu.readNonEmptyString("Enter IC Number: "									);
		String ccd 		= Menu.readNonEmptyString("Enter guest credit card detail: "					);
		String contact 	= Menu.readNonEmptyString("Enter guest contact number: "						);
		
		int index = guestICSearch(guestList, ic);
		
		if (index == -1) {
			Guest g = new Guest(name, addr, country, gender, nat, idt, ic, ccd, contact);
			guestList.add(g);
		}else {
			System.out.println("Guest with identity: " + ic + " has already existed");
		}
	}
	
	public static void updateGuest(ArrayList<Guest>guestList, ArrayList<Room>roomList, ArrayList<Reservation>reservationList) {
		//Ask for guest identity as primary key
		String identifier = Menu.readString("Please enter identity of guest to update: ");
		int index = guestICSearch(guestList, identifier);
		
		if (index == -1) {
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
				guestList.get(index).setCreditDetails(ccd);
				
			
			String contact = Menu.readString("Enter new guest contact number: ");
			if (!contact.equals("")) 
				guestList.get(index).setContact(contact);

			
			//Update reservation and room accordingly
			int roomIndex = roomICSearch(roomList,identifier);
			//Guest is currently staying in a room
			if(roomIndex != -1) {
				if (!name.equals("")) 
					roomList.get(roomIndex).setCustomerName(name);
			}
			int reservationIndex = reservationICSearch(reservationList,identifier);
			//Guest has a reservation
			if (reservationIndex != -1) {
				if (!name.equals("")) 
					reservationList.get(reservationIndex).setGuestName(name);
				if (!ccd.equals(""))
					reservationList.get(reservationIndex).setCreditCard(ccd);
			}
			
			System.out.println(" -------------------------------------------");
			System.out.println(" Guest updated!");
		}
	}
	
	public static void searchGuest(ArrayList<Guest>guestList) {
		//Ask for guest name to search
		String identifier = Menu.readString("Please enter name of guest to update: ");
		
		String IC = guestNameSearch(guestList, identifier);
		if (IC != null) {
			int index = guestICSearch(guestList, IC);
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
		}
	}
}

