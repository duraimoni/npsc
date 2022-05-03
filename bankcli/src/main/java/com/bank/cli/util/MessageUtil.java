package com.bank.cli.util;

import static com.bank.cli.util.service.BankService.loginCustomer;;

public class MessageUtil {

	public static String printLoginMessage() {
		double balance = loginCustomer.getBalance();
		double owingFrom = loginCustomer.getOwingFrom() == null ? 0l : loginCustomer.getOwingFrom();
		double owingTo = loginCustomer.getOwingTo() == null ? 0l : loginCustomer.getOwingTo();
		String owingName = loginCustomer.getOwingCustomerName() == null ? "" : loginCustomer.getOwingCustomerName();
		String name = loginCustomer.getName();

		String startMessage = "login " + name + " Hello, " + name + "!";
		String owingFromMessage = owingFrom == 0 ? "" : " Owing " + owingFrom + " from " + owingName;
		String owingToMessage = owingTo == 0 ? "" : " Owing " + owingTo + " to " + owingName;
		String balanceMessage = " Your balance is " + balance;
		String printMessage = startMessage + balanceMessage + owingToMessage + owingFromMessage;
		System.out.println(printMessage);
		return printMessage;
	}

	/**
	 * 
	 */
	public static String printpayMessage(double amount, double tAmount, double owingFrom, double owingTo) {
		double balance = loginCustomer.getBalance();
		String owingName = loginCustomer.getOwingCustomerName() == null ? "" : loginCustomer.getOwingCustomerName();
		String name = loginCustomer.getName();

		String startMsg = "pay " + owingName + " " + amount;
		String balanceMsg = " Your balance is " + balance + ".";
//		String owingMessage = " Owing " + owing + (owing < 0 ? " to " : " from ") + owingName + ".";
		String owingFromMessage = owingFrom > 0 ? "Owing " + owingFrom + " from " + owingName : "";
		String owingToMessage = owingTo > 0 ? "Owing " + owingTo + " to " + owingName : "";
		;
		String transferMsg = " Transferred " + tAmount + " to " + owingName + ".";

		String printMessage = startMsg + (tAmount == 0 ? " " : transferMsg) + owingFromMessage + balanceMsg
				+ owingToMessage;

		System.out.println(printMessage);
		return printMessage;
	}

	/**
	 * 
	 */
	public static String printTopupMessage(double tAmount, double trAmount, double owingTo) {
		double balance = loginCustomer.getBalance();
		String owingName = loginCustomer.getOwingCustomerName() == null ? "" : loginCustomer.getOwingCustomerName();

		String startMsag = "topup " + tAmount;
		String balanceMsg = " Your balance is " + balance;
		String owingMsg = " Owing " + owingTo + " to " + owingName;
		String transferedMsg = " Transferred " + trAmount + " to " + owingName;
		if (trAmount == 0) {
			transferedMsg = "";
		}

		// topup 30 Transferred 30 to Harry. Your balance is 0. Owing 40 to Harry.
		String printMessage = startMsag + transferedMsg + balanceMsg + (owingTo > 0 ? owingMsg : "");
		System.out.println(printMessage);
		return printMessage;
	}

}
