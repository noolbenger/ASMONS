import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main
{
	public static String search = "";
	public static String one = "http://tickerreport.com/?s=";
	public static int value = 0;
	public static int inst_up;
	public static int inst_down;
	public static String articles;
	public static int stocknumber;
	public static int stocknummodif;
	public static int stockarraylocation = 0;
	public static int x = 0;
	public static String[][] board = new String[stocknumber][2];
	//array of stocks
  public static void main(String args[])
  {
	  stocks();
	  for(int x = 0; x<stocknummodif; x++)
	  {
	  geturls();
	  }
	  mainanalyzer();
	  goodbad();
	  
 }
  public static void stocks()
  {
	  getnumstocks();
	  while(stockarraylocation < stocknumber)
	  {
	  getstock();
	  stockarraylocation++;
	  }
  }
  
  public static void getnumstocks()
  {

	  Scanner scanner = new Scanner (System.in);
	  System.out.print("Enter Number of Stocks you Have");  
	  String out = scanner.next();
	  stocknumber = Integer.parseInt(out);
	  stocknummodif = stocknumber;
  }
  public static void geturls()
  {
	  String nextLine;
      URL url = null;
      URLConnection urlConn = null;
      InputStreamReader  inStream = null;
      BufferedReader buff = null;
      search=board[x][1] ;
      x++;
      try
      {
         // Create the URL object that points
         // at the default file index.html
         url  = new URL(search);
         stocknumber++;
         urlConn = url.openConnection();
        inStream = new InputStreamReader( 
                          urlConn.getInputStream());
          buff= new BufferedReader(inStream);

      // Read and print the lines from index.html
       while (true)
       {
           nextLine =buff.readLine(); 
           
           //System.out.println("Checker");
           if (nextLine !=null)
           {
        	   if(nextLine.contains("<h2><a href=\""))
               {
            	   
            	   String[] arr=nextLine.split("<h2><a href=\"");
            	   String[] link=arr[1].split("\"");
            	   getpagecontents(link[0]);
            	   System.out.println(link[0]);
               }
               //System.out.println(nextLine); 
           }
           else
           {
              break;
           } 
       }
    } 
      catch(MalformedURLException e)
    {
      System.out.println("Please check the URL:" + 
                                          e.toString() );
    } catch(IOException  e1)
    {
     System.out.println("Can't read  from the Internet: "+ 
                                         e1.toString() ); 
 }
  }
  public static void getpagecontents(String link)
  {
	  String nextLine;
      URL url = null;
      URLConnection urlConn = null;
      InputStreamReader  inStream = null;
      BufferedReader buff = null;
      
      try
      {
         // Create the URL object that points
         // at the default file index.html
         url  = new URL(link);
         urlConn = url.openConnection();
        inStream = new InputStreamReader( 
                          urlConn.getInputStream());
          buff= new BufferedReader(inStream);

      // Read and print the lines from index.html
       while (true)
       {
           nextLine =buff.readLine(); 
           
           //System.out.println("Checker");
           if (nextLine !=null)
           {
        	   if(nextLine.contains("<p>"))
               {
            	   
            	   String[] arr=nextLine.split("<p>");
            	   String[] content=arr[1].split("</p>");
            	   if(content[0].contains("Get Analysts' Upgrades and Downgrades via Email - Stay on top of analysts' coverage with"))
            	   {
            		   
            		   System.out.println("=========================END OF ARTICLE=============================");
            		   break;
            	   }
            	   String[] test = content[0].split("[<|>]");
            	   for (int i=0;i<test.length;i++)
            	   {
            		   if (test[i].contains("a href")||test[i].contains("/a")||test[i].contains("img")||test[i].contains("/img")||test[i].contains("/span")||test[i].contains("span")||test[i].contains("!--")||test[i].contains("br /")){}
            		   else
            		   {
            			   articles = articles.concat(test[i]);
            			   System.out.println(test[i]);
            		   }
            	   }
               }
               System.out.println(value); 
           }
           else
           {
              break;
           } 
       }
    } 
      catch(MalformedURLException e)
    {
      System.out.println("Please check the URL:" + 
                                          e.toString() );
    } catch(IOException  e1)
    {
     System.out.println("Can't read  from the Internet: "+ 
                                         e1.toString() ); 
 }
  }
  public static void mainanalyzer()
  {
	  
  }
  public static void getstock()
  {
	  Scanner scanner = new Scanner (System.in);
	  System.out.print("Enter Stock Ticker");  
	  String ticker = scanner.next();
	  board[stockarraylocation][1]= ticker;
	  
  }
  public static void goodbad()
  {
	  //should there be a margin to allow for a vote system to help?
	  if(inst_up > inst_down)
	  {
		  System.out.println("You Should BUY this STOCK");
	  }
	  if(inst_down>inst_up)
	  {
		  System.out.println("You Should SELL this STOCK");
	  }
  }
  //Modifies the array of stock in the person's posession and put either good or bad as the value

}