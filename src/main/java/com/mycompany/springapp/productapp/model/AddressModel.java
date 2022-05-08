package com.mycompany.springapp.productapp.model;

import javax.persistence.*;

@Entity(name = "ADDRESS")
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADDRESS_ID")
    private Long addressId;
    @Column(name = "HOUSE_NO")
    private String houseNo;
    @Column(name = "STREET")
    private String street;
    @Column(name = "PINCODE")
    private String pinCode;
    @Column(name = "STATE")
    private String state;
    @Column(name = "COUNTRY")
    private String country;

    @OneToOne(mappedBy = "address") //This will enable bidirectional mapping with address to User i.e. which address is mapped to which user
    private UserModel userModel;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
