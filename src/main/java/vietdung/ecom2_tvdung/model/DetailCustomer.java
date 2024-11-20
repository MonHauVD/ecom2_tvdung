/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.model;

/**
 *
 * @author TranVietDung
 */
public class DetailCustomer
    {

        private Long CusId;
        private String image, firstName, lastName, email, phoneNumber, address;

        public DetailCustomer()
        {
        }

        public DetailCustomer(Long CusId, String image, String firstName, String lastName, String email, String phoneNumber, String address)
        {
            this.CusId = CusId;
            this.image = image;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }

        public Long getCusId()
        {
            return CusId;
        }

        public void setCusId(Long CusId)
        {
            this.CusId = CusId;
        }

        public String getImage()
        {
            return image;
        }

        public void setImage(String image)
        {
            this.image = image;
        }

        public String getFirstName()
        {
            return firstName;
        }

        public void setFirstName(String firstName)
        {
            this.firstName = firstName;
        }

        public String getLastName()
        {
            return lastName;
        }

        public void setLastName(String lastName)
        {
            this.lastName = lastName;
        }

        public String getEmail()
        {
            return email;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public String getPhoneNumber()
        {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        @Override
        public String toString()
        {
            return "DetailCustomer{" + "CusId=" + CusId + ", image=" + image + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + '}';
        }
        
        
    }