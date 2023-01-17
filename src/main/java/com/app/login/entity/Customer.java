package com.app.login.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import static com.app.login.util.jpa_custom_key_generator.BhangarwaleCustomerIdGenerator.VALUE_PREFIX_PARAMETER;
import static org.hibernate.id.enhanced.SequenceStyleGenerator.INCREMENT_PARAM;
import static com.app.login.util.jpa_custom_key_generator.BhangarwaleCustomerIdGenerator.NUMBER_FORMAT_PARAMETER;

@Entity
public class Customer {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customerId")
	private List<FirebaseToken> firebaseTokens;

	@OneToOne(cascade = CascadeType.ALL)
	private Mobile mobile;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bhangarwale_seq")
	@GenericGenerator(name = "bhangarwale_seq", strategy = "com.app.login.util.jpa_custom_key_generator.BhangarwaleCustomerIdGenerator", parameters = {
			@Parameter(name = INCREMENT_PARAM, value = "1"),
			@Parameter(name = VALUE_PREFIX_PARAMETER, value = "Bhangarwale_"),
			@Parameter(name = NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String customerId;

	public Customer() {
		super();
	}

	public Customer(List<FirebaseToken> firebaseTokens, Mobile mobile) {
		super();
		this.firebaseTokens = firebaseTokens;
		this.mobile = mobile;
	}

	public List<FirebaseToken> getFirebaseTokens() {
		return firebaseTokens;
	}

	public void setFirebaseTokens(List<FirebaseToken> firebaseTokens) {
		this.firebaseTokens = firebaseTokens;
	}

	public Mobile getMobile() {
		return mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}

	public String getCustomerId() {
		return customerId;
	}
	
}
