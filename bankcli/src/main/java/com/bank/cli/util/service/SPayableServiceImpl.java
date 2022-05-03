package com.bank.cli.util.service;

import com.bank.cli.util.MessageUtil;
import com.bank.cli.util.model.Customer;

/**
 * @author Rathakrishnan Duraimoni
 *
 */
public class SPayableServiceImpl implements PayableService {

	@Override
	public String topUp(String name, double amount) {
		Customer customer = customers.get(name);
		double amountToTransfer = 0;
		double owingTo = customer.getOwingTo() == null ? 0 : customer.getOwingTo();  //70
		double balance = 0l;
		if (owingTo > 0) {  
			 if(amount < owingTo) {
				 amountToTransfer = amount;
				 owingTo = customer.getOwingTo() - amount;
				 balance = 0;
			 } else {
				 amountToTransfer = owingTo;
				 owingTo = 0;
				 balance = customer.getBalance() + (amount-amountToTransfer);
			 }
		} else {
			balance = customer.getBalance() + amount; //
		}
		
		updateLoginCustomer(balance, owingTo, loginCustomer.getOwingFrom() ==null ?0 : loginCustomer.getOwingFrom(), loginCustomer.getOwingCustomerName());
		if(loginCustomer.getOwingCustomerName() !=null) {
			Customer customerO = customers.get(loginCustomer.getOwingCustomerName());
			updateOtherCustomer(loginCustomer.getOwingCustomerName(), owingTo, amountToTransfer, customer.getName() ,customerO.getOwingFrom() ==null ? 0 : customerO.getOwingFrom() );
		}
		String message = MessageUtil.printTopupMessage(amount, amountToTransfer,owingTo);
		return message;
	}

	 

	@Override
	public String payOther(String fromName, String toName, double amount) {

		Customer customer = loginCustomer;// customers.get(fromName);
		double transferAmount = 0;
		double owingTo = 0;
		double balance = 0;
		double owingFrom = 0;
		if (customer.getBalance() < 0) {
			balance = 0;
			owingTo = amount;
			transferAmount = 0;
		} else if (amount > customer.getBalance()) { // if transfer amount > balance //200 - 30
			balance = 0;
			owingTo = amount - customer.getBalance(); // 170
			transferAmount = customer.getBalance();

		}else if((customer.getOwingFrom() ==null ? 0: customer.getOwingFrom()) > amount)  { // balance 200 , tramount 30
			balance = customer.getBalance();
			owingTo = 0;
			transferAmount = 0;
			owingFrom = customer.getOwingFrom() - amount;
		} else  { // balance 200 , tramount 30
			balance = customer.getBalance() - amount;
			owingTo = 0;
			transferAmount = amount;
		}
		updateLoginCustomer(balance, owingTo, owingFrom, toName);

		String message = MessageUtil.printpayMessage(amount, transferAmount, loginCustomer.getOwingFrom(), owingTo);
		
		updateOtherCustomer(toName, owingTo, transferAmount, customer.getName(), owingFrom);
		return message;
	}

	/**
	 * @param otherCustomer
	 * @param amountToTransfer
	 * @param balance
	 */
	private void updateOtherCustomer(String otherCustomer, double owingFrom, double transferAmount, String fromName,double owingToV) {
		Customer customer = customers.get(otherCustomer);
		double owingTo = customer.getOwingTo() == null ? 0 : customer.getOwingTo();
		double owingFromI = owingFrom ;//customer.getOwingFrom() == null ? 0 : customer.getOwingFrom() + owingFrom;
		if (owingFrom > 0 && customer.getOwingTo() > 0) {
			owingTo = customer.getOwingTo() - owingFrom; // 120 -100
			if (owingTo < 0) {
				owingFromI = owingTo;
			}
		}
		//
		// updated other customer
		
		customer.setOwingCustomerName(fromName);
		customer.setOwingFrom(owingFromI);
		customer.setOwingTo(owingToV);
		double balance = customer.getBalance() + transferAmount;
		customer.setBalance(balance);
	}

	/**
	 * @param balance
	 * @param owingTo
	 * @param owingFrom
	 * @param toName
	 */
	private void updateLoginCustomer(double balance, double owingTo, double owingFrom, String toName) {
		CustomerService customerService = new CusomerServiceImpl();
		customerService.resetCustomer(balance, owingTo, owingFrom, toName);
	}

	@Override
	public Double owingAmount(String userName) {
		return loginCustomer.getOwingFrom();
	}

	@Override
	public Double balanceAmount(String userName) {
		return loginCustomer.getBalance();
	}

	@Override
	public String owingPerson(String userName) {
		return loginCustomer.getOwingCustomerName();
	}

}
