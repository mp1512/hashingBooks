package hashingBooks;
import java.io.Serializable;
import java.time.LocalDate;




public class LibraryBook extends Book implements Comparable< Book>, Serializable {
	private static final long serialVersionUID = 10L;
	private String copyID;
	private String borrowerID;
	private LocalDate dateBorrowed;
	
	public LibraryBook(String copyID,String t, String a, String id){
		super(t, a, id);  //call superclass constructor
		this.copyID = copyID;
		borrowerID = null;
		dateBorrowed = null;
		
	}
	public LibraryBook(String copyID,String t, String a, String id,Double p,Double c){
		super(t,a,id,0.0,c);
		this.copyID = copyID;
		borrowerID = null;
		
	}
	public void setPrice(double p)throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	//hide setPrice and getPrice since LibraryBook doesn't have a price  
	
    public Double getPrice() throws UnsupportedOperationException{
    	throw new UnsupportedOperationException();
    }
    
    public void setBorrower(String id)throws BookBorrowException{
    	if (borrowerID !=null)
    		throw new BookBorrowException ("book is checked out");
    	else
    		borrowerID = id;
    }
    
    public void resetBorrower()throws BookBorrowException{
    	if (borrowerID == null) 
    		throw new BookBorrowException("book is not checked out");
    	else
    		borrowerID = null;
    }
    
    public String getBorrower() {return borrowerID;}
    public String getCopyID() {return copyID;}
    
    public String toString(){
    	String info = super.toString();
    	info += " Copy ID " + copyID;
    	info += " Borrower: " + borrowerID;
    	return info;
    }
    
    @Override    //must have same argument signature
    public int compareTo(Book lb) {
    	if (lb instanceof LibraryBook) {
    		LibraryBook temp = (LibraryBook)lb;
    	if (this.getISBN().equals(lb.getISBN())) {
    		return this.copyID.compareTo(temp.getCopyID());
    	}
    	else {
    		return this.getISBN().compareTo(temp.getISBN());
    	}
    	}
    	else {
    		return super.compareTo(lb);
    	}
    }
    
    @Override   //equality may be determined by copyid or isbn
    public boolean equals (Object o) {
    	if (o == null) {
    		return false;
    	}
    	else {
    		if (o instanceof LibraryBook) {
    			LibraryBook temp = (LibraryBook)o;   //typecast the book
    			return this.copyID.equals(temp.getCopyID());
    		}
    		else {
    			if (o instanceof Book) {
    				Book temp = (Book)o;
    				return this.getISBN().equals(temp.getISBN());
    			}
    			else {
    				return false;
    			}
    		}
    	}
    }
    
}
