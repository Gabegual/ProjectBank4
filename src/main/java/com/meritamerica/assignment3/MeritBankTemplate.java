package com.meritamerica.assignment3;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.BufferedReader;

class MeritBankTemplate {
	
	private static long nextAccountNumber = 0;
	private static AccountHolderTemplate AccountHoldersArray[] = new AccountHolderTemplate[0];
	private static CDOfferingTemplate CDOfferingsArray[] = new CDOfferingTemplate[0];

	public static void addAccountHolder(AccountHolderTemplate accountHolder) {
		AccountHolderTemplate[] newAccountHolderArray = new AccountHolderTemplate[AccountHoldersArray.length+1];
			for (int i = 0; i < AccountHoldersArray.length; i++) {
				newAccountHolderArray[i] = AccountHoldersArray[i];
			}
		AccountHoldersArray = newAccountHolderArray;
		AccountHoldersArray[AccountHoldersArray.length-1] = accountHolder;
	}
	
	public static AccountHolderTemplate[] getAccountHolders() {
		return AccountHoldersArray;
	}

	public static CDOfferingTemplate[] getCDOfferings() {
		return CDOfferingsArray;
	}
	
	public static CDOfferingTemplate getBestCDOffering(double depositAmount) {
		double best = 0.0; 
		CDOfferingTemplate bestOffering = null;
		if(CDOfferingsArray == null) {
			return null;
		}
			for(CDOfferingTemplate offering :  CDOfferingsArray) {
				if(futureValue(depositAmount, offering.getInterestRate(), offering.getTerm()) > best) {
					bestOffering = offering;
					best = futureValue(depositAmount, offering.getInterestRate(), offering.getTerm());
				}
			}
		return bestOffering;
	}

	public static CDOfferingTemplate getSecondBestCDOffering(double depositAmount) {
		if(CDOfferingsArray == null) {
			return null;
		}
			CDOfferingTemplate bestOffering = null;
			double best = 0.0; 
			CDOfferingTemplate secondBestOffering = null;
		
				for(CDOfferingTemplate offering :  CDOfferingsArray) {
					if(futureValue(depositAmount, offering.getInterestRate(), offering.getTerm()) > best) {
						secondBestOffering = bestOffering;
						bestOffering = offering;
						best = futureValue(depositAmount, offering.getInterestRate(), offering.getTerm());
					}
				}
		return secondBestOffering;
	}

	public static void clearCDOfferings() {
		CDOfferingsArray = null;
	}
	
	public static void setCDOfferings(CDOfferingTemplate[] offerings) {
		CDOfferingsArray = offerings;
	}

	public static long getNextAccountNumber() {
		return nextAccountNumber;
	}

	public static double totalBalances() {
		double total = 0.0;
			for(AccountHolderTemplate accounts : AccountHoldersArray) {
				total += accounts.getCombinedBalance();
			}
		System.out.println("Total aggregate account balance: $" + total);
		return total;
		
	}

	public static double futureValue(double presentValue, double interestRate, int term) {
		double fValue = presentValue*Math.pow(1 + interestRate,  term);
		return fValue;
	}
	
	static boolean readFromFile( String fileName) {
		CDOfferingTemplate offering[] = new CDOfferingTemplate[0];
		try {
			FileReader reader = new FileReader (fileName);
			BufferedReader bufferedReader = new BufferedReader(reader);
			Long nextAccountNumber = Long.valueOf(bufferedReader.readLine());
			int holdOfferNum = Integer.valueOf(bufferedReader.readLine());
				for(int i = 0; i < holdOfferNum; i++) {
					offering = Arrays.copyOf(offering, offering.length + 1);
					offering [offering.length-1] = CDOfferingTemplate.readFromString(bufferedReader.readLine());
				}
			int numOfAcctHld = Integer.valueOf(bufferedReader.readLine());
			AccountHolderTemplate [] newAccountHolders = new AccountHolderTemplate[numOfAcctHld];
			for(int i = 0; i<numOfAcctHld; i++) {				
				AccountHolderTemplate acctH = AccountHolderTemplate.readFromString(bufferedReader.readLine());
				int numOfChecking = Integer.valueOf(bufferedReader.readLine());
					for(int j = 0; j<numOfChecking; j++) {
						acctH.addCheckingAccount(CheckingAccountTemplate.readFromString(bufferedReader.readLine()));				
					}
				int numOfSavings = Integer.valueOf(bufferedReader.readLine());
					for(int k = 0; k<numOfSavings; k++) {
						acctH.addSavingsAccount(SavingsAccount.readFromString(bufferedReader.readLine()));				
					}
				int numOfCD = Integer.valueOf(bufferedReader.readLine());
					for(int m = 0; m<numOfCD; m++) {
						acctH.addCDAccount(CDAccountTemplate.readFromString(bufferedReader.readLine()));				
					}
				newAccountHolders[i] = acctH;	
			}
			setNextAccountNumber(nextAccountNumber);
			CDOfferingsArray = offering;
			AccountHoldersArray = newAccountHolders;
			reader.close();
			return true;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	static boolean writeToFile( String fileName)  {
		try {
		FileWriter writer = new FileWriter(fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		bufferedWriter.write(String.valueOf(nextAccountNumber));
		bufferedWriter.newLine();
		bufferedWriter.write(String.valueOf(CDOfferingsArray.length));
		bufferedWriter.newLine();
			for(int i = 0; i<CDOfferingsArray.length;i++) {
				bufferedWriter.write(CDOfferingsArray[i].writeToString());
				bufferedWriter.newLine();
			}
			
		bufferedWriter.write(String.valueOf(AccountHoldersArray.length));
		bufferedWriter.newLine();
			for(int j = 0; j <AccountHoldersArray.length;j++) {
				bufferedWriter.write(AccountHoldersArray[j].writeToString());
				bufferedWriter.newLine();
				bufferedWriter.write(String.valueOf(AccountHoldersArray[j].getCheckingAccounts().length));
				bufferedWriter.newLine();
					for(int k = 0; k < AccountHoldersArray[j].getCheckingAccounts().length;k++) {
							bufferedWriter.write(AccountHoldersArray[j].getCheckingAccounts()[k].writeToString());
							bufferedWriter.newLine();
					}
				bufferedWriter.write(String.valueOf(AccountHoldersArray[j].getSavingsAccounts().length));
				bufferedWriter.newLine();
					for(int m = 0; m < AccountHoldersArray[j].getSavingsAccounts().length;m++) {
							bufferedWriter.write(AccountHoldersArray[j].getSavingsAccounts()[m].writeToString());
							bufferedWriter.newLine();
					}
				bufferedWriter.write(String.valueOf(AccountHoldersArray[j].getCDAccounts().length));
				bufferedWriter.newLine();
					for(int n = 0; n < AccountHoldersArray[j].getCDAccounts().length;n++) {
							bufferedWriter.write(AccountHoldersArray[j].getCDAccounts()[n].writeToString());
							bufferedWriter.newLine();
					}			
			}
			writer.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	
	}
	
	static AccountHolderTemplate[] sortAccountHolders() {
		Arrays.sort(AccountHoldersArray, Collections.reverseOrder());
			for(AccountHolderTemplate a : AccountHoldersArray) {
				System.out.println(a);
			}
		return AccountHoldersArray;
	}
	
	static void setNextAccountNumber( long accountNumber) {
		nextAccountNumber = accountNumber;
		
	}
}