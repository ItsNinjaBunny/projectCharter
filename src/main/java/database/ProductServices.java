package database;
import com.opencsv.bean.CsvBindByPosition;

@SuppressWarnings("serial")
public class ProductServices implements java.io.Serializable {
	
    
   
	@CsvBindByPosition(position = 0)
	public int productServiceID;
    @CsvBindByPosition(position = 1)
    public String title;

    @CsvBindByPosition(position = 2)
    public float cost;

    @CsvBindByPosition(position = 3)
    public String category;
    
    @CsvBindByPosition(position = 4)
    public String supplier;

    public ProductServices(){}
    public ProductServices(int productServiceID, String title, float cost, String category, String supplier) {
    	this.productServiceID = productServiceID;
        this.title			  = title;
        this.cost 			  = cost;
        this.category 		  = category;
        this.supplier 		  = supplier;
    }
  
    public int getProductServiceID()
    {
    	return productServiceID;
    }
    public void setProductServiceID(int productServiceID)
    {
    	this.productServiceID = productServiceID;
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
        return 	"Product/Service ID: " + productServiceID +
        		" Title: " + this.title +
                " Cost: " + this.cost +
                " Category: " + this.category +
                " Supplier: " + this.supplier;
    }


}
