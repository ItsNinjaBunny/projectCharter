package Property;

import com.opencsv.bean.CsvBindByPosition;

import Encryption.Decrypt;
import Encryption.Encrypt;

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
    	setId(propertyID);
    	setTitle(title);
    	setCost(cost);
    	setLocation(location);
    }
    public Property(String title, String cost, String location) {
 
    	setTitle(title);
    	setCost(cost);
    	setLocation(location);
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
    public String getTitle(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(propertyName);
    	}
    	else
    	{
    		return Decrypt.decryptData(propertyName);
    	}
    }
    public void setTitle(String title) {
        this.propertyName = title.toUpperCase();
    }

    
    
    public String getCost() {
        return cost;
    }
    public String getCost(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(cost);
    	}
    	else
    	{
    		return Decrypt.decryptData(cost);
    	}
    }
    public void setCost(String cost) {
        this.cost = cost.toUpperCase();
    }

    
    
    public String getLocation() {
        return location;
    }
    public String getLocation(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(location);
    	}
    	else
    	{
    		return Decrypt.decryptData(location);
    	}
    }
    public void setLocation(String location)
    {
    	this.location = location.toUpperCase();
    }


    

    @Override
    public String toString() {
        return  " PropertyID: " + this.id +
        		" PropertyName: " + this.propertyName +
                " Cost: " + this.cost +
                " Location: " + this.location;
    }


}
