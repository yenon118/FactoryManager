/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ycth8factorymanager;

import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author WIN
 */
public class ImportationModal implements Transaction{
    
    private Integer importationID;
    private Integer registrationID;
    private String companyName;
    private String pointOfContact;
    private Integer countryCode;
    private Long phoneNumber;
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
        totalPrice = Math.ceil(pricePerUnit*quantity);
        return totalPrice;
    }

    public Integer getImportationID() {
        return importationID;
    }

    public void setImportationID(Integer importationID) {
        this.importationID = importationID;
    }

    public Integer getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(Integer registrationID) {
        this.registrationID = registrationID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPointOfContact() {
        return pointOfContact;
    }

    public void setPointOfContact(String pointOfContact) {
        this.pointOfContact = pointOfContact;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static Comparator<ImportationModal> sortImportationDate = new Comparator<ImportationModal>() {
        @Override
        public int compare(ImportationModal o1, ImportationModal o2) {
            return o1.getCurrentDate().compareTo(o2.getCurrentDate());
        }
    };
    
}
