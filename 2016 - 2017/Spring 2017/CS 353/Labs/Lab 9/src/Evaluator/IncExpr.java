/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

/**
 *
 * @author Marsha
 */
public abstract class IncExpr extends Expression{
    
    String operation;
    String variable; 
        
    public IncExpr(String var, String op, String ty){
        variable = var;
        operation = op;
        type = ty;
    }
           
    
    
    
    
}
