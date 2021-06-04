/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilers.pkg2.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.shape.Path;

/**
 *
 * @author Abbas
 */
public class Compilers2Scanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //readFromFile("Files/file.txt");
        new Compilers2ScannerInterface().setVisible(true);
        /*
        while ((l = r.readLine()) != null) {
            
            Scanner code = new Scanner(l);
            
            while (code.hasNext()) {
                lexeme = code.next();
                System.out.println("lineNo:"+r.getLineNumber()+"||||"+lexeme+"\n");
            }
        }
        
  */    
          /*
            
            
        for(int i = 0;i<6;i++){
            System.out.println("d");
            if(i == 2){
                i--;
            }
        }
*/  System.out.println(isNumber("1.11.11"));
    }
     static int isNumber(String s)
    {
       int res, i = 0, chAscii, flagFloat = 0, fractCounter = 0;
       char ch ;
       while (i < s.length())
       {
           ch = s.charAt(i);
           chAscii = (int) ch;
           if(i != 0)
           {
               if((chAscii >=48 && chAscii <= 57) || chAscii == 46)  
               {
                    if (chAscii == 46) {
                        flagFloat = 1;
                        fractCounter++; // Must be one fractional point
                    }
                }
                else
                    return 0; // Not Number
           }
           else
           {
               if(chAscii >=48 && chAscii <= 57)  
               {
                    if (chAscii == 46) {
                        flagFloat = 1;
                        fractCounter++; // Must be one fractional point
                    }
                }
                else
                    return 0; // Not Number
           }
           
           i++;
       }
       
       if(flagFloat == 1) {
           if(fractCounter == 1)
               res = 1; // Float
           else
               res = 0; // Not Valid
       }
           
       else
            res = 2; // Int
       
       return res;
   }
    

    public static void readFromFile(String filename) {
        LineNumberReader lineNumberReader = null;
        try {
            //Construct the LineNumberReader object
            lineNumberReader = new LineNumberReader(new FileReader(filename));

            //Print initial line number 
            System.out.println("Line " + lineNumberReader.getLineNumber());

            //Setting initial line number
            lineNumberReader.setLineNumber(5);

            //Get current line number
            System.out.println("Line " + lineNumberReader.getLineNumber());

            //Read all lines now; Every read increase the line number by 1
            String line = null;
            while ((line = lineNumberReader.readLine()) != null) {
                System.out.println("Line " + lineNumberReader.getLineNumber() + ": " + line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Close the LineNumberReader
            try {
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
