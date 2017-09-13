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
public class condExpression extends Expression {
    
    Expression E1,E2,E3;
    
    public condExpression(Expression E1, Expression E2, Expression E3){
        this.E1 = E1;
        this.E2 = E2;
        this.E3 = E3;
    }
    
    public int getValue(){
        if (E1.getValue() == 1){
            return E2.getValue();
        } else {
            return E3.getValue();
        }
    }    
    
    public String toString(){
       return "("+E1.toString()+"?"+E2.toString()+":"+E3.toString()+")";
    }
}
