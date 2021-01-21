package hashingBooks;

import java.util.Comparator;

public class TitleComparator implements Comparator<Book>{
	public int compare(Book a, Book b){
		return a.getTitle().compareTo(b.getTitle());
	}
    public boolean equals(Book a, Book b){
    	return (a.getTitle().compareTo(b.getTitle())==0);
    }
}
