package database;


import com.opencsv.bean.CsvBindByPosition;

@SuppressWarnings("serial")
public class financialHoldings implements java.io.Serializable {
	
    
   //create AcountNumber value that will be at the end pos 4
   
    @CsvBindByPosition(position = 0)
    public int accountID;
    
    @CsvBindByPosition(position = 1)
    public String accountName;

    @CsvBindByPosition(position = 2)
    public float balance;
    
    @CsvBindByPosition(position = 3)
    public String bankingInstitution;
    
    

    public financialHoldings(){}
    public financialHoldings(int accountID, String accountName, float balance, String bankingInstitution) {
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
    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance ) {
        this.balance = balance;
    } 
    public String getBankingInstitution() {
        return bankingInstitution;
    }

    public void setBankingInstitution(String bankingInstitution) {
        this.bankingInstitution = bankingInstitution;
    }
   
    

    @Override
    public String toString() {
        return "AccountID: " + this.accountID +
                " AccountName: " + this.accountName +
                " Balance: " + this.balance +
                " BankingInstituion: " + this.bankingInstitution;
    }


}
