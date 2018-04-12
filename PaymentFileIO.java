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
			String[] arr1 = str.split("\\|");
			int i=0;
			double[] arr = null;
			arr[0]= Double.parseDouble(arr1[0]);
			arr[1]= Double.parseDouble(arr1[1]);
			arr[2]= Double.parseDouble(arr1[2]);
			arr[3]= Double.parseDouble(arr1[3]);
			arr[4]= Double.parseDouble(arr1[4]);
			arr[5]= Double.parseDouble(arr1[5]);
			arr[6]= Double.parseDouble(arr1[6]);
			arr[7]= Double.parseDouble(arr1[7]);
			arr[8]= Double.parseDouble(arr1[8]);
			arr[9]= Double.parseDouble(arr1[9]);
			while(i<=8) {
				arr[i]= Double.parseDouble(arr1[i]);
				i++;
			}
			Payment temp = new Payment(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9]);
			plist.add(temp);
		}
		
		myScanner.close();
	}
	public void export (Payment p, PrintWriter fout) {
		fout.print(p.getTax() + "|");
		fout.print(p.getPromo() + "|");
		fout.print(p.getWifiPrice() + "|");
		fout.print(p.getTvPrice() + "|");
		fout.print(p.getWErates() + "|");
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
