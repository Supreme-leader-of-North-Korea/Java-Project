import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PaymentMenu extends Menu{

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

	public static void printPaymentMenu() {
		System.out.println(" ===========================================");
		System.out.println(" *                 Payment                 *");
		System.out.println(" ===========================================");
		System.out.println(" * 1. Update Price & Promotion Rates       *");
		System.out.println(" * 2. Print Rates                          *");
		System.out.println(" * 3. Previous                             *");
		System.out.println(" * 4. Quit                                 *");
	}

	public static void updatePaymentRates(ArrayList<Payment>paymentList) {
		int index = 0; //there will be one row in paymentList for now as we do not keep history  	
		System.out.println("Please enter new payment rates details ('Enter' key to skip)");

		//Try to change to readDouble; readDouble
		String promo = readString("Enter new promotion rates(%): ");
		if (!promo.equals("")) 
			paymentList.get(index).setPromo(Double.parseDouble(promo));

		String gst = readString("Enter new gst rates(%): ");
		if (!gst.equals("")) 
			paymentList.get(index).setTax(Double.parseDouble(gst));

		String wifiRates = readString("Enter new wifiRates/day: ");
		if (!wifiRates.equals("")) 
			paymentList.get(index).setWifiPrice(Double.parseDouble(wifiRates));

		String tvRates = readString("Enter new Cabled TV rates/day: ");
		if (!tvRates.equals("")) 
			paymentList.get(index).setTvPrice(Double.parseDouble(tvRates));

		String singleRoomRates = readString("Enter new room rates (Single room): ");
		if (!singleRoomRates.equals("")) 
			paymentList.get(index).setSingleRoomPrice(Double.parseDouble(singleRoomRates));

		String doubleRoomRates = readString("Enter new room rates (Double room): ");
		if (!doubleRoomRates.equals("")) 
			paymentList.get(index).setDoubleRoomPrice(Double.parseDouble(doubleRoomRates));

		String deluxeRoomRates = readString("Enter new room rates (Deluxe room): ");
		if (!deluxeRoomRates.equals("")) 
			paymentList.get(index).setDeluxeRoomPrice(Double.parseDouble(deluxeRoomRates));

		String vipRoomRates = readString("Enter new room rates (VIP room): ");
		if (!vipRoomRates.equals("")) 
			paymentList.get(index).setVipRoomPrice(Double.parseDouble(vipRoomRates));		
	}

	public static void printRates(ArrayList<Payment>paymentList) {
		int index = 0; //there will be one row in paymentList for now as we do not keep history  	

		double fineRate = paymentList.get(index).getOverStayingFine();
		double promo = paymentList.get(index).getPromo();
		double gst = paymentList.get(index).getTax();
		double wifiRates = paymentList.get(index).getWifiPrice();
		double tvRates = paymentList.get(index).getTvPrice();
		double doubleRoomRates = paymentList.get(index).getDoubleRoomPrice();
		double singleRoomRates = paymentList.get(index).getSingleRoomPrice();
		double deluxeRoomRates = paymentList.get(index).getDeluxeRoomPrice();
		double vipRoomRates = paymentList.get(index).getVipRoomPrice();

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



	public static void printInvoice(ArrayList<Payment>paymentList, ArrayList<RoomService>serviceList, ArrayList<Room>roomList, int roomIndex) {

		int index = 0;		

		double fineRate = paymentList.get(index).getOverStayingFine();
		double promo = paymentList.get(index).getPromo();
		double tax = paymentList.get(index).getTax();
		double wifiRates = paymentList.get(index).getWifiPrice();
		double tvRates = paymentList.get(index).getTvPrice();
		double WErates = paymentList.get(index).getWErates();
		String roomId = roomList.get(roomIndex).getRoomId(); 
		double rsTotal = rsTotal(serviceList, roomId);
		double WDrates;
		boolean wifiEnable = roomList.get(roomIndex).isWifiEnabled();

		if (roomList.get(roomIndex) instanceof Room_single) {
			WDrates = paymentList.get(index).getSingleRoomPrice();
		}else if (roomList.get(roomIndex) instanceof Room_double) {
			WDrates = paymentList.get(index).getDoubleRoomPrice();
		}else if(roomList.get(roomIndex) instanceof Room_deluxe) {
			WDrates = paymentList.get(index).getDeluxeRoomPrice();
		}else {
			WDrates = paymentList.get(index).getVipRoomPrice();

		}
		if (wifiEnable) {
			WDrates += wifiRates;
		}

		// calculate no  of WeekDays and no WeekEnds and total of days stayed
		Date checkIn = roomList.get(index).getCheckInDate();
		Date checkOut = roomList.get(index).getCheckOutDate();
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
