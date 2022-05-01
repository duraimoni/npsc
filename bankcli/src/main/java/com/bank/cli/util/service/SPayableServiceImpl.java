package com.bank.cli.util.service;

import com.bank.cli.util.MessageUtil;
import com.bank.cli.util.model.Customer;

/**
 * @author Rathakrishnan Duraimoni
 *
 */
public class SPayableServiceImpl implements PayableService {

	@Override
	public String topUp(String name, Long amount) {
		Customer customer = customers.get(name);
		Long balance = customer.getBalance() + amount;
		Long amountToTransfer = 0l;
		Long balanceOwingTo = 0l;
		Long owningAmt = customer.getOwning() == null ? 0l : customer.getOwning();
		if (owningAmt < 0) {
			amountToTransfer = balance - owningAmt;
			if (amountToTransfer < balance) {
				amountToTransfer = owningAmt - balance;
			}
			balanceOwingTo = owningAmt - amountToTransfer;
			customer.setOwning(balanceOwingTo);
			loginCustomer.setOwning(balanceOwingTo);
		}

		customer.setBalance(balance);
		loginCustomer.setBalance(balance);

		String message = MessageUtil.printTopupMessage(amount, amountToTransfer);
		return message;
	}

	@Override
	public String payOther(String fromName, String toName, Long amount) {

		Customer customer = customers.get(fromName);
		Long balance = customer.getBalance() - amount;

		if (balance < 0) {
			balance = 0l;
		}
		customer.setBalance(balance);
		customer.setOwingCustomerName(toName);
		loginCustomer.setBalance(balance);
		loginCustomer.setOwingCustomerName(toName);

		Long amountToTransfer = 0l;
		Long balanceOwingTo = 0l;
		Long owningAmt = customer.getOwning() == null ? 0l : customer.getOwning();
		if (owningAmt < 0) {
			amountToTransfer = balance - owningAmt;
			if (amountToTransfer < balance) {
				amountToTransfer = owningAmt - balance;
			}
			balanceOwingTo = owningAmt - amountToTransfer;
			customer.setOwning(balanceOwingTo);
			loginCustomer.setOwning(balanceOwingTo);
		}

		customer.setBalance(balance);
		loginCustomer.setBalance(balance);
		updateOtherCustomer(toName, amount, amountToTransfer);
		String message = MessageUtil.printpayMessage(amount, amountToTransfer);
		return message;
	}

	/**
	 * @param otherCustomer
	 * @param amountToTransfer
	 * @param balance
	 */
	private void updateOtherCustomer(String otherCustomer, Long amountToTransfer, Long amount) {
		Long owing = amount - amountToTransfer;

		Customer customer = customers.get(otherCustomer);
		Long balance = customer.getBalance() + amountToTransfer;
		customer.setBalance(balance);
		owing = customer.getOwning()== null ? 0l : customer.getOwning() + owing;
		customer.setOwning(owing);

	}

	@Override
	public Long owingAmount(String userName) {
		return loginCustomer.getOwning();
	}

	@Override
	public Long balanceAmount(String userName) {
		return loginCustomer.getBalance();
	}

	@Override
	public String owingPerson(String userName) {
		return loginCustomer.getOwingCustomerName();
	}

}
