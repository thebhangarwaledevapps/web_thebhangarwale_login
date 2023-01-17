package com.app.login.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mobile {

	@Column(unique = true, nullable = false, length = 10, columnDefinition = "decimal(10)")
	private String mobileNumber;

	@Column(nullable = false, length = 2, columnDefinition = "decimal(2)")
	private String countryCode;

	@JoinTable(name = "mob_otp")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Otp otp;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	public Mobile() {
		super();
	}

	public Mobile(String mobileNumber, String countryCode, Otp otp) {
		super();
		this.mobileNumber = mobileNumber;
		this.countryCode = countryCode;
		this.otp = otp;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Otp getOtp() {
		return otp;
	}

	public void setOtp(Otp otp) {
		this.otp = otp;
	}

	public String getId() {
		return id;
	}

}
