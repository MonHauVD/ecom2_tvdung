package vietdung.ecom2_tvdung.controller.dto;

public class CustomerRegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
        private String number;
        private String street;
        private String ward;
        private String district;
        private String province;
        private String country;
        private String phoneNumber;
        private String image;
	
	public CustomerRegistrationDto(){
		
	}

    public CustomerRegistrationDto(String firstName, String lastName, String email, String password, String number, String street, String ward, String district, String province, String country, String phoneNumber, String image)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.number = number;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.province = province;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

        
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getWard()
    {
        return ward;
    }

    public void setWard(String ward)
    {
        this.ward = ward;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    

        public String getPhoneNumber()
        {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
        }

        public String getImage()
        {
            return image;
        }

        public void setImage(String image)
        {
            this.image = image;
        }
        
        
}
