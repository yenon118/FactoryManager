/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorymanager;

import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author WIN
 */
public class DeliveryModal implements Transaction{
    
    private Integer deliveryID;
    private Integer registrationID;
    private Integer exportationID;
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
    private Double containerPricePerUnit;
    private Integer containerQuantity;
    private Double shipmentPrice;
    private Double totalPrice;

    @Override
    public Double calculateTotal() {
        totalPrice = shipmentPrice + (containerPricePerUnit * containerQuantity);
        return totalPrice;
    }

    public Integer getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(Integer deliveryID) {
        this.deliveryID = deliveryID;
    }

    public Integer getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(Integer registrationID) {
        this.registrationID = registrationID;
    }

    public Integer getExportationID() {
        return exportationID;
    }

    public void setExportationID(Integer exportationID) {
        this.exportationID = exportationID;
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

    public Double getContainerPricePerUnit() {
        return containerPricePerUnit;
    }

    public void setContainerPricePerUnit(Double containerPricePerUnit) {
        this.containerPricePerUnit = containerPricePerUnit;
    }

    public Integer getContainerQuantity() {
        return containerQuantity;
    }

    public void setContainerQuantity(Integer containerQuantity) {
        this.containerQuantity = containerQuantity;
    }

    public Double getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(Double shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public static Comparator<DeliveryModal> sortDeliveryDate = new Comparator<DeliveryModal>() {
        @Override
        public int compare(DeliveryModal o1, DeliveryModal o2) {
            return o1.getCurrentDate().compareTo(o2.getCurrentDate());
        }
    };
    
}
