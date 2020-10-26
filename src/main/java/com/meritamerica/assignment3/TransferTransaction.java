package com.meritamerica.assignment3;

public class TransferTransaction extends Transaction{
	
	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount){
		super(targetAccount,sourceAccount, amount);

	}
}