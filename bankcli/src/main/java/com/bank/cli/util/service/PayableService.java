 package com.bank.cli.util.service;

/**
 * @author A575613
 *
 */
public interface PayableService extends BankService{

	String topUp(String name, Long amount);
	
	String payOther(String fromName, String toName, Long amountToTransfe);
	
	Long owingAmount(String userName);
	
	Long balanceAmount(String userName);
	
	String owingPerson(String userName);
}
