
/**
 * My Store
 * 
 * This program is an "online" store which allows customer to view and purchase different products from a store. This
 * program also offers an admin mode. Admin mode allow the Administration to modify product/inventory details like
 * adding new products, deleting products, editing products, changing password of admin mode, changing tool limit, etc.
 * This program also involves a bit of GUI (via java swing library) as well.
 * 
 * @author Murtaza Shah
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class MyStoreSHAH {

	// Declare and initialize global variables needed
	// Using ArrayLists instead of arrays
	static List<Product> products = new ArrayList<Product>(), cart = new ArrayList<Product>();
	static int toolLimit, itemsPurchased = 0;
	static String usrPwd = "";

	// The concept of using lambdas to implement action listeners to buttons was
	// learned using the following video tutorial:
	// Link: https://www.youtube.com/watch?v=nfVi_CCVXl0
	// Creator: Hassan Baajour - University of Northumbria

	/**
	 * Creates and displays a GUI/visual of the welcome window when a customer
	 * enters in to customer mode
	 */
	public static void createWelcomeFrame() {

		// Declare and initialize JFrame
		JFrame f = new JFrame();

		// Declare the JComponents needed
		JPanel main, buttonP, textP;
		JLabel welcome, inst;
		JButton okB;
		JDialog welcomeD;

		// Initialize the main JPanel(), and set its layout
		main = new JPanel();
		main.setLayout(new BorderLayout(20, 70));

		// Initialize the ButtonP JPanel()
		buttonP = new JPanel();

		// initialize the okB JButton and set its Font
		okB = new JButton("OK");
		okB.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		// Add the JButton okB to the buttonP JPanel()
		// don't set the buttonP JPanel() to opaque(transparent so that the background
		// color of the entire main frame can also be seen in this JPanel())
		buttonP.add(okB);
		buttonP.setOpaque(false);

		// Initialize the textP JPanel()
		textP = new JPanel();

		// Initialize the welcome JLabel(), set its alignment and font
		welcome = new JLabel("Welcome to Murtaza's Tool Depot!");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Papyrus", Font.BOLD + Font.ITALIC, 50));

		// Initialize the inst JLabel(), set its alignment and font
		inst = new JLabel(
				"We offer various saws, pliers, hammers, and screw drivers - make sure to buy some great tools!");
		inst.setHorizontalAlignment(SwingConstants.CENTER);
		inst.setFont(new Font("Times New Roman", Font.ITALIC, 20));

		// Set the layout of the textP JPanel() and add the JLabels welcome and inst to
		// ir
		textP.setLayout(new BorderLayout());
		textP.add("Center", welcome);
		textP.add("South", inst);
		textP.setOpaque(false);

		// Initialize the welcomeD JDialog(), set it as a modal dialog. Set its layout
		welcomeD = new JDialog(f, "Murtaza's Tool Depot", true);
		welcomeD.setLayout(new BorderLayout(20, 70));

		// Add action listener to the okB JButton. Use a lambda to set what happens when
		// the button is pressed
		okB.addActionListener((ActionEvent e) -> {
			// when pressed, make it disappear
			welcomeD.setVisible(false);
		});

		// Add the JPanels() textP and buttonP to the main JPanel(), set the main
		// JPanel()'s background
		main.add("Center", textP);
		main.add("South", buttonP);
		main.setBackground(new Color(188, 242, 138));

		// add the main JPanel() to the welcomeD JDialog()
		welcomeD.add("Center", main);
		// Set the JDialog's location in the screen
		welcomeD.setLocation(180, 300);
		welcomeD.setBackground(new Color(188, 242, 138));
		// Set the dialog to be always on top
		welcomeD.setAlwaysOnTop(true);
		// set the size
		welcomeD.setSize(970, 300);
		// set resizable to false
		welcomeD.setResizable(false);
		// make the dialog visible
		welcomeD.setVisible(true);
		// Close the dialog
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	} // void createWelcomeFrame()

	/**
	 * Creates / displays a JFrame() where the user can enter the admin password in
	 * a JPasswordField
	 */
	public static void createPasswordFrame() {

		// Declare JComponents needed
		JFrame f2 = new JFrame();
		JPasswordField userPassword;
		JButton signInB;
		JLabel adminTitle, pwdLabel;
		JPanel pwdP, buttonP, mainPwdP, mainP;
		JDialog adminPwdD;

		buttonP = new JPanel();

		signInB = new JButton(" Sign-In ");
		signInB.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		adminTitle = new JLabel("Administrative Mode Password");
		adminTitle.setHorizontalAlignment(SwingConstants.CENTER);
		adminTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		buttonP.setOpaque(false);
		buttonP.setLayout(new BorderLayout());

		buttonP.add("East", new JLabel("                              "));
		buttonP.add("Center", signInB);
		buttonP.add("West", new JLabel("                              "));

		pwdP = new JPanel();
		pwdP.setLayout(new GridLayout(1, 4, 15, 5));
		pwdP.setOpaque(false);

		pwdLabel = new JLabel("Enter Password:");
		pwdLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

		// Use a JPasswordField() to get user to enter password
		userPassword = new JPasswordField(10);
		userPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

		pwdP.add(new JLabel("                     "));
		pwdP.add(pwdLabel);
		pwdP.add(userPassword);
		pwdP.add(new JLabel("                     "));

		mainPwdP = new JPanel();
		mainPwdP.setLayout(new GridLayout(2, 1, 5, 50));
		mainPwdP.add(adminTitle);
		mainPwdP.add(pwdP);
		mainPwdP.setOpaque(false);

		mainP = new JPanel();
		mainP.setLayout(new BorderLayout(0, 10));
		mainP.setBorder(new LineBorder(new Color(204, 255, 153), 20));
		mainP.setBackground(new Color(204, 255, 153));

		mainP.add("Center", mainPwdP);
		mainP.add("South", buttonP);

		adminPwdD = new JDialog(f2, "Admin Password", true);
		adminPwdD.setLayout(new BorderLayout(0, 10));
		adminPwdD.add(mainP);

		// use a lambda to determine what happens when the sign in button is pressed
		signInB.addActionListener((ActionEvent e) -> {

			// Set the usrPwd to blank
			usrPwd = "";

			// Get the password in the JPasswordField() and store the password characters in
			// a char array
			char[] pwdChars = userPassword.getPassword();

			// Iterate through the password char array
			for (int i = 0; i < pwdChars.length; i++) {

				// Add each char to usrPwd to obtain a String of what the password is that the
				// user entered
				usrPwd += pwdChars[i];
			} // end for

			// Hide the dialog
			adminPwdD.setVisible(false);
		});

		// Set the dialog's background
		adminPwdD.setBackground(new Color(204, 255, 153));
		// Set dialog's size
		adminPwdD.setSize(700, 225);
		// Don not set it to be resizable
		adminPwdD.setResizable(false);
		// Always have the dialog focused - on top
		adminPwdD.setAlwaysOnTop(true);
		// Set the dialog's location on the screen when it appears
		adminPwdD.setLocation(330, 300);
		// set the dialog visible
		adminPwdD.setVisible(true);

	} // void createPasswordFrame(void)

	public static void displayPopup(String text) {

		// Declare and initialize a JFrame() popupFrame
		JFrame popupFrame = new JFrame();

		// Always set the Frame focused (on top)
		popupFrame.setAlwaysOnTop(true);

		// Display a dialog with "text"
		JOptionPane.showMessageDialog(popupFrame, text);

		// Exit/completely close the Frame when exiting
		popupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	} // void displayPopup(String)

	/**
	 * Reads data from the inventory file and populates the products ArrayList with
	 * objects of the Product class
	 */
	public static void populateList() throws IOException {

		// Declare variable needed
		String text;

		// Open file to read
		BufferedReader in = new BufferedReader(new FileReader("inventory.csv"));

		// Read a line
		text = in.readLine();

		// Keep looping until the end of the file is reached
		while (text != null) {

			// Construct a Product object (using the appropriate constructor), and add that
			// object to the ArrayList
			products.add(new Product(text));

			// Read another line
			text = in.readLine();
		} // end while (reading the file)

		// Close the file
		in.close();

	} // void populateList(void)

	/**
	 * Takes the data stored in the products ArrayList and writes the data back to
	 * the file (using .toCSV())
	 */
	public static void writeToFile() throws IOException {

		// Open file to write
		PrintWriter f = new PrintWriter(new FileWriter("inventory.csv"));

		// Iterate through the products ArrayList
		for (int i = 0; i < products.size(); i++) {

			// Write the data to the file (using .toCSV())
			f.println(products.get(i).toCSV());
		} // end for

		// Close file
		f.close();

	} // void writeToFile()

	/**
	 * Opens the file which stores the tool limit, store the tool limit in a
	 * variable so that it can be accessed later
	 */
	public static void getToolLimit() throws IOException {

		// Open file to read
		BufferedReader limit = new BufferedReader(new FileReader("tool-limit.txt"));

		// Read the line, parse to integer and store the number in toolLimit
		toolLimit = Integer.parseInt(limit.readLine());

		// Close the file
		limit.close();

	} // void getToolLimit(void)

	/**
	 * Displays the title (Skips 2 lines, displays the title and then underlines it)
	 * 
	 * @param section String - the title of the section
	 */
	public static void displaySectionTitle(String section) {

		// Declare and initialize the underline String
		String underline = "";

		// Display separator
		System.out.println(
				"\n\n____________________________________________________________________________________________________________________________________________________");

		// Skip 2 lines and display the title
		System.out.println("\n" + section);

		// For loop to keep on adding equal signs to underline for however many
		// characters there are in "section" to get an appropriate
		// length of the underline.
		for (int i = 0; i < section.length(); i++) {
			underline += "=";
		} // end for

		// Display the underline
		System.out.println(underline);

	} // void displaySectionTitle(String)

	/**
	 * Display headers for the different data/information tables
	 * 
	 * @param num int - this number represents and determines which header to
	 *            display
	 */
	public static void displayHeaders(int num) {

		// If num is 1, display header with name, price, quantity, and order status
		if (num == 1) {
			System.out.printf("\n%3s. %-60s %10s       %10s       %-40s%n", "#", "Product Name", "Price ($)",
					"Quantity", "Order Status");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------");
		}

		// if num is 2, display header with name and price
		else if (num == 2) {
			System.out.printf("\n%3s. %-60s %10s%n", "#", "Product Name", "Price ($)");
			System.out.println("-----------------------------------------------------------------------------------");
		}

		// if num is 3, display header with name, price, and availability
		else if (num == 3) {
			System.out.printf("\n%3s. %-60s %10s       %-15s%n", "#", "Product Name", "Price ($)", "Availability");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------");
		}

		// if num is 4, diplay header with name, price, availability, and quantity of
		// item in cart
		else {
			System.out.printf("\n%3s. %-60s %10s       %-15s %16s%n", "#", "Product Name", "Price ($)", "Availability",
					"Quantity in Cart");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
		} // end if

	} // void displayHeader(int)

	/**
	 * Waits for the user to press enter before continuing to the next stage
	 * 
	 * @param prompt String - a statement to tell user why to press enter
	 */
	public static void pressEnter(String prompt) {

		// Declare scanner
		Scanner s = new Scanner(System.in);

		// Display the prompt (what is happening/ why to press enter)
		System.out.print(prompt);

		// Prompt user to press enter
		s.nextLine();

	} // void pressEnter(String)

	/**
	 * Obtains a valid character (depending on the situation there are different
	 * valid characters) from the user and returns that valid character
	 * 
	 * @param prompt     String - a statement that tells the user why to enter a
	 *                   character
	 * @param validChars - char[] - an array of all the valid characters
	 * @return char - the valid character chosen by user
	 */
	public static char getValidChar(String prompt, char[] validChars) {

		// Declare scanner
		Scanner s = new Scanner(System.in);

		// Declare variables needed
		char usrOpt;
		boolean validChar = false;

		// Error trap for obtaining a valid character
		while (true) {

			// Display the prompt
			System.out.print(prompt);

			// Get user's input, convert to lower case, and take the first char. Store it in
			// usrOpt
			usrOpt = s.nextLine().toLowerCase().charAt(0);

			// For loop to iterate through the validChars array
			for (int i = 0; i < validChars.length; i++) {

				// If the character the user entered was one of the valid characters, set
				// validChar (a valid char has been entered) to true,
				// and break out of the for loop
				if (validChars[i] == usrOpt) {
					validChar = true;
					break;
				} // end if
			} // end for

			// If a valid char was entered, break out of error trap (while loop)
			if (validChar == true) {
				break;
			} // end if

			// Otherwise if an invalid char was entered, display an error message
			System.out.println("Please enter a valid option.");
		} // end while

		// return usrOpt (the valid char entered by user)
		return usrOpt;

	} // char getValidChar(String, char[])

	/**
	 * Obtains either y or n from the user. Overloaded method (validChars are set to
	 * y or n by default if no array is passed in)
	 * 
	 * @param prompt String - a statement to tell user why to enter y or n
	 * @return char - either y or n
	 */
	public static char getValidChar(String prompt) {

		// Make an array of validChars
		char[] validChars = { 'y', 'n' };

		// return the valid character
		return getValidChar(prompt, validChars);

	} // char getValidChar(String)

	/**
	 * Prompts the user to enter a password to gain entry into admin mode. If
	 * password is correct, returns true, else returns false
	 * 
	 * @return boolean - true if password is correct, false if incorrect
	 */
	public static boolean checkPassword() throws IOException {

		// Declare scanner
		Scanner s = new Scanner(System.in);

		// Declare variables needed
		String adminPwd;
		boolean pwdMatch = false;

		// Open password file to read
		BufferedReader in = new BufferedReader(new FileReader("admin-password.txt"));

		// Read the line and store the password in the file in a variable
		adminPwd = in.readLine();

		// close the file
		in.close();

		// Call the displaySectionTitle() method to display the title
		displaySectionTitle("Admin Password Check");

		// Display few instructions
		System.out.println(
				"You will be prompted to enter the Admin password, you have three tries to enter the correct password. If you");
		System.out.println("fail to do so, you will be directed back to the Main Menu at the start.");

		// call pressEnter() to prompt user to press enter
		pressEnter("\nPlease press enter after you have read the instruction above to proceed further...");

		// Loop three times (user has 3 tries to enter the correct password)
		for (int i = 1; i <= 3; i++) {

			// Call the createPasswordFrame() method to display the GUI for obtaining the
			// password from the user
			createPasswordFrame();

			// Check if the password which the user entered is correct
			if (usrPwd.equals(adminPwd)) {

				// If correct, pwdMatch is set to true, and break out of loop
				pwdMatch = true;
				break;

			} // end if

			// Otherwise, if password is incorrect
			else {

				// If the number of try is not 3 (because it should not display '0 tries left'
				// and should simply say that access cannot be given at that point)
				if (i != 3) {

					// Call the displayPopup() method and tell user that password is incorrect and
					// how many attempts remain
					displayPopup("Incorrect password, " + (3 - i) + " attempt(s) left.");

				} // end if
			} // end if
		} // end for

		// If pwdMatch is true (correct password)...
		if (pwdMatch == true) {

			// call the displayPopup() method to tell user that the password is correct and
			// they can access admin mode
			displayPopup("Correct password, you have been given entry into admin mode.");

		}

		// Otherwise, if user was not able to enter the correct password within 3
		// tries...
		else {

			// call the displayPopup() method to tell user that they cannot be given access
			// to admin mode
			displayPopup("Sorry, that was three tries, you cannot be given access to admin mode.");

		} // end if

		// return pwdMatch
		return pwdMatch;

	} // boolean checkPassword(void)

	/**
	 * Obtains a valid value from the user which is within the range of accepted
	 * integers
	 * 
	 * @param prompt  String - sentence to tell user why to enter a value
	 * @param lowest  int - the lowest acceptable integer
	 * @param highest int - the highest acceptable integer
	 * @return int - the valid value (integer) user entered
	 */
	public static int getValidVal(String prompt, int lowest, int highest) {

		// Declare and initialize the scanner
		Scanner s = new Scanner(System.in);

		// Declare choice (int)
		int choice;

		// Error trap for choice values
		while (true) {

			// Prompt the user to enter an integer, and store the value in choice.
			System.out.print(prompt);
			choice = s.nextInt();

			// Check if the entered integer is within the accepted range, if so, break out
			// of loop
			if (choice >= lowest && choice <= highest) {
				break;
			} // end if

			// If choice is not within the accepted range, then display an error message.
			System.out.println("Please enter a valid choice between " + lowest + " and " + highest + ".");
		} // end while (error trap for choice values)

		// return the valid integer that the user entered.
		return choice;

	} // int getValidVal(int, int)

	/**
	 * Obtains a value from the user which is greater than 0
	 * 
	 * @param prompt String - Sentence which tells user why to enter a number
	 * @return int - the number which is greater than 0
	 */
	public static int getZeroOrGreater(String prompt) {

		// Declare and initialize the scanner
		Scanner s = new Scanner(System.in);

		// Declare choice (int)
		int choice;

		// Error trap for choice values
		while (true) {

			// Prompt the user to enter an integer, and store the value in choice.
			System.out.print(prompt);
			choice = s.nextInt();

			// Check if the entered integer is within the accepted range, if so, break out
			// of loop
			if (choice >= 0) {
				break;
			} // end if

			// If choice is not within the accepted range, then display an error message.
			System.out.println("\nPlease enter a valid input (greater than or equal to zero).");
		} // end while (error trap for choice values)

		// return the valid integer that the user entered.
		return choice;
	} // int getZeroOrGreater(String)

	/**
	 * Obtains a valid price from the user - greater than zero
	 * 
	 * @param prompt String - sentence which tells user what to enter
	 * @return double - the valid price entered by user
	 */
	public static double getValidPrice(String prompt) {

		// Declare and initialize the scanner
		Scanner s = new Scanner(System.in);

		// Declare choice (int)
		double price;

		// Error trap for choice values
		while (true) {

			// Prompt the user to enter an integer, and store the value in choice.
			System.out.print(prompt);
			price = s.nextDouble();

			// Check if the entered integer is within the accepted range, if so, break out
			// of loop
			if (price > 0) {
				break;
			} // end if

			// If choice is not within the accepted range, then display an error message.
			System.out.println("\nPlease enter a valid price (greater than zero).");
		} // end while (error trap for choice values)

		// return the valid integer that the user entered.
		return price;

	} // double getValidPrice(String)

	/**
	 * When passed in how many occurrences of an event occur (count), and the
	 * location of those occurrences (int spot array), this
	 * method obtains a valid value from the user representing the event chosen. It
	 * then returns an integer value accordingly to
	 * what the count is and what the user entered
	 * 
	 * @param prompt String - statement which tells user why to enter a valid value
	 *               (make a selection)
	 * @param spot   int[] - array of integers which stores all the indexes of the
	 *               desired event
	 * @param count  int - how many times the desired event occurred
	 * @return int - a value which indicates what the user selected, and what the
	 *         outcome was when a certain event was desired
	 */
	public static int obtainValidSelection(String prompt, int[] spot, int count) {

		// Declare variables needed
		int returnValue;
		int productChoice;

		// If count is 0 - if the desired event never occurred, then set the return
		// value to -2
		if (count == 0) {
			returnValue = -2;
		}

		// If the desired event did occur
		else {

			// Call the getValidVal() method to obtain a valid value for which of the many
			// desired events the user wants to select
			// Store the returned value in productChoice
			productChoice = getValidVal(prompt, 0, count);

			// If user entered 0- they did not want to select any, and want to exit, set
			// returned value to -1
			if (productChoice == 0) {
				returnValue = -1;
			}

			// Otherwise user wants to choose from a series of wanted events, and set the
			// returned value to the index of where that
			// event (product) is found in the main array (products)
			else {
				returnValue = spot[productChoice - 1];
			} // end if
		} // end if

		// return the value indicating the outcome of what the desired events were, how
		// many were there, and which one the user selected
		return returnValue;

	} // int obtainValidSelection(String, int[], int)

	/**
	 * Allows user to select an item when the user wants to do something based on a
	 * specified category. It displays all the products in the
	 * specified category and allows the user to select one.
	 * 
	 * @param customer boolean - this determines if a customer wants to do something
	 *                 by a specific category (true), or an admin. This determines
	 *                 which header to display.
	 * @param prompt   String - tells user why to select a product
	 * @param category String - the category that needs to be displayed
	 * @return int - the index of the selected product of a category in the products
	 *         ArrayList, or a number which tells if no products were found or if
	 *         user wanted to exit
	 */
	public static int displayByCategory(boolean customer, String prompt, String category) {

		// Declare and initialize variables needed
		int count = 0;
		int[] spot = new int[products.size()];

		// Depending on if it is a customer or admin, display a table header accordingly
		if (!customer) {
			displayHeaders(2);
		} else {
			displayHeaders(3);
		} // end if

		// Iterate through the products ArrayList
		for (int i = 0; i < products.size(); i++) {

			// If a product is in the wanted category...
			if (products.get(i).getCategory().equals(category)) {

				// Store the index the product object is found in spot array
				spot[count] = i;

				// increment count by 1
				count++;

				// Depending on whether or not it is a customer or admin, display the
				// appropriate minimized version
				if (!customer) {
					System.out.println(products.get(i).displayMinProduct(count));
				} else {
					System.out.println(products.get(i).displayMinCustomer(count, false));
				} // end if

			} // end if
		} // end for

		// return the value returned from the obtainValidSelection() method
		return obtainValidSelection(prompt, spot, count);

	} // int displayByCategory(boolean, String, String)

	/**
	 * Allows user to view all the products in all categories and allows them to
	 * select one to do something with it
	 * 
	 * @param customer boolean - Determines if it is a customer (true), or admin
	 *                 (false) - display headers and minimized versions of the
	 *                 product accordingly
	 * @param prompt   String - tells user why to select a product
	 * @return int - the index of the selected product of a category in the products
	 *         ArrayList, or a number which tells if no products were found or if
	 *         user wanted to exit
	 */
	public static int displayAllCategories(boolean customer, String prompt) {

		// Declare variables needed
		int count = 0;
		int[] spot = new int[products.size()];

		// Display header appropriately if customer or not
		if (!customer) {
			displayHeaders(2);
		} else {
			displayHeaders(3);
		} // end if

		// Iterate through the products ArrayList
		for (int i = 0; i < products.size(); i++) {

			// Store indexes in spot array, and keep incrementing count by 1
			spot[count] = i;
			count++;

			// Display appropriate minimized versions of products if admin or customer
			if (!customer) {
				System.out.println(products.get(i).displayMinProduct(count));
			} else {
				System.out.println(products.get(i).displayMinCustomer(count, false));
			} // end if
		} // end for

		// return the value obtained by the obtainValidSelection() method
		return obtainValidSelection(prompt, spot, count);
	} // int displayAllCategories(boolean, String)

	/**
	 * Displays the category menu and obtains a valid category from the user
	 * 
	 * @param prompt String - tells user why to choose a category
	 * @return String - the chosen category
	 */
	public static String getValidCategory(String prompt) {

		// Declare / initialize variables needed
		char usrChoice;
		char[] validChars = { 'h', 's', 'p', 'd' };

		// Display the category menu
		System.out.println("\nChoose the category:");
		System.out.println("h - Hammers");
		System.out.println("s - Saws");
		System.out.println("p - Pliers");
		System.out.println("d - Screwdrivers");

		// Call the getValidChar() method to get a valid character which represents a
		// category. Store the returned value in usrCHoice
		usrChoice = getValidChar(prompt, validChars);

		// Use a HashMap to map all the category characters to actual full names of the
		// categories
		Map categoryValues = new HashMap();
		categoryValues.put('h', "Hammers");
		categoryValues.put('s', "Saws");
		categoryValues.put('p', "Pliers");
		categoryValues.put('d', "Screwdrivers");

		// return the mapped value (the category) of the character user selected
		return String.valueOf(categoryValues.get(usrChoice));

	} // String getValidCategory(String)

	/**
	 * Displays the country menu and obtains a valid country from the user
	 * 
	 * @param prompt String - tells user why to enter a country
	 * @return String - the chosen country
	 */
	public static String getValidCountry(String prompt) {

		// Declare/initialize variables needed
		char usrChoice;
		char[] validChars = { 'a', 'b', 'c', 'd', 'e', 'f' };

		// Display the country menu
		System.out.println("\nChoose the country:");
		System.out.println("a - Canada");
		System.out.println("b - United States");
		System.out.println("c - China");
		System.out.println("d - Germany");
		System.out.println("e - Taiwan");
		System.out.println("f - Other");

		// Call the getValidChar() method to obtain a valid char which matches the
		// country chars. Store the returned value in usrChoice
		usrChoice = getValidChar(prompt, validChars);

		// Map each of the characters to the country names
		Map countryValues = new HashMap();
		countryValues.put('a', "Canada");
		countryValues.put('b', "United States");
		countryValues.put('c', "China");
		countryValues.put('d', "Germany");
		countryValues.put('e', "Taiwan");
		countryValues.put('f', "Other");

		// Return the mapped value (country) of the valid country character the user
		// chooses.
		return String.valueOf(countryValues.get(usrChoice));

	} // String getValidCountry(String)

	/**
	 * Allows user to enter input (displays prompt and takes input in the same line
	 * of code when this method is called)
	 * 
	 * @param prompt String - tells user what/why to enter an input
	 * @return String - the input entered by user
	 */
	public static String takeInput(String prompt) {

		// Declare scanner
		Scanner s = new Scanner(System.in);

		// Declare variable needed
		String usrInp;

		// Prompt the user to enter an input (display prompt) and store the input in
		// usrInp
		System.out.print(prompt);
		usrInp = s.nextLine();

		// return the user's input
		return usrInp;
	} // String takeInput(String)

	/**
	 * Allows the admin to edit the details of products, writes the updated data
	 * back to file
	 */
	public static void editProduct() throws IOException {

		// Declare/initialize variables needed
		boolean updtFile = false;
		char proceed;
		char usrOpt;
		int index;
		char[] validChars = { 'a', 's' };
		String category = "";

		// DIsplay the title and few instructions
		displaySectionTitle("View and Edit Products");
		System.out.println(
				"Here, you will be able to view all your products in the inventory and you can also select at tool from a");
		System.out.println("list if you wish to further view its details or edit any of its attributes.");

		// Call the getValidChar() method to ask user if they want to edit by all
		// categories or by a specific category
		usrOpt = getValidChar(
				"\nWould you like to view/edit by viewing all categories (a) or a specific category (s) (a or s)? ",
				validChars);

		// If user wants to search by a specific category
		if (usrOpt == 's') {

			// Call the getValidCategiry() method to get a valid category and store it in
			// category
			category = getValidCategory(
					"\nWhich category do you wish to view/edit in (enter the appropriate letter that matches the category): ");

		} // end if

		// Start loop to edit products
		while (true) {

			// If user selected the option to edit in a specific category
			if (usrOpt == 's') {

				// Display the chosen category
				System.out.println("\nCategory chosen to view/edit from: " + category);

				// Call the displayByCategory() method to display the products in that category
				// and obtain the index of the selected product. Store
				// returned value in index
				index = displayByCategory(false,
						"\nEnter the number of the product you wish to view/edit (enter 0 to exit): ", category);
			}

			// If user wants to edit by viewing all categories, call the
			// displayAllCategories() method. Store returned value in index
			else {
				index = displayAllCategories(false,
						"\nEnter the number of the product you wish to view/edit (enter 0 to exit): ");
			} // end if

			// if index is -1, user wants to exit, break out of loop
			if (index == -1) {
				break;
			} // end if

			// If index is -2, no products found
			if (index == -2) {

				// Display a message that tells user that no products were found and call the
				// pressEnter() method, and break out of loop
				System.out.println("\nSorry, no products found.");
				pressEnter("\nPlease press enter to continue...");
				break;
			}

			// Else, products were found and user selected one
			else {

				// Display the expanded version of the selected product
				System.out.println("\nThe product you are viewing/editing is:");
				System.out.println(products.get(index).toString());

				// Ask user if they would like to proceed in editing the product (call the
				// getValidChar() method). Store returned value in proceed
				proceed = getValidChar("\nAre you sure you want to edit this product (y or n)? ");

				// If user wants to edit
				if (proceed == 'y') {

					// Start loop for editing a product
					while (true) {

						// Declare variables needed
						char whichField;
						char[] validFieldChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'x' };

						// Display a menu which asks the user which field they would like to edit
						System.out.println("Which field would you like to edit:");
						System.out.println("a - name");
						System.out.println("b - price");
						System.out.println("c - quantity");
						System.out.println("d - brand");
						System.out.println("e - country");
						System.out.println("f - colour");
						System.out.println("g - JIT Trigger");
						System.out.println("h - Category");
						System.out.println("i - Description");
						System.out.println("x - EXIT");

						// Call the getValidChar() method to obtain a valid field. Store returned value
						// in whichField
						whichField = getValidChar(
								"\nChoose the field you like to edit (enter the appropriate letter for the corresponding field): ",
								validFieldChars);

						// If user wants to exit ('x'), break out of loop
						if (whichField == 'x') {
							break;
						} // end if

						// Set updtFIle to true if user wants to edit and not exit
						updtFile = true;

						// The following if structure determines which field the user wants to edit,
						// obtains the new value for the chosen field (by using
						// appropriate methods) and then sets the value of that field to the new value
						// entered by user
						if (whichField == 'a') {
							products.get(index).setName(takeInput("Enter changed product name: "));
						} else if (whichField == 'b') {
							products.get(index).setPrice(getValidPrice("Enter changed price: "));
						} else if (whichField == 'c') {
							products.get(index).setQuantity(getZeroOrGreater("Enter changed quantity: "));
						} else if (whichField == 'd') {
							products.get(index).setBrand(takeInput("Enter changed brand: "));
						} else if (whichField == 'e') {
							products.get(index).setCountry(getValidCountry("Select changed country of the product: "));
						} else if (whichField == 'f') {
							products.get(index).setColor(takeInput("Enter changed color: "));
						} else if (whichField == 'g') {
							products.get(index).setJitTrigger(getZeroOrGreater("Enter changed JIT Trigger: "));
						} else if (whichField == 'h') {
							products.get(index)
									.setCategory(getValidCategory("Select changed category of the product: "));
						} else {
							products.get(index).setDescription(takeInput("Enter changed description: "));
						} // end if

						// Call the pressEnter() method
						pressEnter("\nPlease press enter to continue (product updated)...");

						// Display the updated product
						System.out.println("\nThe updated product is now:");
						System.out.println(products.get(index).toString());

						// Call pressEnter() method
						pressEnter("\nPlease press enter to continue...");
					} // end while (editing a product by asking the field)
				} // end if
			} // end if
		} // end while (loop for editing products - choose from a list)

		// If changes are made, call the writeToFile() method to update the inventory
		// file.
		if (updtFile == true) {
			writeToFile();
		} // end if

	} // void editProduct(void)

	/**
	 * Allows user to add a new product to their inventory
	 */
	public static void addProduct() throws IOException {

		// Declare variables needed
		String name, category, brand, color, country, description;
		int jitTrigger, quantity;
		double price;
		char addAnother;

		// Display the title and few instructions
		displaySectionTitle("Adding Products");
		System.out.println(
				"Here, you are able to create a new tool/product which you will be able to add to your inventory.");

		// Start loop for adding products
		while (true) {

			// The following algorithm asks the user information about all the fields of the
			// new product being added
			category = getValidCategory("\nEnter the category the product belongs to: ");
			name = takeInput("Enter the product's name: ");
			price = getValidPrice("Enter the product's price: ");
			quantity = getZeroOrGreater("Enter the product's quantity: ");
			jitTrigger = getZeroOrGreater("Enter the product's JIT Trigger: ");
			brand = takeInput("Enter the product's brand: ");
			color = takeInput("Enter the prodcut's color: ");
			country = getValidCountry("Enter the product's country: ");
			description = takeInput("Enter the description for the product: ");

			// Create the new Product object by passing all the values into the constructor
			// and add the new Product object to the products ArrayList
			products.add(new Product(name, price, quantity, category, brand, color, country, jitTrigger, description));

			// Prompt user to press enter
			pressEnter("\nPlease press enter to continue (product created)...");

			// Ask user if they want to add another item
			addAnother = getValidChar("\nWould you like to add another product (y or n)? ");

			// If user doesn't want to add another item, break out of loop
			if (addAnother == 'n') {
				break;
			} // end if

		} // end while

		// update the file by calling the writeToFile() method
		writeToFile();
	} // void addProduct(void)

	/**
	 * Allows user to delete products from the inventory
	 */
	public static void deleteProduct() throws IOException {

		// Declare/ initialize variables needed
		boolean updtFile = false;
		char proceed;
		int index;
		char usrChoice;
		char[] validChars = { 'a', 's' };
		String category = "";

		// Display the title and few instructions
		displaySectionTitle("Delete Products");
		System.out.println("Here, you will be able to delete an item completely from your inventory.");

		// Ask user if they want to delete by viewing a specific category or by all
		// categories. Store the returned value in usrChoice
		usrChoice = getValidChar(
				"\nWould you like to delete by viewing all products (a) or a specific category (s) (a or s)? ",
				validChars);

		// If the user wants to do it by a specific category
		if (usrChoice == 's') {

			// Call the getValidCategory() method to get a category. Store the returned
			// value (category) in category
			category = getValidCategory("Enter the category you wish to delete the product from: ");

		} // end if

		// Start loop for continuously asking user if they want to delete
		while (true) {

			// If user chose a specific category to delete in
			if (usrChoice == 's') {

				// Display the category chosen
				System.out.println("\nCategory chosen to delete from: " + category);

				// Call the displayByCategory() method to display the products of that category
				// and get the index of the chosen product
				index = displayByCategory(false,
						"\nEnter the number of the product you wish to delete (enter 0 to exit): ", category);
			}

			// Otherwise, user wanted to delete by viewing all categories
			else {

				// Call the displayAllCategories() method to display the products of that
				// category and get the index of the chosen product
				index = displayAllCategories(false,
						"\nEner the number of the product you wish to delete (enter 0 to exit): ");
			} // end if

			// If index is -1, user wants to exit, break out of loop
			if (index == -1) {
				break;
			} // end if

			// If index is -2, no products were found
			if (index == -2) {

				// Display a message that tells user that no products were found and call the
				// pressEnter() method, break out of loop
				System.out.println("\nSorry, no products found.");
				pressEnter("\nPlease press enter to continue...");
				break;

			}

			// User selected a product and products were found
			else {

				// Display the expanded version of product
				System.out.println("\nThe product you are deleting is:");
				System.out.println(products.get(index).toString());

				// Ask user if they are sure they want to delete the product. Store the returned
				// value in proceed
				proceed = getValidChar("\nAre you sure you want to delete this product (y or n)? ");

				// If user wants to delete
				if (proceed == 'y') {

					// Set updtFile to true
					updtFile = true;

					// Remove the Product object from the products ArrayList
					products.remove(index);

					// Prompt user to press enter
					pressEnter("\nPlease press enter to continue (product deleted from inventory)...");
				}

				// Otherwise, user doesn't want to delete
				else {

					// Promp user to press enter while also displaying that the product was not
					// deleted
					pressEnter("\nPlease press enter to continue (product not deleted from inventory)...");
				} // end if
			} // end if
		} // end while

		// If changes has been made (updtFile is true)
		if (updtFile == true) {

			// Call the writeToFile() method to update the inventory file
			writeToFile();
		} // end if
	} // void deleteProduct(void)

	/**
	 * Allows user to change the limit of tools per customer
	 */
	public static void changeLimit() throws IOException {

		// Declare variables needed
		char proceed;
		int newLimit;

		// Display the section title and instructions
		displaySectionTitle("Change Tool Limit Per Customer");
		System.out.println(
				"You can change the allowed number of tools per customer here so you can set the limit lower if for example");
		System.out.println(
				"there is a big sale, or make the limit higher if it is a 'clearance'. If no limit is wanted - set it really high.");
		System.out.println("\nNOTE: the limit of tools must be between 1 and 100 tools per customer.");

		// Display the current limit
		System.out.println("The current limit is: " + toolLimit + " tools / customer");

		// Ask user if they want to change the limit. Store the returned value in
		// proceed
		proceed = getValidChar("\nAre you sure you want to change the limit (y or n)? ");

		// If user wants to change the limit
		if (proceed == 'y') {

			// Obtain the new limit from the user (call the getValidVal() method). Sotre it
			// in newLimit
			newLimit = getValidVal("\nEnter the new tool limit: ", 1, 100);

			// Open the file (in which the limit is stored) to read
			PrintWriter f = new PrintWriter(new FileWriter("tool-limit.txt"));

			// Write the new limit to the file
			f.println(newLimit);

			// Close the file
			f.close();

			// Display a pop-up (GUI/visual) that the limit has been changed
			displayPopup("Limit successfuly changed to " + newLimit + " tools(s).");
		}

		// Otherwise, user didn't want to change the limit
		else {

			// Prompt user to press enter
			pressEnter("\nPlease press enter to continue (limit not changed)...");

		} // end if
	} // void changeLimit(void)

	/**
	 * Allows the user to view the JIT inventory monitor
	 */
	public static void displayJIT() {

		// Declare variables needed
		char usrChoice;
		char[] validChars = { 'a', 's', 'x' };
		String category = "";

		// Display the title and instructions
		displaySectionTitle("View JIT Inventory Monitor");
		System.out.println(
				"This menu displays all the items in the store with their remaining quantities and tells the product's");
		System.out.println("order status - one of ORDER NOW, ORDER SOON, or IN - STOCK.");

		// Start loop if user wants to continuously view the JIT monitor if different
		// categories
		while (true) {

			// Set count to 0
			int count = 0;

			// Ask user how they wish to view the JIT monitor
			usrChoice = getValidChar(
					"\nDo you wish to view the JIT Inventory Monitor by viewing all products (a) or products of a specific category (s) (a or s, x to exit): ",
					validChars);

			// If user wants to exit, break out of loop
			if (usrChoice == 'x') {
				break;
			} // end if

			// if user wants to view by a specific category
			if (usrChoice == 's') {

				// Call the getValidCategory() method to display the products in the category
				// and allows user to select a product in that category. Store the returned
				// value in index.
				category = getValidCategory("\nChoose the category you wish to view the JIT Monitor by: ");
				// Display which category is chosen
				System.out.println("\nCategory chosen to view JIT by: " + category);
			} // end if

			// DIsplay the table header for JIT
			displayHeaders(1);

			// Iterate through the products ArrayList
			for (int i = 0; i < products.size(); i++) {

				// If the user wanted to view by a certain category and a product matches that
				// category
				if (usrChoice == 's' && products.get(i).getCategory().equals(category)) {

					// increment count by 1
					count++;
					// display the minimized version of the contact (for JIT)
					System.out.println(products.get(i).displayJIT(count));
				}

				// otherwise, if the user chose to view by all categories
				else if (usrChoice == 'a') {
					// increment count by 1
					count++;
					// display the minimized version of the contact (for JIT)
					System.out.println(products.get(i).displayJIT(count));
				} // end if
			} // end for

			// If no products were found, tell user that and prompt user to press enter
			if (count == 0) {
				pressEnter("\nSorry, no products found, please press enter to continue...");
			} else {
				pressEnter("\nPlease press enter to continue...");
			} // end if
		} // end while

	} // void displayJIT(void)

	/**
	 * Allows the user to change the password to admin mode
	 */
	public static void changePassword() throws IOException {

		// Declare scanner
		Scanner s = new Scanner(System.in);

		// Declare variables needed
		String newPwd;
		char proceed;

		// Display title and instructions
		displaySectionTitle("Change Admin Password");
		System.out.println("This option will allow you to change the password to admin mode.");
		System.out.println(
				"NOTE: The new password must be at least 8 characters long and must not contain any spaces.\n");

		// Error trap for checking if user entered a valid new password
		while (true) {

			// Prompt user for the new password, store it in newPwd
			System.out.print("Enter new password: ");
			newPwd = s.nextLine();

			// If the length of the password is 8 or more, and the password contains no
			// spaces (also tabs), then break out of error trap loop
			if (newPwd.length() >= 8 && newPwd.indexOf(" ") == -1 && newPwd.indexOf("\t") == -1) {
				break;
			} // end if

			// If password is invalid, display a pop-up that tells the user that password is
			// invalid
			displayPopup("Invalid password. Password must contain 8 characters at least and no spaces.");
		} // end while

		// Ask user if they are sure they want to change the password
		proceed = getValidChar("\nAre you sure you want to change the password (y or n)? ");

		// If they are sure
		if (proceed == 'y') {

			// Open the file in which the password is stored
			PrintWriter f = new PrintWriter(new FileWriter("admin-password.txt"));

			// Write the new password to the file
			f.println(newPwd);

			// Close the file
			f.close();

			// Display a pop-up that says that the password has been changed
			displayPopup("Password Successfuly changed");
		}
		// otherwise, user did not want to change password, so tell the user that and
		// prompt them to press enter
		else {
			pressEnter("Please press enter to continue (password not changed)...");
		} // end if
	} // void changePassword(void)

	/**
	 * Allows user to view the dollar amount of the session sales and the grand
	 * total sales. Also allows them to roll the amount over if the session sales
	 * is greater than zero dollars
	 */
	public static void sessionSales() throws IOException {

		// Declare variables needed
		char rollOver;
		double sessionSales;
		double grandTotalSales;

		// Display title and instructions
		displaySectionTitle("Session Sales");
		System.out.println(
				"Here you will be able to view the total dollar amount which represents all the sales that took place since");
		System.out.println(
				"this program was installed and you will also be able to view the dollar amount in the 'cash register' since");
		System.out.println(
				"the manager last collected all the all the money from it. If the amount in the register is not $0, you will");
		System.out.println("have the option to 'roll the amount over' and add it to your grand total sales amount.");

		// Open the file which contains the session sales amount
		BufferedReader session = new BufferedReader(new FileReader("salestotal.txt"));

		// Parse the value to a double and store the amount in sessionSales
		sessionSales = Double.parseDouble(session.readLine());

		// Close the file
		session.close();

		// Open the file which contains the grand total sales amount
		BufferedReader grand = new BufferedReader(new FileReader("salesgrandtotal.txt"));

		// Parse the value to a double and store it in grandTotalSales
		grandTotalSales = Double.parseDouble(grand.readLine());

		// Close the file
		grand.close();

		// Display the session sales as well as the grand total sales
		System.out.printf("\n%19s $%40.2f%n", "Session Sales: ", sessionSales);
		System.out.printf("%19s $%40.2f%n", "Grand Total Sales: ", grandTotalSales);

		// If sessionSales amount is greater than 0
		if (sessionSales > 0) {

			// Ask user if they want to roll over the amount
			rollOver = getValidChar("\nWould you like to roll the amount over (y or n)? ");

			// If they want to roll over
			if (rollOver == 'y') {

				// Add the sessionSales to the grandTotalSales
				grandTotalSales += sessionSales;

				// Set sessionSales to 0
				sessionSales = 0;

				// Open the sales grand total file and write the new salesGrandTotal amount to
				// it
				PrintWriter f = new PrintWriter(new FileWriter("salesgrandtotal.txt"));
				f.println(grandTotalSales);

				// Close the file
				f.close();

				// Open the session sales file and write the new sessionSales amount to it
				PrintWriter f2 = new PrintWriter(new FileWriter("salestotal.txt"));
				f2.println(sessionSales);

				// Close the file
				f2.close();

				// Display the updated sales information
				System.out.println("\nUpdated Sales information:");
				System.out.printf("\n%19s $%40.2f%n", "Session Sales: ", sessionSales);
				System.out.printf("%19s $%40.2f%n", "Grand Total Sales: ", grandTotalSales);

				// prompt user to press enter
				pressEnter("\nPlease press enter to continue (amount rolled over)...");
			}
			// Otherwise the user did not want to roll the amount over
			else {
				// prompt user to press enter and tell them the amount is not rolled over
				pressEnter("\nPlease press enter to continue (amount not rolled over)...");
			} // end if
		}
		// Otherwise, sessionSales was already 0, so no need to ask for roll over
		else {
			// prompt user to press enter
			pressEnter("\nPlease press enter to return to admin menu...");
		} // end if
	} // void SessionSales(void)

	/**
	 * Allows the customer to view and add multiple products to their cart to
	 * purchase
	 */
	public static void shop() throws IOException {

		// Declare/initialize variables needed
		int qtyPurchased;
		char addToCart;
		int index;
		char usrChoice;
		char[] validChars = { 'a', 's' };
		String category = "";

		// Display the title and instructions
		displaySectionTitle("Shop Tools");
		System.out.println("View and add to cart the tools that interest you! Note that a limit of " + toolLimit
				+ " tool(s) per customer is placed (you cannot");
		System.out.println(
				"buy more than that amount of tools), and you cannot buy more than the quantity available for a certain tool.");

		// Ask user if they want to shop by viewing a certain category or by viewing all
		// categories
		usrChoice = getValidChar(
				"\nWould you like to shop by viewing all products (a) or a specific category (s) (a or s)? ",
				validChars);

		// If user wants to shop by a specific category
		if (usrChoice == 's') {
			// Call the getValidCategory() method
			category = getValidCategory("\nEnter the category you wish to shop from: ");
		} // end if

		// Loop for continuously asking user to add something to their cart
		while (true) {

			// If user wants to shop by a specific category, display the category and call
			// the displayByCategory() method
			if (usrChoice == 's') {
				System.out.println("\nCategory chosen to shop from: " + category);
				index = displayByCategory(true, "\nEnter the number of the product you wish to view (0 to exit): ",
						category);
			}
			// otherwise, user wants to shop by all categories
			else {
				// call the displayAllCategories() method
				index = displayAllCategories(true, "\nEnter the number of the product you wish to view (0 to exit): ");
			} // end if

			// If index is -1, user wants to exit, break out of loop
			if (index == -1) {
				break;
			} // end if

			// if no products are found
			if (index == -2) {

				// Display a message that tells user that no products were found and call the
				// pressEnter() method, break out of loop
				System.out.println("\nSorry, no products found.");
				pressEnter("\nPlease press enter to continue...");
				break;
			}
			// Otherwise, products are found and user selected one
			else {

				// Display the product in expanded form
				System.out.println("\nThe product you are viewing is:");
				System.out.println(products.get(index).displayExpandedCustomer());

				// Check if user has not exceeded the limit and the product is not out of stock
				if (products.get(index).getQuantity() > 0 && (toolLimit - itemsPurchased) > 0) {

					// Ask user if they would like to add this product to their cart
					addToCart = getValidChar("\nWould you like to add this item to your cart (y or n)? ");

					// If they want to add the product to their cart
					if (addToCart == 'y') {

						// ask the user for the quantity; pass in (1 - lowest, and minimum of (quantity
						// of product, space remaining to reach limit) - highest) into getValidVal()
						// method
						qtyPurchased = getValidVal("\nEnter the quantity of the product: ", 1,
								Integer.min(products.get(index).getQuantity(), toolLimit - itemsPurchased));

						// Increment items purchased by the quantity of the product the user wants
						itemsPurchased += qtyPurchased;

						// Set the quantity of the Product object to the quantity the user entered
						products.get(index).setQtyPurchased(qtyPurchased);

						// Add the selected Product object to the cart ArrayList
						cart.add(products.get(index));

						// Prompt user to press enter
						pressEnter("\nPlease press enter to continue (item added to cart)...");
					}
				}
				// Otherwise, the product is out of stock or the user has reached the tool limit
				else {

					// The following if structure determines what the issue is for not being able to
					// add a product to cart and displays the issue
					// to the customer
					if (products.get(index).getQuantity() == 0 && (toolLimit - itemsPurchased) == 0) {
						System.out.println(
								"\nSorry, you would not be able to add this item to your cart since your cart is full and this item is sold out.");
					} else if (products.get(index).getQuantity() == 0) {
						System.out.println(
								"\nSorry, you would not be able to add this item to your cart - it is currently sold out.");
					} else {
						System.out.println(
								"\nSorry, you would not be able to add this item to your cart since you reached your limit of "
										+ toolLimit + " tool(s).");
					} // end if

					// Prompt user to press enter
					pressEnter("\nPlease press enter to continue...");

				} // end if
			} // end if
		} // end while
	} // void shop(void)

	/**
	 * Allows user to view and edit their cart
	 */
	public static void viewAndEditCart() {

		// Declare variables needed
		int index;
		char remove;
		char edit;

		// Display title and instructions
		displaySectionTitle("View and Edit Cart");
		System.out.println(
				"Here you can view all the items you have added to your cart as well as their quantities in your cart.");
		System.out.println(
				"Remember when editing quantities, you have to stay within the tool limit placed and you cannot buy more");
		System.out.println("than the quantity of a certain tool.");

		// Loop for continuously asking user to select an item in their cart to delete
		// or edit the quantity of
		while (true) {

			// Display the number of items in the cart and the space remaining in cart
			System.out.println("\nTotal Items in Cart: " + itemsPurchased);
			System.out.println("Space available in cart: " + (toolLimit - itemsPurchased));

			// Prompt user to press enter
			pressEnter("Please press enter to continue...");

			// Declare variables needed to keep track of which item the user selects
			int count = 0;
			int[] spot = new int[cart.size()];

			// Display the appropriate table header
			displayHeaders(4);

			// Iterate through the cart ArrayList
			for (int i = 0; i < cart.size(); i++) {

				// Store the index in the spot array
				spot[count] = i;
				// increment count by 1
				count++;
				// Display the minimized version of the product in the cart
				System.out.println(cart.get(i).displayMinCustomer(count, true));
			} // end for

			// Call the obtainValidSelection() method to obtain a valid index/number of
			// which item the user wants to select
			index = obtainValidSelection("\nEnter the number of the item you wish to view/edit/remove (0 to exit): ",
					spot, count);

			// If user wants to exit, break out of loop
			if (index == -1) {
				break;
			} // end if

			// If no products were found, tell the user that they have no items in their
			// cart, prompt them to press enter, and break out of loop
			if (index == -2) {
				System.out.println("\nCurrently, there are no items in your cart...");
				pressEnter("\nPlease press enter to continue...");
				break;
			}
			// Otherwise, products were found and user selected one
			else {

				// Display the expanded version of the chosen product
				System.out.println("\nThe item you are viewing is:");
				System.out.println(cart.get(index).displayExpandedCustomer());
				// Display the quantity of the product in cart
				System.out.println("\nQuantity of this item in your cart: " + cart.get(index).getQtyPurchased());

				// Ask if user wants to remove the product from the cart
				remove = getValidChar("\nWould you like to remove this item from you cart completely (y or n)? ");

				// If user wants to remove the product from the cart
				if (remove == 'y') {

					// Decrement itemsPurchased by the quantity of that product in the cart
					itemsPurchased -= cart.get(index).getQtyPurchased();
					// Set the quantity in cart (qtyPurchased) to 0 for that product
					cart.get(index).setQtyPurchased(0);
					// remove the item from the cart
					cart.remove(index);
					// Prompt user to press enter
					pressEnter("\nPlease press enter to continue (item removed from cart)...");
				}
				// Otherwise user doesn't want to remove
				else {

					// Ask user if they want to edit the quantity of the item
					edit = getValidChar("\nWould you like to edit the quantity of this item (y or n)? ");

					// If user wants to edit the quantity
					if (edit == 'y') {

						// Store the current quantity of item in cart in prevQty
						int prevQty = cart.get(index).getQtyPurchased();
						// Get the new quantity of product in the cart from user (1 - lowest, minimum of
						// the object's qty and the sum of the space remaining and qty of product in
						// cart - highest)
						int newQty = getValidVal("\nEnter changed quantity of this item: ", 1,
								Integer.min(cart.get(index).getQuantity(),
										toolLimit - itemsPurchased + cart.get(index).getQtyPurchased()));
						// Set the quantity of the product in cart to the newQty
						cart.get(index).setQtyPurchased(newQty);
						// Increment itemsPurchased by the difference between newQty and prevQty
						itemsPurchased += (newQty - prevQty);
						// Prompt user to press enter
						pressEnter("\nPlease press enter to continue (quantity updated)...");
					} // end if
				} // end if
			} // end if
		} // end while
	} // void viewAndEditCart(void)

	/**
	 * Allows user to pay and view their receipt for the items they have added to
	 * their cart
	 * 
	 * @return boolean - true if user has items in cart and wants to pay and exit,
	 *         false in any other case
	 */
	public static boolean checkOut() throws IOException {

		// Declare variables needed
		boolean returnVal = false;
		double currAmount;
		double total = 0;
		char proceed;
		String popupDisplay;

		// Display title and instructions
		displaySectionTitle("Pay and Check-Out");
		System.out.println("Here you will be able to pay for the items you have added to your cart.");

		// If there are items in cart
		if (cart.size() > 0) {

			// ask user if they want to pay and exit
			proceed = getValidChar("\nAre you sure you want to pay/checkout and exit (y or n)? ");

			// If they want to pay and exit
			if (proceed == 'y') {

				// set returnVal to true
				returnVal = true;

				// Display the receipt titles
				System.out.println("\nYour receipt is displayed below:");
				System.out.println("\nMurtaza's Tool Depot");
				System.out.println("====================");
				System.out.printf("%-70s %10s%n", "Description", "Amount ($)");
				System.out.println(
						"-------------------------------------------------------------------------------------------");

				// Iterate through the cart ArrayList
				for (int i = 0; i < cart.size(); i++) {

					// Increment total by the unit price of each product times the quantity of the
					// product in cart
					total += cart.get(i).getPrice() * cart.get(i).getQtyPurchased();
					// display the product and how many of it
					System.out.printf("%-70s %10.2f%n",
							cart.get(i).getName() + "   X   " + cart.get(i).getQtyPurchased(),
							cart.get(i).getPrice() * cart.get(i).getQtyPurchased());
					// Update the product's quantity since user bought some
					cart.get(i).setQuantity(cart.get(i).getQuantity() - cart.get(i).getQtyPurchased());
					// Set the quantity of that product in cart back to zero
					cart.get(i).setQtyPurchased(0);
				} // end if

				// clear the cart ArrayList
				cart.clear();

				// Display the receipt
				System.out.printf("%-70s %10s%n", "", "__________");
				System.out.printf("%-70s %10.2f%n", "Subtotal", total);
				System.out.printf("%-70s %10.2f%n", "Tax", total * 0.13);
				System.out.printf("%-70s %10s%n", "", "__________");
				System.out.printf("%-70s %10.2f%n", "Total", total + (total * 0.13));

				// Open the session sales text file and store the amount in that file to a
				// variable
				BufferedReader salesTotal = new BufferedReader(new FileReader("salestotal.txt"));
				currAmount = Double.parseDouble(salesTotal.readLine());
				salesTotal.close();

				// Open the session sales text file and update the amount
				PrintWriter salesTotalUpdt = new PrintWriter(new FileWriter("salestotal.txt"));
				salesTotalUpdt.println(currAmount += total + (total * 0.13));
				salesTotalUpdt.close();

				// Prompt user to press enter
				pressEnter("\nPlease press enter to pay...");

				// Format the popupDisplay String and display a pop-up with a message
				popupDisplay = String.format(
						"%s\n%s\n\nPayment Successfuly Made: \n$%.2f\n\nYou bought %d tool(s) today. \nRefunds accepted within 30 days of purchase.\nThank you, and come back for more soon!",
						"Murtaza's Tool Depot", "=================", total * 1.13, itemsPurchased);
				displayPopup(popupDisplay);

				// Set items purchased to 0
				itemsPurchased = 0;

				// Update inventory file
				writeToFile();
			}
			// If user does not want to pay and exit yet, prompt them to press enter
			else {
				pressEnter("\nPlease press enter to continue...");
			} // end if
		}
		// Otherwise, there are no items in cart
		else {
			// Tell the user that there are no items in cart, prompt them to press enter
			System.out.println(
					"\nYou have not added any items in your cart to pay for...you may want to add some items or use the 'exit' option to exit.");
			pressEnter("\nPlease press enter to continue...");
		} // end if

		// return the returnVal
		return returnVal;
	} // boolean checkOut(void)

	/**
	 * Allows user to exit the customer mode if no items are in cart
	 * 
	 * @return boolean - true if nothing in cart (clear to exit), or false, if there
	 *         are items in cart (must use the oay option)
	 */
	public static boolean exitShop() {

		// declare variables needed
		boolean returnVal = false;

		// If cart has no items
		if (cart.size() == 0) {
			// Display a message, prompt to press enter, and break out of loop
			System.out.println("\nHave a great day, be sure to buy a tool next time!");
			pressEnter("Please press enter to completely exit...");
			returnVal = true;
		}
		// Otherwise if there are items in cart
		else {
			// Tell them they need to use the 'Pay and Checkout" option to exit, prompt to
			// press enter
			System.out.println("\nYou have items in your cart, please use the 'Checkout Option' to pay.");
			pressEnter("Please press enter to return to customer menu...");
		} // end if

		// return the returnVal
		return returnVal;

	} // boolean exitShop(void)

	/**
	 * Displays "Help" depending on where the user asks for help (main, customer, or
	 * admin)
	 * 
	 * @param num int - this determines which type of help to display (1 - main, 2 -
	 *            Admin, 3 - customer)
	 */
	public static void help(int num) {

		// Initialize variable needed
		String help = "Help" + "\n====";

		// The following if structure determines which part of the program help is
		// needed in and sets the variable 'help' to the appropriate
		// instructions based on which part help is needed in
		if (num == 1) {

			help += "\n\nCurrently, you are presented with 4 main options - Administrative Mode, Customer Mode, Close Program, and Help."
					+
					"\n\nSimply choose the option that suits your purposes. If you are a customer please select the 'Customer Mode'."
					+
					"\n\nIf you are an administrator wanting to make changes, select the 'Admin Mode', and then you will be prompted"
					+
					"\nfor a password to gain entry into Admin Mode (you have 3 tries to enter the correct password)." +
					"\n\nIf you simply want to close the program, select the 'Close Program' option." +
					"\n\nYou are cuurently in 'Help'." +
					"\n\nTo select the option, simply enter the number beside the option.";
		} else if (num == 2) {
			help += "\n\nCurrently you are in the 'Administrative Mode'." +
					"\n\nOption 1 - It allows you to change the password required to gain entry into Admin mode (there are some rules you"
					+
					"\nwould have to follow - specified in that option)." +
					"\n\nOption 2 - Allows you to change the limit of tools per customer depending on an event (must be between 1 - 100)."
					+
					"\n\nOption 3 - Allows you to view all your products (by all categories or by a specifc category) and it also allows"
					+
					"\nyou to make changes/edit any of the details of the selected product (brand, price, quantity, etc)."
					+
					"\n\nOption 4 - This option simply asks you all the information about a product and allows you to create a new product."
					+
					"\n\nOption 5 - This option allows you to select a product from a list (if any found) and it deletes the selected product"
					+
					"\nfrom the inventory." +
					"\n\nOption 6 - This option simply displays all the products or the products of a specified category with all the remaining"
					+
					"\nquantities and displays whether or not the product should be ordered. (JIT (Just-In-Time) Inventory Monitor)"
					+
					"\n\nOption 7 - This option displays the dollar amount in the register since the last time the manager/supervisor collected"
					+
					"\nall the money from it. It also displays the total dollar value of all the sales that took place since this program was"
					+
					"\ninstalled. It also asks you whether or not you would like to transfer the session sales amount over to the grand total"
					+
					"\namount (basically if you would like to collect all the money from the register)." +
					"\n\nOption 8 - Help (where you currently are - a place of guidance)." +
					"\n\nOption 9 - This allows you to exit and sign-out of the admin mode and the program returns to the very main menu.";
		} else {
			help += "\n\nCurrently you are in the 'Customer Mode'." +
					"\n\nOption 1 - This allows you to browse through all our tools or all the tools in a specified category. You can view the"
					+
					"\nproducts and you can add them to your cart if you wish to do so. Note that you have a limit of 10 items to buy and you"
					+
					"\nalso cannot buy more than the quantity of a certain product." +
					"\n\nOption 2 - This option allows you to view all the the products in your cart (the ones you have selected to potentially"
					+
					"\npurchase). In this option, you can remove products from your cart or edit the quantity of the products in your cart."
					+
					"\n\nOption 3 - Help - where you currently are" +
					"\n\nOption 4 - This option allows you to checkout and pay for the products you purchased, and your receipt will be displayed."
					+
					"\n\nOption 5 - If you did not buy anything - you do not need to pay, so simply select this option to exit if you did not add"
					+
					"\nanything to your cart.";
		} // end if

		// Display a pop-up of the instructions or "Help"
		displayPopup(help);
	}

	// MAIN PROGRAM //

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// Declare and initialize str to the ASCII art title (use unicodes)
		// The ASCII art was generated using the following website:
		// https://fsymbols.com/generators/carty/
		String str = """
				\u2588\u2588\u2588\u2557\u2591\u2591\u2591\u2588\u2588\u2588\u2557\u2588\u2588\u2557\u2591\u2591\u2591\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2557\u2591\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2003\u2003\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2591\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2557\u2591\u2591\u2591\u2591\u2591  \u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2591\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557
				\u2588\u2588\u2588\u2588\u2557\u2591\u2588\u2588\u2588\u2588\u2551\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u255A\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u255A\u2550\u2550\u2550\u2550\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u255A\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u2003\u2003\u255A\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2551\u2591\u2591\u2591\u2591\u2591  \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u255A\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255D
				\u2588\u2588\u2554\u2588\u2588\u2588\u2588\u2554\u2588\u2588\u2551\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2588\u2554\u2550\u255D\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551\u2591\u255A\u255D\u255A\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2003\u2003\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2551\u2591\u2591\u2591\u2591\u2591  \u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2557\u2591\u2591\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591
				\u2588\u2588\u2551\u255A\u2588\u2588\u2554\u255D\u2588\u2588\u2551\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u255D\u2591\u2591\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2551\u2591\u2591\u2591\u2591\u255A\u2550\u2550\u2550\u2588\u2588\u2557\u2003\u2003\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2551\u2591\u2591\u2591\u2591\u2591  \u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u255D\u2591\u2591\u2588\u2588\u2554\u2550\u2550\u2550\u255D\u2591\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591
				\u2588\u2588\u2551\u2591\u255A\u2550\u255D\u2591\u2588\u2588\u2551\u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2551\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2003\u2003\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591\u255A\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u255A\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2551\u2591\u2591\u2591\u2591\u2591\u255A\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2591\u2591\u2591\u2588\u2588\u2551\u2591\u2591\u2591
				\u255A\u2550\u255D\u2591\u2591\u2591\u2591\u2591\u255A\u2550\u255D\u2591\u255A\u2550\u2550\u2550\u2550\u2550\u255D\u2591\u255A\u2550\u255D\u2591\u2591\u255A\u2550\u255D\u2591\u2591\u2591\u255A\u2550\u255D\u2591\u2591\u2591\u255A\u2550\u255D\u2591\u2591\u255A\u2550\u255D\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D\u255A\u2550\u255D\u2591\u2591\u255A\u2550\u255D\u2591\u2591\u2591\u255A\u2550\u2550\u2550\u2550\u2550\u255D\u2591\u2003\u2003\u2591\u2591\u2591\u255A\u2550\u255D\u2591\u2591\u2591\u2591\u255A\u2550\u2550\u2550\u2550\u255D\u2591\u2591\u255A\u2550\u2550\u2550\u2550\u255D\u2591\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D  \u255A\u2550\u2550\u2550\u2550\u2550\u255D\u2591\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D\u255A\u2550\u255D\u2591\u2591\u2591\u2591\u2591\u2591\u255A\u2550\u2550\u2550\u2550\u255D\u2591\u2591\u2591\u2591\u255A\u2550\u255D\u2591\u2591\u2591
								""";

		// Declare variables needed
		int usrChoice;
		int usrChoiceAdmin;
		int usrChoiceCustomer;

		// Call the populateList() method to populate the products ArrayList with data
		populateList();

		// Loop for continuously coming back to the main menu
		while (true) {

			// Initialize toolLimit by calling the getToolLimit() method
			getToolLimit();

			// Display ASCII art and the Main Menu
			System.out.println(
					"\n\n_____________________________________________________________________________________________________________________________________________________");
			System.out.println("\n" + str);
			System.out.println("Choose an option from the Main Menu below to get started.");

			System.out.println("\nMain Menu:");
			System.out.println("1 - Administrative Mode");
			System.out.println("2 - Customer Mode");
			System.out.println("3 - Close Program");
			System.out.println("4 - Help");

			// Ask user for which option they want
			usrChoice = getValidVal("\nEnter your menu choice: ", 1, 4);

			// If user selected one (Admin Mode)
			if (usrChoice == 1) {

				// Call checkPassword() method to ask for admin password, and only continue into
				// Admin mode if password is correct (checkPassword() returns true)
				if (checkPassword()) {

					// Loop for continuously returning to the Admin Menu
					while (true) {

						// Display the title and instructions
						displaySectionTitle("Administrative Mode");
						System.out.println(
								"This mode will allow you to change and view product details and inventory. Select the");
						System.out.println("option that suits your purposes from the Admin Menu below.");

						// Display the Admin Menu
						System.out.println("\n\nAdmin Menu:");
						System.out.println("1 - Change Admin Password");
						System.out.println("2 - Change Tool Limit");
						System.out.println("3 - View / Edit Products");
						System.out.println("4 - Add Products");
						System.out.println("5 - Delete Products");
						System.out.println("6 - View JIT Inventory Monitor");
						System.out.println("7 - Session Sales Amount");
						System.out.println("8 - Help");
						System.out.println("9 - Exit / Signout");

						// Ask user which option the want
						usrChoiceAdmin = getValidVal("\nEnter your choice: ", 1, 9);

						// The following if structure calls the appropriate methods depending on which
						// option the user selected
						if (usrChoiceAdmin == 1) {
							changePassword();
						} else if (usrChoiceAdmin == 2) {
							changeLimit();
						} else if (usrChoiceAdmin == 3) {
							editProduct();
						} else if (usrChoiceAdmin == 4) {
							addProduct();
						} else if (usrChoiceAdmin == 5) {
							deleteProduct();
						} else if (usrChoiceAdmin == 6) {
							displayJIT();
						} else if (usrChoiceAdmin == 7) {
							sessionSales();
						} else if (usrChoiceAdmin == 8) {
							help(2);
						} else {
							displayPopup("Sign-out Successful!");
							break;
						} // end if
					} // end while
				} // end if
			}
			// If user selected 2 (Customer Mode)
			else if (usrChoice == 2) {

				// Display a welcome GUI/pop-up
				createWelcomeFrame();

				// Loop for continuously coming back back to customer menu
				while (true) {

					// Display title and instructions
					displaySectionTitle("Customer Mode");
					System.out.println("Welcome to Murtaza's Tool Depot!");
					System.out.println(
							"You can buy a variety of hammers, saws, pliers, and screw drivers. Note that we limit");
					System.out.println(
							"the number of tools per customer depending on different situations (sales, clearout, etc).");
					System.out.println("Choose an option that suits your purposes from the customer menu below.");
					System.out.println("\nCurrent limit: " + toolLimit + " tools(s) per customer.");

					// Display customer menu
					System.out.println("\n\nCustomer Menu:");
					System.out.println("1 - View / Shop Tools");
					System.out.println("2 - View / Edit Your Cart");
					System.out.println("3 - Help");
					System.out.println("4 - Pay and Checkout");
					System.out.println("5 - Exit (if nothing in cart)");

					// Ask user which option they want
					usrChoiceCustomer = getValidVal("\nEnter your choice: ", 1, 5);

					// The following if structure calls the appropriate methods depending on which
					// option the user selected
					if (usrChoiceCustomer == 1) {
						shop();
					} else if (usrChoiceCustomer == 2) {
						viewAndEditCart();
					} else if (usrChoiceCustomer == 3) {
						help(3);
					} else if (usrChoiceCustomer == 4) {
						if (checkOut()) {
							break;
						} // end if
					} else {
						if (exitShop()) {
							break;
						} // end if
					} // end if
				} // end while
			}
			// If user wants "Help", call the help() method and pass in the appropriate
			// value
			else if (usrChoice == 4) {
				help(1);
			}
			// Else, user wants to close the program, so break out of loop
			else {
				break;
			} // end if
		} // end while

		// Display ending remarks
		System.out.println(
				"\n\n________________________________________________________________________________________________________________________________________________\n");
		System.out.println("Exiting Murtaza's Tool Depot...");
		System.out.println("Goodbye, have a great day!");
		System.out.println("Designed by Murtaza Shah");

		// Terminate the program
		System.exit(0);
	} // end main
} // end class (MyStoreSHAH)