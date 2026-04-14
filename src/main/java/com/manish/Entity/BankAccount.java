package com.manish.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "bank_Account")
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account_number", unique = true, nullable = false)
	private String accountNumber;

	@Column(name = "account_holder_name", nullable = false)
	private String accountHolderName;

	@Column(name = "email")
	private String email;

	@Column(name = "balance", nullable = false)
	private double balance;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "created_at", updatable = false)
	private LocalDate createdAt;

	@Column(name = "update_at")
	private LocalDate updateAt;

	
	@PrePersist
	private void onCreate() {
		this.createdAt = LocalDate.now();
	}

	@PreUpdate
	public void onUpdate() {
		this.updateAt = LocalDate.now();
	}
}
