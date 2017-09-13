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
public class andExpression extends BinaryExpression{
   
    Expression E1;
    Expression E2; 
    
    public andExpression (Expression E1, Expression E2){
        this.E1 = E1;
        this.E2 = E2;
    }
    
    public int getValue(){
        if ((E2.getValue() == 1) && (E1.getValue() == 1) ){
           return 1;
        } else {
           return 0;    
        }
    }
    
    public String toString(){
        return "("+ E1.toString() + " && " + E2.toString() + ")";
    }
    
    
}
