/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionevaluator;

/**
 *
 * @author Marco
 */
public class minExpression extends BinaryExpression {
    
    Expression E1;
    Expression E2;
    
    public minExpression(Expression E1, Expression E2){
        this.E1 = E1;
        this.E2 = E2; 
    }
    
    public int getValue(){
        return E2.getValue() - E1.getValue();
    }
    
    public String toString(){
        return "(" + E1.toString() + " - " + E2.toString() + ")";
              
    }
}

