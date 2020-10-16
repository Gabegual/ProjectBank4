package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccountTemplate extends BankAccountTemplate {
	CDOfferingTemplate offering;
	Date date;
	private double balance;
	private long accountNumber;
	private int term=0;
		
	public CDAccountTemplate(CDOfferingTemplate offering, double openBalance) {
		super(openBalance,offering.getInterestRate());
		this.offering = offering;
		this.term = offering.getTerm();
	}
	
	public CDAccountTemplate(long accountNumber, double balance, double interestRate, Date accountOpenedOn, int term) {
		super(accountNumber, balance,interestRate,accountOpenedOn);
		this.term = term;
	}
	
	public int getTerm() {
		return this.term;
	}
	
	public Date getStartDate(){
		return date;
	}
	
	public double futureValue() {
		return super.futureValue(term);
	}
	
	@Override
	public boolean withdraw(double amount) {
        return false;
    }
    
    public boolean deposit(double amount) {
    	return false;
    }
    
    public static CDAccountTemplate readFromString(String accountData)throws ParseException, NumberFormatException {
    	String [] holding = accountData.split(",");
    	SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    	long accountNumber = Long.parseLong(holding[0]);
    	double balance = Double.parseDouble(holding[1]);
    	double interestRate = Double.parseDouble(holding[2]);
    	Date accountOpenedOn = date.parse(holding[3]);
    	int term = Integer.parseInt(holding[4]);
    	CDAccountTemplate newCDAccount = new CDAccountTemplate(accountNumber,balance,interestRate,accountOpenedOn,term);
    	return newCDAccount;
    }
    
    public String writeToString() {
    	StringBuilder override = new StringBuilder();
    	override.append(writeToString()).append(",");
    	override.append(term);
    	return override.toString();
    }


}
