package database;
import com.opencsv.bean.CsvBindByPosition;

@SuppressWarnings("serial")
public class Employee implements java.io.Serializable {
	
    
   
   
    @CsvBindByPosition(position = 0)
    public int id;

    @CsvBindByPosition(position = 1)
    public String firstName;

    @CsvBindByPosition(position = 2)
    public String lastName;

    @CsvBindByPosition(position = 3)
    public int hireYear;
    
    @CsvBindByPosition(position = 4)
    public String ssn;
    
    @CsvBindByPosition(position = 5)
    public String occupation;
    
    public Employee(){}
    public Employee(int id, String firstName, String lastName , int hireYear, String ssn, String occupation) {
        this.id=id;
        this.firstName=firstName;
        this.lastName = lastName;
        this.hireYear = hireYear;
        this.ssn	  = ssn;
        this.occupation = occupation;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }

    public String getSSN() {
        return ssn;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }
    
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "id: " + this.id +
                "First Name: " + this.firstName +
                "Last Name: " + this.lastName +
                "Year Hired: " + this.hireYear +
                "SSN: " + this.ssn +
                "Occupation: " + this.occupation;
    }


}
