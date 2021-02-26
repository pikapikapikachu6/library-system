package main;

import java.util.ArrayList;
import java.util.List;

public class Member  {
	private String name;
	private String memberNumber;
	private List<Book> rentHistory;
	private List<Book> currentRent;
	private static Library library;

	public Member(String name, String memberNumber) {
		this.name = name;
		this.memberNumber = memberNumber;
		this.rentHistory = new ArrayList<Book>();
		this.currentRent = new ArrayList<Book>();
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", memberNumber=" + memberNumber + ", rentHistory=" + rentHistory
				+ ", currentRent=" + currentRent + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public List<Book> getRentHistory() {
		return rentHistory;
	}

	public void setRentHistory(List<Book> rentHistory) {
		this.rentHistory = rentHistory;
	}

	public List<Book> getCurrentRent() {
		return currentRent;
	}

	public void setCurrentRent(List<Book> currentRent) {
		this.currentRent = currentRent;
	}

	public static Library getLibrary() {
		return library;
	}

	public static void setLibrary(Library library) {
		Member.library = library;
	}
	
	public static void LibraryIntro(Library library) {
		if (Member.library == null) {
			Member.library = library;
		}
	}

	public boolean rent(Book book) {
		if (book == null) {
			return false;
		}
		if (book.isRented() == true) {
			return false;
		}
		book.rent(this);
		this.currentRent.add(book);
		return true;
	}

	public boolean relinquish(Book book) {
		if (book == null) {
			return false;
		}
		if (book.getRenter() != this) {
			return false;
		}
		if (library.getBooks().containsValue(book) == false) {
			return false;
		}
		int result = -1;
		for (int i = 0; i < currentRent.size(); i++) {
			if (currentRent.get(i).getSerialNumber().equals(book.getSerialNumber())) {
				result = i;
			}
		}
		this.rentHistory.add(book);
		book.relinquish​(this);
		currentRent.remove(result);
		return true;
	}

	public void relinquishAll() {
		for (int i = 0; i < currentRent.size(); i++) {
			currentRent.get(i).relinquish​(this);
			this.rentHistory.add(currentRent.get(i));
		}
		currentRent = new ArrayList<>();
	}

	public List<Book> history() {
		//List<Book> needReturn = new ArrayList<Book>();
		//for (int i = 0; i < getRentHistory().size(); i++) {
			//if (needReturn.contains(getRentHistory().get(i)) == false) {
				//needReturn.add(getRentHistory().get(i));
			//}
		//}
		return getRentHistory();
	}

	public List<Book> renting() {
		return getCurrentRent();
		
	}

	public static List<Book> commonBooks​(Member[] members) {
		if (members == null) {
			return null;
		}
		return null;
	}
}
