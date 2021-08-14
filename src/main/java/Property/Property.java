package Property;

import com.opencsv.bean.CsvBindByPosition;

@SuppressWarnings("serial")
public class Property implements java.io.Serializable {
	
    
   
   
    @CsvBindByPosition(position = 0)
    public int id;

    @CsvBindByPosition(position = 1)
    public String propertyName;

    @CsvBindByPosition(position = 2)
    public String cost;

    @CsvBindByPosition(position = 3)
    public String location;
    public Property(){}
    public Property(int propertyID, String title, String cost, String location) {
    	this.id = propertyID;
        this.propertyName		= title;
        this.cost 		= cost;
        this.location 	= location;
    }
  
    public int getId()
    {
    	return id;
    }
    
    public void setId(int propertyID)
    {
    	this.id = propertyID;
    }
    
    public String getTitle() {
        return propertyName;
    }

    public void setTitle(String title) {
        this.propertyName = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLastName(String location) {
        this.location = location;
    }


    

    @Override
    public String toString() {
        return  " PropertyID: " + this.id +
        		" PropertyName: " + this.propertyName +
                " Cost: " + this.cost +
                " Location: " + this.location;
    }


}
