package database;


import com.opencsv.bean.CsvBindByPosition;

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
    public int accountNumber;
    

    public FinancialHoldings(){}
    public FinancialHoldings(int accountID, String accountName, String balance, String bankingInstitution) {
        this.accountID  		= accountID;
        this.accountName 		= accountName;
        this.balance 	 		= balance;
        this.bankingInstitution = bankingInstitution;
    }
  
    public int getId() {
        return accountID;
    }

    public void setId(int accountID) {
        this.accountID = accountID ;
    }
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName ;
    } 
    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance ) {
        this.balance = balance;
    } 
    public String getBankingInstitution() {
        return bankingInstitution;
    }

    public void setBankingInstitution(String bankingInstitution) {
        this.bankingInstitution = bankingInstitution;
    }
   

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "AccountID: " + this.accountID +
                " AccountName: " + this.accountName +
                " Balance: " + this.balance +
                " BankingInstituion: " + this.bankingInstitution;
    }


}
