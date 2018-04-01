import java.io.FileNotFoundException;
import java.util.*;

public class HRPSApp {

    public static void main(String[] args) throws FileNotFoundException{
    	
    	//List of data
    	ArrayList<Guest>guestList = new ArrayList<Guest>();
    	ArrayList<Room>roomList = new ArrayList<Room>();
    	
    	//Parse and populate guest list from data file
    	FileIO.parseDataList(guestList, roomList);
    	
        int choice = 0;
        
        //Select menu
        do {
        	//Print main menu
            Menu.mainMenu();
        	
            //Get user's choice
            System.out.println(" -------------------------------------------");
            choice = Menu.readInt(" Please enter your choice: ");
            
            switch(choice) {
                case 1: Menu.guestMenu(guestList, roomList);
                		break;
				case 2: Menu.roomMenu(guestList, roomList);		
						break;
				case 3: Menu.roomServiceMenu();		
						break;
				case 4: Menu.reservationMenu();		
						break;
				case 5: Menu.paymentMenu();
						break;
				case 6: System.out.println("Exiting. Goodbye!");
						FileIO.exportAll(guestList, roomList);
						break;
				default:System.out.println("Wrong Input. Please input from 1 - 6.");
						break;
            }
       
        } while (choice != 6);
    }
}