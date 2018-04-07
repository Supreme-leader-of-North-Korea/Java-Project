import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationFileIO extends FileIO<Reservation>{
	
	//Attributes
	final static String fileName = "Reservationlist.txt";
	final static File file = new File(fileName);
	
	//Retrieve data from file
        
	public void parseList (ArrayList<Reservation> rlist) throws FileNotFoundException{
		
		// to create file when it does not exist, else Exception will be thrown
		try { 
			file.createNewFile();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Scanner myScanner = new Scanner (new File (fileName));
		String str;
		
		if (myScanner.hasNextLine())
			myScanner.nextLine();
		
		while (myScanner.hasNextLine()) {
			str = myScanner.nextLine();
			String[] arr = str.split("\\|");
			
			Reservation temp = new Reservation (Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
			rlist.add(temp);
		}
		
		myScanner.close();
	}
	
	public void export (Reservation r, PrintWriter fout) {
		fout.print(r.getReservationId() + "|");
		fout.print(r.getRoomId() + "|");
		fout.print(r.getCustomerName() + "|");
		fout.print(r.getCreditCard() + "|");
		fout.print(r.getCheckIn() + "|");
		fout.print(r.getCheckOut() + "|");
		fout.println(r.getPax());
	}
	
	public void exportAll (ArrayList<Reservation> rlist) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));
		
		fileOut.println("Guest List");
		
		for (Reservation temp: rlist) 
			export (temp, fileOut);
		
		fileOut.close();
	}
}
