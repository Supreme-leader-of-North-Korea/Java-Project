import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * ReservationFileIO handles input and output of data into Reservationlist.txt
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class ReservationFileIO extends FileIO<Reservation>{

	/**The name of the file which the information of the Reservation will be written to and read from	 */
	final static String fileName = "Reservationlist.txt";

	/**Creating a new file object	 */
	final static File file = new File(fileName);

	/**
	 * This method reads in from Reservationlist.txt and add each Reservation object into rlist.
	 * 
	 * @param rlist An Array List which holds all the details from Reservationlist.txt.
	 * @throws FileNotFoundException
	 */
	public void parseList (ArrayList<Reservation> rlist) throws FileNotFoundException{

		// to create file when it does not exist, else Exception will be thrown
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

			Reservation temp = new Reservation(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], 
					Menu.strToDate(arr[4]), Menu.strToDate(arr[5]), arr[6], Reservation.strToReservationStatus(arr[7]), arr[8]);
			rlist.add(temp);
		}

		myScanner.close();
	}

	/**
	 * This method will export one Reservation object to a Reservationlist.txt.
	 * 
	 * @param r The Reservation object that is going to be exported.
	 * @param fout A PrintWriter object.
	 */
	public void export (Reservation r, PrintWriter fout) {
		fout.print(r.getReservationId() + "|");
		fout.print(r.getRoomId() + "|");
		fout.print(r.getGuestName() + "|");
		fout.print(r.getCreditCard() + "|");
		fout.print(Menu.dateToStr(r.getCheckInDate()) + "|");
		fout.print(Menu.dateToStr(r.getCheckOutDate()) + "|");
		fout.print(r.getPax() + "|");
		fout.print(r.getReserveStatus() + "|"); 
		fout.println(r.getGuestIC());
	}

	/**
	 * This method exports every Reservation object stored in the rlist to Reservationlist.txt.
	 * It calls the export function repeatedly until it reaches the end of rlist.
	 * 
	 * @param rlist This ArrayList contains all the new updates made from the user and the existing ones.
	 * @throws FileNotFoundException
	 */
	public void exportAll (ArrayList<Reservation> rlist) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));

		for (Reservation temp: rlist) 
			export (temp, fileOut);

		fileOut.close();
	}
}
