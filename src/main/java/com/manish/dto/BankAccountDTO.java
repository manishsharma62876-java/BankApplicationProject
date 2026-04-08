package com.manish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {
	private Long id;
	private String account_number;
	private String account_holder_name;
	private String email;
	private double balance;
	private String accountType;
}
