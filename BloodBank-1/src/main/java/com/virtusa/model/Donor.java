package com.virtusa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="donor")

public class Donor {

	private String name;
	private String mobileNumber;
	private String bloodGroup;
	private String date;
	@Id
	private String aadharNumber;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	@Override
	public String toString() {
		return "Donor [name=" + name + ", mobileNumber=" + mobileNumber + ", bloodGroup=" + bloodGroup + ", date="
				+ date + ", aadharNumber=" + aadharNumber + "]";
	}
	
	
}
