package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public abstract class Transaction {
	BankAccount sourceAccount;
	BankAccount targetAccount;
	double amount;
	Date transactionDate;
	String rejectReason;

	public Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount, Date transactionDate,
			String rejectReason) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.rejectReason = rejectReason;

	}

	public Transaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
	}

	public Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
	}

	public BankAccount getSourceAccount() {
		return this.sourceAccount;

	}

	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public BankAccount getTargetAccount() {
		return this.targetAccount;
	}

	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date date) {
		this.transactionDate = date;
	}

	public String writeToString() {
		return "";
	}

	public Transaction readFromString(String transactionDataString) {

		System.out.println(transactionDataString);
		boolean depositOrWithdraw = true;
		int initialDepositOrWithdraw = 0;
		double amount = 0.0;
		Date transactionDate = new Date();
		String rejectReason = "";

		try {
			StringTokenizer st1 = new StringTokenizer(transactionDataString, ",");
			int index = 0;
			while (st1.hasMoreTokens()) {
				switch (index) {
				case 0:
					initialDepositOrWithdraw = Integer.parseInt(st1.nextToken());
					if (initialDepositOrWithdraw == -1) {
						if (amount > 0) {

							depositOrWithdraw = true;
						} else {

							depositOrWithdraw = false;
						}
					}
					break;
				case 1:
					if (depositOrWithdraw) {
						this.sourceAccount;

					}

					break;
				case 2:
					amount = Double.parseDouble(st1.nextToken());
					break;
				case 3:
					transactionDate = new SimpleDateFormat("MM/dd/yyyy").parse(st1.nextToken());
					break;
				default:
					break;
				}
				index++;
			}

			sourceAccount = new addTransaction(sourceAccount, targetAccount, amount, transactionDate, rejectReason);

		} catch (ParseException pex) {
			pex.printStackTrace();
			System.err.println("ParseException: " + pex.getMessage());
		} catch (NumberFormatException nex) {
			nex.printStackTrace();
			System.err.println("NumberFormatExcepton is Handled!, " + nex.getMessage());
		}
		return sourceAccount;
	}

	public boolean isProcessedByFraudTeam() {
		return true;
	}

	public void setProcessedByFraudTeam(boolean isProcessed) {

	}

	public String getRejectionReason() {

		return this.rejectReason = rejectReason;
	}

	public void setRejectionReason(String reason) {
		this.rejectReason = rejectReason;

	}

	public void getTransaction() {

	}

	public void setTransaction() {

	}

}
