package com.capgemini.bankapp.model;

import com.capgemini.bankapp.exception.DebitLimitExceededException;

public class CurrentBankAccount extends BankAccount{
	private double debitLimit;
	private double borrowedAmount;
	
	
	public CurrentBankAccount() {

	}


	public CurrentBankAccount(long accountId, String accountHolderName, String accountType, double accountBalance, double debitLimit) {
		super(accountId, accountHolderName, accountType, accountBalance);
		this.debitLimit = debitLimit;
	}


	public double getDebitLimit() {
		return debitLimit;
	}


	public void setDebitLimit(double debitLimit) {
		this.debitLimit = debitLimit;
	}


	public double getBorrowedAmount() {
		return borrowedAmount;
	}


	public void setBorrowedAmount(double borrowedLimit) {
		this.borrowedAmount = borrowedLimit;
	}
	
	@Override
	public double withdraw(double amount) {
		double temp = getAccountBalance() - amount;
		if(temp >= 0)
			setAccountBalance(temp);
		else if(Math.abs(temp) <= (debitLimit - borrowedAmount)) {
			setAccountBalance(0);
			borrowedAmount = borrowedAmount + Math.abs(temp);
		}
		else
			throw new DebitLimitExceededException("Debit Limit exceeded.");
		
		return getAccountBalance();
	}
	
	@Override
	public double deposit(double amount) {
		if(borrowedAmount == 0)
			return super.deposit(amount);
		else if(amount >= borrowedAmount) {
			setAccountBalance(amount - borrowedAmount);
			borrowedAmount = 0;			
		}
		else {
			borrowedAmount = borrowedAmount - amount;
		}
		return getAccountBalance();
	}
}
