package hashingBooks;
import java.io.Serializable;


public class Book implements Comparable<Book>, Serializable{
  private static final long serialVersionUID = 5L;
  private String title;
  private String author;
  private String isbn;
  private Double price;
  private Double cost;
  
 
  public Book(Book aCopy){ //copy constructor
	  title = aCopy.title;
	  author = aCopy.author;
	  isbn = aCopy.isbn;
	  price = aCopy.price;
	  cost = aCopy.cost;
	  
  }
  public Book(String id){
	  isbn =id;
	  author = null;
	  title =null;
	  price =null;
	  cost =null;
  }
  public Book(String t, String a, String id){
	  title = t;
	  author =a;
	  isbn =id;
	  price =null;  //price is not known
	  cost = null;
  }
  public Book(String t, String a, String id, Double p, Double c){
	  title = t;
	  author =a;
	  isbn =id;
	  price =p;  
	  cost = c;
	  
  }
  
  private Book(){;}  //don't allow a Book to be set up without identifying data
  
  public void setPrice(double p){
	  if (price >0) price = p;
  } 
  
  public Double getPrice(){return price;}


  public void setCost(double c){if (c >=0) cost= c;}
  public double getCost(){return cost;}
  public String getAuthor(){return author;}
  public String getTitle(){return title;}
  public String getISBN(){return isbn;}
  
  public int compareTo(Book b){
	  return isbn.compareTo(b.isbn);
	  }
  
  
  public boolean equals(Object o){
	  if (o ==null) {
		  return false;
	  }
	  else {
		  if (! (o instanceof Book)) {
			  return false;
		  }
	  }
	  //two books are the same if their isbn matches
	  Book temp = (Book)o;
	  return isbn.compareTo(temp.isbn)==0;
  }
  public String toString(){
	  StringBuffer bookInfo = new StringBuffer();
	 
	  bookInfo.append("\nBook ");
	  bookInfo.append(isbn);
	  bookInfo.append(" Title: ");
	  bookInfo.append(title);
	  bookInfo.append(" Author: ");
	  bookInfo.append(author);
	  bookInfo.append("Price: " );
	  bookInfo.append(price);
	  bookInfo.append("Cost:");
	  bookInfo.append(cost);
	  return bookInfo.toString();
	 
  }
  public Book clone(){  //returns deep copy of a Book instance
	  Book aCopy = new Book(title, author,isbn, price,cost);
      return aCopy;
  }
}

 
  