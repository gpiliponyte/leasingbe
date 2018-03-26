package itacademy.vehicleleasingbe.leasingbe.beans.response;

import itacademy.vehicleleasingbe.leasingbe.beans.documents.BusinessCustomerInfo;



public class ShowBusinessCustomerInfo extends Response {

    private String companyName;
    private String companyCode;
    private String email;
    private String phoneNumber;
    private String street;
    private String city;
    private String postCode;
    private String country;

    public ShowBusinessCustomerInfo (BusinessCustomerInfo businessCustomerInfo){
        this.companyName = businessCustomerInfo.getCompanyName();
        this.companyCode = businessCustomerInfo.getCompanyCode();
        this.email = businessCustomerInfo.getEmail();
        this.phoneNumber = businessCustomerInfo.getPhoneNumber();
        this.street = businessCustomerInfo.getStreet();
        this.city = businessCustomerInfo.getCity();
        this.postCode = businessCustomerInfo.getPostCode();
        this.country = businessCustomerInfo.getCountry();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
