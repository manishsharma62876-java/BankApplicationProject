package com.manish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manish.Entity.BankAccount;
import com.manish.Service.BankAccountService;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

	@Autowired
	private BankAccountService service;

	@PostMapping("/addAccount")
	public BankAccount createAccount(@RequestBody BankAccount account) {
		return service.createBankAccount(account);

	}

	@GetMapping("/{id}")
	public BankAccount getAccountByID(@PathVariable Long id) {
		return service.getAccountById(id);
	}

	@PutMapping("/{id}")
	public BankAccount updateAccount(@PathVariable Long id, @RequestBody BankAccount account) {
		return service.updateAccount(id, account);
	}

	@DeleteMapping("/{id}")
	public String deleteAccount(@PathVariable Long id) {
		service.deleteAccount(id);
		return "Account deleted successfully with id::" + id;
	}

	@PostMapping("/{id}/deposit")
	public BankAccount deposit(@PathVariable Long id, @RequestParam double amount) {
		return service.deposit(id, amount);
	}

	@PostMapping("/{id}/withdraw")
	public BankAccount withdraw(@PathVariable Long id, @RequestParam double amount) {
		return service.withdraw(id, amount);
	}	
	
	@PostMapping("/transfer")
	public String transferMoney(@RequestParam String fromAccount,
			@RequestParam String toAccount
			,@RequestParam double amount) {
		
	     service.transfermoney(fromAccount, toAccount, amount);
	     return "Transfer money successfull";
	}
	}


