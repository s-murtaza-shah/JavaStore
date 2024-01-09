/**
 * Generic class to create objects for a store. 
 */ 

public class BaseProduct { 
	
	protected String name; 
	protected double price = 0.0; 
	protected int quantity = 0;
	 
	public BaseProduct() { 
	} // Product() 
		
	public BaseProduct(String name, double price, int quantity) { 
		this.name = name; 
		this.price = price; 
		this.quantity = quantity; 
	} // ProductDone (String,,double,int) 
		
	public String getName() { 
		return name; 
	} 
	
	public void setName(String name) { 
		this.name = name; 
	} 
	
	public double getPrice() { 
		return price; 
	} 
	
	public void setPrice(double price) { 
		this.price = price; 
	} 
	
	public int getQuantity() { 
		return quantity; 
	} 
	
	public void setQuantity(int quantity) { 
		this.quantity = quantity; 
	} 
	
	public String toString() { 
		return "BaseProduct [name=" + name + ", price=" + price + ", quantity=" 
		+ quantity + "]"; 
	}
	
} // end Product class 