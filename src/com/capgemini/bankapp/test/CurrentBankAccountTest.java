package com.capgemini.bankapp.test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import com.capgemini.bankapp.exception.DebitLimitExceededException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.model.CurrentBankAccount;

public class CurrentBankAccountTest {

	private BankAccount account;
	
	@Before
	public void setUp()
	{
		account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
	}
	
	@Test
	public void testCurrentBankAccountObjectIsCreated()
	{
		BankAccount account = new CurrentBankAccount();
		assertNotNull(account);
	}
	
	@Test
	public void testSavingBankAccountObjectIsCreatedWithParameterized()
	{
		assertEquals(101, account.getAccount());
		assertEquals("John Doe", account.getAccountHolderName());
		assertEquals("SAVING", account.getAccountType());
		assertEquals(45000, account.getAccountBalance(),0.01);
		assertEquals(10000, ((CurrentBankAccount) account).getDebitLimit(),0.01);
		assertEquals(0, ((CurrentBankAccount) account).getBorrowedAmount(),0.01);
		
	}
	
	@Test
	public void testCurrentBankAccountWithdrawWithSufficientFund()
	{
		assertEquals(500, account.withdraw(44500),0.01);
	}
	
	@Test
	public void testCurrentBankAccountWithdrawWithFundWithinDebitLimit()
	{
		assertEquals(0, account.withdraw(51000),0.01);
		assertEquals(6000, ((CurrentBankAccount) account).getBorrowedAmount(), 0.01);
	}
	
	@Test(expected = DebitLimitExceededException.class)
	public void testCurrentBankAccountWithdrawWithFundWithDebitLimitExceeded()
	{
		assertEquals(45000, account.withdraw(56000),0.01);
		assertEquals(0, ((CurrentBankAccount) account).getBorrowedAmount(), 0.01);
	}
	
	@Test
	public void testCurrentBankAccountDepositWithNoBorrowedAmount()
	{
		assertEquals(65000, account.deposit(20000),0.01);
	}
	
	@Test
	public void testCurrentBankAccountDepositWithAmountGreaterThanBorrowedAmount()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(0, account.withdraw(47000),0.01);
		assertEquals(2000, account.getBorrowedAmount(),0.01);
		assertEquals(8000, account.deposit(10000),0.01);
	}
	
	@Test
	public void testCurrentBankAccountDepositWithAmountLessThanBorrowedAmount()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(0, account.withdraw(47000),0.01);
		assertEquals(2000, account.getBorrowedAmount(),0.01);
		assertEquals(0, account.deposit(1000),0.01);
		assertEquals(1000, account.getBorrowedAmount(),0.01);
	}
}
