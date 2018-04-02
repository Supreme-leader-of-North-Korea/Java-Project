import java.io.FileNotFoundException;
import java.util.*;

public class HRPSApp {

    public static void main(String[] args) throws FileNotFoundException{
    	
    	//List of data
    	ArrayList<Guest>guestList = new ArrayList<Guest>();
    	ArrayList<Room>roomList = new ArrayList<Room>();
    	ArrayList<MenuItem>menuList = new ArrayList<MenuItem>();
        ArrayList<RoomService>serviceList = new ArrayList<RoomService>();
    	//Parse and populate guest list from data file
    	//FileIO.parseDataList(guestList, roomList);
    	
        GuestFileIO gfio = new GuestFileIO();
        ServiceFileIO sfio = new ServiceFileIO();
        MenuFileIO mfio = new MenuFileIO();
        RoomFileIO rfio = new RoomFileIO();
        rfio.parseList(roomList);
    	gfio.parseList(guestList);
    	sfio.parseList(serviceList);
        mfio.parseList(menuList);
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
				case 3: Menu.reservationMenu();	
						break;
				case 4: Menu.roomServiceMenu(serviceList, menuList);		
						break;
				case 5: Menu.paymentMenu();
						break;
				case 6: System.out.println("Exiting. Goodbye!");
						gfio.exportAll(guestList);
                                                rfio.exportAll(roomList);
                                                sfio.exportAll(serviceList);
                                                mfio.exportAll(menuList);
						break;
				default:System.out.println("Wrong Input. Please input from 1 - 6.");
						break;
            }
       
        } while (choice != 6);
    }
}