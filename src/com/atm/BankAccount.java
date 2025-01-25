package com.atm;

public class BankAccount {
	private String aadharNumber;
	private String accountHolderName;
	private String pin;
	private double balance;

	public BankAccount(String aadharNumber, String accountHolderName, String pin, double initialBalance) {
		this.aadharNumber = aadharNumber;
		this.accountHolderName = accountHolderName;
		this.pin = pin;
		this.balance = initialBalance;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public String getPin() {
		return pin;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		this.balance += amount;
	}

	public boolean withdraw(double amount) {
		if (amount <= balance) {
			this.balance -= amount;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "BankAccount [aadharNumber=" + aadharNumber + ", accountHolderName=" + accountHolderName + ", pin=" + pin
				+ ", balance=" + balance + "]";
	}
	
	
}
