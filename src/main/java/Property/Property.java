package Property;

import com.opencsv.bean.CsvBindByPosition;

@SuppressWarnings("serial")
public class Property implements java.io.Serializable {
	
    
   
   
    @CsvBindByPosition(position = 0)
    public String title;

    @CsvBindByPosition(position = 1)
    public int cost;

    @CsvBindByPosition(position = 2)
    public String location;

    public Property(){}
    public Property(String title, int cost, String location) {
        this.title=title;
        this.cost = cost;
        this.location = location;
    }
  
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
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
        return "Title: " + this.title +
                "Cost: " + this.cost +
                "Location: " + this.location;
    }


}
