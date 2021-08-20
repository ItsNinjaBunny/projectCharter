package csvFiles;
import com.opencsv.bean.CsvBindByPosition;

import Encryption.Decrypt;
import Encryption.Encrypt;

@SuppressWarnings("serial")
public class Employee implements java.io.Serializable {
	
    
   
   
    @CsvBindByPosition(position = 0)
    public int id;

    @CsvBindByPosition(position = 1)
    public String firstName;

    @CsvBindByPosition(position = 2)
    public String lastName;

    @CsvBindByPosition(position = 3)
    public String hireYear;
    
    @CsvBindByPosition(position = 4)
    public String ssn;
    
    @CsvBindByPosition(position = 5)
    public String occupation;
    
    public Employee(){}
    public Employee(int id, String firstName, String lastName , String hireYear, String ssn, String occupation) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setHireYear(hireYear);
        setSSN(ssn);
        setOccupation(occupation);
    }
   // Used for insertEmployee method in GUI.java
    public Employee(String firstName, String lastName , String hireYear, String ssn, String occupation) {
        setFirstName(firstName);
        setLastName(lastName);
        setHireYear(hireYear);
        setSSN(ssn);
        setOccupation(occupation);
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getFirstName(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(firstName);
    	}
    	else
    	{
    		return Decrypt.decryptData(firstName);
    	}
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    
    
    public String getLastName() {
        return lastName;
    }
    // Used for getting variable encoded or decoding a variable
    public String getLastName(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(lastName);
    	}
    	else
    	{
    		return Decrypt.decryptData(lastName);
    	}
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

    
    
    public String getHireYear() {
        return hireYear;
    }
    public String getHireYear(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(hireYear);
    	}
    	else
    	{
    		return Decrypt.decryptData(hireYear);
    	}
    }
    public void setHireYear(String hireYear) {
        this.hireYear = hireYear.toUpperCase();
    }
    
    

    public String getSSN() {
        return ssn;
    }
    public String getSSN(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(ssn);
    	}
    	else
    	{
    		return Decrypt.decryptData(ssn);
    	}
    }
    public void setSSN(String ssn) {
        this.ssn = ssn.toUpperCase();
    }
    
    
    
    public String getOccupation() {
        return occupation;
    }
    public String getOccupation(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(occupation);
    	}
    	else
    	{
    		return Decrypt.decryptData(occupation);
    	}
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation.toUpperCase();
    }

    @Override
    public String toString() {
        return "id: " + this.id +
                "First Name: " + getFirstName(true) +
                "Last Name: " + getLastName(true) +
                "Year Hired: " + getHireYear(true) +
                "SSN: " + getSSN(true) +
                "Occupation: " + getOccupation();
    }


}
