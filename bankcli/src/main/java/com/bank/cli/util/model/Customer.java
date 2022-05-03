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
	private Double balance;
	private Double owingFrom;
	private Double owingTo;
	@Override
	public String toString() {
		return "Customer [name=" + name + ", balance=" + balance + ", owingFrom=" + owingFrom + ", owingTo=" + owingTo
				+ ", owingCustomerName=" + owingCustomerName + ", anotherClient=" + anotherClient + "]";
	}

	private String owingCustomerName;

	 

	public Customer(String name) {
		super();
		this.name = name;
		this.balance = 0d;
	}

	public Customer() {
		super();
	}

	public Customer(String toName, double d) {
		// TODO Auto-generated constructor stub
		this.name = toName;
		this.balance = 0d;
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Map<String, Customer> getAnotherClient() {
		return anotherClient;
	}

	public void setAnotherClient(Map<String, Customer> anotherClient) {
		this.anotherClient = anotherClient;
	}
 

 
	public Double getOwingFrom() {
		return owingFrom;
	}

	public void setOwingFrom(Double owingFrom) {
		this.owingFrom = owingFrom;
	}

	public Double getOwingTo() {
		return owingTo;
	}

	public void setOwingTo(Double owingTo) {
		this.owingTo = owingTo;
	}

	public String getOwingCustomerName() {
		return owingCustomerName;
	}

	public void setOwingCustomerName(String owingCustomerName) {
		this.owingCustomerName = owingCustomerName;
	}

	 


}
