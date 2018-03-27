import java.io.FileNotFoundException;
import java.util.*;

public class Menu {
	
	//List of data
	static ArrayList<Guest>guestList = new ArrayList<Guest>();
	
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
	
	public static void guestMenu() throws FileNotFoundException {
		
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
                		createNewGuest();
                		break;
                		
                case 5: System.out.println("Returning to main menu...");
                		FileIO.exportAll(guestList);
						break;
						
				default:System.out.println("Wrong Input. Please input from 1 - 5.");
						break;
            }
            
        } while (choice != 5);  
	}
	
	public static void printGuestMenu() {
		System.out.println(" ===========================================");
        System.out.println(" *                  Guest                  *");
        System.out.println(" ===========================================");
        System.out.println(" * 1. New Guest                            *");
        System.out.println(" * 2. Update Guest Details                 *");
        System.out.println(" * 3. Search Guest Details                 *");
        System.out.println(" * 4. Previous          			       *");
        System.out.println(" * 5. Quit                        		   *");
    }
	
	public static void createNewGuest() {
		Guest g = new Guest();
		
        g.setName(Menu.readString("Enter guest name: "));
		g.setAddress(Menu.readString("Enter guest address: "));
		g.setCountry(Menu.readString("Enter guest country: "));
		g.setGender(Menu.readString("Enter guest gender: "));
		g.setNationality(Menu.readString("Enter guest nationality: "));
		g.setIdentity(Menu.readString("Enter guest identity[(D)riving License/(P)assport]: "));
		g.setCreditDetails(Menu.readString("Enter guest credit card detail: "));
		g.setContact(Menu.readString("Enter guest contact number: "));
		
		guestList.add(g);
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
                		
                case 5: System.out.println("Returning to main menu...");
						break;
						
				default:System.out.println("Wrong Input. Please input from 1 - 8.");
						break;
            }
            
        } while (choice != 8);  
	}
	
	public static void printRoomMenu() {
    	System.out.println(" ===========================================");
        System.out.println(" *                  Room                   *");
        System.out.println(" ===========================================");
        System.out.println(" * 1. Check Room Status by Guest Name      *");
        System.out.println(" * 2. Check Room Status by Room		       *");
        System.out.println(" * 3. Update Room Details                  *");
        System.out.println(" * 4. Room Check-in            			   *");
        System.out.println(" * 5. Room Check-out           			   *");
        System.out.println(" * 6. Print Room Status statistic report   *");
        System.out.println(" * 7. Previous          			       *");
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
                		
                case 5: System.out.println("Returning to main menu...");
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
						
				default:System.out.println("Wrong Input. Please input from 1 - 6.");
						break;
            }
            
        } while (choice != 6);
		
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
                		
                case 5: System.out.println("Returning to main menu...");
						break;
						
				default:System.out.println("Wrong Input. Please input from 1 - 5.");
						break;
            }
            
        } while (choice != 5);
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
