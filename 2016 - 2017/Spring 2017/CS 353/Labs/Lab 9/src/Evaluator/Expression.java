/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

/**
 *
 * @author Marco
 */
public abstract class Expression {
    
      
    String type;  
    abstract public int getValue();
    abstract public String toString();
    abstract public String compile();
    
    
    
    
}
