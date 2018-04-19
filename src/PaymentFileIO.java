import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * PaymentFileIO handles input and output of data into PaymentRates.txt
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class PaymentFileIO extends FileIO<Payment>{

	/**The name of the file which the information of the Payment will be written to and read from	 */
	final static String fileName = "PaymentRates.txt";

	/**Creating a new file object	 */
	final static File file = new File(fileName);

	/**
	 * This method reads in from PaymentRates.txt and add each Payment object into plist.
	 * 
	 * @param plist An ArrayList which holds all the details from PaymentRates.txt.
	 * @throws FileNotFoundException
	 */
	public void parseList (ArrayList<Payment> plist) throws FileNotFoundException{
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

			Payment temp = new Payment(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]), Double.parseDouble(arr[2]), 
					Double.parseDouble(arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]), 
					Double.parseDouble(arr[7]), Double.parseDouble(arr[8]), Double.parseDouble(arr[9]));
			plist.add(temp);
		}

		myScanner.close();
	}

	/**
	 * This method will export one Payment object to a PaymentRates.txt.
	 * 
	 * @param p The Payment object that is going to be exported.
	 * @param fout A PrintWriter object.
	 */
	public void export (Payment p, PrintWriter fout) {
		fout.print(p.getTax() + "|");
		fout.print(p.getPromo() + "|");
		fout.print(p.getWifiPrice() + "|");
		fout.print(p.getTvPrice() + "|");
		fout.print(p.getWErates() + "|");
		fout.print(p.getSingleRoomPrice() + "|");
		fout.print(p.getDoubleRoomPrice() + "|");
		fout.print(p.getDeluxeRoomPrice() + "|");
		fout.print(p.getVipRoomPrice() + "|");
		fout.println(p.getOverStayingFine());
	}

	/**
	 * This method exports every Payment object stored in the plist to PaymentRates.txt.
	 * It calls the export function repeatedly until it reaches the end of plist.
	 * 
	 * @param plist This ArrayList contains all the new updates made from the user and the existing ones.
	 * @throws FileNotFoundException
	 */
	public void exportAll (ArrayList<Payment> plist) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));

		for (Payment temp: plist) 
			export (temp, fileOut);

		fileOut.close();
	}
}
