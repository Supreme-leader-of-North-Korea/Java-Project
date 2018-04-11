import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PaymentFileIO extends FileIO<Payment>{
	
	//Attributes
	final static String fileName = "PaymentRates.txt";
	final static File file = new File(fileName);
	
	//Retrieve data from file
        
	public void parseList (ArrayList<Payment> plist) throws FileNotFoundException{
		
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
			
			Payment temp = new Payment(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8]);
			plist.add(temp);
		}
		
		myScanner.close();
	}
	
	public void export (Payment p, PrintWriter fout) {
		fout.print(p.getTax() + "|");
		fout.print(p.getPromo() + "|");
		fout.print(p.getSingleSizePrice() + "|");
		fout.print(p.getDoubleSizePrice() + "|");
		fout.print(p.getKingSizePrice() + "|");
		fout.print(p.getSingleRoomPrice() + "|");
		fout.print(p.getDoubleRoomPrice() + "|");
		fout.print(p.getDeluxeRoomPrice() + "|");
		fout.println(p.getVipRoomPrice() + "|");
	}
	
	public void exportAll (ArrayList<Payment> plist) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));
		
		fileOut.println("Payment Rates");
		
		for (Payment temp: plist) 
			export (temp, fileOut);
		
		fileOut.close();
	}
}