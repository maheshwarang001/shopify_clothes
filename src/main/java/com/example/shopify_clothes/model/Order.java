package com.example.shopify_clothes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "order_list")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderID;

    @ElementCollection
    private List<Long> orderItems;

    private float tPrice;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    @Transient
    private String nameOnCard;

    @Pattern(regexp="(^$|[0-9]{10})",message = "Number must be 10 digit")
    private String cardNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])/([0-9]{2})$", message="Expiration must be in the format MM/YY")
    private String expiration;


    @Pattern(regexp="^[0-9]{3}$", message="CVV must be 3 digits")
    private String cvv;

    @NotNull(message = "cannot be null")
    private LocalDate date;


    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public List<Long> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Long> orderItems) {
        this.orderItems = orderItems;
    }

    public float gettPrice() {
        return tPrice;
    }

    public void settPrice(float tPrice) {
        this.tPrice = tPrice;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
