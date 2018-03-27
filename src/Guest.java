
public class Guest {
	
	//Attributes
    private String name, creditDetails, address, country, gender, nationality, identity, contact;
    
    //Constructor
    public Guest() {
		super();
	}
	
	//Getter and setters
	public String getName() {
		return name;
	}

	public String getCreditDetails() {
		return creditDetails;
	}

	public String getAddress() {
		return address;
	}

	public String getCountry() {
		return country;
	}

	public String getGender() {
		return gender;
	}

	public String getNationality() {
		return nationality;
	}

	public String getIdentity() {
		return identity;
	}

	public String getContact() {
		return contact;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreditDetails(String creditDetails) {
		this.creditDetails = creditDetails;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}