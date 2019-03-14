package com.capgemini.bankapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.bankapp.model.BankAccount;

public class BankAccountTest {

	private BankAccount account;
	
	@Before
	public void setUp() {
		account = new BankAccount(101, "John Doe", "SAVING", 35000);
	}
	
	@Test
	public void testBankAccountObjectIsCreated()
	{
		BankAccount account = new BankAccount();
		assertNotNull(account);
	}

	@Test
	public void testBankAccountObjectIsCreatedWithParameterizedConstructor()
	{
		BankAccount account = new BankAccount(101, "John Doe", "SAVING",45000);
		assertNotNull(account);
		assertEquals(101, account.getAccount());
		assertEquals("John Doe", account.getAccountHolderName());
		assertEquals("SAVING", account.getAccountType());
		assertEquals(45000, account.getAccountBalance(), 0.01);
	}
	
	@Test
	public void getBankAccountCurrentBalance() {
		assertEquals(35000, account.getAccountBalance(), 0.01);
	}
	
	@Test
	public void testWithdrawWithSufficientFund()
	{
		account.withdraw(5000);
		assertEquals(30000, account.getAccountBalance(), 0.01);
	}
	
	@Test
	public void testWithdrawWithInsufficientFund()
	{
		account.withdraw(40000);
		assertEquals(35000, account.getAccountBalance(), 0.01);
	}
	
	@Test
	public void testDeposit()
	{
		account.deposit(5000);
		assertEquals(40000, account.getAccountBalance(), 0.01);
	}
}
