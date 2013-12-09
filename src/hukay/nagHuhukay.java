/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hukay;

//foreign package imports
import database.SQLConnection;

//java imports
import java.io.BufferedWriter; //writing to textfile
import java.io.BufferedReader; //reading from textfile
import java.io.FileReader;     //reading files
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
//java sql imports
import java.sql.*;
//twitter4j inputs
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener; 
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 *
 * @author Laurenz T.
 * Rawr!!
 */
public class nagHuhukay 
{
    private SQLConnection sql = new SQLConnection();
    
    
    public void streamSearch()
     {
         CSVConverter csvFile       = new CSVConverter();
         Status status              = null;
         TwitterStream tweetStream  = new TwitterStreamFactory().getInstance(); 
         StatusListener listener    = new StatusListener() 
         {
             
                @Override
                public void onStatus(Status status) 
                {
                    
                    
                    System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                    //Storing to MySQL Database
                    //(String username, String date, String country, String tweet) 
                    addToDB(status.getUser().getScreenName(), status.getCreatedAt().toString(), status.getUser().getLocation(), status.getText());
                    try 
                    {   //Store to txt file
                        writing(status.getUser().getScreenName(), status.getCreatedAt(), status.getText());
                    } catch (FileNotFoundException ex) 
                    {
                        Logger.getLogger(nagHuhukay.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) 
                    {
                        Logger.getLogger(nagHuhukay.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) 
                    {
                        Logger.getLogger(nagHuhukay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) 
                {

                    System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
                }

                @Override
                public void onTrackLimitationNotice(int numberOfLimitedStatuses) 
                {

                    System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
                }

                @Override
                public void onScrubGeo(long userId, long upToStatusId)
                {

                    System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
                }

                @Override
                public void onStallWarning(StallWarning warning) 
                {

                    System.out.println("Got stall warning:" + warning);
                }

                @Override
                public void onException(Exception ex) 
                {

                    ex.printStackTrace();
                }
            }; //belongs to abstract override
        
         //Adds Listener : IMPT
         tweetStream.addListener(listener);
         //Dictionary of keywords to track
        try {
            csvFile.Converter();
        } catch (IOException ex) {
            Logger.getLogger(nagHuhukay.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         String[] track = csvFile.getDictionaryContent();;
         
         /* //Searching Locations
            double[][] locations = { { 40.714623d, -74.006605d },
                { 42.3583d, -71.0603d } };
          */
         
         FilterQuery filt = new FilterQuery(0, null, track);
         tweetStream.filter(filt);
     }
     
     public int csvConverter() throws IOException 
     {
        BufferedReader reader;
        String foods    = null;
        int commaCount  = 0;
        
        try 
        {
            reader = new BufferedReader(new FileReader("/fooddictionary.csv"));
            
            if((foods = reader.readLine()) != null)
            {
              String[] split = foods.split(",");
            }
            
        } catch (FileNotFoundException ex) 
            {
                Logger.getLogger(nagHuhukay.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("File to read not found*************************************");
            }
        
         return 0;
     }
    
     public void writing(String username, Date date, String tweet) throws FileNotFoundException, UnsupportedEncodingException, IOException
     {
        //PrintWriter writer = new PrintWriter("tweets.txt", "UTF-8");
        PrintWriter out  = new PrintWriter(new BufferedWriter(new FileWriter("test3.txt", true)));
       
        //@laurenz >>createdAt>> Mon Apr 15 15:02:44 +0000 2013 >>tweeted>> #wow #wow #wow #wow
        out.println("@" + username + " >> " + date + " >> " + tweet + ",");
        
        out.close();
     }
     
     public void addToDB(String username, String date, String country, String tweet)
     {
         sql.connect();
         
         try
         {
             Statement stmt = sql.getConnection().createStatement();
             String query = "INSERT INTO tweets VALUES(DEFAULT, '"
                     + username +"','" + date + "','" + country + "','" + tweet + "')";
             stmt.executeUpdate(query);
         } catch(SQLException exc)
                 {
                     exc.printStackTrace();
                 }
     }
}
