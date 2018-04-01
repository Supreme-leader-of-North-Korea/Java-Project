import java.io.FileNotFoundException;
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
	
	public static void guestMenu(ArrayList<Guest>guestList) throws FileNotFoundException {
		
		int choice = 0;
        
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
                		FileIO.exportAll(guestList);
                		break;
                case 3: System.out.println("Searching guest details");
                		searchGuest(guestList);
                		break;			
                case 4: System.out.println("Returning to main menu...");
                		FileIO.exportAll(guestList);
                		break;
				case 5: System.out.println("Exiting...");
						FileIO.exportAll(guestList);
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
		String ccd = Menu.readString("Enter guest credit card detail: ");
		String contact = Menu.readString("Enter guest contact number: ");
		
		Guest g = new Guest(name, addr, country, gender, nat, idt, ccd, contact);
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
	
	public static void roomMenu() {
		
		int choice = 0;
        
        //Select menu
        do {
        	//Print room menu
        	printRoomMenu();
        	
        	//Get user's choice
        	System.out.println(" -------------------------------------------");
            choice = Menu.readInt(" Please enter your choice: ");
            
            switch(choice) {
                case 1: System.out.println("Option 1");
                		break;
                		
                case 7: System.out.println("Returning to main menu...");
						break;
		case 8: System.out.println("Exiting...");
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

//Room Service Menu -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void roomServiceMenu() {
		
		int choice = 0;
        
        //Select menu
        do {
        	//Print room service menu
        	printRoomServiceMenu();
        	
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
	
	public static void printRoomServiceMenu() {
    	System.out.println(" ===========================================");
        System.out.println(" *              Room Service               *");
        System.out.println(" ===========================================");        
        System.out.println(" * 1. New Room Service Menu Item           *");
        System.out.println(" * 2. Update Room Service Menu Item        *");
        System.out.println(" * 3. Remove Room Service Menu Item        *");
        System.out.println(" * 4. Create Room Service Order	           *");
        System.out.println(" * 4. Previous          			       *");
        System.out.println(" * 5. Quit                                 *");
    }
	
//Reservation Menu ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void reservationMenu() {
		
		int choice = 0;
        
        //Select menu
        do {
        	//Print reservation menu
        	printReservationMenu();
        	
        	//Get user's choice
        	System.out.println(" -------------------------------------------");
		choice = Menu.readInt(" Please enter your choice: ");
            
            switch(choice) {
                case 1: System.out.println("Option 1");
                		break;
                		
                case 5: System.out.println("Returning to main menu...");
						break;
		case 6: System.out.println("Exiting...");
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
        System.out.println(" * 5. Previous          			       *");
        System.out.println(" * 6. Quit                                 *");
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
