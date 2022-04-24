 package com.bank.cli.util.service;

/**
 * @author A575613
 *
 */
public interface PayableService extends BankService{

	String topUp(String name, Long amount);
	
	String payOther(String fromName, String toName, Long amountToTransfe);
}
