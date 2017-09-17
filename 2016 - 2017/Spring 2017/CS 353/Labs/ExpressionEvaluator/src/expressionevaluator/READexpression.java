/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionevaluator;

import java.util.Scanner;

/**
 *
 * @author Marco
 */
public class READexpression extends Expression{
    int userInput; 
    public READexpression(){
        Scanner input = new Scanner (System.in);
        userInput = input.nextInt();
        
    }
    
    public int getValue(){
        return userInput;
    }
    
    @Override
    public String toString(){
        return Integer.toString(userInput);
    }
    
    
}
