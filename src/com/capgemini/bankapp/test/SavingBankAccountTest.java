package com.capgemini.bankapp.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.bankapp.model.SavingBankAccount;

class SavingBankAccountTest {

	private SavingBankAccount account;
	
	@BeforeEach
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
		assertEquals(45000, account.getAccountBalance());
		assertEquals(true, account.isSalaryAccount());
	}
	
	@Test
	public void testSavingBankAccountWithdrawWithSalaryAccount()
	{
		assertEquals(500, account.withdraw(44500));
	}
	
	@Test
	public void testSavingBankAccountWithdrawWithSalaryAccountWithInsufficientFund()
	{
		assertEquals(45000, account.withdraw(45001));
	}
	
	@Test
	public void testSavingBankAccountWithdrawWithNormalAccount()
	{
		SavingBankAccount account = new SavingBankAccount(101, "John Doe", "SAVING", 45000, false);
		assertEquals(1200, account.withdraw(43800));
	}

	@Test
	public void testSavingBankAccountWithdrawWithNormalAccountWithInsufficientFund()
	{
		SavingBankAccount account = new SavingBankAccount(101, "John Doe", "SAVING", 45000, false);
		assertEquals(45000, account.withdraw(44100));
	}
	
	@Test
	public void testSavingBankAccountDeposit()
	{
		SavingBankAccount account = new SavingBankAccount(101, "John Doe", "SAVING", 45000, false);
		assertEquals(90000, account.deposit(45000));
	}

}
