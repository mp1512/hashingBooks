package hashingBooks;

public class BookBorrowException extends RuntimeException{

	public BookBorrowException() {
		super("book borrow status inconsistent with request");
	}
	
	public BookBorrowException(String message) {
		super(message);
	}

}
