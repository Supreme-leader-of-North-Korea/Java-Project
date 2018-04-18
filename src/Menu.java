import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	// Guest Search based on guest name.
	public static String guestNameSearch(ArrayList<Guest>guestList, String identifier) {
		ArrayList<Guest>guestList2 = new ArrayList<Guest>();

		int index = 0;
		int number = 1;
		String IC = "";
		for (Guest g: guestList) {
			if (g.getName().toLowerCase().contains(identifier.toLowerCase())) {
				guestList2.add(guestList.get(index));
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

			int guestNo = readInt("Please select a guest to see more information.");
			while(guestNo > number || guestNo <= 0){
				guestNo = readInt("Please select a valid number.");			
			}
			if(guestNo != number) {
				System.out.println(guestNo + ":" + guestList2.get(guestNo-1).getName() +" has been selected!");
				IC = guestList2.get(guestNo-1).getIc();
			}
		}
		return IC;
	}

	// General search method for all classes
	// methodName - take in the method name which user wants to call
	// identifier - search term to search against
	// ArrayList - take in ArrayList 
	public static int genericSearch(String methodName, String identifier, ArrayList<?>list) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		int index = 0;
		//Class<?>[] paramType = {String.class};
		boolean found = false;
		
		for (Object o: list) {
			Method method = o.getClass().getMethod(methodName);
			if(methodName == "getReservationId") {
				int comparator = (int) method.invoke(o);
				if (Integer.parseInt(identifier) == comparator) {
					found = true;
					break;
				}
				index++;
			} else {
				String comparator = (String) method.invoke(o);
				if (identifier.equals(comparator)) {
					found = true;
					break;
				}
				index++;
			}
		}
		if (found == true)
			return index;
		else
			return -1;
	}

	public static ArrayList<Room> searchRoomType(String roomType, ArrayList<Reservation>reservationList ,ArrayList<Room>roomList, Date checkIn, Date checkOut) {
		ArrayList<Room> temp = new ArrayList<Room>();
		//Populate temp arrayList with user selected type of room
		for (Room r: roomList) { 
			if (r instanceof Room_single && roomType.equals("SINGLE")){ 	
				temp.add(r);
			}
			if(r instanceof Room_double && roomType.equals("DOUBLE")){ 	
				temp.add(r);
			}
			if(r instanceof Room_deluxe && roomType.equals("DELUXE")){ 	
				temp.add(r);
			}
			if(r instanceof Room_vip && roomType.equals("VIP")){ 	
				temp.add(r);
			}
		}
		
		//check if any booking date overlaps with the user intended check in and check out. 
		temp = overLappingDateCheck(temp, reservationList, checkIn, checkOut);
		if (temp.size() != 0)
			System.out.println("Please select one of the room for booking");
		for (Room r: temp) { 
			System.out.println("Room No. :" + r.getRoomId());
		}
		return temp;
	}
	
	public static  ArrayList<Room> overLappingDateCheck(ArrayList<Room>roomList, ArrayList<Reservation>reservationList, Date checkIn, Date checkOut) {
		ArrayList<Room> roomTemp = new ArrayList<Room>();
		ArrayList<Reservation> reserveTemp = new ArrayList<Reservation>();
		// loop through the ArrayList which only contains the selected room type
		for (Room r: roomList) { 
			/*if user A intended check in date is before user B check out 
			  OR user A intended check out date is after user B check in
			  then user A cannot make reservation for this room */
			if(!dateToStr(r.getCheckOutDate()).equals("null") && !dateToStr(r.getCheckInDate()).equals("null")) {	
				if ( (checkIn.after(r.getCheckInDate())  && checkIn.before(r.getCheckOutDate())) || 
						(checkOut.after(r.getCheckInDate()) && checkOut.before(r.getCheckOutDate())) ||
						(checkIn.before(r.getCheckInDate()) && checkOut.after(r.getCheckOutDate())) ){
					roomTemp.add(r);
				}
			}
		}
		for (Reservation re: reservationList) { 	
				if ( (checkIn.after(re.getCheckInDate())  && checkIn.before(re.getCheckOutDate())) || 
						(checkOut.after(re.getCheckInDate()) && checkOut.before(re.getCheckOutDate())) ||
						(checkIn.before(re.getCheckInDate()) && checkOut.after(re.getCheckOutDate())) ){
					reserveTemp.add(re);
				}
		}	
		int index;
		for (Reservation re: reserveTemp) { 
			try {
				index = genericSearch("getRoomId", re.getRoomId(), roomList);
				roomTemp.add(roomList.get(index));
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
					| SecurityException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		roomList.removeAll(roomTemp);
		return roomList;	
	}

	//Service Search
	public static double rsTotal(ArrayList<RoomService>serviceList, String identifier) {
		double total = 0;
		for (RoomService rs: serviceList) {
			if (identifier.equals(rs.getRoomId())) {
				total+=(rs.getPrice()*rs.getQuantity());
			}
		}		
		return total;
	}
	
	public static ArrayList<RoomService> roomServiceSearch(ArrayList<RoomService>serviceList, ArrayList<MenuItem>menuList, ArrayList<Room>roomList, String roomNo){
		ArrayList<RoomService> itemsOrdered = new ArrayList<RoomService>();
		ArrayList<RoomService> temp = new ArrayList<RoomService>();
		try {
			int roomIndex = genericSearch("getRoomId",roomNo,roomList);
			if (roomIndex != -1 ) {
				for (RoomService s: serviceList) {
					temp.add(s);
					if (roomNo.equals(s.getRoomId())) {
						itemsOrdered.add(s);
					}
				}
				serviceList.removeAll(itemsOrdered);
			} else {
				System.out.println("Room does not exist");
			}

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemsOrdered;
	}
	//misc methods
	@SuppressWarnings("resource")
	// Method to read user input and output it to a string
	public static String readString(String prompt) {
		System.out.print(prompt);
		return new java.util.Scanner(System.in).nextLine().trim();
	}
	// Method to read user input and output it to a string, not allowing empty field as an input
	public static String readNonEmptyString(String prompt) {
		String check;
		do {
			check = readString(prompt);
		}while (check.equals(""));
		return check;
	}
	// Method to read user input and output it to a integer
	public static int readInt(String prompt) {
		int input = 0;
		boolean valid = false;

		while (!valid) {
			try {
				input = Integer.parseInt(readString(prompt));
				if (input > 0)
					valid = true;
				else 
					System.out.println("*** Please enter a positive number ***");
			} catch (NumberFormatException e) {
				System.out.println("*** Please enter an integer ***");
			}
		}
		return input;
	}
	// Method to read user input and output it to a long 
	public static long readLong(String prompt) {
		long input = 0;
		boolean valid = false;

		while (!valid) {
			try {
				input = Long.parseLong(readString(prompt));
				if (input > 0)
					valid = true;
				else 
					System.out.println("*** Please enter a positive number ***");
			} catch (NumberFormatException e) {
				System.out.println("*** Please enter an integer ***");
			}
		}
		return input;
	}
	// Method to read user input and output it to a double 
	public static double readDouble(String prompt) {
		double input = 0;
		boolean valid = false;
		String inputValidate;
		while (!valid) {
			try {
				inputValidate = readString(prompt);
				if (!inputValidate.equals(""))
					input = Double.parseDouble(inputValidate);
				else
					return -1;
				if (input > 0)
					valid = true;
				else 
					System.out.println("*** Please enter a positive number ***");
			} catch (NumberFormatException e) {
				System.out.println("*** Please enter a double ***");
			}
		}
		return input;
	}
    // Method to read user input and output it to a Date
	public static Date readDate(String prompt) {
            boolean input = false;
            Date t = null;
            do {
		String result = readNonEmptyString(prompt);		
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
                    t = new Date();
                    try {
			t = ft.parse(result);
			input = true;
                    } catch (ParseException e) { 
			System.out.println("Please enter a valid date");
                    }	
            }while (!input);  
		return t;
	}
	// Method to convert String to Date
	public static Date strToDate(String date) {
		boolean input = false;
		if(!date.equals("null")) {		
			SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
			Date t = new Date();
			try {
				t = ft.parse(date);
				input = true;
			} catch (ParseException e) { 
				System.out.println("Please enter a valid date");
				input = false;
			}
			if(input)
				return t;
		}
		return null;
	}
    // Method to convert Date to String
	public static String dateToStr(Date date) {
		try
		{
			SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
			return ft.format(date);
		} catch (Exception e) {
			return "null";
		}
	}

}