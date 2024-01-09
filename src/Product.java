/**
 * Class specifically for tool products - extends the BaseProduct class
 * 
 * @author Murtaza Shah
 */

public class Product extends BaseProduct{
	
	// Declare fields/variables needed and initialize them to default values
	private String category = "";
	private String brand = "";
	private String color = "";
	private int jitTrigger = 0;
	private String country = "";
	private String description = "";
	private int qtyPurchased = 0;
	
	public Product(String CSVLine) {
		
		String[] data = CSVLine.split("\\|", 15);
		this.name = data[0];
		this.price = Double.parseDouble(data[1]);
		this.quantity = Integer.parseInt(data[2]);
		this.category = data[3];
		this.brand = data[4];
		this.color = data[5];
		this.country = data[6];
		this.jitTrigger = Integer.parseInt(data[7]);		
		this.description = data[8];
	} // Product(String)
	
	public Product(String name, double price, int quantity, String category, String brand, String color, String country, int jitTrigger, String description) {
		
		// Call the appropriate constructor to set these fields from the parent class
		super(name, price, quantity);
		
		this.category = category;
		this.brand = brand;
		this.color = color;
		this.country = country;
		this.jitTrigger = jitTrigger;
		this.description = description;
	} // ProductDone(String, double, int, String, String, String, String, int, String)
	
