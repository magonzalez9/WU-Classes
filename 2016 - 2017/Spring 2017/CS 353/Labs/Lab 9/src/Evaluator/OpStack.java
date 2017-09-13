/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Eric Keefe
 */
public class OpStack {
    
    private ArrayList <String> operation = new ArrayList<String>();
    private Hashtable <String, Integer> pri = new Hashtable<String, Integer>();
    String type; 
    
    public OpStack(){
        
        initializePriority();
        
    }
    
    public void push(String op){
        operation.add(0, op);
          
    }
    
    public String reduce(){
        if (operation.size() == 0 || operation.size() == 1 || pri.get(operation.get(0)) < pri.get(operation.get(1))){
            //System.out.println("I havn't reduced by removing the 2nd element because the stack size "
                   // + "is too small or the priority of the added element is more impotant");
            return null;
        }else{
            //System.out.println("I'm reducing!");
            String temp  = operation.get(1);
            operation.remove(1);
            return temp;
        }
        
    }
    
    public String get(){
        if (operation.size() == 0){
            return null;
        }else{
            return operation.get(0);
        } 
    }
    
    public String pop(){
        if (operation.size() == 0){
            return null;
        }else{
            String temp = operation.get(0);
            operation.remove(0);
            return temp;
        }
    }
    
    public void initializePriority(){ //smaller priorities are more important
        pri.put("*", 0);
        pri.put("+", 1);
        pri.put("-", 1);
        pri.put("<", 2);
        pri.put(">", 2);
        pri.put("==", 2);
        pri.put("&", 3);
        pri.put("|", 3);
        pri.put("=", 4); 
        pri.put("w", 5);
        pri.put(":", 6);
        pri.put("?", 7);
        pri.put("(", 100); //should never reduce out a parenthesis
        pri.put(")", 100); //should never reduce out a parenthesis
    }
    
          
     
    
    
    
    
}
