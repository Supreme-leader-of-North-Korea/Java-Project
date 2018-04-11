
public class Payment {
	private String tax, promo, singleSizePrice, doubleSizePrice, kingSizePrice, singleRoomPrice, doubleRoomPrice, deluxeRoomPrice, vipRoomPrice;
    //Constructor
    public Payment(String tax, String promo, String singleSizePrice, String doubleSizePrice, String kingSizePrice, 
    		String singleRoomPrice, String doubleRoomPrice, String deluxeRoomPrice, String vipRoomPrice) {
    	this.tax = tax;
    	this.promo = promo;
    	this.singleSizePrice = singleSizePrice;
    	this.doubleSizePrice = doubleSizePrice;
		this.kingSizePrice = kingSizePrice;
		this.singleRoomPrice = singleRoomPrice;
		this.doubleRoomPrice = doubleRoomPrice;
		this.deluxeRoomPrice = deluxeRoomPrice;
		this.vipRoomPrice = vipRoomPrice;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getPromo() {
		return promo;
	}
	public void setPromo(String promo) {
		this.promo = promo;
	}
	public String getSingleSizePrice() {
		return singleSizePrice;
	}
	public void setSingleSizePrice(String singleSizePrice) {
		this.singleSizePrice = singleSizePrice;
	}
	public String getDoubleSizePrice() {
		return doubleSizePrice;
	}
	public void setDoubleSizePrice(String doubleSizePrice) {
		this.doubleSizePrice = doubleSizePrice;
	}
	public String getKingSizePrice() {
		return kingSizePrice;
	}
	public void setKingSizePrice(String kingSizePrice) {
		this.kingSizePrice = kingSizePrice;
	}
	public String getSingleRoomPrice() {
		return singleRoomPrice;
	}
	public void setSingleRoomPrice(String singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}
	public String getDoubleRoomPrice() {
		return doubleRoomPrice;
	}
	public void setDoubleRoomPrice(String doubleRoomPrice) {
		this.doubleRoomPrice = doubleRoomPrice;
	}
	public String getDeluxeRoomPrice() {
		return deluxeRoomPrice;
	}
	public void setDeluxeRoomPrice(String deluxeRoomPrice) {
		this.deluxeRoomPrice = deluxeRoomPrice;
	}
	public String getVipRoomPrice() {
		return vipRoomPrice;
	}
	public void setVipRoomPrice(String vipRoomPrice) {
		this.vipRoomPrice = vipRoomPrice;
	}
    
    
    
}
