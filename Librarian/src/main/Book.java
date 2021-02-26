package main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Book  {
	private String title;
	private String author;
	private String genre;
	private String serialNumber;
	private Member renter;
	private boolean rented;
	private List<Member> rentHistory;
	private static Library library;

	public Book(String title, String author, String genre, String serialNumber) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.serialNumber = serialNumber;
		this.rentHistory = new ArrayList<Member>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Member getRenter() {
		return renter;
	}

	public void setRenter(Member renter) {
		this.renter = renter;
	}

	public List<Member> getRentHistory() {
		return rentHistory;
	}

	public void setRentHistory(List<Member> rentHistory) {
		this.rentHistory = rentHistory;
	}

	public static Library getLibrary() {
		return library;
	}

	public static void setLibrary(Library library) {
		Book.library = library;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public boolean getrented() {
		return rented;
	}

	public String longString() {
		if (this.renter != null) {
			return String.format("%s: %s (%s, %s)\nRented by:%s.", this.serialNumber, this.title, this.author,
					this.genre, this.renter.getMemberNumber());
		} else {
			return String.format("%s: %s (%s, %s)\nCurrently available.", this.serialNumber, this.title, this.author,
					this.genre);
		}
	}

	public String shortString() {
		return String.format("%s (%s)", this.title, this.author);
	}

	public List<Member> renterHistory() {
		if (getRentHistory() == null) {
			return null;
		}
		return getRentHistory();
	}

	public boolean isRented() {
		return getrented();
	}

	public boolean rent(Member member) {
		if (member == null || rented == true) {
			return false;
		}
		if (library == null || library.getMembers() == null) {
			return false;
		}
		if (library.getMembers().containsValue(member) == false) {
			return false;
		}
		this.rented = true;
		this.renter = member;
		this.rentHistory.add(member);
		return true;
	}

	public boolean relinquish​(Member member) {
		if (member == null) {
			return false;
		}
		if (this.renter != member) {
			return false;
		}
		if (library == null || library.getMembers() == null) {
			return false;
		}
		if (library.getMembers().containsValue(member) == false) {
			return false;
		}
		this.rented = false;
		this.renter = null;
		return true;
	}

	public static Book readBook​(String filename, String serialNumber) {
		if (filename == null || serialNumber == null) {
			return null;
		}
		if (library == null || library.getBooks() == null) {
			return null;
		}
		if (library.getBooks().containsKey(serialNumber) == false) {
			return null;
		}
		List<Book> newbooks = readBookCollection​(filename);
		if (newbooks == null) {
			return null;
		}
		for (int i = 0; i <= newbooks.size(); i++) {
			if (newbooks.get(i).getSerialNumber() == serialNumber) {
				return newbooks.get(i);
			}
		}
		return null;
	}

	private static Book readNextLine(String nextline) {
		String[] collections = nextline.split(",");
		if (collections.length != 4) {
			return null;
		}
		return new Book(collections[0], collections[1], collections[2], collections[3]);
	}

	public static List<Book> readBookCollection​(String filename) {
		if (filename == null) {
			return null;
		}
		List<Book> newbook = new ArrayList<Book>();
		try {
			Scanner scan = new Scanner(new File(filename));
			scan.nextLine();
			if (scan.hasNextLine()) {
				String newLine = scan.nextLine();
				if (readNextLine(newLine) != null) {
					newbook.add(readNextLine(newLine));
				}
			}
			scan.close();
		} catch (IOException e) {
			return null;
		}
		return newbook;
	}

	public static void saveBookCollection​(String filename, Collection<Book> books) {
		if (filename == null || books == null) {
			return;
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
			for (Book s : books) {
				writeText.write(s.getSerialNumber() + s.getTitle() + s.getAuthor() + s.getGenre());
				writeText.newLine();
			}
			writeText.close();
			osw.close();
			out.close();
		} catch (IOException e) {
			return;
		}
	}

	public static List<Book> filterAuthor​(List<Book> books, String author) {
		if (books == null || author == null) {
			return null;
		}
		List<Book> authorList = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getAuthor() == author) {
				authorList.add(books.get(i));
			}
		}

		return authorList;
	}

	public static List<Book> filterGenre​(List<Book> books, String genre) {
		if (books == null || genre == null) {
			return null;
		}
		List<Book> genreList = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getGenre() == genre) {
				genreList.add(books.get(i));
			}
		}
		return genreList;
	}

	public static void LibraryIntro(Library library) {
		if (Book.library == null) {
			Book.library = library;
		}
	}
}