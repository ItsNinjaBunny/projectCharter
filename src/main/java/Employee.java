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
    public Employee(){}
    public Employee(int id, String firstName, String lastName , int hireYear) {
        this.id=id;
        this.firstName=firstName;
        this.lastName = lastName;
        this.hireYear = hireYear;
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

    

    @Override
    public String toString() {
        return "id: " + this.id +
                "First Name: " + this.firstName +
                "Last Name: " + this.lastName +
                "Year Hired: " + this.hireYear;
    }


}
