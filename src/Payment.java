/**
 * Represents the Payment.
 * A payment can be made by only 1 guest.
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class Payment {
	
	/**	The tax which the government impose*/
	private double tax;
	
	/**	The promotion which the hotel provides the guest with */
	private double promo;
	
	/**	The price which the hotel charge for enabling the Wifi */
	private double wifiPrice;
	
	/**	The price which the hotel charge for having cabled TV */
	private double tvPrice;
	
	/**	The weekend rates of the hotel */
	private double WErates;
	
	/**	The price of single Room in the hotel */
	private double singleRoomPrice;
	
	/**	The price of double Room in the hotel */
	private double doubleRoomPrice;
	
	/**	The price of checking out after the Check Out Date */
	private double overStayingFine;
	
	/**	The price of deluxe Room in the hotel */
	private double deluxeRoomPrice;
	
	/**	The price of VIP Room in the hotel */
	private double vipRoomPrice;
	
	
	/**
	 * Payment's Constructor
	 * @param tax The tax which the government impose
	 * @param promo The promotion which the hotel provides the guest with
	 * @param wifiPrice The price which the hotel charge for enabling the Wifi
	 * @param tvPrice The price which the hotel charge for having cabled TV
	 * @param WErates The weekend rates of the hotel
	 * @param singleRoomPrice The price of single Room in the hotel
	 * @param doubleRoomPrice The price of double Room in the hotel
	 * @param deluxeRoomPrice The price of deluxe Room in the hotel
	 * @param vipRoomPrice The price of VIP Room in the hotel
	 * @param overStayingFine The price of VIP Room in the hotel
	 */
	public Payment(double tax, double promo, double wifiPrice, double tvPrice, double WErates, double singleRoomPrice, 
			double doubleRoomPrice, double deluxeRoomPrice, double vipRoomPrice, double overStayingFine) {
		this.tax = tax;
		this.promo = promo;
		this.wifiPrice = wifiPrice;
		this.tvPrice = tvPrice;
		this.WErates = WErates;
		this.singleRoomPrice = singleRoomPrice;
		this.doubleRoomPrice = doubleRoomPrice;
		this.deluxeRoomPrice = deluxeRoomPrice;
		this.vipRoomPrice = vipRoomPrice;
		this.overStayingFine = overStayingFine;
	}

	/**
	 * Get the current tax imposed by the government.
	 * @return The current tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * Set the tax imposed by the government.
	 * @param tax The tax that user wants to set.
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}

	/**
	 * Get the current promotion rate provided by the hotel.
	 * @return The current promotion rate.
	 */
	public double getPromo() {
		return promo;
	}

	/**
	 * Set the promotion rate given by the hotel.
	 * @param promo The promotion that user wants to set.
	 */
	public void setPromo(double promo) {
		this.promo = promo;
	}

	/**
	 * Get the current WiFi price provided by the hotel.
	 * @return The current WiFi price.
	 */
	public double getWifiPrice() {
		return wifiPrice;
	}

	/**
	 * Set the WiFi price provided by the hotel.
	 * @param wifiPrice The WiFi price that user wants to set.
	 */
	public void setWifiPrice(double wifiPrice) {
		this.wifiPrice = wifiPrice;
	}

	/**
	 * Get the current TV price provided by the hotel.
	 * @return The current TV price.
	 */
	public double getTvPrice() {
		return tvPrice;
	}

	/**
	 * Set the TV price provided by the hotel.
	 * @param tvPrice The TV price that user wants to set.
	 */
	public void setTvPrice(double tvPrice) {
		this.tvPrice = tvPrice;
	}

	/**
	 * Get the current Weekend Rate provided by the hotel.
	 * @return The current Weekend Rate.
	 */
	public double getWErates() {
		return WErates;
	}

	/**
	 * Set the Weekend Rate provided by the hotel.
	 * @param wErates The Weekend Rate that user wants to set.
	 */
	public void setWErates(double wErates) {
		WErates = wErates;
	}

	/**
	 * Get the current Single Room price provided by the hotel.
	 * @return The current Single Room price.
	 */
	public double getSingleRoomPrice() {
		return singleRoomPrice;
	}

	/**
	 * Set the Single Room price provided by the hotel.
	 * @param singleRoomPrice The Single Room price that user wants to set.
	 */
	public void setSingleRoomPrice(double singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}

	/**
	 * Get the current Double Room price provided by the hotel.
	 * @return The current Double Room price.
	 */
	public double getDoubleRoomPrice() {
		return doubleRoomPrice;
	}

	/**
	 * Set the Double Room price provided by the hotel.
	 * @param doubleRoomPrice The Double Room price that user wants to set.
	 */
	public void setDoubleRoomPrice(double doubleRoomPrice) {
		this.doubleRoomPrice = doubleRoomPrice;
	}

	/**
	 * Get the current Deluxe Room price provided by the hotel.
	 * @return The current Deluxe Room price.
	 */
	public double getDeluxeRoomPrice() {
		return deluxeRoomPrice;
	}

	/**
	 * Set the Deluxe Room price provided by the hotel.
	 * @param deluxeRoomPrice The Deluxe Room price that user wants to set.
	 */
	public void setDeluxeRoomPrice(double deluxeRoomPrice) {
		this.deluxeRoomPrice = deluxeRoomPrice;
	}

	/**
	 * Get the current VIP Room price provided by the hotel.
	 * @return The current VIP Room price.
	 */
	public double getVipRoomPrice() {
		return vipRoomPrice;
	}

	/**
	 * Set the VIP Room price provided by the hotel.
	 * @param vipRoomPrice The VIP Room price that user wants to set.
	 */
	public void setVipRoomPrice(double vipRoomPrice) {
		this.vipRoomPrice = vipRoomPrice;
	}

	/**
	 * Get the current OverStaying fine provided by the hotel.
	 * @return The current OverStaying fine.
	 */
	public double getOverStayingFine() {
		return overStayingFine;
	}

	/**
	 * Set the OverStaying Fine provided by the hotel.
	 * @param overStayingFine The OverStaying fine that user wants to eat.
	 */
	public void setOverStayingFine(double overStayingFine) {
		this.overStayingFine = overStayingFine;
	}  

}
