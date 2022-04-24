package com.bank.cli.util.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rathakrishnan Duraimoni
 * 
 * hold the customer information 
 *
 */
public class Customer {

	private String name;
	private Long balance;
	private Long owning;


	public Customer(String name,Long balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	public Customer() {
		super();
	}

	/**
	 * to hold the values of another clients to credit/debit amounts
	 */
	private Map<String,Customer> anotherClient = new HashMap();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Map<String, Customer> getAnotherClient() {
		return anotherClient;
	}

	public void setAnotherClient(Map<String, Customer> anotherClient) {
		this.anotherClient = anotherClient;
	}
	public Long getOwning() {
		return owning;
	}

	public void setOwning(Long owning) {
		this.owning = owning;
	}


}
