package database;
import com.opencsv.bean.CsvBindByPosition;

@SuppressWarnings("serial")
public class ProductServices implements java.io.Serializable {
	
    
   
   
    @CsvBindByPosition(position = 0)
    public String title;

    @CsvBindByPosition(position = 1)
    public float cost;

    @CsvBindByPosition(position = 2)
    public String category;
    
    @CsvBindByPosition(position = 3)
    public String supplier;

    public ProductServices(){}
    public ProductServices(String title, float cost, String category, String supplier) {
        this.title=title;
        this.cost = cost;
        this.category = category;
        this.supplier = supplier;
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
        return "Title: " + this.title +
                " Cost: " + this.cost +
                " Category: " + this.category +
                " Supplier: " + this.supplier;
    }


}
