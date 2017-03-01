//Javier Espinal - Cs370 Project 1
/*Purpose of this code is to extract the important content 
 * from a amazon page such as title, isbn, product details
*/
import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AmazonReader 
{
    public static void main (String[] args) throws IOException 
    {
    	 String sourceLine;
         String siteContent = "";//where entire page will be extracted
         // The URL address of the page to open.
         URL address = new URL("https://www.amazon.com/Potter-Cursed-Special-Rehearsal-Script/dp/1338099132/ref=sr_1_3?ie=UTF8&qid=1481165612&sr=8-3&keywords=harry+potter");
         
         // Open the address and create a BufferedReader with the source code.
         InputStreamReader urlInput = new InputStreamReader(address.openStream());
         BufferedReader source = new BufferedReader(urlInput);

         // Append each new HTML line into one string. Add a tab character.
         while ((sourceLine = source.readLine()) != null) {
        	 siteContent += sourceLine;//extract entire webpage into siteContent
        	 
         }
         //Now create matcher and start stripping what we want
         Matcher matcher; //matcher created
         //strips title tag
         Pattern title = Pattern.compile("<title>(.*?)</title>", Pattern.DOTALL);
        
         matcher = title.matcher(siteContent);
                 if (matcher.find()) 
                 {
                     String siteTitle = matcher.group(1);
                     System.out.println(siteTitle);
                 } 
                 //Strips product details
                Pattern details = Pattern.compile("<td class=\"bucket\"><h2>Product Details</h2>(.*?)</td>", Pattern.DOTALL);              
                matcher = details.matcher(siteContent); 
                if (matcher.find()) 
                 {
                     String details1 = matcher.group(1);
                     //strips publisher
                     Pattern publisher = Pattern.compile("<li><b>Publisher:</b>(.*?)</li>");
                 	 matcher = publisher.matcher(details1);
                 	 if(matcher.find())
                 	 {
                 	 String publisher1 = matcher.group(1);
                 	 System.out.println(publisher1);//print publisher
                 	 }
                 	//strip isbn number
                 	 Pattern isbn = Pattern.compile("<li><b>ISBN-10:</b>(.*?)</li>");
                 	 matcher = isbn.matcher(details1);
                 	 if(matcher.find())
                 	 {
                 	 String isbn1 = matcher.group(1);
                     System.out.println(isbn1);//print isbn number
                 	 }
                 	else { 
                    	System.out.println("Details not found"); //else could not find details
                 	}
                    Pattern dimensions = Pattern.compile("<li><b>Product Dimensions:</b>(.*?)</li>");
                 	matcher = dimensions.matcher(details1); 
                 	if(matcher.find())
                 	{
                 		String dimensions1 = matcher.group(1);
                 		System.out.println(dimensions1);
                 	}
                 	else 
                 	{ 
                 		System.out.println("Dimensions not found");
                 	}//else
                 }//if
    }//main
}//AmazonReader