import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
	
	public static void mainMenu(){
        System.out.println(" ===========================================");
        System.out.println(" *         Hotel Management System         *");
        System.out.println(" ===========================================");
        System.out.println(" * 1. About Guest                          *");
        System.out.println(" * 2. About Room                           *");
        System.out.println(" * 3. About Reservation                    *");
        System.out.println(" * 4. About Room Service                   *");
        System.out.println(" * 5. Quit                                 *");
    } 
	
//Misc
	// search for guest
	public static String guestNameSearch(ArrayList<Guest>guestList, String identifier) {
		ArrayList<Guest>guestList2 = new ArrayList<Guest>();

		int index = 0;
		int i = 0, number = 1;
		String IC = null;
		for (Guest g: guestList) {
			if (g.getName().toLowerCase().contains(identifier.toLowerCase())) {
				guestList2.add(guestList.get(index));
				i++;
			}
			index++;
		} 
		
		if (guestList2.isEmpty()) {
			System.out.println("There does not exist a Guest: " + identifier);
		} else {
			for (Guest g: guestList2) {
				System.out.println(number + ":" + g.getName() + ", IC No.:" + g.getIC());
				number++;
			}
			System.out.println(number + ":Quit");
				
			int guestNo = Menu.readInt("Please select a guest to see more information.");
			while(guestNo > number || guestNo <= 0){
				guestNo = Menu.readInt("Please select a valid number.");			
			}
			if(guestNo != number) {
				System.out.println(guestNo + ":" + guestList2.get(guestNo-1).getName() +" has been selected!");
				IC = guestList2.get(guestNo-1).getIC();
			}
		}
		return IC;
	}
	
	
	public static int guestICSearch(ArrayList<Guest>guestList, String identifier) {
		
		boolean found = false;
		int index = 0;
		for (Guest g: guestList) {
			if (identifier.equals(g.getIC())) {
				found = true;
				break;
			}
			index++;
		}
		if(found == true) 
			return index;
		else 
			return -1;
	}
	

	public static int roomICSearch(ArrayList<Room>roomList, String identifier) {
		boolean found = false;
		int index = 0;
		for (Room r: roomList) {
			if (identifier.equals(r.getGuestIC())) {
				found = true;
				break;
			}
			index++;
		}
		if(found == true) 
			return index;
		else 
			return -1;
	}
	
	
	public static int roomIDSearch(ArrayList<Room>roomList, String identifier) {
		boolean found = false;
		int index = 0;
		for (Room r: roomList) {
			if (identifier.equals(r.getRoomId())) {
				found = true;
				break;
			}
			index++;
		}
		if(found == true) 
			return index;
		else 
			return -1;
	}
	
	
	
	
	
	
	
	
	
	public static int reservationSearch(ArrayList<Reservation>reservationList, String identifier) {
		boolean found = false;
		int index = 0;
		for (Reservation r: reservationList) {
			if (Integer.parseInt(identifier)==(r.getReservationId())) {
				found = true;
				break;
			}
			index++;
		}		
		if(found == true) 
			return index;
		else 
			return -1;
	}
	
	public static double rsTotal(ArrayList<RoomService>serviceList, String identifier) {
		double total = 0;
		for (RoomService rs: serviceList) {
			if (identifier.equals(rs.getRoomId())) {
				total+=(rs.getPrice()*rs.getQuantity());
			}
		}		
		return total;
	}

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
	
	public static double readDouble(String prompt) {
		double input = 0;
		boolean valid = false;
		
		while (!valid) {
			try {
				input = Double.parseDouble(readString(prompt));
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("*** Please enter a double ***");
			}
		}
		return input;
	}
	
	
	
}
