import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Main Menu of the whole system.
 * Contains Share Static methods which can be called by the Sub-Menu
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu,Kok Jia Hui
 * @version 1.0
 */
public class Menu {
	
	/**
	 * This method prints the Main Menu to inform the users of the options.
	 */
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
	
	/**
	 * This method will check the if the identifier can be found in the guestList.
	 * If the identifier can be found in the guestList, the IC (Identity Card Number)
	 * will be return. If the identifier does not exist within the guestList,
	 * an empty string - "" will be return instead.
	 * 
	 * @param guestList	 the ArrayList which contains all guest information.
	 * @param identifier the name of this guest.
	 * @return 			 this Guest's identity card number.
	 */
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

	1
	/**
	 * This Method is a general search template for every classes.
	 * The methodName retrieve result from the list which will be compared against the identifier.
	 * Upon any matches, this method will return the row in which the match has been discovered.
	 * If no matches occur throughout the list, this method will return -1.
	 * 
	 * @param methodName A getter method. The class in which the getter method is derived from is based on the list.
	 * @param identifier the information which the method will compare against.
	 * @param list the ArrayList of a wild card class.
	 * @return The row number of the identifier if it is found in the list. Otherwise, it returns -1. 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static int genericSearch(String methodName, String identifier, ArrayList<?>list) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		int index = 0;
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

	2
	/**
	 * Search for the roomType in the roomList
	 * 
	 * @param roomType 
	 * @param reservationList
	 * @param roomList
	 * @param checkIn
	 * @param checkOut
	 * @return
	 */
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
	
	
	/**
	 * This method will check if the check in and check out dates of a guest that is making a reservation
	 * overlaps with the date which is already present in the reservationList and roomList.
	 * This method is also use for checking if
	 * 
	 * @param roomList An ArrayList which contains the room details.
	 * @param reservationList An ArrayList which contains the reservation details.
	 * @param checkIn The intended check in or start of maintenance date.
	 * @param checkOut The intended check out or end of maintenance date.
	 * @return An ArrayList which contains information of room that does not have overlapping dates.
	 */
	public static  ArrayList<Room> overLappingDateCheck(ArrayList<Room>roomList, ArrayList<Reservation>reservationList, Date checkIn, Date checkOut) {
		ArrayList<Room> roomTemp = new ArrayList<Room>();
		ArrayList<Reservation> reserveTemp = new ArrayList<Reservation>();
		// loop through the ArrayList which only contains the selected room type
		for (Room r: roomList) { 
			/*if user A intended check in date is before user B check out 
			  OR user A intended check out date is after user B check in
			  then user A cannot make reservation for this room */
			if(!dateToStr(r.getCheckOutDate()).equals("null") && !dateToStr(r.getCheckInDate()).equals("null")) {	
				if ( ((checkIn.after(r.getCheckInDate()) || checkIn.compareTo(r.getCheckInDate()) == 0 )  && checkIn.before(r.getCheckOutDate())) || 
						(checkOut.after(r.getCheckInDate()) && checkOut.before(r.getCheckOutDate())) ||
						(checkIn.before(r.getCheckInDate()) && (checkOut.after(r.getCheckOutDate())) || checkOut.compareTo(r.getCheckOutDate()) == 0) ){
					roomTemp.add(r);
				}
			}
		}
		for (Reservation re: reservationList) { 	
				if ( ((checkIn.after(re.getCheckInDate()) || checkIn.compareTo(re.getCheckInDate()) == 0 ) && checkIn.before(re.getCheckOutDate())) || 
						(checkOut.after(re.getCheckInDate()) && checkOut.before(re.getCheckOutDate())) ||
						(checkIn.before(re.getCheckInDate()) && (checkOut.after(re.getCheckOutDate()) || checkOut.compareTo(re.getCheckOutDate()) == 0)) ){
					reserveTemp.add(re);
				}
		}	
		int index;
		for (Reservation re: reserveTemp) { 
			try {
				index = genericSearch("getRoomId", re.getRoomId(), roomList);
				if (index != -1)
					roomTemp.add(roomList.get(index));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		roomList.removeAll(roomTemp);
		return roomList;	
	}

	3
	/**
	 * 
	 * @param serviceList
	 * @param identifier
	 * @return
	 */
	public static double rsTotal(ArrayList<RoomService>serviceList, String identifier) {
		double total = 0;
		for (RoomService rs: serviceList) {
			if (identifier.equals(rs.getRoomId())) {
				total+=(rs.getPrice()*rs.getQuantity());
			}
		}		
		return total;
	}
	
	4
	/**
	 * This methods checks if the roomNo matches with the RoomId field in serviceList.
	 * For all matches, it will be added to itemsOrdered ArrayList and also be removed from serviceList.
	 *  
	 * @param serviceList An ArrayList which 
	 * @param menuList 
	 * @param roomList
	 * @param roomNo
	 * @return A RoomService ArrayList of data when the roomNo matches the room ID field in serviceList 
	 */
	public static ArrayList<RoomService> roomServiceSearch(ArrayList<RoomService>serviceList, ArrayList<MenuItem>menuList, ArrayList<Room>roomList, String roomNo){
		ArrayList<RoomService> itemsOrdered = new ArrayList<RoomService>();
		try {
			int roomIndex = genericSearch("getRoomId",roomNo,roomList);
			if (roomIndex != -1 ) {
				for (RoomService s: serviceList) {
					if (roomNo.equals(s.getRoomId())) {
						itemsOrdered.add(s);
					}
				}
				serviceList.removeAll(itemsOrdered);
			} else {
				System.out.println("Room does not exist");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemsOrdered;
	}

	/**
	 * This method read and returns the user input 
	 * @param prompt A prompt message to request for user input.
	 * @return the user input in String.
	 */
	@SuppressWarnings("resource")
	// Method to read user input and output it to a string
	public static String readString(String prompt) {
		System.out.print(prompt);
		return new java.util.Scanner(System.in).nextLine().trim();
	}
	
	
	/**
	 * This method read and returns user input. 
	 * It will be invoke repeatedly if the user input is empty.
	 * @param prompt A prompt message to request for user input.
	 * @return the user input in String.
	 */
	public static String readNonEmptyString(String prompt) {
		String check;
		do {
			check = readString(prompt);
		}while (check.equals(""));
		return check;
	}
	
	/**
	 * This method to read user input and parse it into a integer type format.
	 * Any inputs which are negative or 0 will cause the readInt function to be invoke 
	 * repeatedly until the user input is able to be parse into a integer format.
	 * An empty input will return -1.
	 * 
	 * @param prompt A prompt message to request for user input.
	 * @return integer format of the user input.
	 */
	public static int readInt(String prompt) {
		int input = 0;
		boolean valid = false;
		String inputValidate;
		while (!valid) {
			try {
				inputValidate = readString(prompt);
				if (!inputValidate.equals(""))
					input =Integer.parseInt(inputValidate);
				else
					return -1;
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
	
	/**
	 * This method to read user input and parse it into a long type format.
	 * Any inputs which are negative will cause the readLong function to be invoke 
	 * repeatedly until the user input is able to be parse into a long format.
	 * An empty input will return -1.
	 * 
	 * @param prompt A prompt message to request for user input.
	 * @return long format of the user input.
	 */
	public static long readLong(String prompt) {
		long input = 0;
		boolean valid = false;
		String inputValidate;
		while (!valid) {
			try {
				inputValidate = readString(prompt);
				if (!inputValidate.equals(""))
					input = Long.parseLong(inputValidate);
				else
					return -1;
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
	
	/**
	 * This method to read user input and parse it into a double type format.
	 * Any inputs which are negative will cause the readDouble function to be invoke 
	 * repeatedly until the user input is able to be parse into a double format.
	 * An empty input will return -1.
	 * 
	 * @param prompt A prompt message to request for user input.
	 * @return double format of the user input.
	 */
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
    
	/**
	 * This method read the user input and parse it into date type format.
	 * If the user input is unparsable, the readDate function will be invoke 
	 * infinitely until the user input is able to be parse into a date format.
	 * 
	 * @param prompt A prompt message to request for user input.
	 * @return Date format of the user input.
	 */
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

	/**
	 * This method converts String to Date format. 
	 * If date is a string "null", the function will return null.
	 * @param date the date which will be formatted from string into date.
	 * @return Date format of the date and null when date is a string "null".
	 */
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
 
	/**
	 * This method converts Date to String format. 
	 * If the date cannot be formatted into String,
	 * this function will return a String "null"
	 * 
	 * @param date the date which will be formatted from date into String.
	 * @return String format of the date and string "null" if date cannot be formatted.
	 */
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