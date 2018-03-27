import java.util.Scanner;

public class HRPSApp {

    public static void main(String[] args) {
    	
        int choice;
        
        //Open scanner
        Scanner sc = new Scanner(System.in);
        
        //Print menu
        Menu.mainMenu();
        
        //Select menu
        do {
            choice = sc.nextInt();
            
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
        
        //Close Scanner
        sc.close();
    }
}