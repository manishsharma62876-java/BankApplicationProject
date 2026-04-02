package com.manish.Service;

import java.util.Optional;

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

}
