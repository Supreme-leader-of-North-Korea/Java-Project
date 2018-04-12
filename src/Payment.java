
public class Payment {
	private double tax, promo, wifiPrice, tvPrice, WErates, singleRoomPrice, doubleRoomPrice, deluxeRoomPrice, vipRoomPrice;
    
	
	
	//Constructor
    public Payment(double tax, double promo, double wifiPrice, double tvPrice, double WErates,
    		double singleRoomPrice, double doubleRoomPrice, double deluxeRoomPrice, double vipRoomPrice) {
    	this.tax = tax;
    	this.promo = promo;
    	this.wifiPrice = wifiPrice;
    	this.tvPrice = tvPrice;
    	this.WErates = WErates;
		this.singleRoomPrice = singleRoomPrice;
		this.doubleRoomPrice = doubleRoomPrice;
		this.deluxeRoomPrice = deluxeRoomPrice;
		this.vipRoomPrice = vipRoomPrice;
	}


	//Getter and setters
	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getPromo() {
		return promo;
	}

	public void setPromo(double promo) {
		this.promo = promo;
	}

	public double getWifiPrice() {
		return wifiPrice;
	}

	public void setWifiPrice(double wifiPrice) {
		this.wifiPrice = wifiPrice;
	}

	public double getTvPrice() {
		return tvPrice;
	}

	public void setTvPrice(double tvPrice) {
		this.tvPrice = tvPrice;
	}

	public double getWErates() {
		return WErates;
	}

	public void setWErates(double wErates) {
		WErates = wErates;
	}

	public double getSingleRoomPrice() {
		return singleRoomPrice;
	}

	public void setSingleRoomPrice(double singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}

	public double getDoubleRoomPrice() {
		return doubleRoomPrice;
	}

	public void setDoubleRoomPrice(double doubleRoomPrice) {
		this.doubleRoomPrice = doubleRoomPrice;
	}

	public double getDeluxeRoomPrice() {
		return deluxeRoomPrice;
	}

	public void setDeluxeRoomPrice(double deluxeRoomPrice) {
		this.deluxeRoomPrice = deluxeRoomPrice;
	}

	public double getVipRoomPrice() {
		return vipRoomPrice;
	}

	public void setVipRoomPrice(double vipRoomPrice) {
		this.vipRoomPrice = vipRoomPrice;
	}  
    
}
