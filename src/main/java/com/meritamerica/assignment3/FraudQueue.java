
package com.meritamerica.assignment3;

import java.util.ArrayList;

public class FraudQueue {
	
	private ArrayList<Transaction> transactions =new ArrayList<Transaction>();
	
	public FraudQueue(){
	}
	
	public void addTransaction(Transaction transaction){
		this.transactions.add(transaction);
	}
	
	public ArrayList<Transaction> getTransaction(){
		return transactions;
		
	}

}
