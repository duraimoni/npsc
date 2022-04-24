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
	public String topUp(String name, Long amount) {
		Customer customer = customers.get(name);
		Long finalBalance = customer.getBalance() + amount;
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
		List<String> owingLst = otherClients.entrySet().stream().filter(obj -> obj.getValue().getOwning() > 0)
				.map(Map.Entry::getValue).map(obj -> " Owing " + obj.getOwning() + " to " + obj.getName())
				.collect(Collectors.toList());
		for(String owingStr : owingLst) {
			retMessage = retMessage +" " + owingStr;
		}
		return retMessage;
	}

	@Override
	public String payOther(String fromName, String toName, Long amountToTransfer) {
		// update the other Customer
		Long orgAmount = amountToTransfer;
		Customer logedInCustomer = customers.get(fromName);
		Long balance = logedInCustomer.getBalance() - amountToTransfer;
		Long owing = 0l;
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
				long toBalance = toCustomer.getBalance() + amountToTransfer < 0 ? 0 : amountToTransfer;
				toCustomer.setBalance(toBalance);
			}
		}
		toCustomer.setOwning(owing);

		logedInCustomer.setBalance(balance);

		logedInCustomer.getAnotherClient().put(toName, toCustomer);
		String returnMessage = "pay " + toName + " " + orgAmount + " Transferred " + amountToTransfer + "   to "
				+ toName + ". Your balance is " + balance;
		if (owing > 0) {
			returnMessage = returnMessage + ". Owing " + owing + " to " + toName + ".";
		}

		return returnMessage;
	}

}
