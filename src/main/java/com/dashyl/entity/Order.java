package com.dashyl.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Created by Darya on 05.04.2015.
 */

@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.getAll", query = "select p from Order p")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "date", updatable = false, insertable = false)
	@Generated(value = GenerationTime.INSERT)
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "cost", nullable = false)
	private double cost;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany
	@JoinColumn(name = "order_id")
	private List<OrderedProduct> products;

	public Order(Date date, double cost, Client client, List<OrderedProduct> products) {
		this.date = date;
		this.cost = cost;
		this.client = client;
		this.products = products;
	}

	public Order() {
        products = new ArrayList<OrderedProduct>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderedProduct> getProducts() {
		return products;
	}

	public void setProducts(List<OrderedProduct> products) {
		this.products = products;
	}

	public void addProductToOrder(OrderedProduct product) {
		products.add(product);
	}

    public void deleteProduct(OrderedProduct product) {
        products.remove(product);
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Order order = (Order) o;

		return id == order.id;

	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return "Order{" +
				"date=" + date +
				", cost=" + cost +
				", client=" + client +
				", products=" + products +
				'}';
	}
}