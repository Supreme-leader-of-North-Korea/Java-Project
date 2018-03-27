
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
	
	public static void guestMenu() {
		System.out.println(" ===========================================");
        System.out.println(" *                  Guest                  *");
        System.out.println(" ===========================================");
        System.out.println(" * 1. New Guest                            *");
        System.out.println(" * 2. Update Guest Details                 *");
        System.out.println(" * 3. Search Guest Details                 *");
        System.out.println(" * 4. Previous          			       *");
        System.out.println(" * 5. Quit                        		   *");
    }
    
    public static void roomMenu() {
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
    
    public static void reservationMenu() {
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
    
    public static void roomServiceMenu() {
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
    
    public static void paymentMenu() {
    	System.out.println(" ===========================================");
        System.out.println(" *                 Payment                 *");
        System.out.println(" ===========================================");        
        System.out.println(" * 1. Check Payment Details                *");
        System.out.println(" * 2. Update Payment Details               *");
        System.out.println(" * 3. Make Payment                         *");
        System.out.println(" * 4. Previous          			       *");
        System.out.println(" * 5. Quit                                 *");
    }
}
