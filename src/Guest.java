/**
 * Represents a Guest added to the System.  
 * A Guest can check in from 0 to multiple rooms.
 * 
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */

public class Guest {

	/** The full name of this guest. */
	private String name;

	/** The credit card number of this guest. */
	private String creditDetails; 

	/** The house address of this guest. */
	private String address;

	/** The country that this guest originates from. */
	private String country;

	/** The gender of this guest. */
	private String gender;

	/** The nationality of this guest. */
	private String nationality;

	/** The type of identity this guest used when they are being registered to the system. */
	private String identity;

	/** The identity card number of this guest based on the type of identity the guest holds. */
	private String ic;

	/** The contact number of this guest. */
	private String contact;

	/**
	 * Creates a new Guest with the given name, address, country, gender, nationality, 
	 * identity, identity card number, credit card number and contact number.   
	 * @param name This Guest's name.
	 * @param address This Guest's address.
	 * @param country This Guest's country.
	 * @param gender This Guest's gender.
	 * @param nationality This Guest's nationality.
	 * @param identity This Guest's identity.
	 * @param ic This Guest's identity card number.
	 * @param creditDetails This Guest's credit card number.
	 * @param contact This Guest's contact number.
	 */
	public Guest(String name, String address, String country, String gender, String nationality,
			String identity, String ic, String creditDetails,String contact) {
		super();
		this.name = name;
		this.creditDetails = creditDetails;
		this.address = address;
		this.country = country;
		this.gender = gender;
		this.nationality = nationality;
		this.identity = identity;
		this.ic = ic;
		this.contact = contact;
	}


	/**
	 * Get the name of this Guest. 
	 * @return This Guest's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name to the name of this Guest.
	 * @param name This Guest's name. 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the credit card number of this Guest.
	 * @return This Guest's credit card number.
	 */
	public String getCreditDetails() {
		return creditDetails;
	}

	/**
	 * Set the credit card number of this Guest.
	 * @param creditDetails This Guest's credit card number.
	 */
	public void setCreditDetails(String creditDetails) {
		this.creditDetails = creditDetails;
	}

	/**
	 * Get the address of this Guest.
	 * @return This Guest's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address of this Guest.
	 * @param address This Guest's address.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the country which this Guest originate from.
	 * @return The country which this Guest originate from.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Set the country to the country which this Guest originate from.
	 * @param country The country which this Guest originate from.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Get the nationality of this Guest.
	 * @return This Guest's nationality.
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Set the nationality of this Guest.
	 * @param nationality This Guest's nationality.
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Get the contact number of this Guest.
	 * @return This Guest's contact number.
	 */
	public String getContact() {
		return contact;
	}
	
	/**
	 * Set the contact number of this Guest.
	 * @param contact This Guest's contact number.
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * Get the Identity Card number of this Guest.
	 * @return The Identity Card number of this Guest.
	 */
	public String getIc() {
		return ic;
	}
	
	/**
	 * Set the Identity Card number of this Guest.
	 * @param ic The Identity Card number of this Guest.
	 */
	public void setIc(String ic) {
		this.ic = ic;
	}

	/**
	 * Get the gender of this Guest to Male or Female.
	 * @return The gender of this Guest.
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Set the gender of this Guest to Male or Female.
	 * @param gender The gender of this Guest.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * Get the type of identity this Guest is holding or using e.g (Driving License, Passport).
	 * @return the type of identity this Guest is holding or using.
	 */
	public String getIdentity() {
		return identity;
	}
	
	/**
	 * Set the type of identity this Guest is holding or using e.g (Driving License, Passport).
	 * @param identity the type of identity this Guest is holding or using e.g (Driving License, Passport).
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
