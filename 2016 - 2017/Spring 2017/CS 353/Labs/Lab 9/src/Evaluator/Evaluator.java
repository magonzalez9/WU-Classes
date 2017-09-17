/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eric Keefe
 */
public class Evaluator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String line = "5 + 4 - 3 * 2 + 8";
        Parser parse = new Parser();
               parse.isBinary(line);
              System.out.println(parse.isBinary(line));
       
          
            
        
        
    }
    
    
    
}
