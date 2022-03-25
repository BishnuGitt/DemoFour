package com.employee.Rest.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address_tbl")
public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idAddress", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IdAddress; 
	private String street; 
	private String city; 
	private String state; 
	private String zipCode; 
	private String country;
	
	public Address(int idAddress, String street, String city, String state, String zipCode, String country) {
		super();
		IdAddress = idAddress;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	public int getIdAddress() {
		return IdAddress;
	}

	public void setIdAddress(int idAddress) {
		IdAddress = idAddress;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [IdAddress=" + IdAddress + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + ", country=" + country + "]";
	}
}