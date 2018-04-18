import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceFileIO extends FileIO<RoomService> {

	//Attributes
	final static String fileName = "RoomServiceList.txt";
	final static File file = new File(fileName);

	//Retrieve data from file
	/**
	 * 
	 * @param list
	 * @throws FileNotFoundException
	 */
	public void parseList (ArrayList<RoomService> list) throws FileNotFoundException{
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
			list.add(temp);
		}

		myScanner.close();
	}

	/**
	 * 
	 * @param rs
	 * @param fout
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
	 * 
	 * @param list
	 * @throws FileNotFoundException
	 */
	public void exportAll (ArrayList<RoomService> list) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));

		for (RoomService temp: list) 
			export (temp, fileOut);

		fileOut.close();
	}
}
