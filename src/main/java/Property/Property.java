package Property;

import com.opencsv.bean.CsvBindByPosition;

@SuppressWarnings("serial")
public class Property implements java.io.Serializable {
	
    
   
   
    @CsvBindByPosition(position = 0)
    public int propertyID;

    @CsvBindByPosition(position = 1)
    public String title;

    @CsvBindByPosition(position = 2)
    public float cost;

    @CsvBindByPosition(position = 3)
    public String location;
    public Property(){}
    public Property(int propertyID, String title, float cost, String location) {
    	this.propertyID = propertyID;
        this.title		= title;
        this.cost 		= cost;
        this.location 	= location;
    }
  
    public int getPropertyID()
    {
    	return propertyID;
    }
    
    public void setPropertyID(int propertyID)
    {
    	this.propertyID = propertyID;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
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
        return  "PropertyID: " + this.propertyID +
        		" Title: " + this.title +
                " Cost: " + this.cost +
                " Location: " + this.location;
    }


}
