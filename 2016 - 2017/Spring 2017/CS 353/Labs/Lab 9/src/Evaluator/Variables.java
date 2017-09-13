/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

import java.util.Hashtable;

/**
 *
 * @author Eric Keefe
 */
public class Variables {
    
    private static Hashtable<String, Integer> varMap = new Hashtable <String, Integer>();
    
    public static void set(String s, int value){
        //System.out.println(s + ": " + Integer.toString(value));
        varMap.put(s, value);
        if (varMap.containsKey(s)){
            //System.out.println("hi!");
        }
        
        
    }
    
    public static int get(String s){
        
        if (varMap.containsKey(s)){
            return varMap.get(s);
        }else{
            //System.out.println("error");
            //System.out.println("We are attempting to get: " + s);
            return -1;
        }
        
        
    }
    
    
    
}
