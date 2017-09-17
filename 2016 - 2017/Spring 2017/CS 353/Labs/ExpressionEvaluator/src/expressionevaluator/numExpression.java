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
public class numExpression extends Expression{
    
   int number; 
    
   public numExpression (int number){
       this.number= number;
   }
   
   public int getValue() {
       return number; 
   }
   
   @Override
    public String toString(){
        
       return Integer.toString(number);
    }
}
