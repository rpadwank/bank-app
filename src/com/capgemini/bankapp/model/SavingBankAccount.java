package com.capgemini.bankapp.model;

import com.capgemini.bankapp.exception.InsufficientFundException;

public class SavingBankAccount extends BankAccount {

	private boolean salaryAccount;
	public static final double MINIMUM_BALANCE=1000;
	public SavingBankAccount() {
	}

	public SavingBankAccount(long accountId, String accountHolderName, String accountType, double accountBalance, boolean salaryAccount) {
		super(accountId, accountHolderName, accountType, accountBalance);
		this.salaryAccount = salaryAccount;
	}

	public boolean isSalaryAccount() {
		return salaryAccount;
	}

	public void setSalaryAccount(boolean salaryAccount) {
		this.salaryAccount = salaryAccount;
	}
	
	@Override
	public double withdraw(double amount) 
	{
		if(salaryAccount) 
			return super.withdraw(amount);
		else 
		{
			if(getAccountBalance() - amount >= MINIMUM_BALANCE)
				setAccountBalance(getAccountBalance() - amount); 
			else
				throw new InsufficientFundException("you don't have sufficient fund.");
			return getAccountBalance();
		}
	}

}
