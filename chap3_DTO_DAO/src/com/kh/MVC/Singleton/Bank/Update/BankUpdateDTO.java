package com.kh.MVC.Singleton.Bank.Update;

import java.util.Date;

public class BankUpdateDTO {
	private int accountId;
	private String accountNumber;
	private String accountName;
	private double balance;
	private String branchName;
	private Date lastTransactionDate;
	
	public BankUpdateDTO() {
		
	}
	public BankUpdateDTO(int accountId, double balance) {
		this.accountId = accountId;
		this.balance = balance;
	
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Date getLastTransactionDate() {
		return lastTransactionDate;
	}
	public void setLastTransactionDate(Date lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}
	
	
	
	
	

}
