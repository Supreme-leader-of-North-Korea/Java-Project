
public class Guest {
	
	//Attributes
    
    private String name, creditDetails, address, country, nationality, contact, ic, gender, identity;
    
    //Constructor
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

	
	//Getter and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditDetails() {
		return creditDetails;
	}

	public void setCreditDetails(String creditDetails) {
		this.creditDetails = creditDetails;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
    

}
