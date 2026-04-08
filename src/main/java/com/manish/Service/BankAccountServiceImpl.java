package com.manish.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manish.BankRepository.BankAccountRepository;
import com.manish.Entity.BankAccount;
import com.manish.Exception.AccountNotFoundException;
import com.manish.Exception.InsufficientBalanceException;
import com.manish.Exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository repository;

	@Override
	public BankAccount createBankAccount(BankAccount account) {
		return repository.save(account);
	}

	@Override
	public BankAccount getAccountById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with " + id));
	}

	@Override
	public BankAccount updateAccount(Long id, BankAccount updateAccount) {
		BankAccount existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found...!"));

		existing.setAccount_holder_name(updateAccount.getAccount_holder_name());
		existing.setEmail(updateAccount.getEmail());
		existing.setBalance(updateAccount.getBalance());
		existing.setAccountType(updateAccount.getAccountType());
		existing.setAccount_number(updateAccount.getAccount_number());

		return repository.save(existing);
	}

	@Override
	public void deleteAccount(Long id) {
		BankAccount exiting = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found with " + id));
		repository.delete(exiting);
	}

	@Override
	public BankAccount deposit(Long id, double amount) {
		BankAccount account = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found.....!"));
		account.setBalance(account.getBalance() + amount);
		return repository.save(account);
	}

	@Override
	public BankAccount withdraw(Long id, double amount) {

		if (amount <= 0) {
			throw new IllegalArgumentException("Amount must be greater then 0");
		}

		BankAccount account = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with id::" + id));

		if (account.getBalance() < amount) {
			throw new InsufficientBalanceException("Insufficient balance");
		}

		account.setBalance(account.getBalance() - amount);

		return repository.save(account);
	}

	@Override
	@Transactional
	public void transfermoney(String fromAccountNumber, String toAccountNumber, double amount) {

		BankAccount fromAccount = repository.findByAccountNumber(fromAccountNumber)
				.orElseThrow(() -> new AccountNotFoundException("From Account not found"));

		BankAccount toAccount = repository.findByAccountNumber(toAccountNumber)
				.orElseThrow(() -> new AccountNotFoundException("To Account not found"));

		if (amount <= 0) {
			throw new RuntimeException("Amount must be greater than zero");
		}

		if (fromAccount.getBalance() < amount) {
			throw new InsufficientBalanceException("Insufficient balance");
		}

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		repository.save(fromAccount);
		repository.save(toAccount);
	}

	// check balance

}
