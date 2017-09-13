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
public class CondExpr extends Expression{
    
    Expression e1;
    Expression e2;
    Expression e3;
    
    public CondExpr(Expression ee1, Expression ee2, Expression ee3){
        e1 = ee1;
        e2 = ee2;
        e3 = ee3;
    }
    
    public int getValue(){
        if (e1.getValue() == 1){
            return e2.getValue();
        }else{
            return e3.getValue();
        }
    }
    
    public String toString(){
        return ("(" + e1.toString() + " ? "  + e2.toString() + " : " + e3.toString() + ")");
    }
    
    public String compile(){
        if (e1.getValue() == 1){
            String register = e2.compile();
            return register;
        }else{
            return e3.compile();
        }
    }
}
