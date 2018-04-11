import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RoomServiceMenu extends Menu {
	//Room Service Menu -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
		public static void roomServiceMenu(ArrayList<RoomService>serviceList, ArrayList<MenuItem>menuList) throws FileNotFoundException{
			
			int choice = 0;
	        MenuFileIO mfio = new MenuFileIO();
	        ServiceFileIO sfio = new ServiceFileIO();
	        //Select menu
	        do {
	        	//Print room service menu
	        	printRoomServiceMenu();
	        	
	        	//Get user's choice
	        	System.out.println(" -------------------------------------------");
	            choice = Menu.readInt(" Please enter your choice: ");
	            
	            switch(choice) {
	                case 1: System.out.println("Creating new menu item...");
	                        createNewMenu(menuList);
	                	break;
	                case 2: System.out.println("Updating menu details...");
	                	updateMenu(menuList);
	                	mfio.exportAll(menuList);
	                	break;
	                case 3: System.out.println("Removing menu details...");
	                	removeMenu(menuList);
	                	mfio.exportAll(menuList);
	                	break;
	                case 4: createNewRS(menuList, serviceList);
	                        sfio.exportAll(serviceList);
	                        break;
	                case 5: System.out.println("Returning to main menu...");
	                        mfio.exportAll(menuList);
				break;
			case 6: System.out.println("Exiting...");
	                        mfio.exportAll(menuList);
				System.exit(0);
				break;				
			default:System.out.println("Wrong Input. Please input from 1 - 5.");
				break;
	            }
	            
	        } while (choice != 5);
		}
		
		public static void printRoomServiceMenu() {
	    	System.out.println(" ===========================================");
	        System.out.println(" *              Room Service               *");
	        System.out.println(" ===========================================");        
	        System.out.println(" * 1. New Room Service Menu Item           *");
	        System.out.println(" * 2. Update Room Service Menu Item        *");
	        System.out.println(" * 3. Remove Room Service Menu Item        *");
	        System.out.println(" * 4. Create Room Service Order	           *");
	        System.out.println(" * 5. Previous          			       *");
	        System.out.println(" * 6. Quit                                 *");
	    }
	        
	        public static void createNewMenu(ArrayList<MenuItem>menuList) {
			
			String name = Menu.readString("Enter menu name: ");
			String desc = Menu.readString("Preparation method: ");
			double price = Menu.readDouble("Enter the item price: ");
			
			MenuItem m = new MenuItem(name, desc, price);
			menuList.add(m);
		}
	        
	        public static void updateMenu(ArrayList<MenuItem>menuList) {
			//Ask for guest identity as primary key
			String identifier = Menu.readString("Please enter the name of menu item to update: ");
			
			boolean found = false;
			int index = 0;
			
			for (MenuItem m: menuList) {
				System.out.println(m.getName());
				if (identifier.equals(m.getName())) {
					found = true;
					break;
				}
				index++;
			}
			
			if (!found) {
				System.out.println("Menu with name: " + identifier + " not found!");
			} else {
				System.out.println("Menu with name: " + identifier + " found!");
				
				System.out.println(" -------------------------------------------");
				System.out.println("Please enter new menu details ('Enter' key to skip)");
				
				String name = Menu.readString("Enter new menu name: ");
				if (!name.equals("")) 
					menuList.get(index).setName(name);
				
				String desc = Menu.readString("Enter new menu preparation method: ");
				if (!desc.equals("")) 
					menuList.get(index).setDescription(desc);
				
				double price = Menu.readDouble("Enter new menu price: ");
				if (price != 0) 
					menuList.get(index).setPrice(price);
				
				System.out.println(" -------------------------------------------");
				System.out.println(" Menu item updated!");
			}
		}
	        
	        public static void removeMenu(ArrayList<MenuItem>menuList) {
			//Ask for guest name as primary key
			String identifier = Menu.readString("Please enter the name of guest you would like to search: ");
			
			boolean found = false;
			int index = 0;
			
			for (MenuItem m: menuList) {
				System.out.println(m.getName());
				if (identifier.equals(m.getName())) {
					found = true;
					break;
				}
				index++;
			}
			
			if (!found) {
				System.out.println("Menu with name: " + identifier + " not found!");
			} else {
				System.out.println("Menu with name: " + identifier + " found!");
				
				System.out.println(" -------------------------------------------");
				menuList.remove(index);
				System.out.println(" -------------------------------------------");
				
			}
		}
	        
	        public static void createNewRS (ArrayList<MenuItem>menuList, ArrayList<RoomService>serviceList) {
	            
			String no = Menu.readString("Enter your room no: ");
	                
			if (menuList.isEmpty())
				System.out.print("None");
			else {	
				for (MenuItem m: menuList) {
					String name = m.getName();
	                                String desc = m.getDescription();
	                                double price = m.getPrice();
					System.out.println(name + " | " + desc + " | " + price);
				}
			}
					String order = Menu.readString("Enter your order: ");
	                int quantity = Menu.readInt("How many would you like to order ?: ");
	                String remark = Menu.readString("Any remark(s) for your order ? ('Enter' key to skip)");
	                if (remark.equals(""))
	                    remark = "-";
	                	int orderNo = Integer.parseInt(order);
	                	RoomService rs = new RoomService(no, order, quantity, remark, menuList.get(orderNo-1).getPrice());
	                	serviceList.add(rs);
		}
		

}
