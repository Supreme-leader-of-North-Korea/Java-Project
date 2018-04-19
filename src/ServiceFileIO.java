import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ServiceFileIO handles input and output of data into RoomServiceList.txt
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class ServiceFileIO extends FileIO<RoomService> {

	/**The name of the file which the information of the Room service will be written to and read from	 */
	final static String fileName = "RoomServiceList.txt";
	
	/**Creating a new file object	 */
	final static File file = new File(fileName);

	/**
	 * This method reads in from RoomServiceList.txt and add each RoomService object into rslist.
	 * 
	 * @param rslist An ArrayList which holds all the details from RoomServiceList.txt.
	 * @throws FileNotFoundException
	 */
	public void parseList (ArrayList<RoomService> rslist) throws FileNotFoundException{
		try {
			file.createNewFile();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Scanner myScanner = new Scanner (new File (fileName));
		String str;

		while (myScanner.hasNextLine()) {
			str = myScanner.nextLine();
			String[] arr = str.split("\\|");

			RoomService temp = new RoomService (arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), 
					arr[3], Double.parseDouble(arr[4]), RoomService.strToStatus(arr[5]));
			rslist.add(temp);
		}

		myScanner.close();
	}

	/**
	 * This method will export one Room object to a RoomServiceList.txt.
	 * 
	 * @param rs The RoomService object that is going to be exported.
	 * @param fout A PrintWriter object.
	 */
	public void export (RoomService rs, PrintWriter fout) {
		fout.print(rs.getRoomId() + "|");
		fout.print(rs.getMenuItemNo() + "|");
		fout.print(rs.getQuantity() + "|");
		fout.print(rs.getRemarks() + "|");
		fout.print(rs.getPrice() + "|");
		fout.println(rs.getStatus());
	}

	/**
	 * This method exports every RoomService object stored in the list to RoomServiceList.txt.
	 * It calls the export function repeatedly until it reaches the end of list.
	 * 
	 * @param rslist This ArrayList contains all the new updates made from the user and the existing ones.
	 * @throws FileNotFoundException
	 */
	public void exportAll (ArrayList<RoomService> rslist) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));

		for (RoomService temp: rslist) 
			export (temp, fileOut);

		fileOut.close();
	}
}
