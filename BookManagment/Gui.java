/**
 * This class is not only a class with GUI, but extracting user's input, and parsing amazon's url
 * I Created a Gui with a field that corresponds to the columns of the database
 * First user fills out the fields, the fields are taken and sent to the database, 
 * User said Insert, delete, and Update
 * User can also enter an amazon url, and this class will extract the book info and add it to the database 
 */
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
//Gui lay, Labels, Textfield, and buttons
public class Gui extends JFrame{
 JLabel L_ISBN_Number, L_authorFirstName, L_authorLastName, L_title, L_Year_Published, L_Publisher, L_Quantity, L_Quality, L_URL;
 JTextField JT_fname, JT_lname, JT_age,JT_id, T_ISBN_Number, T_authorFirstName, T_authorLastName, T_title, T_Year_Published, T_Publisher, T_Quantity, T_Quality, T_URL;
 JButton btn_insert, btn_update, btn_delete, btn_url;
 String TitleOfBook = "";
 String Publisher = "";
 String ISBN10 = "";
 String  DimensionsOfBook = "";
 public Gui(){
	 //ISBN number, Author First Name, Author Last Name, Title, 
	 //Year Published, Publisher, Quantity, Quality 
     super("INSERT UPDATE DELETE");
     
   //labels
     L_ISBN_Number = new JLabel("ISBN number:");
     L_authorFirstName = new JLabel("Author's First Name:");
     L_authorLastName = new JLabel("Author's Last Name:");
     L_title = new JLabel("Title of Book:");
     L_Year_Published = new JLabel("Year Published:");
     L_Publisher = new JLabel("Publisher:");
     L_Quantity = new JLabel("Quantity:");
     L_Quality = new JLabel("Quality:");
     L_URL = new JLabel("URL:");
     
    //labels dimensions
     L_ISBN_Number.setBounds(20, 10, 100, 20);
     L_authorFirstName.setBounds(20, 30, 140, 20);
     L_authorLastName.setBounds(20, 50, 140, 20);
     L_title.setBounds(20, 70, 100, 20);
     L_Year_Published.setBounds(20, 90, 100, 20);
     L_Publisher.setBounds(20, 110, 100, 20);
     L_Quantity.setBounds(20, 130, 100, 20);
     L_Quality.setBounds(20, 150, 100, 20);
     L_URL.setBounds(20, 200, 100, 20);
    
     
  
    //textfields
     T_ISBN_Number = new JTextField(20);
     T_authorFirstName = new JTextField(20);
     T_authorLastName = new JTextField(20);
     T_title = new JTextField(20);
     T_Year_Published = new JTextField(20);
     T_Publisher = new JTextField(20);
     T_Quantity = new JTextField(20);
     T_Quality = new JTextField(20);
     T_URL = new JTextField(300);
    
     //textfield dimensions
     T_ISBN_Number.setBounds(170,10,150,15);
     T_authorFirstName.setBounds(170,30,150,15);
     T_authorLastName.setBounds(170,50,150,15);
     T_title.setBounds(170,70,150,15);
     T_Year_Published.setBounds(170,90,150,15);
     T_Publisher.setBounds(170,110,150,15);
     T_Quantity.setBounds(170,130,150,15);
     T_Quality.setBounds(170,150,150,15);
     T_URL.setBounds(170, 200, 400, 15);
     
     //buttons
     btn_insert = new JButton("Insert");
     btn_update = new JButton("Update");
     btn_delete = new JButton("Delete");
     btn_url = new JButton("Insert Book");
     
     //button dimension
     btn_insert.setBounds(400, 50, 80, 20);
     btn_update.setBounds(400, 80, 80, 20);
     btn_delete.setBounds(400, 110, 80, 20);
     btn_url.setBounds(600, 200, 100, 20);
     
     setLayout(null);
     
     //add all the featuers
     add(L_ISBN_Number);
     add(L_authorFirstName);
     add(L_authorLastName);
     add(L_title);
     add(L_Year_Published);
     add(L_Publisher);
     add(L_Quantity);
     add(L_Quality);
     add(L_URL);
     add(T_ISBN_Number);
     add(T_authorFirstName);
     add(T_authorLastName);
     add(T_title);
     add(T_Year_Published);
     add(T_Publisher);
     add(T_Quantity);
     add(T_Quality);
     add(T_URL);
     add(btn_insert);
     add(btn_update);
     add(btn_delete);
     add(btn_url);
     
     
     

   //Insert button action
    btn_insert.addActionListener(new  ActionListener() {

         public void actionPerformed(ActionEvent e) {
         try{
        	 theQuery("insert into Books (ISBN_Number, authorFirstName, authorLastName, title, yearPublished, publisher, quantity, quality) values('"+Integer.parseInt(T_ISBN_Number.getText())+"','"+T_authorFirstName.getText() +"','"+T_authorLastName.getText()+"','"+T_title.getText()+"','"+T_Year_Published.getText()+"','"+T_Publisher.getText()+"','"+Integer.parseInt(T_Quantity.getText())+"','"+T_Quality.getText()+"')");
            
         }
         catch(Exception ex){} 
         }
     });
    
        //Update button action 
        btn_update.addActionListener(new  ActionListener() {

         public void actionPerformed(ActionEvent e) {
         try{
         
           theQuery("update Books set authorFirstName = '"+T_authorFirstName.getText()+"',authorLastName = '"+T_authorFirstName.getText()+"', title = '"+T_title.getText()+"', yearPublished = '"+T_Year_Published.getText()+"', publisher = '"+T_Publisher.getText()+"', quantity = '"+Integer.parseInt(T_Quantity.getText())+"', quality = '"+T_Quality.getText()+"' where ISBN_Number = "+Integer.parseInt(T_ISBN_Number.getText()));
         }	
         catch(Exception ex){}
         }
     });
       
         //delete button action
        btn_delete.addActionListener(new  ActionListener() {

         public void actionPerformed(ActionEvent e) {
         try{
          
             theQuery("delete from Books where ISBN_Number = "+Integer.parseInt(T_ISBN_Number.getText()));
         }
         catch(Exception ex){}
         }
     });
        //button insert
       
        
        //url button action
        
        //Once the user clicks on the button, the url address will read, and before the action 
        //is comitted, the url source will be extracted
        btn_url.addActionListener(new  ActionListener() {

             public void actionPerformed(ActionEvent e) {
             try{
            	 
            	  URL URLaddress = new URL(T_URL.getText());
            	  
            	 //Buffered reader to read in the URL input
                 InputStreamReader URL = new InputStreamReader(URLaddress.openStream());
                 BufferedReader origin = new BufferedReader(URL);
                 
            	 String LineReadIn; // each line html element data
                 String extracted = "";//save each line to Extracted
                 // The URL address of the page to open.
                
                 
                 

                 // Append each new HTML line into one string. Add a tab character.
                 while ((LineReadIn = origin.readLine()) != null) {
                	 extracted += LineReadIn;//extract entire webpage into siteContent
                	 
                 }
                 //Matcher is used to strip the biblical info. 
                 Matcher MATCHERKEY; //MATCHERKEY is the Matchkey
                 //strip the html element <title> tag to extract the title of the book 
                 Pattern getTitle = Pattern.compile("<title>(.*?)</title>", Pattern.DOTALL);
                //once the pattern is extracted, go to extracted and find the pattern
                 MATCHERKEY = getTitle.matcher(extracted);
                         if (MATCHERKEY.find()) 
                         {	//once the pattern has been found save into a String, TitleOfBook
                             TitleOfBook = MATCHERKEY.group(1);
                             System.out.println(TitleOfBook);
                         } 
                         //Now strip the publisher   Regex pattern to go in the field product details 
                        Pattern attribute = Pattern.compile("<td class=\"bucket\"><h2>Product Details</h2>(.*?)</td>", Pattern.DOTALL);              
                        MATCHERKEY = attribute.matcher(extracted);
                        if (MATCHERKEY.find()) //nested if loop 1st is to find product detail, 2nd if loop is to find publisher, isbn number, dimensions 
                         {
                             String attribute1 = MATCHERKEY.group(1);
                             //strips publisher
                             Pattern publisher = Pattern.compile("<li><b>Publisher:</b>(.*?)</li>");
                         	 MATCHERKEY = publisher.matcher(attribute1);
                         	 if(MATCHERKEY.find())
                         	 {
                         	 String Publisher = MATCHERKEY.group(1);
                         	 System.out.println(Publisher);
                         	 }
                         	//strip isbn10 number
                         	 Pattern isbn10 = Pattern.compile("<li><b>ISBN-10:</b>(.*?)</li>");
                         	 MATCHERKEY = isbn10.matcher(attribute1);
                         	 if(MATCHERKEY.find())
                         	 {
                         	  ISBN10 = MATCHERKEY.group(1);
                             System.out.println(ISBN10);//print isbn number
                         	 }
                         	else { 
                            	System.out.println("Details not found"); //else could not find details
                         	}
                            Pattern dimensions = Pattern.compile("<li><b>Product Dimensions:</b>(.*?)</li>");
                         	MATCHERKEY = dimensions.matcher(attribute1); 
                         	if(MATCHERKEY.find())
                         	{
                         		DimensionsOfBook = MATCHERKEY.group(1);
                         		System.out.println(DimensionsOfBook);
                         	}
                         	else 
                         	{ 
                         		System.out.println("Dimensions not found");
                         	}//else
                         }//if
            	 theQuery("insert into Books (ISBN_Number, title, publisher, Quantity, dimensions) values('"+Integer.parseInt(ISBN10)+"','"+TitleOfBook+"','"+Publisher+"','"+'1'+"','"+DimensionsOfBook+"')");
            	 
             
             }
             catch(Exception ex){} 
             }
         });
     
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setVisible(true);
     setLocationRelativeTo(null);
     setSize(500,200);
    
 }
 



 //function to execute the insert update delete insert book query
  public void theQuery(String query){
      Connection con = null;
      Statement st = null;
     //everytime user does a query it will be noted with the date and time 
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
	   Date date = new Date();
	   String logDate = (dateFormat.format(date));
	   
      try{ 
    	 //write to the output file for every query done by the user 
    	  FileWriter fw = new FileWriter("src/output2.txt", true);
    	  BufferedWriter bw = new BufferedWriter(fw);
	    	PrintWriter out = new PrintWriter(bw);
          con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/?autoReconnect=true&useSSL=false","root","");
          st = con.createStatement();
          st.execute("use MyBooks2");
          System.out.println("Using MyBooks database...");
         
          out.println("Transaction occurred at "+ logDate +"\n" +query + "\n");
          
          st.executeUpdate(query);
          out.println("Transaction occurred at "+ logDate +"\n" +query);
          JOptionPane.showMessageDialog(null,"Query Executed");
          out.close();
          
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,ex.getMessage());
      }
  }
  
}
      