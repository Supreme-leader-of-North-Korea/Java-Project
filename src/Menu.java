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
				System.out.println(number + ":" + g.getName() + ", IC No.:" + g.getIc());
				number++;
			}
			System.out.println(number + ":Quit");
				
			int guestNo = Menu.readInt("Please select a guest to see more information.");
			while(guestNo > number || guestNo <= 0){
				guestNo = Menu.readInt("Please select a valid number.");			
			}
			if(guestNo != number) {
				System.out.println(guestNo + ":" + guestList2.get(guestNo-1).getName() +" has been selected!");
				IC = guestList2.get(guestNo-1).getIc();
			}
		}
		return IC;
	}
	
	public static int guestICSearch(ArrayList<Guest>guestList, String identifier) {
		
		boolean found = false;
		int index = 0;
		for (Guest g: guestList) {
			if (identifier.equals(g.getIc())) {
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
		for (Reservation re: reservationList) {
			if (identifier.equals(re.getRoomId())) {
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
	//method overriding
	public static int reservationSearch(ArrayList<Reservation>reservationList, int identifier) {
		boolean found = false;
		int index = 0;
		for (Reservation re: reservationList) {
			if (identifier==re.getReservationId()) {
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

	public static int reservationICSearch(ArrayList<Reservation>reservationList, String identifier) {
		boolean found = false;
		int index = 0;
		for (Reservation re: reservationList) {
			if (identifier.equals(re.getGuestIC())) {
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
	public static int menuNameSearch(ArrayList<MenuItem>menuList, String identifier) {
		boolean found = false;
		int index = 0;
		
		for (MenuItem m: menuList) {
			System.out.println(m.getName());
			if (identifier.equals(m.getName())) {
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
	
	public static boolean roomOccupancyCheck(ArrayList<Room>roomList,String roomNo) {
		boolean found = false;
		int index = 0;
		for (Room r: roomList) {
			if (roomNo.equals(r.getRoomId())) {
				found = true;
				break;
			}
			index++;
		}
		if(found) {
			if(roomList.get(index).getRoomStatus().equals(Room.RoomStatus.OCCUPIED)){
				return true;
			}else {
				System.out.println("Room No: " + roomNo + " is currently not occupied!");
				return false;
			}
		}else {
			System.out.println("Room No: " + roomNo + " does not exist!");
			return false;
		}
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

	
	//misc methods
	@SuppressWarnings("resource")
	public static String readString(String prompt) {
		System.out.print(prompt);
		return new java.util.Scanner(System.in).nextLine();
	}
	
	public static String readNonEmptyString(String prompt) {
		String check;
		do {
			check = readString(prompt);
		}while (check.equals(""));
		return check;
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
