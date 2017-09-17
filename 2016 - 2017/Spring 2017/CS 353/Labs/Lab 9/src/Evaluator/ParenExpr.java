/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

/**
 *
 * @author Eric Keefe
 */
public class ParenExpr extends Expression{
    
    Expression e1;
    
    public ParenExpr(Expression e){
        e1 = e;
        type = "Paren";
    }
    
    public int getValue(){
        return e1.getValue();
    }
    
    public String toString(){
        return ("(" + e1.toString() + ")");
    }
    
    public String compile(){
        return e1.compile();
        
    }
    
}
