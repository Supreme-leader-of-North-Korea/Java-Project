
public class PaymentMenu {
	
	
	public static void printInvoice() {
		
			double subtotal = wePrice+wdPrice+rsTotal;
			double promotion = discount/100*subtotal
			double gst = tax/100*(subtotal-promotion);
			double total = subtotal-promotion+gst;
				
	        System.out.println(" =======================================");
	        System.out.println(" *               Invoice               *");
	        System.out.println(" =======================================");
	        System.out.println(" No of days stayed:                    	" + noDay);
	        System.out.println(" 	Weekdays("+ WDrates +"/days):			" + wdPrice);
	        System.out.println(" 	Weekends("+ WErates +"/days):			" + wePrice	);
	        System.out.println(" Room Service Total                  	" + rsTotal);
	        System.out.println(" ---------------------------------------");
	        System.out.println(" Sub-total:                             " + subtotal);
	        System.out.println(" GST("+ tax +"%)		                   	    " + gst);
	        System.out.println(" Promotion(if any):                	    " + promotion );
	        System.out.println(" =======================================");
	        System.out.println(" Grand Total:	                	    " + total);
	        
	        
	} 
	}

