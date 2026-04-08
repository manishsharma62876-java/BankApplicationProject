package com.manish.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.manish.Entity.BankAccount;

public interface BankAccountService {

	// create account
	BankAccount createBankAccount(BankAccount account);

	// get account by id
	BankAccount getAccountById(Long id);

	// update Account
	BankAccount updateAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount);
	
	//delete Account
	public void  deleteAccount(Long id);
	
	//deposit money
	BankAccount deposit(Long id, double amount);

	//withdraw money
	BankAccount withdraw(Long id,double amount);
	
	//transfer money
	public void transfermoney(String fromAccountNumber , String toAccountNumber , double amount);
	
}
