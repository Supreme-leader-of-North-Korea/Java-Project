import java.io.FileNotFoundException;
import java.util.*;

public class HRPSApp {

    public static void main(String[] args) throws FileNotFoundException{
    	
        int choice = 0;
        
        //Select menu
        do {
        	//Print main menu
            Menu.mainMenu();
        	
            //Get user's choice
            System.out.println(" -------------------------------------------");
            choice = Menu.readInt(" Please enter your choice: ");
            
            switch(choice) {
                case 1: Menu.guestMenu();
                		break;
				case 2: Menu.roomMenu();		
						break;
				case 3: Menu.roomServiceMenu();		
						break;
				case 4: Menu.reservationMenu();		
						break;
				case 5: Menu.paymentMenu();
						break;
				case 6: System.out.println("Exiting. Goodbye!");
						break;
				default:System.out.println("Wrong Input. Please input from 1 - 6.");
						break;
            }
       
        } while (choice != 6);
    }
}