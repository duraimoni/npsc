package com.bank.cli.util.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rathakrishnan Duraimoni
 * 
 * hold the customer information 
 *
 */
public class OwingCustomer {

	private String name;
	private Long balance;
	private Long owning;

	public OwingCustomer(String name,Long balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	public OwingCustomer() {
		super();
	}

	/**
	 * to hold the values of another clients to credit/debit amounts
	 */
	
	
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
	public Long getOwning() {
		return owning;
	}

	public void setOwning(Long owning) {
		this.owning = owning;
	}


}
