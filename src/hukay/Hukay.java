/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hukay;

import java.util.logging.Logger;
import java.io.*;
import java.util.logging.Level;

import twitter4j.*;
import twitter4j.Twitter.*;
import twitter4j.TwitterFactory.*;
import twitter4j.ResponseList;


/**
 *
 * @author Laurenz
 */
public class Hukay 
{
        
     public static void main(String[] args) throws TwitterException
     {
         nagHuhukay newhukay = new nagHuhukay();
         newhukay.streamSearch();
        
        
     }
     
     public void streamSearch()
     {
         Status status              = null;
         TwitterStream tweetStream  = new TwitterStreamFactory().getInstance(); 
         StatusListener listener    = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
            
            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                //ex.printStackTrace();
            }
            };
        
         tweetStream.addListener(listener);
         
         String[] track = new String[5];
         track[0] = "gsp";
         track[1] = "ufc167";
         track[2] = "hendricks";
         track[3] = "gspvshendricks";
         track[4] = "mma";
                 
         //long[] follow = new long[1];
         
         FilterQuery filt = new FilterQuery(0, null, track);
         tweetStream.filter(filt);
     }
     public static void writing() throws FileNotFoundException, UnsupportedEncodingException
     {
        PrintWriter writer = new PrintWriter("tweets.txt", "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();
     }
     
     public void trash()
     {
         /**
        Twitter twitter     = new TwitterFactory().getInstance();     
        QueryResult result  = null;
        Query query = new Query("#lakers");
        
        try 
        {
            result = twitter.search(query);            
        }   catch (TwitterException te) 
        {
            te.printStackTrace();
        }

        for (Status status : result.getTweets()) 
        {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
        **/
        
        /*****************************************************
        // The factory instance is re-useable and thread safe.
        
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("source:twitter4j yusukey");
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
        *********************************************************/   
     }
     
}
