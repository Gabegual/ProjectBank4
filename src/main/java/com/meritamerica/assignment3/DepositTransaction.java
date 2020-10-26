package com.meritamerica.assignment3;

public class DepositTransaction extends Transaction {
	
	private BankAccount targetAccount;
		private double amount;
	
	DepositTransaction (BankAccount targetAccount, double amount){
		
		
		super (targetAccount, amount);
		
	}

}
