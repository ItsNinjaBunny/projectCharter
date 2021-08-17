package database;
import com.opencsv.bean.CsvBindByPosition;

import Encryption.Decrypt;
import Encryption.Encrypt;

@SuppressWarnings("serial")
public class ProductServices implements java.io.Serializable {
	
    
   
	@CsvBindByPosition(position = 0)
	public int productServiceID;
    @CsvBindByPosition(position = 1)
    public String title;

    @CsvBindByPosition(position = 2)
    public String cost;

    @CsvBindByPosition(position = 3)
    public String category;
    
    @CsvBindByPosition(position = 4)
    public String supplier;

    public ProductServices(){}
    public ProductServices(int productServiceID, String title, String cost, String category, String supplier) {
    	setId(productServiceID);
    	setTitle(title);
    	setCost(cost);
    	setCategory(category);
    	setSupplier(supplier);
    
    }
    public ProductServices(String title, String cost, String category, String supplier) {
    
    	setTitle(title);
    	setCost(cost);
    	setCategory(category);
    	setSupplier(supplier);
    
    }
    public ProductServices(String title, String cost, String category) {
        
    	setTitle(title);
    	setCost(cost);
    	setCategory(category);
   
    
    }
    public int getId()
    {
    	return productServiceID;
    }
    public void setId(int productServiceID)
    {
    	this.productServiceID = productServiceID;
    }
    
    
    public String getTitle() {
        return title;
    }
    public String getTitle(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(title);
    	}
    	else
    	{
    		return Decrypt.decryptData(title);
    	}
    }
    public void setTitle(String title) {
        this.title = title.toUpperCase();
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
    
    
    

    public String getCategory() {
        return category;
    }
    public String getCategory(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(category);
    	}
    	else
    	{
    		return Decrypt.decryptData(category);
    	}
    }
    public void setCategory(String category) {
        this.category = category.toUpperCase();
    }
    
    
    public String getSupplier() {
        return supplier;
    }
    public String getSupplier(boolean encode) {
    	if(encode==true)
    	{
    		return Encrypt.encryptData(supplier);
    	}
    	else
    	{
    		return Decrypt.decryptData(supplier);
    	}
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier.toUpperCase();
    }


    

    @Override
    public String toString() {
        return 	"Id: " + productServiceID +
        		" Name: " + this.title +
                " Cost: " + this.cost +
                " Category: " + this.category +
                " Supplier: " + this.supplier;
    }


}
