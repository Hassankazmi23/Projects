/**Make sure to change the database URL (line33), username (line 36) and password (line 37) 
 
* This file consists of connecting mysql using JDBC api
* a splash screen when the program runs
 * Creating a database
 * Creating tables for the database
 * Inputting data in database from the input file 
 */
import java.sql.*;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @author HK
 * 
 * First the Program will connect to DB_URL 
 * Then it will create a database in the given DB_URL
 * After that's done, the program will create the table
 * Then it will read the input file and extract the  populate data into the tables
 * Then it will call the Gui class, which will be the client side of the program
 * 
 *
 */
public class Phase3 {
	
	/**
	 *Setting up database connection and creating database 
	 **/
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://127.0.0.1/?autoReconnect=true&useSSL=false";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "";
   
   public static void main(String[] args) {
		
	   JWindow window = new JWindow();
		window.getContentPane().add(new JLabel("", new ImageIcon("src/giphy.gif"),SwingConstants.CENTER));
		window.setBounds(550, 400, 400, 340);
		window.setVisible(true);
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException e){
			
		}
		
		window.dispose();
	    
	     	   
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
    System.out.println("Creating database...");
     stmt = conn.createStatement();
      //Create database
      String sql = "Create DATABASE MyBooks2";
      stmt.executeUpdate(sql);
      System.out.println("Database created successfully...");
      //use database
      String use = "Use MyBooks2";
      
      stmt.execute(use);	  //use database is executed 
     System.out.println("Database in use");

  
    //Creating tables in database; Customer, Employee, Checkout, Item, Inventory, Checkout_Action
      			String Books =		  
    			"CREATE TABLE Books " +
              "(ISBN_Number INTEGER not NULL, " +
              " authorFirstName VARCHAR(50), " + 
              " authorLastName VARCHAR(50), " + 
              " title VARCHAR(100), " + 
              " yearPublished VARCHAR(20)," +
              " publisher VARCHAR(30), " +
              " quantity CHAR(2), "+
              " quality VARCHAR(10)," +
              " dimensions VARCHAR(40), "+
              " PRIMARY KEY ( ISBN_Number ))";
     	
     stmt.execute(Books);
      System.out.println("Books table created in Database");
   
	  ArrayList<Book> listBook = getListBookFromTextFile("src/input.txt");
	   
	  
	  for (int i = 0; i < listBook.size(); i++){
		  
		 stmt.execute("Insert into Books (ISBN_Number, authorFirstName, authorLastName, title, yearPublished, publisher, quantity, quality )" +
		 "values('"+ listBook.get(i).getISBN_Number() + "','" + listBook.get(i).getAuthorFirstName() + "','" + listBook.get(i).getAuthorLastName() + "','" + listBook.get(i).getTitle() + "','" +listBook.get(i).getYearPublished() + "','" + listBook.get(i).getPublisher() + "','" +listBook.get(i).getQuantity()+ "','" + listBook.get(i).getQuality() + "')");
		  
		  
		 
	  }
   System.out.println("values has been inserted");    
   
   }
   
   catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }   
  
   finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   
   //call gui class
   Gui gui = new  Gui();
   gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   gui.setSize(710, 300);
   gui.setVisible(true);
   
   
}//end main **/
   
   //This method is used to get the input file and put into the database
   
   public static ArrayList<Book> getListBookFromTextFile(String filePath){
	   FileInputStream fis = null;
	   InputStreamReader isr = null;
	   BufferedReader bReader = null;
	   ArrayList<Book> listResult = new ArrayList<Book>();
	   try{
		   fis = new FileInputStream(filePath);
		   isr = new InputStreamReader(fis);
		   bReader = new BufferedReader(isr);
		   //String save line get from text line
		   String line = null;
		   //Array save book
		   String [] strBooks = null;
		   
		   //Loop and get all data in text file 
		   while(true){
			   //Get 1 line
			   line = bReader.readLine();
			   
			   //Check line get empty, exit loop
			  
			   if(line == null ){
				   break;
			   } else {
				   strBooks = line.split(",");
				   listResult.add(new Book (Integer.parseInt(strBooks[0]), strBooks[1], strBooks[2], strBooks[3], strBooks[4], strBooks[5], Integer.parseInt(strBooks[6]), strBooks[7]));
			   }
		   }
		   
	   } catch (Exception e){
		   System.out.println("Read file error");
		   e.printStackTrace();
		   } finally {
			  //close file
			   try {
				   bReader.close();
				   fis.close();
				   isr.close();
			   } catch (IOException e){
				   e.printStackTrace();
			   }
			   
		   }
	   	return listResult;
	   
   }
}//end JDBCExample
