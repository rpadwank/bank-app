package com.capgemini.bankapp.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.bankapp.model.CurrentBankAccount;

class CurrentBankAccountTest {

	//private CurrentBankAccount account;
	
	/*@BeforeEach
	public void setUp()
	{
		account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
	}*/
	
	@Test
	public void testCurrentBankAccountObjectIsCreated()
	{
		CurrentBankAccount account = new CurrentBankAccount();
		assertNotNull(account);
	}
	
	@Test
	public void testSavingBankAccountObjectIsCreatedWithParameterized()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(101, account.getAccount());
		assertEquals("John Doe", account.getAccountHolderName());
		assertEquals("SAVING", account.getAccountType());
		assertEquals(45000, account.getAccountBalance());
		assertEquals(10000, account.getDebitLimit());
		assertEquals(0, account.getBorrowedAmount());
		
	}
	
	@Test
	public void testCurrentBankAccountWithdrawWithSufficientFund()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(500, account.withdraw(44500));
	}
	
	@Test
	public void testCurrentBankAccountWithdrawWithFundWithinDebitLimit()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(0, account.withdraw(51000));
		assertEquals(6000, account.getBorrowedAmount(), 0.01);
	}
	
	@Test
	public void testCurrentBankAccountWithdrawWithFundWithDebitLimitExceeded()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(45000, account.withdraw(56000));
		assertEquals(0, account.getBorrowedAmount(), 0.01);
	}
	
	@Test
	public void testCurrentBankAccountDepositWithNoBorrowedAmount()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(65000, account.deposit(20000));
	}
	
	@Test
	public void testCurrentBankAccountDepositWithAmountGreaterThanBorrowedAmount()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(0, account.withdraw(47000));
		assertEquals(2000, account.getBorrowedAmount());
		assertEquals(8000, account.deposit(10000));
	}
	
	@Test
	public void testCurrentBankAccountDepositWithAmountLessThanBorrowedAmount()
	{
		CurrentBankAccount account = new CurrentBankAccount(101, "John Doe", "SAVING", 45000, 10000);
		assertEquals(0, account.withdraw(47000));
		assertEquals(2000, account.getBorrowedAmount());
		assertEquals(0, account.deposit(1000));
		assertEquals(1000, account.getBorrowedAmount());
	}
}
