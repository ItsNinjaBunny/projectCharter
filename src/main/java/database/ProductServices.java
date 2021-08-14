package database;
import com.opencsv.bean.CsvBindByPosition;

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
    	this.productServiceID = productServiceID;
        this.title			  = title;
        this.cost 			  = cost;
        this.category 		  = category;
        this.supplier 		  = supplier;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
