/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hukay;

import database.SQLConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Laurenz T.
 * Rawr!!
 */
public class DBManager {
    
    public void Convert()
    {
        String user     = "";
        String tweet    = "";
        
        try
        {
            BufferedReader in = new BufferedReader(new FileReader("laurenz-tweets.txt"));
            
            while(in.ready())
            {
                
                String s    = in.readLine();
                String sarray[];
                
                //evaluate line
                sarray  = s.split(" ");
                
                user    = sarray[0];
                
                if(!user.equalsIgnoreCase("@enjoyglobe") && !user.equalsIgnoreCase("@talk2globe") && user.startsWith("@"))
                {
                    System.out.println("user :" + user + "\t\ttweet :" + tweet);
                }
                
                //database store
                //AddContent(user, tweet);
            }
                user="";
                tweet="";
        }
        catch(Exception e )
        {
            
        }
        
    }
    
}
