package com.bank.cli.util;

import static com.bank.cli.util.service.BankService.loginCustomer;;

public class MessageUtil {

	public static String printLoginMessage() {
		Long balance = loginCustomer.getBalance();
		Long owing = loginCustomer.getOwning() == null ? 0l : loginCustomer.getOwning();
		String owingName = loginCustomer.getOwingCustomerName() == null ? "" : loginCustomer.getOwingCustomerName();
		String name = loginCustomer.getName();

		String printMessage = "login " + name + " Hello, " + name + "!"
				+ (owing != 0 ? (" Owing " + -(-owing) + " " + (owing > 0 ? "From" : "To ") + owingName) : "")
				+ " Your balance is " + balance;
		System.out.println(printMessage);
		return printMessage;
	}

	/**
	 * 
	 */
	public static String printpayMessage(Long amount, Long tAmount) {
		Long balance = loginCustomer.getBalance();
		Long owing = loginCustomer.getOwning() == null ? 0l : loginCustomer.getOwning();
		String owingName = loginCustomer.getOwingCustomerName() == null ? "" : loginCustomer.getOwingCustomerName();
		String name = loginCustomer.getName();

		String startMsg = "pay " + owingName + " " + amount;
		String balanceMsg = ". Your balance is " + balance + ".";
		String owingMessage = " Owing " + owing + (owing < 0 ? " to " : " from ") + owingName + ".";
		String transferMsg = " Transferred " + tAmount + " to " + owingName + ".";
		
		
		String printMessage = startMsg + (owing > 0 ? owingMessage : transferMsg) + balanceMsg
				+ (owing < 0 ? owingMessage : "");
		System.out.println(printMessage);
		return printMessage;
	}

	/**
	 * 
	 */
	public static String printTopupMessage(Long tAmount, Long trAmount) {
		Long balance = loginCustomer.getBalance();
		Long owing = loginCustomer.getOwning() == null ? 0l : loginCustomer.getOwning();
		String owingName = loginCustomer.getOwingCustomerName() == null ? "" : loginCustomer.getOwingCustomerName();

		String startMsag = "topup " + tAmount;
		String balanceMsg = " Your balance is " + balance;
		String owingMsg = " Owing " + owing + " to " + owingName;
		String transferedMsg = " Transferred " + trAmount + " to " + owingName;
		if (trAmount == 0) {
			transferedMsg = "";
		}
		if (owing == 0) {
			owingMsg = "";
		}
		// topup 30 Transferred 30 to Harry. Your balance is 0. Owing 40 to Harry.
		String printMessage = startMsag + (owing > 0 ? owingMsg : transferedMsg) + balanceMsg
				+ (owing < 0 ? owingMsg : "");
		System.out.println(printMessage);
		return printMessage;
	}

}
