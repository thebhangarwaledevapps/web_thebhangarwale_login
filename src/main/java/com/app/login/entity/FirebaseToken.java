package com.app.login.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FirebaseToken {
	
	@Id
	private String firebaseToken;

	public FirebaseToken() {
		super();
	}

	public FirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
	}

	public String getFirebaseToken() {
		return firebaseToken;
	}
	
}
