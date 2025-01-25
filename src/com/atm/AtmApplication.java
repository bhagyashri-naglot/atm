package com.atm;

import java.util.Scanner;

public class AtmApplication {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BankDatabase bankDatabase = new BankDatabase();
		UserValidation userValidation = new UserValidation();

		System.out.println("Welcome to the ATM!");
		System.out.print("Please enter your Aadhar Card Number: ");
		String aadharNumber = scanner.nextLine();

		// Aadhar validation
		if (!userValidation.isValidAadhar(aadharNumber)) {
			System.err.println("Invalid Aadhar Number. Access Denied.");
			return;
		}

		// Check if user is registered
		if (!userValidation.isRegisteredUser(aadharNumber)) {
			System.err.println("User is not registered. Please register first.");
			return;
		}

		System.out.println("Aadhar Verified. Please enter your PIN: ");
		String pin = scanner.nextLine();

		// Retrieve user's account
		BankAccount userAccount = bankDatabase.getAccountByAadhar(aadharNumber);
		if (userAccount != null && userAccount.getPin().equals(pin)) {
			System.out.println("PIN Verified. Welcome, " + userAccount.getAccountHolderName());
			int option;
			do {
				System.out.println("\nATM Menu:");
				System.out.println("1. View Balance");
				System.out.println("2. Deposit Money");
				System.out.println("3. Withdraw Money");
				System.out.println("4. Transfer Money");
				System.out.println("5. Exit");
				System.out.print("Please select an option: ");
				option = scanner.nextInt();

				switch (option) {
				case 1:
					System.out.println("Your current balance: ₹" + userAccount.getBalance());
					break;
				case 2:
					System.out.print("Enter amount to deposit: ₹");
					double depositAmount = scanner.nextDouble();
					userAccount.deposit(depositAmount);
					System.out.println("Deposit Successful. New Balance: ₹" + userAccount.getBalance());
					break;
				case 3:
					System.out.print("Enter amount to withdraw: ₹");
					double withdrawAmount = scanner.nextDouble();
					if (userAccount.withdraw(withdrawAmount)) {
						System.out.println("Withdrawal Successful. New Balance: ₹" + userAccount.getBalance());
					} else {
						System.out.println("Insufficient funds or invalid amount.");
					}
					break;
				case 4:
					// Transfer Money Option
					System.out.print("Enter recipient's Aadhar Number: ");
					scanner.nextLine(); // Consume newline character left by nextInt()
					String recipientAadhar = scanner.nextLine();
					System.out.print("Enter amount to transfer: ₹");
					double transferAmount = scanner.nextDouble();

					if (bankDatabase.transferMoney(userAccount, recipientAadhar, transferAmount)) {
						System.out.println("Transfer Successful. New Balance: ₹" + userAccount.getBalance());
					} else {
						System.out.println("Transfer Failed. Please check recipient details or insufficient balance.");
					}
					break;
				case 5:
					System.out.println("Thank you for using the ATM. Goodbye!");
					break;
				default:
					System.out.println("Invalid option. Please try again.");
					break;
				}
			} while (option != 5);
		} else {
			System.out.println("Invalid PIN. Access Denied.");
		}

		scanner.close();
	}
}
