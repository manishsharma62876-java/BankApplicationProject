package com.manish.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manish.BankRepository.BankAccountRepository;
import com.manish.Entity.BankAccount;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository repository;

	@Override
	public BankAccount createBankAccount(BankAccount account) {
		return repository.save(account);
	}

}
