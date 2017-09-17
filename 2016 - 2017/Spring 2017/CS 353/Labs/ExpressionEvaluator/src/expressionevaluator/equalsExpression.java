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
public class equalsExpression extends BinaryExpression{

    Expression E1;
    Expression E2; 
    
    public equalsExpression (Expression E1, Expression E2){
        this.E1 = E1;
        this.E2 = E2;
    }
    
    public int getValue(){
        if (E2.getValue() == E1.getValue()){
           return 1;
        } else {
           return  0;
        }
        
    }
    
    public String toString(){
        return "("+ E1.toString() + " == " + E2.toString() + ")";
    }
    
    
}
