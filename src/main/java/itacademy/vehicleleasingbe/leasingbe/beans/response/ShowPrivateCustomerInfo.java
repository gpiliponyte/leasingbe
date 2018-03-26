package itacademy.vehicleleasingbe.leasingbe.beans.response;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.PrivateCustomerInfo;

public class ShowPrivateCustomerInfo extends Response {

    private String firstName;
    private String lastName;
    private String idCode;
    private String phoneNumber;
    private String street;
    private String city;
    private String postCode;
    private String country;

    public ShowPrivateCustomerInfo (PrivateCustomerInfo privateCustomerInfo){
        this.firstName = privateCustomerInfo.getFirstName();
        this.lastName  = privateCustomerInfo.getLastName();
        this.idCode = privateCustomerInfo.getIdCode();
        this.phoneNumber = privateCustomerInfo.getPhoneNumber();
        this.street = privateCustomerInfo.getStreet();
        this.city = privateCustomerInfo.getCity();
        this.postCode = privateCustomerInfo.getPostCode();
        this.country = privateCustomerInfo.getCountry();
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

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
