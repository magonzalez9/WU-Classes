/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package expressionevaluator;
import java.util.Hashtable;

/**
 *
 * @author Marco
 */
public class parenExpression extends Expression{
  
    Expression expression;
    
    public parenExpression (Expression expression){
        this.expression = expression;
    }
    
    public int getValue(){
        return (expression.getValue());
        
    }
    
    public String toString(){
        return "("+ expression.toString()+")";
    }
      
        
    
}
