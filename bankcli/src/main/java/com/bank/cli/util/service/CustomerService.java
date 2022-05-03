package com.bank.cli.util.service;

import com.bank.cli.util.model.Customer;

/**
 * @author Rathakrishnan Duraimoni
 *
 */
public interface CustomerService extends BankService {

	boolean isValidUser(String name);

	Customer adduser(String name);
	
	String loginUser(String name);
	
	void resetCustomer(double balance, double owingTo, double owingFrom, String toName);
}
