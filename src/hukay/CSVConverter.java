/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hukay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laurenz T.
 * Rawr!!
 */
public class CSVConverter {
    
    private String[] content;
    
    public int Converter() throws IOException 
    {
       BufferedReader reader;       
       String dictionary    = "";
       String[] foods;
       int commaCount = 0;
       char checkInput;
       try 
       {
           reader = new BufferedReader(new FileReader("fooddictionary.csv"));
           FileReader file = new FileReader("fooddictionary.csv");
           System.out.println("File Found");
           
           while((checkInput = (char)file.read()) != '@' )
           {
               
               System.out.print(""+checkInput);
               dictionary = dictionary + checkInput;
           }
           //System.out.println(dictionary + "\n");
           System.out.println(dictionary);
           
           for(int i = 0; i < dictionary.length(); i++)
           {
               if(dictionary.charAt(i) == ',')
               {
                   commaCount++;
               }
           }
           //Split the dictionary into pieces
           foods = dictionary.split(",");
           content = foods;

       } catch (FileNotFoundException ex) 
           {
               Logger.getLogger(nagHuhukay.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("File to read not found*************************************");
           }
        
        return 0;
    }
    
    public String[] getDictionaryContent()
    {
        
        for(String part : content)
        {
            System.out.println(part);
        }
        return content;
    }
}

