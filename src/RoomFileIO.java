import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RoomFileIO extends FileIO<Room>{
	
	//Attributes
	final static String fileName = "RoomList.txt";
	final static File file = new File(fileName);
	
	//Retrieve data from file
        
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
		
		//Read title 'Room List'
		if (myScanner.hasNextLine())
		{
			myScanner.nextLine();
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
						case 0: bedType = "single";
								break;
						case 1: bedType = "double";
								break;
						default: bedType = "master";
								 break;
					}
					
					//Randomly generate room type
					switch (generator.nextInt(4)) {
						case 0: temp = new Room_single (roomNumber, bedType, Room.RoomStatus.VACANT) ;
								break;
						case 1: temp = new Room_double (roomNumber, bedType, Room.RoomStatus.VACANT) ;
								break;
						case 2: temp = new Room_deluxe (roomNumber, bedType, Room.RoomStatus.VACANT) ;
								break;
						default: temp = new Room_vip (roomNumber, bedType, Room.RoomStatus.VACANT) ;
								 break;
					}
					
					rlist.add(temp);
				}
			}
		}
		
		while (myScanner.hasNextLine() && rlist.size() < 48) {
			str = myScanner.nextLine();
			String[] arr = str.split("\\|");
			
			Room temp;
			
			switch (arr[4]) {
				case "single": temp = new Room_single (arr[0], Integer.parseInt(arr[1]), arr[2], Room.strToRoomStatus(arr[3]));
							   break;
				case "double": temp = new Room_double (arr[0], Integer.parseInt(arr[1]), arr[2], Room.strToRoomStatus(arr[3]));
							   break;
				case "deluxe": temp = new Room_deluxe (arr[0], Integer.parseInt(arr[1]), arr[2], Room.strToRoomStatus(arr[3]));
							   break;
				default: temp = new Room_vip (arr[0], Integer.parseInt(arr[1]), arr[2], Room.strToRoomStatus(arr[3]));
						 break;
			}
			
			rlist.add(temp);
		}
		
		myScanner.close();
	}
	
	public void export (Room r, PrintWriter fout) {
		fout.print(r.getRoomId() + "|");
		fout.print(r.getCustomerName() + "|");
		fout.print(r.getBedType() + "|");
		fout.print(r.getRoomStatus() + "|");
		
		if (r instanceof Room_single)
			fout.println("single");
		else if (r instanceof Room_double)
			fout.println("double");
		else if (r instanceof Room_deluxe)
			fout.println("deluxe");
		else
			fout.println("vip");
	}
	
	public void exportAll (ArrayList<Room> rlist) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));
		
		//First: Output Room List
		fileOut.println("Room List");
		
		for (Room temp: rlist) 
			export(temp, fileOut);
		
		fileOut.close();
	}
}
