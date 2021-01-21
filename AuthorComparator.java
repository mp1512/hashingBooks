package hashingBooks;

import java.util.Comparator;

public class AuthorComparator implements Comparator<Book>{
	public int compare(Book book1, Book book2){
		//comparison is based on the book's author
		return (book1.getAuthor().compareTo(book2.getAuthor()));
				
	}
	public boolean equals(Book book1, Book book2){
		return (book1.getAuthor().compareTo(book2.getAuthor())==0);
	}

}
