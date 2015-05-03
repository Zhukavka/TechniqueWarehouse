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
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "amount", nullable = false)
    private int amount;
    @Column(name = "price", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailableProduct that = (AvailableProduct) o;

        if (Double.compare(that.price, price) != 0) return false;
        return product.equals(that.product);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = product.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "AvailableProduct{" +
                "product=" + product +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
