import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RoomServiceMenu extends Menu {
	//Room Service Menu -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
		public static void roomServiceMenu(ArrayList<RoomService>serviceList, ArrayList<MenuItem>menuList, 
							ArrayList<Room>roomList) throws FileNotFoundException{
			
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
	                case 4: createNewRS(menuList, serviceList,roomList);
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
			
			String name = Menu.readNonEmptyString("Enter menu name: ");
			String desc = Menu.readNonEmptyString("Preparation method: ");
			double price = Menu.readDouble("Enter the item price: ");
			
			MenuItem m = new MenuItem(name, desc, price);
			menuList.add(m);
		}
	        
	    public static void updateMenu(ArrayList<MenuItem>menuList) {
	        if(printMenu(menuList)) {
				int index = Menu.readInt("Please enter the index of menu item to be updated: ");
				String identifier = menuList.get(index-1).getName();
				int menuIndex = menuNameSearch(menuList,identifier);
				
				if (menuIndex == -1) {
					System.out.println("Menu with name: " + identifier + " not found!");
				} else {
					System.out.println("Menu with name: " + identifier + " found!");
					
					System.out.println(" -------------------------------------------");
					System.out.println("Please enter new menu details ('Enter' key to skip)");
					
					String name = Menu.readString("Enter new menu name: ");
					if (!name.equals("")) 
						menuList.get(menuIndex).setName(name);
					
					String desc = Menu.readString("Enter new menu preparation method: ");
					if (!desc.equals("")) 
						menuList.get(menuIndex).setDescription(desc);
					
					double price = Menu.readDouble("Enter new menu price: ");
					if (price != 0) 
						menuList.get(menuIndex).setPrice(price);
					
					System.out.println(" -------------------------------------------");
					System.out.println(" Menu item updated!");
				}
	        }
		}
	        
	        public static void removeMenu(ArrayList<MenuItem>menuList) {
	        if(printMenu(menuList)) {
				int index = Menu.readInt("Please enter the index of menu item to be removed ");
				String identifier = menuList.get(index-1).getName();
				int menuIndex = menuNameSearch(menuList,identifier);
				
				if (menuIndex == -1) {
					System.out.println("Menu with name: " + identifier + " not found!");
				} else {
					System.out.println("Menu with name: " + identifier + " found!");
					System.out.println(" -------------------------------------------");
					menuList.remove(menuIndex);
					System.out.println(" -------------------------------------------");
					
				}
	        }
		}
	        
	        public static void createNewRS (ArrayList<MenuItem>menuList, ArrayList<RoomService>serviceList,ArrayList<Room>roomList) {
	            
			String roomNo = Menu.readString("Enter your room no: ");
			
	        int roomIndex = Menu.roomIDSearch(roomList, roomNo);        
	        if(roomIndex == -1) {
	        	System.out.println("room number: " + roomNo + " does not exist");
	        }else {
	        	if(roomOccupancyCheck(roomList,roomNo)) {
					if(printMenu(menuList)) {
			        	int orderNo = Menu.readInt("Enter your option: ");
				        int quantity = Menu.readInt("How many would you like to order ?: ");
				        String remark = Menu.readString("Any remark(s) for your order ? ('Enter' key to skip)");
				       
				        if (remark.equals(""))
				        	remark = "-";
				        
			        	RoomService rs = new RoomService(roomNo, orderNo, quantity, remark, menuList.get(orderNo-1).getPrice(), RoomService.Status.PREPARING);
			        	serviceList.add(rs); 
					}
				}
	        }
		}
	    
	    public static boolean printMenu(ArrayList<MenuItem>menuList) {
			int index = 1;
	    	if (menuList.isEmpty()) {
				System.out.print("The Menu is Empty");
	    		return false;
	    	}else {	
				for (MenuItem m: menuList) {
					String name = m.getName();
	                String desc = m.getDescription();
	                double price = m.getPrice();
					System.out.println(index + " | " + name + " | " + desc + " | " + price);
					index++;
				}
				return true;
			}
	    	 
	    }

}
