import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuFileIO extends FileIO<MenuItem> {
	
	//Attributes
	final static String fileName = "MenuList.txt";
	final static File file = new File(fileName);
	
	//Retrieve data from file
        
	public void parseList (ArrayList<MenuItem> list) throws FileNotFoundException{
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
			
			MenuItem temp = new MenuItem (arr[0], arr[1], Double.parseDouble(arr[2]));
			list.add(temp);
		}
		
		myScanner.close();
	}
	
	public void export (MenuItem m, PrintWriter fout) {
		fout.print(m.getName() + "|");
		fout.print(m.getDescription() + "|");
		fout.println(m.getPrice() + "|");
		
	}
	
	public void exportAll (ArrayList<MenuItem> list) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));
		
		fileOut.println("Menu List");
		
		for (MenuItem temp: list) 
			export (temp, fileOut);
		
		fileOut.close();
	}
}
