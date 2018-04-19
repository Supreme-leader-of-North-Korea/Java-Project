import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Payment Menu which allows the staff to update
 * and print the payment rates and total price.
 * 
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class PaymentMenu extends Menu{

	/**
	 * This method directs the user to the Payment Menu based on the user inputs.
	 * 
	 * @param guestList This list contains the details of all the guest registered in the system.
	 * @param roomList This list contains the details of all the room in the system.
	 * @param reservationList This list contains all the reservations details in the system.
	 * @param serviceList This list contains the details of all the room services ordered in the system.
	 * @param paymentList This list contains the hotel rates.
	 * @throws FileNotFoundException
	 */
	public static void paymentMenu(ArrayList<Guest>guestList, ArrayList<Room>roomList, 
			ArrayList<Reservation>reservationList, ArrayList<RoomService>serviceList,
			ArrayList<Payment>paymentList) throws FileNotFoundException{

		int choice = 0;
		PaymentFileIO pfio = new PaymentFileIO();
		//Select menu
		do {
			//Print room menu
			printPaymentMenu();

			//Get user's choice
			System.out.println(" -------------------------------------------");
			choice = readInt(" Please enter your choice: ");

			switch(choice) {
			case 1: updatePaymentRates(paymentList);
					pfio.exportAll(paymentList);
					break;
			case 2: printRates(paymentList);
					break;
			case 3: System.out.println("Returning to main menu...");
					pfio.exportAll(paymentList);
					break;
			case 4: System.out.println("Exiting...");
					pfio.exportAll(paymentList);
					System.exit(0);
					break;		
			default:System.out.println("Wrong Input. Please input from 1 - 4.");
					break;
			}

		} while (choice != 3);  
	}
	
	/**
	 * This method print the payment menu to inform the users of the options.
	 */
	public static void printPaymentMenu() {
		System.out.println(" ===========================================");
		System.out.println(" *                 Payment                 *");
		System.out.println(" ===========================================");
		System.out.println(" * 1. Update Price & Promotion Rates       *");
		System.out.println(" * 2. Print Rates                          *");
		System.out.println(" * 3. Previous                             *");
		System.out.println(" * 4. Quit                                 *");
	}

	/**
	 * This method updates the payment rates of the hotel that is stored in paymentList.
	 * @param paymentList This list contains the hotel rates.
	 */
	public static void updatePaymentRates(ArrayList<Payment>paymentList) {
		int index = 0; //there will be one row in paymentList for now as we do not keep history  	
		System.out.println("Please enter new payment rates details ('Enter' key to skip)");
		Payment p = paymentList.get(index);
		
		double promo = readDouble("Enter new promotion rates(%): (Current :" + p.getPromo() +") "); 
			p.setPromo(promo);

		double gst = readDouble("Enter new gst rates(%): (Current :" + p.getTax() + ")");
			p.setTax(gst);

		double wifiRates = readDouble("Enter new wifiRates/day: (Current :" + p.getWifiPrice() +") ");
			p.setWifiPrice(wifiRates);

		double tvRates = readDouble("Enter new Cabled TV rates/day: (Current :" + p.getTvPrice() +") ");
			p.setTvPrice(tvRates);

		double singleRoomRates = readDouble("Enter new room rates (Single room): (Current :" + p.getSingleRoomPrice() + ") ");
			p.setSingleRoomPrice(singleRoomRates);

		double doubleRoomRates = readDouble("Enter new room rates (Double room): (Current :" + p.getDoubleRoomPrice() + ") ");
			p.setDoubleRoomPrice(doubleRoomRates);

		double deluxeRoomRates = readDouble("Enter new room rates (Deluxe room): (Current :" + p.getDeluxeRoomPrice() + ") ");
			p.setDeluxeRoomPrice(deluxeRoomRates);

		double vipRoomRates = readDouble("Enter new room rates (VIP room): (Current :" + p.getVipRoomPrice() + ") ");
			p.setVipRoomPrice(vipRoomRates);		
	}
	
	// Print payment rates
	/**
	 * This method prints out the payment rates of the hotel stored in paymentList.
	 * @param paymentList This list contains the hotel rates.
	 */
	public static void printRates(ArrayList<Payment>paymentList) {
		int index = 0; //there will be one row in paymentList for now as we do not keep history  	
		Payment p = paymentList.get(index);
		
		double fineRate = p.getOverStayingFine();
		double promo = p.getPromo();
		double gst = p.getTax();
		double wifiRates = p.getWifiPrice();
		double tvRates = p.getTvPrice();
		double doubleRoomRates = p.getDoubleRoomPrice();
		double singleRoomRates = p.getSingleRoomPrice();
		double deluxeRoomRates = p.getDeluxeRoomPrice();
		double vipRoomRates = p.getVipRoomPrice();

		DecimalFormat df = new DecimalFormat("#.##");

		System.out.println(" =======================================");
		System.out.println("                  Rates                 ");
		System.out.println(" =======================================");
		System.out.println(" Promotions:                          " + df.format(promo) + " %");
		System.out.println(" Tax:                                 " + df.format(gst) + " %");
		System.out.println(" Single Room:                        $" + df.format(singleRoomRates) + "/day");
		System.out.println(" Double Room:                        $" + df.format(doubleRoomRates) + "/day");
		System.out.println(" Deluxe Room:                        $" + df.format(deluxeRoomRates) + "/day");
		System.out.println(" VIP Room:                           $" + df.format(vipRoomRates) + "/day");
		System.out.println(" ----------Additional Charges:--------- ");
		System.out.println(" Delayed Checkout Fine:              $" + df.format(fineRate));
		System.out.println(" Enabling Wifi:                      $" + df.format(wifiRates) +"/day");
		System.out.println(" Cabled Television                   $" + df.format(tvRates) +"/day");
	}

	/**
	 * This method sums up the total bill invoice which is calculated based on the details
	 * obtained from roomList, paymentList and serviceList.
	 * 
	 * @param paymentList This list contains the hotel rates.
	 * @param serviceList This list contains all the room services ordered.
	 * @param roomList This list contains the details of all the room in the hotel.
	 * @param roomIndex This index contains the ID of the room that is checking out.
	 */
	public static void printInvoice(ArrayList<Payment>paymentList, ArrayList<RoomService>serviceList, ArrayList<Room>roomList, int roomIndex) {
		int index = 0;		
		Payment p = paymentList.get(index);
		Room r = roomList.get(roomIndex);
		
		double fineRate = p.getOverStayingFine();
		double promo = p.getPromo();
		double tax = p.getTax();
		double wifiRates = p.getWifiPrice();
		double WErates = p.getWErates();
		String roomId = r.getRoomId(); 
		boolean wifiEnable = r.isWifiEnabled();
		double rsTotal = rsTotal(serviceList, roomId);
		double WDrates;
		
		// Checking the room type and charge accordingly
		if (r instanceof Room_single) {
			WDrates = p.getSingleRoomPrice();
		}else if (r instanceof Room_double) {
			WDrates = p.getDoubleRoomPrice();
		}else if(r instanceof Room_deluxe) {
			WDrates = p.getDeluxeRoomPrice();
		}else {
			WDrates = p.getVipRoomPrice();
		}
		
		if (wifiEnable) {
			WDrates += wifiRates;
		}
		
		// calculate no  of WeekDays and no WeekEnds and total of days stayed
		Date checkIn = r.getCheckInDate();
		Date checkOut = r.getCheckOutDate();
		int []nodays = getWorkingDaysBetweenTwoDates(checkIn,checkOut);
		int weekDays = nodays[0];
		int weekEnds = nodays[1];

		//guest will be evacuated if they stayed after 6pm and charged with delayed checkout fine
		//therefore, there is no need to check if they stay after the checkout date
		
		//Any early checkout will be charge with the same amount as normal checkout
		
		//When guest checkout, a new date will be created, the current hour will be taken
		//if the hour taken exceed 14hours, they will be required to pay fine
		
		boolean overStayFine = false;
		Date today = new Date();
		if (Integer.parseInt(today.toString().substring(11,13)) >=14) {
			overStayFine = true;
		}else {
			overStayFine = false;
		}

		double wePrice = weekEnds * WErates * WDrates;
		double wdPrice = weekDays * WDrates;
		double subtotal = wePrice + wdPrice + rsTotal;
		double promotion = promo/100*subtotal;
		double gst = tax/100*(subtotal-promotion);
		double total = subtotal-promotion+gst;

		DecimalFormat df = new DecimalFormat("#.##");

		System.out.println(" =======================================");
		System.out.println(" *               Invoice               *");
		System.out.println(" =======================================");
		System.out.println(" No of days stayed:                 " + (weekDays+weekEnds));
		System.out.println(weekDays +" Weekdays($"+ WDrates +"/days):  " + wdPrice);
		System.out.println(weekEnds +" Weekends($"+ WErates * WDrates +"/days):  " + wePrice);
		if (overStayFine) {
			System.out.println(" 	Delayed Check Out Fine:        $" + df.format(fineRate));
		}
		System.out.println(" Room Service Total                $" + df.format(rsTotal));
		System.out.println(" ---------------------------------------");
		System.out.println(" Sub-total:                        $" + df.format(subtotal));
		System.out.println(" GST("+ tax +"%)                   $" + df.format(gst));
		System.out.println(" Promotion(if any):                $" + df.format(promotion) );
		System.out.println(" =======================================");
		System.out.println(" Grand Total:                      $" + df.format(total));
	} 

	/**
	 * This method finds the difference between the startDate and the 
	 * endDate date to get the number of weekdays and weekends.
	 * 
	 * @param startDate The starting date of an event.
	 * @param endDate The ending date of an event.
	 * @return An Integer Array of size 2, which contains the number of weekdays and weekends.
	 */
	public static int[] getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);        

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		int weekDays = 0;
		int weekEnds = 0;

		int []totalDays = {weekDays,weekEnds};

		//Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return totalDays;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		do {
			//excluding start date
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				++weekDays;
			}else {
				++weekEnds;
			}
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date
		totalDays[0] = weekDays;
		totalDays[1] = weekEnds;
		return totalDays;
	}
}
