package com.dashyl.entity;

import javax.persistence.*;

/**
 * Created by Darya on 05.04.2015.
 */

@Entity
@Table(name = "available_product")
@NamedQuery(name = "AvailableProduct.getAll", query = "select p from AvailableProduct p")
public class AvailableProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne @MapsId
    private Product product;
    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private double price;

    public AvailableProduct(Product product, int amount, double price) {
        this.product = product;
        this.amount = amount;
        this.price = price;
    }

    public AvailableProduct() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
