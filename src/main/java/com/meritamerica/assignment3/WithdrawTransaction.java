package com.meritamerica.assignment3;

public class WithdrawTransaction extends Transaction {
	
	private BankAccount targetAccount;
		private double amount;

	WithdrawTransaction(BankAccount targetAccount, double amount) {
		
		super(targetAccount, amount);
	}	
	
}
		
