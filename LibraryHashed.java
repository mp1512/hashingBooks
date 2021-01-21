package hashingBooks;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;



public class LibraryHashed implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5579528456513601786L;
	private HashMap<String,LibraryBook> myBookCopies;  //reference to each copy of a LibraryBook by copyid
	private LinkedHashMap<String,LibraryBook> linkedBooks; //reference to each copy of a librarybook by isbn + copyid
	
	private HashMap <String, LinkedList<LibraryBook>> myBooks;  //maps an isbn to copies of a LibraryBook with that ISBN
	
	public LibraryHashed(int number){
		myBooks = new HashMap<String, LinkedList<LibraryBook>>(number);//based on isbn
		myBookCopies = new HashMap<String,LibraryBook> (number);  //based on copyid
		linkedBooks = new LinkedHashMap<String, LibraryBook>(number); //based on isbn and copyid
	}
	
	public void add(LibraryBook aBook){
		//first check if the library already has a book with this isbn
		if (!myBooks.containsKey(aBook.getISBN())) {
			//set up a LinkedList with this LibraryBook
			LinkedList<LibraryBook> books = new LinkedList<LibraryBook>();
			//now add the new book to this list
			books.addFirst(aBook);
			//now place this list into the HashMap at a location based on the ISBN
			myBooks.put(aBook.getISBN(),books);
			
		}
		else {
			//library already has a copy of this book
			LinkedList<LibraryBook> books = myBooks.get(aBook.getISBN());
			//verify that the list has books with the correct isbn
			LibraryBook sample = books.getFirst();
			if (!sample.getISBN().equals(aBook.getISBN())) {
				throw new InconsistencyException();
			}
			else {
				//add the book to the list
				books.addLast(aBook);
				//now place the list back into the HashMap
				
				myBooks.put(aBook.getISBN(),books);
			}
		}
		//in either case do the following
		linkedBooks.put(aBook.getISBN()+aBook.getCopyID(), aBook);  //add the object to LinkedHashMap too
	    //places the book in HashMap based on copyid, doesn't allow duplicates
		//make sure that copyid is unique throughout the libray
		myBookCopies.put(aBook.getCopyID(), aBook);
	}
	
	public void borrow(String copyid,String borrowID)throws Exception{
		if (myBookCopies.containsKey(copyid))
			try{
			myBookCopies.get(copyid).setBorrower(borrowID); //should affect both HashMaps
			
			}
		    catch(Exception e){
		    	throw new Exception("book is currently borrowed by someone else");
		    }
		else
			throw new Exception("book not available");
		
	}
	
	public void returnBook(LibraryBook aBook)throws Exception{
		try{
		     returnBook(aBook.getCopyID());
		}
		catch(Exception e)
		{
		throw e;
		}
		
		
	}
   		
	public String toString(){	
			
		return myBooks.toString();
		    	
	}
	
	public void removeBook(String copyID) {
		LibraryBook theBook = myBookCopies.get(copyID);
		String isbn = theBook.getISBN();
		myBookCopies.remove(copyID);
		myBooks.remove(copyID + isbn);
		
	}
	
	public void returnBook(String copyID)throws Exception{
		if (myBookCopies.containsKey(copyID))
			try{
				myBookCopies.get(copyID).resetBorrower();
				}
			    catch(Exception e){
			    	throw new BookBorrowException("book wasn't borrowed");
			    }
			    else
					throw new BookBorrowException ("book not from this library, can't return here");	    
			
	}
	
	public String listInEntryOrder() {
		return linkedBooks.toString();
		
	}
	
	
	public String listInOrderByISBNCopy(){
		//books sorted using the isbn and copyid as the key
		Map<String, LibraryBook> sortedBooks =
				 new TreeMap<String,LibraryBook>(linkedBooks);
		
		//to be completed by students
		return null;
		
	}
	
	public ArrayList<String> getListISBNs(){
		//to be completed by students
		return null;
	}
	
	public ArrayList<String > getTitles(){
		//to be completed by students , list each title only once
		return null;
	}
	
	public String listInOrderByTitle() {
		StringBuilder info = new StringBuilder();
		
		//books sorted by titles
		Map<String,LibraryBook> sortedTitles =
				 new TreeMap<String,LibraryBook>();
		
		//get a Set of Map.Entry records from the HashMap
		//Then iterate through it entry by entry
		//should only include one copy of each Title 
		for (Map.Entry<String, LibraryBook> entry : myBookCopies.entrySet()) {
			sortedTitles.put(entry.getValue().getTitle(), entry.getValue());
		}
		
		
		for(Map.Entry<String,LibraryBook> entry : sortedTitles.entrySet()) {
			  String key = entry.getKey();
			  LibraryBook value = entry.getValue();
              info.append(key);
              info.append(" ");
              info.append(value.toString());
              info.append(" ");
			 
			}
		
		return info.toString();
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   String filename= null;
		   LibraryHashed myLibrary =null;
		   Scanner userInput = new Scanner(System.in);
		   System.out.println("is this the first time you are opening the library? Y or N");
		   char response = userInput.next().charAt(0);
		   if (response == 'N'){
			   //restore library from the book file
			   filename = "library.ser";
			   try{
			   ObjectInputStream fileInput = new ObjectInputStream (new FileInputStream(filename));
              
			   myLibrary = (LibraryHashed)fileInput.readObject();
			   fileInput.close();
               }
			   catch(ClassNotFoundException e1){
				   System.out.println("couldn't restore data, call IT");
				   System.exit(1);
			   }
               catch(IOException e){
            	   System.out.println("couldn't find library data file, call IT");
            	   System.exit(1);
               }
		   }
		   else{
		        myLibrary = new LibraryHashed(50);
		   LibraryBook aBook = new LibraryBook("100","Data Structures and Algorithms",
	             "McAllister","978-0-7637-5796-4",0.0,50.00);
		   myLibrary.add(aBook);
		    aBook =  new LibraryBook("105","Data Structures and Algorithms",
		             "McAllister","978-0-7637-5796-4",0.0,50.00);
		    myLibrary.add(aBook);
		   aBook = new LibraryBook("101","Object Oriented Data Structures",
	             "Nell Dale","978-0-7637-3746-7",0.0,70.00);
		   myLibrary.add( aBook) ;
		   aBook = new LibraryBook("104","Object Oriented Data Structures",
		             "Nell Dale","978-0-7637-3746-7",0.0,70.00);
		   myLibrary.add(aBook);
		   aBook = new LibraryBook("102","Starting out with Java",
	             "Gaddis","978-0-13-403817-9",0.0,40.00);
		   myLibrary.add(aBook);
		   aBook = new LibraryBook("103","Intro to Java","Liang","978-0-13-293652-1",0.0,45.00);
		   myLibrary.add(aBook);
		   }
		   int choice = menu();
		   while (true){
			   switch(choice){
			   case 1:
				   break;
			   case 2:
				   System.out.println("which book would you like to borrow?");
				   String isbn = userInput.next();
				   System.out.println("enter your library card id");
				   String id = userInput.next();
				   try{
					   myLibrary.borrow(isbn, id);
				   }
				   catch(Exception e){
					   System.out.println("Book is not available");
				   }
				   break;
			   case 3:
				   System.out.println("which book are you returning?");
				   String bookid = userInput.next();  //copy of the book
				   try{
					   myLibrary.returnBook(bookid);
				   }
				   catch(Exception e){
					   System.out.println("can't return a book that wasn't borrowed");
				   }
				   break;
			   case 4 :
				    System.out.println("Library Current Inventory...");
			        System.out.println(myLibrary);
			        System.out.println("\nsorted by title");
			        System.out.println(myLibrary.listInOrderByTitle());
			        System.out.println("\nEntry Sequenced Order");
			        System.out.println(myLibrary.listInEntryOrder());
			        break;
			   case 5:
				   //write out library data for future reference
				   try{
				    if (filename == null)
				    {
				    	filename = "library.ser";
				    }
				   ObjectOutputStream fileOutput = new ObjectOutputStream (new FileOutputStream(filename));
				   fileOutput.writeObject(myLibrary);
				   fileOutput.close();
				   }
				   catch (IOException e){
					   System.out.println("couldn't find file, can't store data, contact IT");
					   
				   }
				   System.out.println("have a nice day");
				   System.exit(1);
				   break;
			       
			   }
			   choice = menu();
		   }

	}
  public static int menu(){
	  Scanner userInput = new Scanner (System.in);
	  System.out.println("1. Add new library book to inventory" +
			             "\n2. Borrow a library book" +
			             "\n3. Return a library book" +
			             "\n4. List inventory" +
			             "\n5. Exit");
	  return userInput.nextInt();
  }
}
