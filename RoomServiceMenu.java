import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
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
			choice = readInt(" Please enter your choice: ");

			switch(choice) {
				case 1: System.out.println("Creating new menu item...");
						createNewMenu(menuList);
						mfio.exportAll(menuList);
						break;
				case 2: System.out.println("Updating menu details...");
						updateMenu(menuList);
						mfio.exportAll(menuList);
						break;
				case 3: System.out.println("Removing menu details...");
						removeMenu(menuList);
						mfio.exportAll(menuList);
						break;
				case 4: createNewRS(menuList, serviceList, roomList);
						sfio.exportAll(serviceList);
						break;
				case 5: updateRS(menuList, serviceList, roomList);
						sfio.exportAll(serviceList);
						break;
				case 6: System.out.println("Returning to main menu...");
						mfio.exportAll(menuList);
						sfio.exportAll(serviceList);
						break;
				case 7: System.out.println("Exiting...");
						mfio.exportAll(menuList);
						System.exit(0);
						break;				
				default:System.out.println("Wrong Input. Please input from 1 - 7.");
						break;
			}
		} while (choice != 6);
	}

	public static void printRoomServiceMenu() {
		System.out.println(" ===========================================");
		System.out.println(" *              Room Service               *");
		System.out.println(" ===========================================");        
		System.out.println(" * 1. New Room Service Menu Item           *");
		System.out.println(" * 2. Update Room Service Menu Item        *");
		System.out.println(" * 3. Remove Room Service Menu Item        *");
		System.out.println(" * 4. Create Room Service Order	           *");
		System.out.println(" * 5. Update Room Service Order	           *");
		System.out.println(" * 6. Previous                             *");
		System.out.println(" * 7. Quit                                 *");
	}

	public static void createNewMenu(ArrayList<MenuItem>menuList) {
		String name = readNonEmptyString("Enter menu name: ");
		String desc = readNonEmptyString("Preparation method: ");
		double price = readDouble("Enter the item price: ");
		MenuItem m = new MenuItem(name, desc, price);
		menuList.add(m);
		System.out.println(" -------------------------------------------");
		System.out.println(" Menu item created!");                      
		
	}

	public static void updateMenu(ArrayList<MenuItem>menuList) {
		if(printMenu(menuList)) {
			int noOfMenu = 0;
			try {
				for (MenuItem m: menuList) { //To get the number of menu items in the list for guest to choose from
					noOfMenu++;
				}
				int index = readInt("Please enter the index of menu item to be updated: ");
				if (index > 0 && index <= noOfMenu) {
					String identifier = menuList.get(index-1).getName(); 
					int menuIndex = genericSearch("getName",identifier,menuList);

					if (menuIndex == -1) {
						System.out.println("Menu with name: " + identifier + " not found!");
					} else {
						System.out.println("Menu with name: " + identifier + " found!");

						System.out.println(" -------------------------------------------");
						System.out.println("Please enter new menu details ('Enter' key to skip for name and description only)");

						String name = readString("Enter new menu name: ");
						if (!name.equals("")) 
							menuList.get(menuIndex).setName(name);

						String desc = readString("Enter new menu preparation method: ");
						if (!desc.equals("")) 
							menuList.get(menuIndex).setDescription(desc);

						double price = readDouble("Enter new menu price: (Current price : " + menuList.get(menuIndex).getPrice() + ") ");
						if (price != 0) 
							menuList.get(menuIndex).setPrice(price);
						System.out.println(" -------------------------------------------");
						System.out.println(" Menu item updated!");
					}
				} else {
					System.out.println(" Invalid input ! Please enter from 1 to " + noOfMenu);
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| NoSuchMethodException | SecurityException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static void removeMenu(ArrayList<MenuItem>menuList) {
		try {
			if(printMenu(menuList)) {
				int index = readInt("Please enter the index of menu item to be removed ");
				if (menuList.size() >= index) {
					String identifier = menuList.get(index-1).getName();
					int menuIndex;				
					menuIndex = genericSearch("getName",identifier,menuList);
					if (menuIndex == -1) {
						System.out.println("Menu with name: " + identifier + " not found!");
					} else {
						System.out.println("Menu with name: " + identifier + " found!");
						System.out.println(" -------------------------------------------");
						menuList.remove(menuIndex);
						System.out.println(" -------------------------------------------");

					}
				} else 
					System.out.println("Please enter from 1 to " + menuList.size() + " !");
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| NoSuchMethodException | SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createNewRS (ArrayList<MenuItem>menuList, ArrayList<RoomService>serviceList,ArrayList<Room>roomList) {
		String roomNo = readString("Enter your room no: ");

		int roomIndex , orderNo;
		try {
			roomIndex = genericSearch("getRoomId", roomNo, roomList);

			if(roomIndex == -1) {
				System.out.println("room number: " + roomNo + " does not exist");
			}else {
				if(roomList.get(roomIndex).getRoomStatus().equals(Room.RoomStatus.OCCUPIED)) {
					if(printMenu(menuList)) {
						orderNo = readInt("Enter your option: ");
						if (menuList.size() >= orderNo) {
							int quantity = readInt("How many would you like to order ?: ");
							String remark = readString("Any remark(s) for your order ? ('Enter' key to skip)");

							if (remark.equals(""))
								remark = "-";

							RoomService rs = new RoomService(roomNo, orderNo, quantity, remark, menuList.get(orderNo-1).getPrice(), RoomService.Status.CONFIRMED);
							serviceList.add(rs); 
						} else 
							System.out.println("Please enter 1 to " + menuList.size());
					} 
				} else {
					System.out.println("Room no: " + roomNo + " is currently unoccupied");
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public static void updateRS(ArrayList<MenuItem>menuList, ArrayList<RoomService>serviceList,ArrayList<Room>roomList) {
		String roomNo = readNonEmptyString("Please Enter the room ID: ");
		//roomServiceSearch returns array of int which contains the index of the menuItem which is place by the room no
		ArrayList<RoomService> items = roomServiceSearch(serviceList, menuList,roomList, roomNo);
		ArrayList<RoomService> temp = roomServiceSearch(serviceList, menuList,roomList, roomNo);
		boolean found = false;
		int i = 0;
		if (items.size() != 0) {
			for(RoomService rs: items) {
				if(rs.getStatus().equals(RoomService.Status.DELIVERED))
					temp.add(rs);
			}
			items.removeAll(temp);
			for(RoomService rs: items) {
				int menuNo = rs.getMenuItemNo();
				System.out.println((i+1) + ":" +
						"\nStatus : " + rs.getStatus() +
						"\nItem : " + menuList.get(menuNo-1).getName());
				found = true;
				i++;
			}
			if (found) {
				int itemNo = readInt("Which item would you like to update: "); 
				itemNo = itemNo - 1;
				if (itemNo < items.size()){
					String choice = readNonEmptyString("Please enter the new Status (P)reparing/(D)elivered: ");
					switch(choice.toUpperCase()) {
					case "P": 	items.get(itemNo).setStatus(RoomService.Status.PREPARING);
								break;
					case "D": 	items.get(itemNo).setStatus(RoomService.Status.DELIVERED);
								break;
					}
				} else 
					System.out.println("Please enter from 1 to " + items.size());
				serviceList.addAll(items);
			} else {
				System.out.println("Please Enter a valid choice");
			}
		} else {
			System.out.println("There are no pending orders");
		}
	}

	
	
}
