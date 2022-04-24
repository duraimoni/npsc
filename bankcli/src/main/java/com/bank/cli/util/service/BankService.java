package com.bank.cli.util.service;

import java.util.HashMap;
import java.util.Map;

import com.bank.cli.util.model.Customer;

public interface BankService {

	public static final Map<String,Customer> customers = new HashMap<String, Customer>();
	
	public static  Customer loginCustomer = new Customer();

}
