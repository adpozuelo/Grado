/**
 * Address class.
 * Embeddable class.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package jpa;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;

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
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Constructor.
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param country
	 */
	public Address(String street, String city, String state, String zipCode,
			String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	public Address() {
	}

}
