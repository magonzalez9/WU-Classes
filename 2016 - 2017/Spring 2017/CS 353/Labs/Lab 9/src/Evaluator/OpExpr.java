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
public abstract class OpExpr extends Expression{
    
    Expression expr1;
    Expression expr2;
       
    public OpExpr(Expression e1, Expression e2, String ty){
        this.expr1 = e1;
        this.expr2 = e2;
        type = ty;
    }
           
    
    
    
    
}
