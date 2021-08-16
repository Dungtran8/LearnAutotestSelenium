package com.onemount.dungtran.models;

import com.onemount.dungtran.utils.RandomUtils;

public class Member {
	String firstname;
	String lastname;
	String email;
	String password;

	public Member() {
		this.firstname = RandomUtils.randomText(8);
		this.lastname = RandomUtils.randomText(8);
		this.email = RandomUtils.randomEmail();
		this.password = RandomUtils.randomPassword(8);
	}

	public Member(String firstname) {
		this.firstname = firstname;
		this.lastname = "";
		this.email = "";
		this.password = "";
	}

	public Member(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = "";
		this.password = "";
	}

	public Member(String firstname, String lastname, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = "";
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
