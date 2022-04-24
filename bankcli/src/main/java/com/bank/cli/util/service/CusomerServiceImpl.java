package com.bank.cli.util.service;

import java.util.Optional;

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
		return customers.put(name, new Customer(name, 0l));
	}

	@Override
	public String loginUser(String name) {
		Customer customer = null;
		if (!isValidUser(name)) {
			adduser(name);
		} 
		customer = customers.get(name);
		resetCustomer(name);
		return "login "+name+" Hello, "+name+"! Your balance is "+customer.getBalance()+".";
	}

	/**
	 * 
	 */
	private void resetCustomer(String name) {
		loginCustomer.setName(name);

	}

}
