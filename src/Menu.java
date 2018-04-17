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
	//Guest Search
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

	public static String[] searchRoomType(ArrayList<Room>roomList, Date checkIn, Date checkOut) {
		int index = 0;
		String roomNo;
		boolean input = false;
		int size = 48;
		String [] roomarr = new String[size];
		int i = 0;
		String roomType = readNonEmptyString("Please Enter the type of room "
				+ "you would like to enquiry [(S)ingle / d(O)uble / d(E)luxe / (V)ip]");
		do {
			if (roomType.length() == 1) {
                            switch (roomType.charAt(0)) {
                                    case 'S':	roomType = "SINGLE";
                                                            input = true;
                                                            break;
                                    case 'O':	roomType = "DOUBLE";
                                                            input = true;
                                                            break;
                                    case 'E':	roomType = "DELUXE";
                                                            input = true;
                                                            break;
                                    case 'V':	roomType = "VIP";
                                                            input = true;
                                                            break;
                                    default:	roomType = readNonEmptyString("Please Enter the correct type of room "
                                                    + "you would like to enquiry [(S)ingle / d(O)uble / d(E)luxe / (V)ip]");
                                                            input = false;
                                                            break;
                            }
                        } else 
                            roomType = readNonEmptyString("Please Enter the correct type of room "
                                                    + "you would like to enquiry [(S)ingle / d(O)uble / d(E)luxe / (V)ip]");
		}while(!input);
		
		for (Room r: roomList) {
			roomNo = r.getRoomId(); 
			if (r instanceof Room_single && roomType.equals("SINGLE")){ 	
				input = statusCheck (r, index, roomNo, checkIn, checkOut);
				if (input) {
					roomarr[i] = roomNo;
					i++;
				}
			}
			if(r instanceof Room_double && roomType.equals("DOUBLE")){ 	
				input = statusCheck (r, index, roomNo, checkIn, checkOut);
				if (input) {
					roomarr[i] = roomNo;
					i++;
				}
			}
			if(r instanceof Room_deluxe && roomType.equals("DELUXE")){ 	
				input = statusCheck (r, index, roomNo, checkIn, checkOut);
				if (input) {
					roomarr[i] = roomNo;
					i++;
				}
			}
			if(r instanceof Room_vip && roomType.equals("VIP")){ 	
				input = statusCheck (r, index, roomNo, checkIn, checkOut);
				if (input) {
					roomarr[i] = roomNo;
					i++;
				}
			}

			index++;
		}
		return roomarr;
	}
	

	public static boolean statusCheck (Room r, int index, String roomNo, Date checkIn, Date CheckOut) {
		if (strConvertDate(r.getCheckInDate()).equals("null") && 
				strConvertDate(r.getCheckOutDate()).equals("null")) {
			if (r.getRoomStatus().equals(Room.RoomStatus.VACANT)) {
				System.out.println(" Room No: " + roomNo + " is " + r.getRoomStatus() + "!");
				return true;
			}
		} else {
			if(checkIn.after(r.getCheckOutDate()) || CheckOut.before(r.getCheckInDate())) {
				System.out.println(" Room No: " + roomNo + " is available!");
				// might not want to print this
				System.out.println("It is " + r.getRoomStatus() 
				+ " from "+ r.getCheckInDate() + " to " + r.getCheckOutDate());
				
				return true;
			}
		}
		return false;
		
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
					if (roomNo.equals(s.getRoomId()) && !s.getStatus().equals(RoomService.Status.DELIVERED)) {
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
	public static String readString(String prompt) {
		System.out.print(prompt);
		return new java.util.Scanner(System.in).nextLine().trim();
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
	
	public static double readDouble(String prompt) {
		double input = 0;
		boolean valid = false;

		while (!valid) {
			try {
				input = Double.parseDouble(readString(prompt));
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
        
	public static String strConvertDate(Date date) {
		try
		{
			SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
			return ft.format(date);
		} catch (Exception e) {
			return "null";
		}
	}

}
