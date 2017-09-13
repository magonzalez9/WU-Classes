/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Eric Keefe
 */
public class ReadExpr extends Expression{
    
    int value;
    
    public ReadExpr(){
        type = "Read";
        String line;
//        line = JOptionPane.showInputDialog(null, "Enter a number, please:");
//                            
//        try { 
//             
//            value = Integer.parseInt(line);
//        }
//        catch (Exception e) { 
//            System.out.println("Error! Bad Input");
//            value = -1;
//        }
    }
    
    public int getValue(){
        return value;
    }
    
    public String toString(){
        return Integer.toString(value);
    }
    
    public String compile(){
        String register = BasicCompiler.getFreeRegister();
        if (register == null){System.out.println("ERROR NO Free registers to read into");return null;}
        String toAdd = "READ " + register + ", DD";
        BasicCompiler.myCode.add(toAdd);
        return register;
              
    }
    
    
    
}
