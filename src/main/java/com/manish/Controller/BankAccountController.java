package com.manish.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manish.Entity.BankAccount;
import com.manish.Service.BankAccountService;

@RestController
@RequestMapping("/Account")
public class BankAccountController {

	@Autowired
	private BankAccountService service;

	@PostMapping("/addAccount")
	public BankAccount createAccount(@RequestBody BankAccount account) {
		return service.createBankAccount(account);

	}
}
