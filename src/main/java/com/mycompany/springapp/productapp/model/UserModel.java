package com.mycompany.springapp.productapp.model;

import javax.persistence.*;

@Entity(name = "USER_TABLE")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    //OneToOne is just a primary key foreign key relationship between user and address table i.e. one user will have only one address
    @OneToOne(cascade = CascadeType.ALL)//Any operation like delete, update that happens to user will automatically reflect the correspond rows/entries in the address table
    //If we delete a user then it will delete its corresponding address
    @JoinColumn(name = "ADDRESS_ID")
    private AddressModel address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
