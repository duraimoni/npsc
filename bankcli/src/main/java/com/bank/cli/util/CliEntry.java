package com.bank.cli.util;

import java.util.Scanner;

import com.bank.cli.util.service.BankService;
import com.bank.cli.util.service.CusomerServiceImpl;
import com.bank.cli.util.service.CustomerService;
import com.bank.cli.util.service.PayableService;
import com.bank.cli.util.service.PayableServiceImpl;

/**
 * @author A575613
 *
 */
public class CliEntry {

	public static void main(String[] args) {
		// List<String> commands = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		String val = "";
		while (scan.hasNext()) {
			val = scan.nextLine();
			String[] inputVal = val.split(" ");
			String message = BankUtil.validateInput(inputVal);
			if (message.isEmpty()) {
				callService(inputVal);
			} else {
				System.out.println(message);
			}

		}
	}

	private static void callService(String[] inputVal) {
		String serviceMessage = "";
		switch (inputVal[0]) {
		case BankUtil.LOGIN: {
			CustomerService customerService = new CusomerServiceImpl();
			serviceMessage = customerService.loginUser(inputVal[1]);
			break;
		}
		case BankUtil.TOPUP: {
			PayableService payableService = new PayableServiceImpl();
			serviceMessage = payableService.topUp(BankService.loginCustomer.getName(), Long.parseLong(inputVal[1]));
			break;
		}
		case BankUtil.PAY: {
			PayableService payableService = new PayableServiceImpl();
			serviceMessage = payableService.payOther(BankService.loginCustomer.getName(), inputVal[1], Long.parseLong(inputVal[2]));
			break;
		}
		}
		System.out.println(serviceMessage);
	}

}
