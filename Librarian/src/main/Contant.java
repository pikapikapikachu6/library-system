package main;

public class Contant {
	public static final String HELP_MSG = "EXIT ends the library process\n" +
										"COMMANDS outputs this help string\n\n" +
										"LIST ALL [LONG] outputs either the short or long string for all books\n" +
										"LIST AVAILABLE [LONG] outputs either the short of long string for all available books\n" +
										"NUMBER COPIES outputs the number of copies of each book\n" +
										"LIST GENRES outputs the name of every genre in the system\n" +
										"LIST AUTHORS outputs the name of every author in the system\n\n" +
										"GENRE <genre> outputs the short string of every book with the specified genre\n" +
										"AUTHOR <author> outputs the short string of every book by the specified author\n\n" +
										"BOOK <serialNumber> [LONG] outputs either the short or long string for the specified book\n" +
										"BOOK HISTORY <serialNumber> outputs the rental history of the specified book\n\n" +
										"MEMBER <memberNumber> outputs the information of the specified member" +
										"MEMBER BOOKS <memberNumber> outputs the books currently rented by the specified member\n" +
										"MEMBER HISTORY <memberNumber> outputs the rental history of the specified member\n\n" +
										"RENT <memberNumber> <serialNumber> loans out the specified book to the given member\n" +
										"RELINQUISH <memberNumber> <serialNumber> returns the specified book from the member\n" +
										"RELINQUISH ALL <memberNumber> returns all books rented by the specified member\n\n" +
										"ADD MEMBER <name> adds a member to the system\n" +
										"ADD BOOK <filename> <serialNumber> adds a book to the system\n\n" +
										"ADD COLLECTION <filename> adds a collection of books to the system\n" +
										"SAVE COLLECTION <filename> saves the system to a csv file\n\n" +
										"COMMON <memberNumber1> <memberNumber2> ... outputs the common books in members’ history\n";
	
	
}
