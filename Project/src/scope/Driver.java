package scope;

import java.util.Scanner;

import scope.more.Market;
import scope.more.StoreDirectory;

/*
  This project is doing by:
    1. Kalabe Kebede
    2.Rahel Teklu
 */
public class Driver {
	private Scanner sc = new Scanner(System.in);
	private String userID;
	private StoreDirectory directory;

	final String CONTINUE = "Review more options? (Y/N)";
	final String line = System.getProperty("line.separator");

	public static void main(String[] args){
		new Driver();
	}
	public Driver(){
		boolean passWord;
		System.out.println("What is your Id? (An Id is a 3-digit code, like 009.)");
		String patter = "[\\d]{3}";
		String userId = sc.nextLine();
		passWord = userId.matches(patter);
           while (!passWord) {
			   System.out.println("You enter wrong password the password must be 3 digit number  pleas try again!");
			   patter = "[\\d]{3}";
			   userId = sc.nextLine();
			   passWord = userId.matches(patter);
		   }
		this.userID = userId;
		directory = new StoreDirectory(this.userID);
		String inputString = "Y";
		String outputString = null;
		displayOptions();
		while(!inputString.equalsIgnoreCase("N")){
			inputString = sc.nextLine();

			if(inputString.equalsIgnoreCase("A")){
				displayNumberOfBooks();
			}
			else if(inputString.equalsIgnoreCase("B")){
				displayNumberOfEmployees();
			}
			else if(inputString.equalsIgnoreCase("C")){
				boolean flag;
				outputString = "In Input Area, type in the employee number using this format:"+line+
							   "   add employee xxx"+line+
							   "where 'xxx' is the 3-digit code for the employee number.";
				//...
				System.out.println(outputString);

				String pattern = "[\\d]{3}";
				String userIdtaken = sc.nextLine();
				flag = userIdtaken.matches(pattern);
				while(!flag) {
					System.out.println("You must enter a digit with three length pleas!");
					pattern = "[\\d]{3}";
					userIdtaken = sc.nextLine();
					flag = userIdtaken.matches(pattern);
				}


				addEmployee(userIdtaken);


			}
			else if(inputString.equalsIgnoreCase("D")){
             boolean flag;
				outputString = "In Input Area, type in the book id number using this format:"+line+
				   "   book xxx"+line+
				   "where 'xxx' is the 3-digit code for the book number.";
				System.out.println(outputString);
				String pattern = "[\\d]{3}";
				String userIdtaken = sc.nextLine();
				flag = userIdtaken.matches(pattern);
				while(!directory.bookIsInStock(userIdtaken)) {
					System.out.println("You enter wrong ID pleas enter the right book ID number" +
							" a digit with three length pleas!");
					pattern = "[\\d]{3}";
					userIdtaken = sc.nextLine();
					flag = userIdtaken.matches(pattern);
				}

				checkIfBookIsInStock(userIdtaken);


			}
			else if(inputString.equalsIgnoreCase("E")){
            boolean flag;
				outputString = "In Input Area, type in the book number using this format:"+line+
				   "   add book xxx"+line+
				   "where 'xxx' is the 3-digit code for the book number.";
				System.out.println(outputString);
				String pattern = "[\\d]{3}";
				String userIdtaken = sc.nextLine();
				flag = userIdtaken.matches(pattern);
				while(!flag) {
					System.out.println("You must enter a  digit with three length pleas!");
					pattern = "[\\d]{3}";
					userIdtaken = sc.nextLine();
					flag = userIdtaken.matches(pattern);
				}


				addBook(userIdtaken);

			}

			else if(inputString.equalsIgnoreCase("H")){
				outputString = "In Input Area, type in the name of the food item using this format:"+line+
				   "   food nn..."+line+
				   "where 'nn...' represents the name of the food item (like bananas or cherries).";
				System.out.println(outputString);
				String foodItem = sc.nextLine();
				while (!directory.marketCarriesFoodItem(foodItem)) {
					System.out.println("Sorry we don't have this item thank you!");
					foodItem= sc.nextLine();
				}
				checkWhetherFoodItemInMarket(foodItem);
			}
			else if(inputString.trim().startsWith("add employee")){  //add employee

				int len = "add employee".length();
				addEmployee(inputString.substring(len).trim());
			}
			else if(inputString.trim().startsWith("add book")){  //add book
				int len = "add book".length();
				addBook(inputString.substring(len).trim());
			}
			else if(inputString.trim().startsWith("book")){
				int len = "book".length();
				checkIfBookIsInStock(inputString.substring(len).trim());
			}
			else if(inputString.trim().startsWith("food")){
				int len = "food".length();
				checkWhetherFoodItemInMarket(inputString.substring(len).trim());
			}
			else if(inputString.trim().equalsIgnoreCase("Y")){
				displayOptions();
			}
			else if(inputString.trim().equalsIgnoreCase("N")){
				System.out.println("Have a nice day! Bye!");
				System.exit(0);
			}
			else {
				System.out.println(line+"I did not understand your response."+line);
				displayOptions();
			}
		}

	}

	//this implementation has been done for you
	void displayNumberOfBooks() {
		int numbooks = directory.getNumberOfBooks();
		if(numbooks > -1){
			System.out.println("Number of books is: "+ numbooks +" (Look for other console messages.)"+line+CONTINUE);
		}

	}
	void displayNumberOfEmployees() {
		//implement -- redo the implementation
		int numEmp = directory.getNumberOfBookstoreEmployees();
		if (numEmp > -1) {
			System.out.println("Number of employees is: " + numEmp + " (Look for other console messages.)" + line + CONTINUE);
		}
	}
	void addEmployee(String employeeId){
		//implement-- redo the implementation
		//call the corresponding method on StoreDirectory Class
		boolean  newEmployeeId = directory.addNewEmployee(employeeId);
		if (newEmployeeId==true)
		System.out.println("The new employee id you add is= "+ newEmployeeId + line+CONTINUE);
	}
	void addBook(String bookId){
		//implement -- redo the implementation
		boolean newBookId = directory.addNewBook2(bookId);
		if (newBookId==true)
		System.out.println("The new book id you add is=  "+ newBookId + line+CONTINUE);
	}
	void checkIfBookIsInStock(String bookId){
		//implement -- redo the implementation
		boolean isBookInStock= directory.bookIsInStock(bookId);
		if (isBookInStock==true)
		System.out.println("Yes, book with bookId "+bookId+" is in stock." +" (Look for console messages.)"+line+CONTINUE);
	}

	void checkWhetherFoodItemInMarket(String foodItem){
		//	implement-- redo the implementation
  directory.marketCarriesFoodItem(foodItem);
		System.out.println("Yes the market carries "+foodItem+line+CONTINUE);
	}

	void displayOptions(){
		String display = "TYPE A LETTER IN INPUT AREA TO MAKE A SELECTION"+line+line+
						 "A. Learn how many books are in bookstore."+line+
						 "B. Learn how many employees are in bookstore."+line+
						 "C. Add an employee to the bookstore."+line+
						 "D. See if a book is in stock in the bookstore."+line+
						 "E. Add a book to the bookstore."+line+
						 "H. See if the market carries a particular food item."+line+
						 "N. Exit the system.";
		System.out.println(display);
	}

}