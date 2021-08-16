package database;


import com.opencsv.bean.CsvBindByPosition;

import Encryption.Decrypt;
import Encryption.Encrypt;

@SuppressWarnings("serial")
public class FinancialHoldings implements java.io.Serializable {
	
    
  
   
    @CsvBindByPosition(position = 0)
    public int accountID;
    
    @CsvBindByPosition(position = 1)
    public String accountName;

    @CsvBindByPosition(position = 2)
    public String balance;
    
    @CsvBindByPosition(position = 3)
    public String bankingInstitution;
    
    @CsvBindByPosition(position = 4)
    public String accountNumber;
    

    public FinancialHoldings(){}
    public FinancialHoldings(int accountID, String accountName, String balance, String bankingInstitution, String accountNumber) {
        setId(accountID);
        setAccountName(accountName);
        setBalance(balance);
        setBankingInstitution(bankingInstitution);
        setAccountNumber(accountNumber);
    }
    public FinancialHoldings(String accountName, String balance, String bankingInstitution, String accountNumber) {
        setAccountName(accountName);
        setBalance(balance);
        setBankingInstitution(bankingInstitution);
        setAccountNumber(accountNumber);
    }
    public FinancialHoldings(String accountName, String balance, String bankingInstitution) {
        setAccountName(accountName);
        setBalance(balance);
        setBankingInstitution(bankingInstitution);
    }
  
    public int getId() {
        return accountID;
    }

    public void setId(int accountID) {
        this.accountID = accountID;
    }
    
    
    public String getAccountName() {
        return accountName;
    }
    public String getAccountName(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encrpytData(accountName);
    	}
    	else
    	{
    		return Decrypt.decryptData(accountName);
    	}
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName.toUpperCase();
    } 
    
    
    
    public String getBalance() {
        return balance;
    }
    public String getBalance(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encrpytData(balance);
    	}
    	else
    	{
    		return Decrypt.decryptData(balance);
    	}
    }
    public void setBalance(String balance ) {
        this.balance = balance.toUpperCase();
    } 
    
    
    
    public String getBankingInstitution() {
        return bankingInstitution;
    }
    public String getBankingInstitution(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encrpytData(bankingInstitution);
    	}
    	else
    	{
    		return Decrypt.decryptData(bankingInstitution);
    	}
    }
    public void setBankingInstitution(String bankingInstitution) {
        this.bankingInstitution = bankingInstitution.toUpperCase();
    }
   

    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountNumber(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encrpytData(accountNumber);
    	}
    	else
    	{
    		return Decrypt.decryptData(accountNumber);
    	}
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber.toUpperCase();
    }
    
    

    @Override
    public String toString() {
        return "AccountID: " + this.accountID +
                " AccountName: " + this.accountName +
                " Balance: " + this.balance +
                " BankingInstituion: " + this.bankingInstitution;
    }


}
