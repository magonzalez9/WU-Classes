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
public class preExpression extends IncExpression{
    
     Variable store = new Variable();
     String incExp;
     Expression E1; 
     int ExpValue; 
     String var; 
     
    public preExpression(String incExp, Expression E1, String var){
        this.incExp = incExp;
        this.E1 = E1; 
        this.var = var; 
    }
    
    public int getValue(){
       if (incExp == "++") {
           ExpValue = E1.getValue();
           ExpValue +=1;
           
           
       } else if (incExp == "--"){
           ExpValue = E1.getValue();
           ExpValue -=1; 
       }
       store.setVar(var, ExpValue);
        return store.getVar(var); 
    }
    
    public String toString(){
        return incExp + E1.toString();
    }
}
