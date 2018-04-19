import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * RoomFileIO handles input and output of data into RoomList.txt
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class RoomFileIO extends FileIO<Room>{

	/**The name of the file which the information of the Room will be written to and read from	 */
	final static String fileName = "RoomList.txt";

	/**Creating a new file object	 */
	final static File file = new File(fileName);

	/**
	 * This method reads in from RoomList.txt and add each Room object into rlist.
	 * 
	 * @param rlist - An Array List which holds all the details from RoomList.txt.
	 * @throws FileNotFoundException
	 */
	public void parseList (ArrayList<Room> rlist) throws FileNotFoundException{

		// to create file when it does not exist, else Exception will be thrown
		File file = new File(fileName);

		// to create file when it does not exist, else Exception will be thrown
		try { 
			file.createNewFile();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Scanner myScanner = new Scanner (file);
		String str;

		if (myScanner.hasNextLine())
		{
			while (myScanner.hasNextLine() && rlist.size() < 48) {
				str = myScanner.nextLine();

				String[] arr = str.split("\\|");

				Room temp;

				switch (arr[7]) {
				case "single":	temp = new Room_single (arr[0], arr[1], Room.strToBedType(arr[2]), Menu.strToDate(arr[3]), Menu.strToDate(arr[4]), arr[5], Room.strToRoomStatus(arr[6]), arr[8]);
								break;
				case "double":	temp = new Room_double (arr[0], arr[1], Room.strToBedType(arr[2]), Menu.strToDate(arr[3]), Menu.strToDate(arr[4]), arr[5], Room.strToRoomStatus(arr[6]), arr[8]);
								break;
				case "deluxe":	temp = new Room_deluxe (arr[0], arr[1], Room.strToBedType(arr[2]), Menu.strToDate(arr[3]), Menu.strToDate(arr[4]), arr[5], Room.strToRoomStatus(arr[6]), arr[8]);
								break;
				default:		temp = new Room_vip (arr[0], arr[1], Room.strToBedType(arr[2]), Menu.strToDate(arr[3]), Menu.strToDate(arr[4]), arr[5], Room.strToRoomStatus(arr[6]), arr[8]);			   				
								break;
				}
				rlist.add(temp);
			}
		} else {
			//If data list is empty, generate empty rooms

			//Iterate level
			for (int i=2; i<=7; i++) {
				//Iterate running room
				for (int j=1; j<=8; j++) {
					String roomNumber = "0" + i + "0" + j;

					Random generator = new Random();
					String bedType;
					Room temp;

					//Randomly generate bed type
					switch (generator.nextInt(3)) {
					case 0: bedType = "SINGLE_SIZE";
					break;
					case 1: bedType = "DOUBLE_SIZE";
					break;
					default: bedType = "KING_SIZE";
					break;
					}

					//Randomly generate room type
					switch (generator.nextInt(4)) {
					case 0: temp = new Room_single (roomNumber, Room.strToBedType(bedType), Room.RoomStatus.VACANT) ;
					break;
					case 1: temp = new Room_double (roomNumber, Room.strToBedType(bedType), Room.RoomStatus.VACANT) ;
					break;
					case 2: temp = new Room_deluxe (roomNumber, Room.strToBedType(bedType), Room.RoomStatus.VACANT) ;
					break;
					default: temp = new Room_vip (roomNumber, Room.strToBedType(bedType), Room.RoomStatus.VACANT) ;
					break;
					}

					if (temp != null)
						rlist.add(temp);
				}
			}
		}

		while (myScanner.hasNextLine() && rlist.size() < 48) {
			str = myScanner.nextLine();
			String[] arr = str.split("\\|");

			Room temp;

			switch (arr[7]) {
			case "single":	temp = new Room_single (arr[0], arr[1], Room.strToBedType(arr[2]), Menu.strToDate(arr[3]), Menu.strToDate(arr[4]), arr[5], Room.strToRoomStatus(arr[6]), arr[7]);
							break;
			case "double":	temp = new Room_double (arr[0], arr[1], Room.strToBedType(arr[2]), Menu.strToDate(arr[3]), Menu.strToDate(arr[4]), arr[5], Room.strToRoomStatus(arr[6]), arr[7]);
							break;
			case "deluxe":	temp = new Room_deluxe (arr[0], arr[1], Room.strToBedType(arr[2]), Menu.strToDate(arr[3]), Menu.strToDate(arr[4]), arr[5], Room.strToRoomStatus(arr[6]), arr[7]);
							break;
			default:		temp = new Room_vip (arr[0], arr[1], Room.strToBedType(arr[2]), Menu.strToDate(arr[3]), Menu.strToDate(arr[4]), arr[5], Room.strToRoomStatus(arr[6]), arr[7]);
							break;

			}

			rlist.add(temp);
		}

		myScanner.close();
	}

	/**
	 * This method will export one Room object to a RoomList.txt.
	 * 
	 * @param r The Room object that is going to be exported.
	 * @param fout A PrintWriter object.
	 */
	public void export (Room r, PrintWriter fout) {
		fout.print(r.getRoomId() + "|");
		fout.print(r.getCustomerName() + "|");
		fout.print(r.getBedType() + "|");
		fout.print(Menu.dateToStr(r.getCheckInDate()) + "|");
		fout.print(Menu.dateToStr(r.getCheckOutDate()) + "|");
		fout.print(r.getPax() + "|");
		fout.print(r.getRoomStatus() + "|");

		if (r instanceof Room_single)
			fout.print("single");
		else if (r instanceof Room_double)
			fout.print("double");
		else if (r instanceof Room_deluxe)
			fout.print("deluxe");
		else
			fout.print("vip"); 

		fout.println("|" + r.getGuestIC());
	}

	/**
	 * This method exports every Room object stored in the rlist to RoomList.txt.
	 * It calls the export function repeatedly until it reaches the end of rlist.
	 * 
	 * @param rlist This ArrayList contains all the new updates made from the user and the existing ones.
	 * @throws FileNotFoundException
	 */
	public void exportAll (ArrayList<Room> rlist) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));

		for (Room temp: rlist) 
			export(temp, fileOut);

		fileOut.close();
	}
}
