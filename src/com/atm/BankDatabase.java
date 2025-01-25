package com.atm;

import java.util.ArrayList;
import java.util.List;

public class BankDatabase {
	private List<BankAccount> accounts;

	public BankDatabase() {
		accounts = new ArrayList<>();
		accounts.add(new BankAccount("1234-5678-9101", "John Doe", "1234", 5000.0));
		accounts.add(new BankAccount("1111-2222-3333", "Alice Smith", "5678", 3000.0));
		accounts.add(new BankAccount("8888-8888-8888", "Bhagyashri Usare", "1234", 40000.0));
	}

	public BankAccount getAccountByAadhar(String aadharNumber) {
		for (BankAccount account : accounts) {
			if (account.getAadharNumber().equals(aadharNumber)) {
				return account;
			}
		}
		return null;
	}

	public boolean transferMoney(BankAccount sender, String recipientAadhar, double amount) {
		BankAccount recipient = getAccountByAadhar(recipientAadhar);

		// Check if recipient exists and sender has enough balance
		if (recipient != null && sender.getBalance() >= amount && amount > 0) {
			sender.withdraw(amount);
			recipient.deposit(amount);
			return true;
		}
		return false;
	}
}
