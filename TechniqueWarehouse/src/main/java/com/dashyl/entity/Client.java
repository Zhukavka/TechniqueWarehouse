package com.dashyl.entity;

import javax.persistence.*;

/**
 * Created by Darya on 05.04.2015.
 */

@Entity
@Table(name = "client")
@NamedQuery(name = "Client.getAll", query = "select p from Client p")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name", length = 32)
	private String name;
	@Column(name = "email", length = 32)
	private String email;
	@Column(name = "phone", length = 32)
	private String phoneNumber;

	public Client(String name, String email, String phoneNumber) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Client() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}