	public void setQtyPurchased(int qtyPurchased) {
		this.qtyPurchased = qtyPurchased;
	}
	public int getQtyPurchased() {
		return qtyPurchased;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getJitTrigger() {
		return jitTrigger;
	}
	public void setJitTrigger(int jitTrigger) {
		this.jitTrigger = jitTrigger;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Determines whether or not a product should be ordered or not (for the JIT monitor)
	 * 
	 * @param quantity int - the quantity of the product
	 * @param jitTrigger int - the JIT Trigger of the product
	 * @return String - the order status of the product
	 */
	public String determineOrderStatus(int quantity, int jitTrigger) {
		
		// Declare variable needed
		String orderStatus;
		
		// If quantity is less than or equal to the JIT Trigger, set the order status to "ORDER NOW"
		if (quantity <= jitTrigger) {
			orderStatus = "ORDER NOW!";
		}
		// if quantity if less than 2 times the JIT Trigger, it is "ORDER SOON"
		else if (quantity < (2*jitTrigger)) {
			orderStatus = "ORDER SOON!";
		}
		// Otherwise, it is in stock so order is not needed
		else {
			orderStatus = "IN STOCK - ORDER NOT NEEDED!";
		} // end if
		
		// return orderStatus
		return orderStatus;
		
	} // String determineOrderStatus(int, int)
	
	/**
	 * Determines the availability of the product
	 * 
	 * @param quantity int - the quantity of the product
	 * @return String - the availability of the product
	 */
	public String determineAvailability(int quantity) {
		
		// Declare variable needed
		String availability;
		
		// If quantity is greater than 0, it is available
		if (quantity > 0) {
			availability = "AVAILABLE!";
		}
		// Otherwise, it is not available
		else {
			availability = "SOLD OUT!";
		} // end if
		
		// return availability
		return availability;
	} // String determineAvailability(int)
	
	/**
	 * Word wraps a paragraph/ string (text) to the desired width.
	 * 
	 * @param text String - The text that needs to be word wrapped.
	 * @param width int - The width or how long each line should be until the text moves to the next line.
	 * @return String - the paragraph with all the lines concatenated so that it is word wrapped
	 */
	private String wordWrap(String text, int width) {
		
		// Word wrapping is done in a way so that the original text 
		// entered is not modified (new substrings are not created)
		
		// Declare variable needed
		String paragraph = "";
		
		// Declare and initialize the starting point to zero
	    int startPoint = 0; 
		  
		// Declare the ending point
		int endPoint; 
		  
		// Keep looping as long as the remaining portion of text or the text itself is greater than the
		// width entered for word wrapping
		while (text.substring(startPoint).length() > width) {
			
			// set endPoint to whatever the startingPoint is plus the width
			endPoint = startPoint + width;
			  
			// Keep decrementing endPoint by 1 until a space is found
			while (text.charAt(endPoint) != ' ') { 				 
				endPoint--;
			} // end while
			  
			// add the text from startingPoint to endPoint into paragraph plus a newline and 16 spaces to properly format it into a paragraph appearance
			paragraph += text.substring(startPoint, endPoint) + "\n                   ";
			  
			// At the end, set the startPoint to whatever the endPoint happened to be + 1 (to avoid the space).
			startPoint = endPoint + 1;
		  
		} // end while (as long as text is greater than width)
		  
		// Add the remaining text as there may be something left since the loop exits when the
		// length of the remaining portion (or the whole thing) is less than the width to paragraph
		paragraph += (text.substring(startPoint)); 
		
		// return paragraph (the text/paragraph word wrapped)
		return paragraph;
	
	} // String wordWrap (String, int)
	
	/**
	 * Displays the products in minimized version for the JIT
	 * 
	 * @param num int - the number of the product on a list
	 * @return String - the minimized version of product to be displayed in JIT
	 */
	public String displayJIT(int num) {

		// Declare variable needed
		String display;
		
		// Format the needed fields into display
		display = String.format("%3d. %-60s %10.2f       %10d       %-30s", num, name, price, quantity, determineOrderStatus(quantity, jitTrigger));
		
		// return the minimized JIT version of a product
		return display;
		
	} // String displayJIT(int)
	
	/**
	 * Displays the minimized version of a product for a customer depending on where the customer views it - in cart, or not in cart
	 * 
	 * @param num int - the number of the product in a list
	 * @param cart boolean - true if being viewed in the 'cart', or false if not being viewed in cart
	 * @return String - the appropriate minimized version that a customer sees
	 */
	public String displayMinCustomer(int num, boolean cart) {
				
		// Declare variable needed
		String display;
		
		// The following if structure determines if the product is being viewed in cart or not and sets the variable, display, accordingly by formatting
		// and adding the appropriate/needed fields
		if (cart) {
			display = String.format("%3d. %-60s %10.2f       %-15s %16d", num, name, price, determineAvailability(quantity), qtyPurchased);
		}
		else {
			display = String.format("%3d. %-60s %10.2f       %-15s" , num, name, price, determineAvailability(quantity));
		} // end if
		
		// return the minimized version
		return display;
	} // String displayMinCustomer(int, boolean)
	
	/**
	 * Displays the minimized product version for admin
	 * 
	 * @param num int - number of product in a list
	 * @return String - the minimized version
	 */
	public String displayMinProduct(int num) {
		
		// Declare variable needed
		String display;
		
		// Format the string appropriately by adding the needed fields
		display = String.format("%3d. %-60s %10.2f", num, name, price);
		
		// return the minimized version
		return display;
	} // String displayMinProduct(int)
	
	/**
	 * Displays the expanded form of a product for a customer
	 * 
	 * @return String - the expanded form of a product
	 */
	public String displayExpandedCustomer() {
		
		// return by concatenating all the needed fields, wordWrap description
		return  "\n         Category: " + category +
				"\n     Product Name: " + name +
				"\n            Price: " + price +
				"\n            Brand: " + brand +
				"\n            Color: " + color +
				"\nCountry of Origin: " + country +
				"\nQuantity in Store: " + quantity +
				"\n      Description: " + wordWrap(description, 115);	
		
	} // String displayExpandedCustomer(void)
	
	/**
	 * Displays the expanded form of a product for admin
	 */
	public String toString() {
		
		// return by concatenating all the needed fields, wordWrap description
		return  "\n             Name: " + name + 
				"\n            Price: " + price + 
				"\n         Quantity: " + quantity + 
				"\n         Category: " + category + 
				"\n            Brand: " + brand +
				"\n            Color: " + color + 
				"\nCountry of Origin: " + country + 
				"\n      JIT Trigger: " + jitTrigger + 
				"\n      Description: " + wordWrap(description, 115);
	} // String toString(void)
	
	/**
	 * Concatenates all the fields of a product by using a pipe ("|") as the delimiter
	 * 
	 * @return String - the value of the fields concatenated by pipes
	 */
	public String toCSV() {
		return String.join("|", name, String.valueOf(price), String.valueOf(quantity), category, brand, color, country, String.valueOf(jitTrigger), description);
	} // String toCSV(void)
	
} // end class (Product)
