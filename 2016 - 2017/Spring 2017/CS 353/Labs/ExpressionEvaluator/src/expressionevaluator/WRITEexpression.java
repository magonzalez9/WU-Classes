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
public class WRITEexpression extends Expression{
    
    Expression expression; 
    
    public WRITEexpression(Expression expresserion){
        this.expression = expresserion; 
    }
    
    @Override
    public int getValue(){
        int temp = expression.getValue();
        System.out.println(temp);
        return temp;
    }
    
    @Override
    public String toString(){
        return expression.toString();
    }
}
