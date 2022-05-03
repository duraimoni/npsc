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
	private double balance;
	private double owning;

	public OwingCustomer(String name,double balance) {
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getOwingFrom() {
		return owning;
	}

	public void setOwingFrom(double owning) {
		this.owning = owning;
	}


}
