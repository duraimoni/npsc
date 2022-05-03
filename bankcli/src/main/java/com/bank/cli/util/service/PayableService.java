 package com.bank.cli.util.service;

/**
 * @author A575613
 *
 */
public interface PayableService extends BankService{

	String topUp(String name, double amount);
	
	String payOther(String fromName, String toName, double amountToTransfe);
	
	Double owingAmount(String userName);
	
	Double balanceAmount(String userName);
	
	String owingPerson(String userName);
}
