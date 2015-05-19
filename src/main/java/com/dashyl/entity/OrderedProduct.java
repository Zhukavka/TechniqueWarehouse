package com.dashyl.entity;

import com.dashyl.util.DAOFactory;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Darya on 05.04.2015.
 */

@Entity
@Table(name = "ordered_product")
@NamedQuery(name = "OrderedProduct.getAll", query = "select p from OrderedProduct p")
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private double price;

    public OrderedProduct(AvailableProduct product, int amount) {
        this.product = product.getProduct();
        this.amount = amount;
        this.price = product.getPrice();
    }


    public OrderedProduct(OrderedProduct product) {
        this.product = product.getProduct();
        this.amount = product.getAmount();
        this.price = product.getPrice();
    }

    public OrderedProduct() {
    }

    public int getId() {
        return id;
    }

    public OrderedProduct setId(int id) {
        this.id = id;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderedProduct that = (OrderedProduct) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "product=" + product +
                ", amount=" + amount +
                '}';
    }
}
