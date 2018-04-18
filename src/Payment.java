public class Payment {
	private double tax, promo, wifiPrice, tvPrice, WErates, singleRoomPrice;
	private double doubleRoomPrice, overStayingFine, deluxeRoomPrice, vipRoomPrice;

	//Constructor
	/**
	 * 
	 * @param tax
	 * @param promo
	 * @param wifiPrice
	 * @param tvPrice
	 * @param WErates
	 * @param singleRoomPrice
	 * @param doubleRoomPrice
	 * @param deluxeRoomPrice
	 * @param vipRoomPrice
	 * @param overStayingFine
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
	 * 
	 * @return
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * 
	 * @param tax
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}

	/**
	 * 
	 * @return
	 */
	public double getPromo() {
		return promo;
	}

	/**
	 * 
	 * @param promo
	 */
	public void setPromo(double promo) {
		this.promo = promo;
	}

	/**
	 * 
	 * @return
	 */
	public double getWifiPrice() {
		return wifiPrice;
	}

	/**
	 * 
	 * @param wifiPrice
	 */
	public void setWifiPrice(double wifiPrice) {
		this.wifiPrice = wifiPrice;
	}

	/**
	 * 
	 * @return
	 */
	public double getTvPrice() {
		return tvPrice;
	}

	/**
	 * 
	 * @param tvPrice
	 */
	public void setTvPrice(double tvPrice) {
		this.tvPrice = tvPrice;
	}

	/**
	 * 
	 * @return
	 */
	public double getWErates() {
		return WErates;
	}

	/**
	 * 
	 * @param wErates
	 */
	public void setWErates(double wErates) {
		WErates = wErates;
	}

	/**
	 * 
	 * @return
	 */
	public double getSingleRoomPrice() {
		return singleRoomPrice;
	}

	/**
	 * 
	 * @param singleRoomPrice
	 */
	public void setSingleRoomPrice(double singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}

	/**
	 * 
	 * @return
	 */
	public double getDoubleRoomPrice() {
		return doubleRoomPrice;
	}

	/**
	 * 
	 * @param doubleRoomPrice
	 */
	public void setDoubleRoomPrice(double doubleRoomPrice) {
		this.doubleRoomPrice = doubleRoomPrice;
	}

	/**
	 * 
	 * @return
	 */
	public double getDeluxeRoomPrice() {
		return deluxeRoomPrice;
	}

	/**
	 * 
	 * @param deluxeRoomPrice
	 */
	public void setDeluxeRoomPrice(double deluxeRoomPrice) {
		this.deluxeRoomPrice = deluxeRoomPrice;
	}

	/**
	 * 
	 * @return
	 */
	public double getVipRoomPrice() {
		return vipRoomPrice;
	}

	/**
	 * 
	 * @param vipRoomPrice
	 */
	public void setVipRoomPrice(double vipRoomPrice) {
		this.vipRoomPrice = vipRoomPrice;
	}

	/**
	 * 
	 * @return
	 */
	public double getOverStayingFine() {
		return overStayingFine;
	}

	/**
	 * 
	 * @param overStayingFine
	 */
	public void setOverStayingFine(double overStayingFine) {
		this.overStayingFine = overStayingFine;
	}  

}
