package com.bank.cli.util.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bank.cli.util.model.Customer;

/**
 * @author Rathakrishnan Duraimoni
 *
 */
public class PayableServiceImpl implements PayableService {

	@Override
	public String topUp(String name, double amount) {
		Customer customer = customers.get(name);
		double finalBalance = customer.getBalance() + amount;
		customers.get(name).setBalance(finalBalance);
		if (finalBalance < 1) {
			finalBalance = 0l;
		}
		// owing customers
		String owingValue = getOwingCustomers(customer.getAnotherClient());
		return "topup " + amount + ", "+ owingValue +", Your balance is " + finalBalance + ".";

	}

	/**
	 * @param otherClients
	 * @return
	 */
	private String getOwingCustomers(Map<String, Customer> otherClients) {
		String retMessage = "";
		List<String> owingLst = otherClients.entrySet().stream().filter(obj -> obj.getValue().getOwingFrom() > 0)
				.map(Map.Entry::getValue).map(obj -> " Owing " + obj.getOwingFrom() + " to " + obj.getName())
				.collect(Collectors.toList());
		for(String owingStr : owingLst) {
			retMessage = retMessage +" " + owingStr;
		}
		return retMessage;
	}

	@Override
	public String payOther(String fromName, String toName, double amountToTransfer) {
		// update the other Customer
		double orgAmount = amountToTransfer;
		Customer logedInCustomer = customers.get(fromName);
		double balance = logedInCustomer.getBalance() - amountToTransfer;
		double owing = 0l;
		if (balance < 0) {
			owing = -(balance);
			balance = 0l;
		}

		amountToTransfer = amountToTransfer - owing;

		Customer toCustomer = customers.get(toName);
		if (owing > 0) {
			if (toCustomer == null) {
				toCustomer = new Customer(toName, amountToTransfer < 0 ? 0 : amountToTransfer);
			} else {
				double toBalance = toCustomer.getBalance() + amountToTransfer < 0 ? 0 : amountToTransfer;
				toCustomer.setBalance(toBalance);
			}
		}
		toCustomer.setOwingFrom(owing);

		logedInCustomer.setBalance(balance);

		logedInCustomer.getAnotherClient().put(toName, toCustomer);
		String returnMessage = "pay " + toName + " " + orgAmount + " Transferred " + amountToTransfer + "   to "
				+ toName + ". Your balance is " + balance;
		if (owing > 0) {
			returnMessage = returnMessage + ". Owing " + owing + " to " + toName + ".";
		}

		return returnMessage;
	}

	@Override
	public Double owingAmount(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double balanceAmount(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String owingPerson(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
