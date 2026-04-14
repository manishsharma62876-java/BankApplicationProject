package com.manish.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manish.BankRepository.BankAccountRepository;
import com.manish.Entity.BankAccount;
import com.manish.Exception.InsufficientBalanceException;
import com.manish.Exception.ResourceNotFoundException;

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

		existing.setAccountHolderName(updateAccount.getAccountHolderName());
		existing.setEmail(updateAccount.getEmail());
		existing.setBalance(updateAccount.getBalance());
		existing.setAccountType(updateAccount.getAccountType());
		existing.setAccountNumber(updateAccount.getAccountNumber());

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
	public BankAccount transfermoney(String fromAccountNumber, String toAccountNumber, double Amount) {
		BankAccount sender = repository.findByAccountNumber(toAccountNumber)
				.orElseThrow(() -> new RuntimeException("sender not found"));

		BankAccount receiver = repository.findByAccountNumber(toAccountNumber)
				.orElseThrow(() -> new RuntimeException("Receiver not found..."));

		if (sender.getBalance() < Amount) {
			throw new RuntimeException("Insufficient balance");
		}

		if (fromAccountNumber.equals(toAccountNumber)) {
			throw new RuntimeException("cann't transfer to same account");
		}

		sender.setBalance(sender.getBalance() - Amount);
		receiver.setBalance(receiver.getBalance() + Amount);

		// repository.save(sender);
		// repository.save(receiver);

		repository.saveAndFlush(sender);
		repository.saveAndFlush(receiver);

		return sender;

	}

	// check balance

}
