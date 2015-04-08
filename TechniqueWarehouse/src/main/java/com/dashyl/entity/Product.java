package com.dashyl.entity;

import javax.persistence.*;

/**
 * Created by Darya on 05.04.2015.
 */

@Entity
@Table(name = "product")
@NamedQuery(name = "Product.getAll", query = "select p from Product p")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "product_name", length = 45)
	private String name;
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	public Product(String name, Category category) {
		this.name = name;
		this.category = category;
	}

	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}