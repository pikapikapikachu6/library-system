package main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Library {
	public static HashMap<String, Member> members = new HashMap<>();
	public static HashMap<String, Book> books = new HashMap<>();

	public Library() {
		// this.members = new HashMap<>();
		// this.books = new HashMap<>();
	}

	public HashMap<String, Member> getMembers() {
		return members;
	}

	public void setMembers(HashMap<String, Member> members) {
		this.members = members;
	}

	public HashMap<String, Book> getBooks() {
		return books;
	}

	public void setBooks(HashMap<String, Book> books) {
		Library.books = books;
	}

	private boolean containsBooks() {
		if (books == null) {
			System.out.println("No books in system");
			return false;
		}
		return true;
	}

	public void getAllBooks​(boolean fullString) {
		if (containsBooks()) {
			if (books.size() == 0) {
				System.out.println("No books in system.");
				return;
			}
			HashMap<Book, String> speciGenre = new HashMap<>();
			for (String cur : books.keySet()) {
				Book now = books.get(cur);
				String nowKey = now.getSerialNumber();
				speciGenre.put(now, nowKey);
			}
			List<Map.Entry<Book, String>> list = new ArrayList<Map.Entry<Book, String>>(speciGenre.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<Book, String>>() {
				public int compare(Map.Entry<Book, String> o1, Map.Entry<Book, String> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
			for (Map.Entry<Book, String> mapping : list) {
				if (fullString) {
					System.out.println(mapping.getKey().longString());
				} else System.out.println(mapping.getKey().shortString());
			}
		}
	}

	public void getAvailableBooks​(boolean fullString) {
		if (containsBooks()) {
			HashMap<Book, String> speciGenre = new HashMap<>();
			for (String cur : books.keySet()) {
				Book now = books.get(cur);
				if (now.isRented() == true) {
					System.out.println("No books available.");
					return;
				}
				String nowKey = now.getSerialNumber();
				speciGenre.put(now, nowKey);
			}
			List<Map.Entry<Book, String>> list = new ArrayList<Map.Entry<Book, String>>(speciGenre.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<Book, String>>() {
				public int compare(Map.Entry<Book, String> o1, Map.Entry<Book, String> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
			for (Map.Entry<Book, String> mapping : list) {
				if (fullString) {
					System.out.println(mapping.getKey().longString());
				} else System.out.println(mapping.getKey().shortString());
			}
		}
	}

	public void getCopies() {
		if (containsBooks()) {
			HashMap<Book, Integer> copies = new HashMap<>();
			for (String cur : books.keySet()) {
				Book now = books.get(cur);
				String Number = now.shortString();
				for (String curs : books.keySet()) {
					Book now1 = books.get(curs);
					if (now.getSerialNumber().equals(now1.getSerialNumber()) == false) {
						String Compare = now1.shortString();
						if (Number.equals(Compare)) {
							copies.put(now, copies.get(now) + 1);
						} else {
							if (copies.containsKey(now) == false) {
								copies.put(now, 1);
							}
						}
					}
				}
			}
			List<String> result = new ArrayList<>();
			for (Book key : copies.keySet()) {
				result.add(String.format("%s:%d", key.shortString(),copies.get(key)));
			}
			result.sort(null);
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
		}
	}

	public void getGenres() {
		if (containsBooks()) {
			List<String> allGenre = new ArrayList<String>();
			if (books.size() == 0) {
				System.out.println("No books in system.");
				return;
			}
			for (String cur : books.keySet()) {
				Book now = books.get(cur);
				String curGenre = now.getGenre();
				if (allGenre.contains(curGenre) == false) {
					allGenre.add(curGenre);
				}
			}
			Collections.sort(allGenre);
			for (int i = 0; i < allGenre.size(); i++) {
				System.out.println(allGenre.get(i));
			}
		}
	}

	public void getAuthors() {
		if (containsBooks()) {
			List<String> allAuthor = new ArrayList<String>();
			if (books.size() == 0) {
				System.out.println("No books in system.");
				return;
			}
			for (String cur : books.keySet()) {
				Book now = books.get(cur);
				String curAuthor = now.getAuthor();
				if (allAuthor.contains(curAuthor) == false) {
					allAuthor.add(curAuthor);
				}
			}
			Collections.sort(allAuthor);
			for (int i = 0; i < allAuthor.size(); i++) {
				System.out.println(allAuthor.get(i));
			}
		}
	}

	public void getBooksByGenre​(String genre) {
		if (containsBooks()) {
			HashMap<Book, String> speciGenre = new HashMap<>();
			for (String cur : books.keySet()) {
				Book now = books.get(cur);
				String curGenre = now.getGenre();
				if (curGenre.equals(genre)) {
					if (speciGenre.containsKey(now) == false) {
						speciGenre.put(now, now.getSerialNumber());
					}
				}
			}
			if (speciGenre.size() == 0) {
				System.out.println(String.format("No books with genre %s.", genre));
			} else {
				List<Map.Entry<Book, String>> list = new ArrayList<Map.Entry<Book, String>>(speciGenre.entrySet());
				Collections.sort(list, new Comparator<Map.Entry<Book, String>>() {
					public int compare(Map.Entry<Book, String> o1, Map.Entry<Book, String> o2) {
						return o1.getValue().compareTo(o2.getValue());
					}
				});
				for (Map.Entry<Book, String> mapping : list) {
					System.out.println(mapping.getKey().shortString());
				}
			}
		}
	}

	public void getBooksByAuthor​(String author) {
		if (containsBooks()) {
			HashMap<Book, String> speciAuthor = new HashMap<>();
			for (String cur : books.keySet()) {
				Book now = books.get(cur);
				String curAuthor = now.getAuthor();
				if (curAuthor.equals(author)) {
					if (speciAuthor.containsKey(now) == false) {
						speciAuthor.put(now, now.getSerialNumber());
					}
				}
			}
			if (speciAuthor.size() == 0) {
				System.out.println(String.format("No books with author %s.", author));
			} else {
				List<Map.Entry<Book, String>> list = new ArrayList<Map.Entry<Book, String>>(speciAuthor.entrySet());
				Collections.sort(list, new Comparator<Map.Entry<Book, String>>() {
					public int compare(Map.Entry<Book, String> o1, Map.Entry<Book, String> o2) {
						return o1.getValue().compareTo(o2.getValue());
					}
				});
				for (Map.Entry<Book, String> mapping : list) {
					System.out.println(mapping.getKey().shortString());
				}
			}
		}
	}

	public void getBook​(String serialNumber, boolean fullString) {
		if (containsBooks()) {
			if (books.containsKey(serialNumber) == false) {
				System.out.println("No such book in system.");
			} else {
				if (fullString) {
					System.out.println(books.get(serialNumber).longString());
				} else {
					System.out.println(books.get(serialNumber).shortString());
				}
			}
		}
	}

	public void bookHistory​(String serialNumber) {
		if (containsBooks()) {
			if (books.containsKey(serialNumber) == false) {
				System.out.println("No such book in system.");
			} else {
				if (books.get(serialNumber).renterHistory() == null) {
					System.out.println("No rental history.");
				} else {
					List<Member> output = new ArrayList<Member>();
					output = books.get(serialNumber).renterHistory();
					if (output.size() == 0) {
						System.out.println();
						return;
					}
					for (int i = 0; i < output.size(); i++) {
						System.out.println(output.get(i).getMemberNumber());
					}
				}
			}
		}
	}

	private static Book readNextLine(String nextline) {
		String[] collections = nextline.split(",");
		if (collections.length != 4) {
			return null;
		}
		return new Book(collections[1], collections[2], collections[3], collections[0]);
	}
	
	public void addBook​(String bookFile, String serialNumber) {
		File file = new File(bookFile);
		if (file.exists() == false) {
			System.out.println("No such file.");
		} else if (bookFile == null) {
			System.out.println("No such file.");
		} else if (books.containsKey(serialNumber)) {
			System.out.println("Book already exists in system.");
		} else {
			try {
				Scanner scan = new Scanner(new File(bookFile));
				scan.nextLine();
				while (scan.hasNextLine()) {
					String newLine = scan.nextLine();
					if (readNextLine(newLine) != null) {
						Book needAdd = readNextLine(newLine);
						if (needAdd.getSerialNumber().equals(serialNumber)) {
							if (books.containsValue(needAdd) == true) {
								System.out.println("Book already exists in system.");
								return;
							}
							books.put(needAdd.getSerialNumber(), needAdd);
							System.out.println(String.format("Successfully added: %s.",needAdd.shortString()));
						}
					}
				}
				scan.close();
			} catch (IOException e) {
				return;
			}
		}
	}

	public void rentBook​(String memberNumber, String serialNumber) {
		if (containsBooks()) {
			if (members == null || members.size() == 0) {
				System.out.println("No members in system.");
			} else if (members.containsKey(memberNumber) == false) {
				System.out.println("No such member in system.");
			} else if (books.containsKey(serialNumber) == false) {
				System.out.println("No such book in system.");
			} else if (books.get(serialNumber).isRented()) {
				System.out.println("Book is currently unavailable.");
			} else {
				Member needRent = members.get(memberNumber);
				Book neededRent = books.get(serialNumber);
				if (needRent.rent(neededRent)) {
					System.out.println("Success.");
				}
			}
		}
	}

	public void relinquishBook​(String memberNumber, String serialNumber) {
		if (containsBooks()) {
			if (members == null || members.size() == 0) {
				System.out.println("No members in system.");
			} else if (members.containsKey(memberNumber) == false) {
				System.out.println("No such member in system.");
			} else if (books.containsKey(serialNumber) == false) {
				System.out.println("No such book in system.");
			} else if (books.get(serialNumber).isRented() == false) {
				System.out.println("Unable to return book.");
			} else {
				Member needRelinquish = members.get(memberNumber);
				Book neededRelinquish = books.get(serialNumber);
				if (needRelinquish.relinquish(neededRelinquish)) {
					System.out.println("Success.");
				}
			}
		}
	}

	public void relinquishAll​(String memberNumber) {
		if (members == null || members.size() == 0) {
			System.out.println("No members in system.");
		} else if (members.containsKey(memberNumber) == false) {
			System.out.println("No such member in system.");
		} else {
			Member needReturnMember = members.get(memberNumber);
			needReturnMember.relinquishAll();
			System.out.println("Success.");
		}
	}

	public void getMember​(String memberNumber) {
		if (members == null || members.size() == 0) {
			System.out.println("No members in system.");
		} else if (members.containsKey(memberNumber) == false) {
			System.out.println("No such member in system.");
		} else {
			Member printMember = members.get(memberNumber);
			System.out.println(memberNumber + ":" + printMember.getName());
		}
	}

	public void getMemberBooks​(String memberNumber) {
		if (members == null) {
			System.out.println("No members in system.");
		} else if (members.containsKey(memberNumber) == false) {
			System.out.println("No such member in system.");
		} else {
			Member currentMember = members.get(memberNumber);
			List<Book> MemberRent = currentMember.renting();
			if (MemberRent.size() == 0 || MemberRent == null) {
				System.out.println("Member not currently renting.");
			} else {
				for (int i = 0; i < MemberRent.size(); i++) {
					Book currentprint = MemberRent.get(i);
					System.out.println(currentprint.shortString());
				}
			}

		}
	}

	public void memberRentalHistory​(String memberNumber) {
		if (members == null) {
			System.out.println("No members in system.");
		} else if (members.containsKey(memberNumber) == false) {
			System.out.println("No such member in system.");
		} else {
			Member currentMember = members.get(memberNumber);
			List<Book> MemberRent = currentMember.history();
			if (MemberRent == null || MemberRent.size() == 0) {
				System.out.println("No rental history for member.");
			} else {
				for (int i = 0; i < MemberRent.size(); i++) {
					Book currentprint = MemberRent.get(i);
					System.out.println(currentprint.shortString());
				}
			}
		}
	}

	public void addMember​(String name) {
		int NewNumber = members.size() + 100000;
		Member newMember = new Member(name, String.format("%s", NewNumber));
		members.put(String.format("%s", NewNumber), newMember);
		System.out.println("Success.");
	}

	public void saveCollection​(String filename) {
		if (filename == null) {
			return;
		} 
		if (books.size() == 0) {
			System.out.println("No books in system.");
		}
		File writeFile = new File(filename);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(writeFile, true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		OutputStreamWriter osw = new OutputStreamWriter(out);
		try {
			BufferedWriter writeText = new BufferedWriter(osw);
			for (String cur : books.keySet()) {
				Book s = books.get(cur);
				writeText.write(s.getSerialNumber() + s.getTitle() + s.getAuthor() + s.getGenre());
				writeText.newLine();
			}
			writeText.close();
			osw.close();
			out.close();
		} catch (IOException e) {
			return;
		}
		System.out.println("Success.");
	}
	
	private static Book readNextBook(String nextline) {
		String[] collections = nextline.split(",");
		if (collections.length != 4) {
			return null;
		}
		return new Book(collections[1], collections[2], collections[3], collections[0]);
	}

	public void addCollection​(String filename) {
		Integer total = 0;
		File file = new File(filename);
		if (file.exists() == false) {
			System.out.println("No such collection.");
		} else 
		if (filename == null) {
			System.out.println("No such collection.");
		} try {
			Scanner scan = new Scanner(new File(filename));
			scan.nextLine();
			while (scan.hasNextLine()) {
				String newLine = scan.nextLine();
				if (readNextLine(newLine) != null) {
					Book newBook = readNextBook(newLine);
					String numberSerial = newBook.getSerialNumber();
					if (books.containsKey(numberSerial) == false) {
						books.put(numberSerial, newBook);
						total ++;
					}
				}
			}
			if (total == 0) {
				System.out.println("No books have been added to the system.");
			} else {
				System.out.println(String.format("%d books successfully added.", total));
			}
			scan.close();
		} catch (IOException e) {
			return;
		} 
	}

	public void common​(String[] memberNumbers) {
		if (members == null || members.size() == 0) {
			System.out.println("No members in system.");
		} else if (books == null || books.size() == 0) {
			System.out.println("No books in system.");
		} else if (memberNumbers == null || memberNumbers.length == 0) {
			System.out.println("No common books.");
		} else {
			HashMap<Book,Integer> collect = new HashMap<>();
			List<Member> printList = new ArrayList<Member>();
			List<Book> needPut = new ArrayList<Book>();
			for (int i = 0; i < memberNumbers.length; i++) {
				if (members.containsKey(memberNumbers[i])) {
					if (printList.contains(members.get(memberNumbers[i]))) {
						System.out.println("Duplicate members provided.");
						return;
					}
					printList.add(members.get(memberNumbers[i]));
				} else {
					System.out.println("No such member in system.");
					return;
				}
			}
			HashMap<Book,String> printBook = new HashMap<>();
			HashMap<Book,Integer> collectBook = new HashMap<>();
			for (int j = 0; j < printList.size(); j++) {
				Member curMember = printList.get(j);
				needPut = curMember.history();
				if (collectBook.containsKey(needPut.get(j)) == false) {
					collectBook.put(needPut.get(j), 1);
				} else {
					collectBook.put(needPut.get(j), collectBook.get(needPut.get(j)) + 1);
				}
			}
			for (Book cur: collectBook.keySet()) {
				int number = collectBook.get(cur);
				if (number == memberNumbers.length) {
					printBook.put(cur, cur.getSerialNumber());
				}
			}
			List<Map.Entry<Book, String>> list = new ArrayList<Map.Entry<Book, String>>(printBook.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<Book, String>>() {
				public int compare(Map.Entry<Book, String> o1, Map.Entry<Book, String> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
			for (Map.Entry<Book, String> mapping : list) {
				System.out.println(mapping.getKey().shortString());
			}
		}
	}

	public static final String  HELP_STRING = "EXIT ends the library process\n" +
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
										"COMMON <memberNumber1> <memberNumber2> ... outputs the common books in members' history\n";
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Library newLibrary = new Library();
		Book.LibraryIntro(newLibrary);
		Member.LibraryIntro(newLibrary);
		while (true) {
			String input = in.nextLine();
			parseCommand(newLibrary, input.split(" "));
			//parseCommand(newLibrary, args);
		}

	}

	private static void parseCommand(Library newLibrary, String[] args) {
		if (args.length == 0) {
			System.exit(1);
		} else if (args[0].equals("EXIT")) {
			System.exit(1);
		} else if (args[0].equals("COMMANDS")) {
			System.out.println(HELP_STRING);
		} else if (args[0].equals("LIST")) {
			if (args[1].equals("ALL")) {
				if (args.length == 3) {
					newLibrary.parseListALL(args[0], args[1], args[2]);
				} else if (args.length == 2) {
					newLibrary.parseListALL(args[0], args[1], null);
				}
			} else if (args[1].equals("AVAILABLE")) {
				if (args.length == 3) {
					newLibrary.parseListAVAILABLE(args[0], args[1], args[2]);
				} else if (args.length == 2) {
					newLibrary.parseListAVAILABLE(args[0], args[1], null);
				}
			} else if (args.length == 2 && args[1].equals("GENRES")) {
				newLibrary.parseListGENRES(args[0], args[1]);
			} else if (args.length == 2 && args[1].equals("AUTHORS")) {
				newLibrary.parseListAUTHORS(args[0], args[1]);
			}
		} else if (args.length == 2 && args[0].equals("NUMBER") && args[1].equals("COPIES")) {
			newLibrary.parseNumberCopies(args[0], args[1]);
		} else if (args.length == 2 && args[0].equals("GENRE")) {
			newLibrary.parseGENRE(args[0], args[1]);
		} else if (args[0].equals("AUTHOR")) {
			String nameString = new String();
			for (int i = 1; i < args.length; i ++) {
				nameString = nameString + args[i] + " "; 
			}
			nameString = nameString.trim();
			newLibrary.parseAUTHOR(args[0], nameString);
		} else if (args[0].equals("BOOK")) {
			if (args[1].equals("HISTORY") && args.length == 3) {
				newLibrary.parseBookHISTORY(args[0], args[1], args[2]);
			} else {
				if (args.length == 3) {
					newLibrary.parseBOOK(args[0], args[1], args[2]);
				} else if (args.length == 2) {
					newLibrary.parseBOOK(args[0], args[1], null);
				}
			}
		} else if (args[0].equals("MEMBER")) {
			if (args.length == 3 && args[1].equals("BOOKS")) {
				newLibrary.parseMemberBOOK(args[0], args[1], args[2]);
			} else if (args.length == 3 && args[1].equals("HISTORY")) {
				newLibrary.parseMemberHISTORY(args[0], args[1], args[2]);
			} else if (args.length == 2) {
				newLibrary.parseMember(args[0], args[1]);
			}
		} else if (args[0].equals("RENT") && args.length == 3) {
			newLibrary.parseRENT(args[0], args[1], args[2]);
		} else if (args[0].equals("RELINQUISH") && args.length == 3) {
			if (args[1].equals("ALL")) {
				newLibrary.parseRelinquishALL(args[0], args[1], args[2]);
			} else {
				newLibrary.parseRELINQUISH(args[0], args[1], args[2]);
			}
		} else if (args[0].equals("ADD")) {
			// System.out.println("input success IN");
			// System.out.println(args.length);
			if (args[1].equals("MEMBER")) {
				String nameString = new String();
				for (int i = 2; i < args.length; i ++) {
					nameString = nameString + args[i] + " "; 
				}
				nameString = nameString.trim();
				newLibrary.parseAddMEMBER(args[0], args[1], nameString);
			} else if (args.length == 4 && args[1].equals("BOOK")) {
				newLibrary.parseAddBOOK(args[0], args[1], args[2], args[3]);
			} else if (args.length == 3 && args[1].equals("COLLECTION")) {
				// System.out.println("input success");
				newLibrary.parseAddCOLLECTION(args[0], args[1], args[2]);
			}
		} else if (args[0].equals("SAVE") && args[0].equals("COLLECTION") && args.length == 2) {
			newLibrary.parseSAVECOLLECTION(args[0], args[1], args[2]);
		} else if (args[0].equals("COMMON")) {
			newLibrary.parseCOMMON(args[0], args);
		}
	}

	private void parseListALL(String args0, String args1, String args2) {
		if (args2 != null) {
			if (args2.equals("LONG")) {
				getAllBooks​(true);
			}
		}
		else 	getAllBooks​(false);
		
	}

	private void parseListAVAILABLE(String args0, String args1, String args2) {
		if (args2 != null) {
			if (args2.equals("LONG")) {
				getAvailableBooks​(true);
			}
		}
		else 	getAvailableBooks​(false);
	}

	private void parseNumberCopies(String args0, String args1) {
		getCopies();
	}

	private void parseListGENRES(String args0, String args1) {
		getGenres();
	}

	private void parseListAUTHORS(String args0, String args1) {
		getAuthors();
	}

	private void parseGENRE(String args0, String args1) {
		getBooksByGenre​(args1);
	}

	private void parseAUTHOR(String args0, String args1) {
		getBooksByAuthor​(args1);
	}

	private void parseBookHISTORY(String args0, String args1, String args2) {
		bookHistory​(args2);
	}

	private void parseBOOK(String args0, String args1, String args2) {
		if (args2 != null) {
			if (args2.equals("LONG")) {
				getBook​(args1, true);
			}
		}
		else 	getBook​(args1, false);
	}

	private void parseMemberBOOK(String args0, String args1, String args2) {
		getMemberBooks​(args2);
	}

	private void parseMemberHISTORY(String args0, String args1, String args2) {
		memberRentalHistory​(args2);
	}

	private void parseMember(String args0, String args1) {
		getMember​(args1);
	}

	private void parseRENT(String args0, String args1, String args2) {
		rentBook​(args1, args2);
	}

	private void parseRelinquishALL(String args0, String args1, String args2) {
		relinquishAll​(args2);
	}

	private void parseRELINQUISH(String args0, String args1, String args2) {
		relinquishBook​(args1, args2);
	}

	private void parseAddMEMBER(String args0, String args1, String args2) {
		addMember​(args2);
	}

	private void parseAddBOOK(String args0, String args1, String args2, String args3) {
		addBook​(args2, args3);
	}

	private void parseAddCOLLECTION(String args0, String args1, String args2) {
		addCollection​(args2);
	}

	private void parseSAVECOLLECTION(String args0, String args1, String args2) {
		saveCollection​(args2);
	}

	private void parseCOMMON(String args0, String[] args) {
		String[] inputNumbers = new String[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			inputNumbers[i - 1] = args[i];
		}
		common​(inputNumbers);
	}

}