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
		return customers.put(name, new Customer(name, 0l));
	}

	@Override
	public String loginUser(String name) {
		Customer customer = null;
		if (!isValidUser(name)) {
			adduser(name);
		} 
		customer = customers.get(name);
		resetCustomer(customer);
		String message = MessageUtil.printLoginMessage();
		return message;// "login "+name+" Hello, "+name+"! Your balance is "+customer.getBalance()+".";
	}

	/**
	 * 
	 */
	private void resetCustomer(Customer custom) {
		loginCustomer.setName(custom.getName());
		loginCustomer.setBalance(custom.getBalance());
		loginCustomer.setOwning(custom.getOwning());
		loginCustomer.setOwingCustomerName(custom.getOwingCustomerName());
	}

}
