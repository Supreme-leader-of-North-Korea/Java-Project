import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GuestMenu extends Menu {
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
                		gfio.exportAll(guestList);
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
		System.out.println("All fields are mandatory");
		
		String name 	= Menu.readString("Enter guest name: "									);
		String addr 	= Menu.readString("Enter guest address: "								);
		String country 	= Menu.readString("Enter guest country: "								);
		String gender 	= Menu.readString("Enter guest gender: "								);
		String nat 		= Menu.readString("Enter guest nationality: "							);
		String idt 		= Menu.readString("Enter guest identity[(D)riving License/(P)assport]: ");
        String ic 		= Menu.readString("Enter IC Number: "									);
		String ccd 		= Menu.readString("Enter guest credit card detail: "					);
		String contact 	= Menu.readString("Enter guest contact number: "						);
		
		int index = guestICSearch(guestList, ic);
		
		if (index == -1) {
			Guest g = new Guest(name, addr, country, gender, nat, idt, ic, ccd, contact);
			guestList.add(g);
		}else {
			System.out.println("Guest with identity: " + ic + " has already existed");
		}
	}
	
	public static void updateGuest(ArrayList<Guest>guestList) {
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
							   "\nIdentity: " + guestList.get(index).getIdentity() +
							   "\nCredit Card Details: " + guestList.get(index).getCreditDetails() +
							   "\nContact: " + guestList.get(index).getContact());
			System.out.println(" -------------------------------------------");
		}
	}
}

