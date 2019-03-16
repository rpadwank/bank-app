package com.capgemini.bankapp.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.bankapp.exception.InsufficientFundException;
import com.capgemini.bankapp.model.SavingBankAccount;

public class SavingBankAccountTest {

	private SavingBankAccount account;
	
	@Before
	public void setUp()
	{
		account = new SavingBankAccount(101, "John Doe", "SAVING", 45000, true);
	}
	
	@Test
	public void testSavingBankAccountObjectIsCreated()
	{
		SavingBankAccount account = new SavingBankAccount();
		assertNotNull(account);
	}
	
	@Test
	public void testSavingBankAccountObjectIsCreatedWithParameterized()
	{
		SavingBankAccount account = new SavingBankAccount(101, "John Doe", "SAVING", 45000, true);
		assertEquals(101, account.getAccount());
		assertEquals("John Doe", account.getAccountHolderName());
		assertEquals("SAVING", account.getAccountType());
		assertEquals(45000, account.getAccountBalance(),0.01);
		assertEquals(true, account.isSalaryAccount());
	}
	
	@Test
	public void testSavingBankAccountWithdrawWithSalaryAccount()
	{
		assertEquals(500, account.withdraw(44500),0.01);
	}
	
	@Test
	public void testSavingBankAccountWithdrawWithSalaryAccountWithInsufficientFund()
	{
		assertEquals(45000, account.withdraw(45001),0.01);
	}
	
	@Test
	public void testSavingBankAccountWithdrawWithNormalAccount()
	{
		SavingBankAccount account = new SavingBankAccount(101, "John Doe", "SAVING", 45000, false);
		assertEquals(1200, account.withdraw(43800),0.01);
	}

	@Test(expected = InsufficientFundException.class)
	public void testSavingBankAccountWithdrawWithNormalAccountWithInsufficientFund()
	{
		SavingBankAccount account = new SavingBankAccount(101, "John Doe", "SAVING", 45000, false);
		assertEquals(45000, account.withdraw(44100),0.01);
	}
	
	@Test
	public void testSavingBankAccountDeposit()
	{
		SavingBankAccount account = new SavingBankAccount(101, "John Doe", "SAVING", 45000, false);
		assertEquals(90000, account.deposit(45000),0.01);
	}

}
