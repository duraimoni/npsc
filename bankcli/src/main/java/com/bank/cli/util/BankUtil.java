package com.bank.cli.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankUtil {

	static final String LOGIN = "login";
	static final String TOPUP = "topup";
	static final String PAY = "pay";

	static List<String> commandLst = new ArrayList(Arrays.asList(LOGIN, TOPUP, PAY));
 

	/**
	 * @param inputVal
	 * @return
	 */
	public static String validateInput(String[] inputVal) {
		String command = inputVal[0];
		String result = "";
		if (contains(command)) {
			switch (command) {
			case LOGIN: {
				result = validateLoginInput(inputVal);
				break;
			}
			case TOPUP: {
				result = validateTopupInput(inputVal);
				break;
			}
			case PAY: {
				result = validatePayInput(inputVal);
				break;
			}
			default: {
				result = "";
				break;
			}
			}

		} 
		return "";
	}

	/**
	 * @param com
	 * @return
	 */
	private static boolean contains(String com) {
		if (commandLst.contains(com.toLowerCase())) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 */
	private static void printCommands() {
		commandLst.forEach(val -> System.out.print(val + " "));
	}

	/**
	 * @param args
	 * @return
	 */
	private static String validateLoginInput(String args[]) {

		if (args.length != 2) {
			return "Login command must be login <client>";
		}

		if (!isString(args[1])) {
			return "Login command must be login <client> : Client name must be string";
		}

		return "";

	}

	/**
	 * @param args
	 * @return
	 */
	private static String validateTopupInput(String args[]) {
		if (args.length != 2) {
			return "topup command must be topup <amount>";
		}
		if (!isDigits(args[1])) {
			return "topup command must be login <client> : amount must be number";
		}
		if (Double.parseDouble(args[1]) < 1) {
			return"topup command must be login <client> : amount must be positive Number";
		}
		return "";
	}

	/**
	 * @param args
	 * @return
	 */
	private static String validatePayInput(String args[]) {

		if (args.length != 3) {
			return "pay command must be pay <another_client> <amount>";
		}
		if (!isString(args[1])) {
			return "pay command must be pay <another_client> <amount> : Client name must be string";
		}
		if (!isDigits(args[2])) {
			return "pay command must be pay <another_client> <amount> : Amount must be Number";
		}
		if (Double.parseDouble(args[2]) < 1) {
			return "pay command must be pay <another_client> <amount> : amount must be positive Number";
		}
		return "";

	}

	/**
	 * @param str
	 * @return
	 */
	private static boolean isDigits(String str) {
		if (str == null) {
			return false;
		}
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * @param str
	 * @return
	 */
	private static boolean isString(String str) {
		return ((!str.equals("")) && (str != null) && (str.matches("^[a-zA-Z]*$")));
	}
}
