package com.dashyl.entity;

import javax.persistence.*;

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

    public OrderedProduct(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public OrderedProduct() {
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
}
