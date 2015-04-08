package com.dashyl.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

/**
 * Created by Darya on 05.04.2015.
 */

@Entity
@Table(name = "order")
@NamedQuery(name = "Order.getAll", query = "select p from Order p")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Column(name = "cost")
	private double cost;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
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
}