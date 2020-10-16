package com.meritamerica.assignment3;

public class AccountHolderTemplate implements Comparable<AccountHolderTemplate>  {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	CheckingAccountTemplate[] checkingArray = new CheckingAccountTemplate[0];
	SavingsAccount[] savingsArray = new SavingsAccount[0];
	CDAccountTemplate[] cdAccountArray = new CDAccountTemplate[0];
	
	public AccountHolderTemplate(String first, String middle, String last, String ssn) {
		this.firstName = first;
		this.middleName = middle;
		this.lastName = last;
		this.ssn = ssn;
	}

	public CheckingAccountTemplate addCheckingAccount(double openBalance) {
		if(getCheckingBalance() + getSavingsBalance() + openBalance >= 250000) {
			System.out.println("Cannot open a new Checking Account because aggregate balance of accounts is to high.");
			return null;
		}
		CheckingAccountTemplate newA = new CheckingAccountTemplate(openBalance, CheckingAccountTemplate.INTEREST_RATE);
		CheckingAccountTemplate[] newCheckingArray = new CheckingAccountTemplate[checkingArray.length+1];
			for (int i = 0; i < newCheckingArray.length - 1; i++) {
				newCheckingArray[i] = checkingArray[i];
			}
		checkingArray = newCheckingArray;
		checkingArray[checkingArray.length-1] = newA;
		return newA;
	}
	
	public boolean addCheckingAccount(CheckingAccountTemplate checkingAccount) {
		try {
		if(checkingAccount.getBalance() + getCheckingBalance() + getSavingsBalance() >= 250000) {
			System.out.println("Cannot open a new Checking Account because aggregate balance of accounts is to high.");
			return false;
		}
		CheckingAccountTemplate[] newCheckingArray = new CheckingAccountTemplate[checkingArray.length+1];
			for (int i = 0; i < newCheckingArray.length-1; i++) {
				newCheckingArray[i] = checkingArray[i];
			}
		checkingArray = newCheckingArray;
		checkingArray[checkingArray.length-1] = checkingAccount;
		return checkingAccount != null;
		}
		catch(NullPointerException e) {
    		e.printStackTrace();
    		return null != null;
    	}
	}	

	public double getCheckingBalance() {
		double total = 0.0;
		int i;
			for(CheckingAccountTemplate balance: checkingArray) {
				total += balance.getBalance();
			}
		return total;
	}

	public SavingsAccount addSavingsAccount(double openBalance) {
		if(getCheckingBalance() + getSavingsBalance() + openBalance >= 250000) {
			System.out.println("Cannot open a new Savings Account because aggregate balance of accounts is to high.");
			return null;
		}	
		SavingsAccount newA = new SavingsAccount(openBalance, SavingsAccount.INTEREST_RATE);
		SavingsAccount[] newSavingsArray = new SavingsAccount[savingsArray.length+1];
		for (int i = 0; i < newSavingsArray.length-1; i++) {
			newSavingsArray[i] = savingsArray[i];
		}
		savingsArray = newSavingsArray;
		savingsArray[savingsArray.length-1] = newA;
		return newA;
	}

	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) {
		if(savingsAccount.getBalance() + getCheckingBalance() + getSavingsBalance() >= 250000) {
			System.out.println("Cannot open a new Savings Account because aggregate balance of accounts is to high.");
			return null;
		}
		SavingsAccount[] newSavingsArray = new SavingsAccount[savingsArray.length+1];
			for (int i = 0; i < newSavingsArray.length-1; i++) {
			       newSavingsArray[i] = savingsArray[i];
			}
		savingsArray = newSavingsArray;
		savingsArray[savingsArray.length-1] = savingsAccount;
		return savingsAccount;
	}

	public SavingsAccount[] getSavingsAccounts() {
		return savingsArray;
	}

	public int getNumberOfSavingsAccounts() {
		return savingsArray.length;
	}

	public double getSavingsBalance() {
		double total = 0.0;
			for(SavingsAccount balance : savingsArray) {
				total += balance.getBalance();
			}
		return total;

	}
	
	public CDAccountTemplate addCDAccount(CDOfferingTemplate offering, double openBalance) {
		CDAccountTemplate newA = new CDAccountTemplate(offering, openBalance);
		CDAccountTemplate[] newCDArray = new CDAccountTemplate[cdAccountArray.length+1];
			for (int i = 0; i < newCDArray.length-1; i++) {
				newCDArray[i] = cdAccountArray[i];
			}
		cdAccountArray = newCDArray;
		cdAccountArray[cdAccountArray.length-1] = newA;
		return newA;
	}
	
	public CDAccountTemplate addCDAccount(CDAccountTemplate cdAccount) {
		CDAccountTemplate[] newCDArray = new CDAccountTemplate[cdAccountArray.length+1];
			for (int i = 0; i < newCDArray.length-1; i++) {
			       newCDArray[i] = cdAccountArray[i];
			}
		cdAccountArray = newCDArray;
		cdAccountArray[cdAccountArray.length-1] = cdAccount;
		return cdAccount;
	}

	public CDAccountTemplate[] getCDAccounts() {
		return cdAccountArray;
	}

	public int getNumberOfCDAccounts() {
		return cdAccountArray.length;
	}

	public double getCDBalance() {
		double total = 0.0;
			for(CDAccountTemplate balance : cdAccountArray) {
				total += balance.getBalance();
			}
		return total;
	}

	public double getCombinedBalance() {
		return getCDBalance() + getSavingsBalance() + getCheckingBalance();
	}
	
	@Override
	public int compareTo(AccountHolderTemplate account) {
		if(this.getCombinedBalance() > account.getCombinedBalance()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first) {
		this.firstName = first;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middle) {
		this.middleName = middle;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String last) {
		this.lastName = last;
	}
	
	public String getSSN() {
		return ssn;
	}
	
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	
	public CheckingAccountTemplate[] getCheckingAccounts() {
		return checkingArray;
	}
	
	public int getNumberOfCheckingAccounts() {
		return checkingArray.length;
	}
	
	public String writeToString() {
    	StringBuilder accountHolderData = new StringBuilder();
    	accountHolderData.append(firstName).append(",");
    	accountHolderData.append(middleName).append(",");
    	accountHolderData.append(lastName).append(",");
    	accountHolderData.append(ssn);
    	return accountHolderData.toString();
    }

	public static AccountHolderTemplate readFromString(String accountHolderData) {
	    String[] holding = accountHolderData.split(",");
	    String firstName = holding[0];
	    String middleName = holding[1];
	    String lastName = holding[2];
	    String ssn = holding[3];	
	    return new AccountHolderTemplate(firstName, middleName, lastName, ssn);
	}
	public String toString() {
		return  "Combined Balance for Account Holder" + this.getCombinedBalance();	
	}
		
}