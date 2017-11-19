/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.util.Date;

/**
 *
 * @author WIN
 */
public class Importation implements Transaction{
    
    private Integer importationID;
    private Integer registrationID;
    private String companyName;
    private String pointOfContact;
    private Integer countryCode;
    private Integer phoneNumber;
    private String email;
    private String address;
    private String city;
    private String state;
    private Integer zipCode;
    private Date currentDate;
    private String product;
    private Double pricePerUnit;
    private Integer quantity;
    private Double totalPrice;
    
    @Override
    public Double calculateTotal() {
        totalPrice = pricePerUnit*quantity;
        return totalPrice;
    }

    public void setImportationID(Integer importationID) {
        this.importationID = importationID;
    }

    public void setRegistrationID(Integer registrationID) {
        this.registrationID = registrationID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPointOfContact(String pointOfContact) {
        this.pointOfContact = pointOfContact;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getImportationID() {
        return importationID;
    }

    public Integer getRegistrationID() {
        return registrationID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPointOfContact() {
        return pointOfContact;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public String getProduct() {
        return product;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
}
