package com.example.CustomerProducer.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Customer_Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "age")
	private int age;

	@Column(name = "addressId")
	private int addressId;

	@Column(name = "typeofAccount")
	private String typeofAccount;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zipCode")
	private String zipCode;

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

	public String getTypeofAccount() {
		return typeofAccount;
	}

	public void setTypeofAccount(String string) {
		this.typeofAccount = string;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Customer_Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer_Bank(int id, String name, int age, int addressId, String typeofAccount, String street, String city,
			String state, String zipCode) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.addressId = addressId;
		this.typeofAccount = typeofAccount;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", addressId=" + addressId
				+ ", typeofAccount=" + typeofAccount + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + "]";
	}

}
