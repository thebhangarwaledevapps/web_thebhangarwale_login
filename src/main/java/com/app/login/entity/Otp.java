package com.app.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Otp {

	@Column(nullable = false, length = 6, columnDefinition = "decimal(6)")
    private int otpCode;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String token;

	public Otp() {
		super();
	}

	public Otp(int otpCode) {
		super();
		this.otpCode = otpCode;
	}

	public int getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(int otpCode) {
		this.otpCode = otpCode;
	}

	public String getToken() {
		return token;
	}
	
}
