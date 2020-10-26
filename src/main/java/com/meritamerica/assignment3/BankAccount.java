package com.meritamerica.assignment3;

import java.awt.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class BankAccount {
	public double balance = 0;
	public double interestRate = 0;
	public long accountNumber = 0;
	public Date accountOpenedOn = null;

	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	public void addTransaction1(Transaction transaction) {
		this.transactions.add(transaction);
	}

	public ArrayList<Transaction> getTransaction() {
		return transactions;

	}

	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = new Date();
		this.accountNumber = MeritBank.getNextAccountNumber();
	}

	BankAccount(double balance, double interestRate, Date accountOpenedOn) {
		this(balance, interestRate);
		this.accountOpenedOn = accountOpenedOn;
	}

	public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		this(balance, interestRate, accountOpenedOn);
		this.accountNumber = accountNumber;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public Date getOpenedOn() {
		return accountOpenedOn;
	}

	public boolean withdraw(double amount) {
		if (balance <= amount) {
			System.out.println("Sorry you do  not have that much in your account you have $" + balance);
			return false;
		} else {
			balance += amount;
			System.out.println("Your new balance is $" + balance);
			return true;
		}
	}

	public boolean deposit(double amount) {
		if (0 < amount) {
			System.out.println("Deposit bank: " + amount);
			this.balance = this.balance + amount;
			return true;
		} else
			System.out.println(" more than 250000");
		return false;
	}

	public double futureValue(int years) {
		double value = 0.00;
		double powered = Math.pow((1 + interestRate), years);
		value = balance * powered;
		return value;
	}

	public String writeToString() {
		StringBuilder accountData = new StringBuilder();
		accountData.append(accountNumber).append(",");
		accountData.append(accountOpenedOn).append(",");
		accountData.append(balance).append(",");
		accountData.append(interestRate);
		return accountData.toString();
	}

	/*
	 * public static BankAccount readFromString(String accountData)throws
	 * ParseException, NumberFormatException { try { String [] holding =
	 * accountData.split(","); SimpleDateFormat date = new
	 * SimpleDateFormat("dd/MM/yyyy"); Long accountNumber =
	 * Long.parseLong(holding[0]); double balance = Double.parseDouble(holding[1]);
	 * double interestRate = Double.parseDouble(holding[2]); Date accountOpenedOn =
	 * date.parse(holding[3]); return new BankAccount(accountNumber, balance,
	 * interestRate, accountOpenedOn);
	 * 
	 * } catch(ParseException e) { e.printStackTrace(); return null; }
	 * catch(NumberFormatException e) { e.printStackTrace(); return null; }
	 * 
	 * }
	 */
	public boolean addTransaction(Transaction transaction) {
		return transactions.add(transaction);
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

}
