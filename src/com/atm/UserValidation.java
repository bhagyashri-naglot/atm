package com.atm;

import java.util.regex.Pattern;

public class UserValidation {

	public boolean isValidAadhar(String aadharNumber) {
		// Aadhar number should be 12 digits long
		String regex = "^[0-9]{4}-[0-9]{4}-[0-9]{4}$";
		return Pattern.matches(regex, aadharNumber);
	}

	public boolean isRegisteredUser(String aadharNumber) {
		BankDatabase bankDatabase = new BankDatabase();
		BankAccount bankAccount = bankDatabase.getAccountByAadhar(aadharNumber);
		if (bankAccount == null) {
			return false;
		}
		return true;
	}
}
