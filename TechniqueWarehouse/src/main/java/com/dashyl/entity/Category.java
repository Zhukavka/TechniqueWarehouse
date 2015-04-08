package com.dashyl.entity;

import javax.persistence.*;

/**
 * Created by Darya on 05.04.2015.
 */

@Entity
@Table(name = "category")
@NamedQuery(name = "Category.getAll", query = "select p from Category p")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "category_name", length = 32)
	private String name;

	public Category(String name) {
		this.name = name;
	}

	public Category() {
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
}