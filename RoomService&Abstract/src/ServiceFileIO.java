import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceFileIO extends FileIO<RoomService> {
	
	//Attributes
	final static String fileName = "roomServiceList.txt";
	final static File file = new File(fileName);
	
	//Retrieve data from file
        
	public  void parseList (ArrayList<RoomService> list) throws FileNotFoundException{
		try {
			file.createNewFile();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Scanner myScanner = new Scanner (new File (fileName));
		String str;
		
		//Read title 'Guest List'
		if (myScanner.hasNextLine())
			myScanner.nextLine();
		
		while (myScanner.hasNextLine()) {
			str = myScanner.nextLine();
			String[] arr = str.split("\\|");
			
			RoomService temp = new RoomService (arr[0], arr[1]);
			list.add(temp);
		}
		
		myScanner.close();
	}
	
	public  void export (RoomService rs, PrintWriter fout) {
		fout.print(rs.getRoomNo() + "|");
		fout.print(rs.getRemarks() + "|");
		fout.println(rs.getStatus() + "|");
	}
	
	public  void exportAll (ArrayList<RoomService> list) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));
		
		fileOut.println("Guest List");
		
		for (RoomService temp: list) 
			export (temp, fileOut);
		
		fileOut.close();
	}
}
