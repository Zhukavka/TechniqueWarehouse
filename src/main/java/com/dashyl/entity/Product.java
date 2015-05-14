package com.dashyl.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Darya on 05.04.2015.
 */

@Entity
@Table(name = "product")
@NamedQueries({
		@NamedQuery(name = "Product.getAll", query = "select p from Product p"),
		@NamedQuery(name = "Product.getByBarcode", query = "select p from Product p where p.barcode = :barcode")
})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "barcode", length = 45, unique = true)
	private String barcode;
	@Column(name = "product_name", length = 45, unique = true)
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		return barcode.equals(product.barcode);

	}

	@Override
	public int hashCode() {
		return barcode.hashCode();
	}

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", barcode='" + barcode + '\'' +
				", category=" + category +
				'}';
	}
}