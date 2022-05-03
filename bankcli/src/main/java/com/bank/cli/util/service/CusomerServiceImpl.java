package com.bank.cli.util.service;

import java.util.Optional;

import com.bank.cli.util.MessageUtil;
import com.bank.cli.util.model.Customer;

/**
 * @author Rathakrishnan Duraimoni
 *
 */
public class CusomerServiceImpl implements CustomerService {
	
	/**
	 *
	 */
	public boolean isValidUser(String name) {
		return Optional.ofNullable(customers.get(name)).isPresent();
	}

	/**
	 *
	 */
	public Customer adduser(String name) {
		return customers.put(name, new Customer(name));
	}

	@Override
	public String loginUser(String name) {
		Customer customer = null;
		if (!isValidUser(name)) {
			adduser(name);
		} 
		customer = customers.get(name);
		resetLogin(customer);
		String message = MessageUtil.printLoginMessage();
		return message;// "login "+name+" Hello, "+name+"! Your balance is "+customer.getBalance()+".";
	}

	/**
	 * 
	 */
	public void resetCustomer(double balance, double owingTo, double owingFrom, String toName) {
		loginCustomer.setBalance(balance);
		loginCustomer.setOwingFrom(owingFrom);
		loginCustomer.setOwingTo(owingTo);
		loginCustomer.setOwingCustomerName(toName);
		resetLoginCustomer();
	}
	
	public static void resetLoginCustomer() {
		Customer customer = new Customer();
		customer.setName(loginCustomer.getName());
		customer.setBalance(loginCustomer.getBalance());
		customer.setOwingFrom(loginCustomer.getOwingFrom());
		customer.setOwingTo(loginCustomer.getOwingTo());
		customer.setOwingCustomerName(loginCustomer.getOwingCustomerName());
		customers.put(loginCustomer.getName(), customer);
	}
	public static void resetLogin(Customer customer) {
		loginCustomer.setName(customer.getName());
		loginCustomer.setBalance(customer.getBalance());
		loginCustomer.setOwingFrom(customer.getOwingFrom());
		loginCustomer.setOwingTo(customer.getOwingTo());
		loginCustomer.setOwingCustomerName(customer.getOwingCustomerName());
		resetLoginCustomer();
		//customers.put(loginCustomer.getName(), loginCustomer);
	}

}
