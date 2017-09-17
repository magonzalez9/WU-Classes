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
public class varExpression extends Expression {
    Variable valueOfVar= new Variable(); 
    String var;
    
    public varExpression(String var){
        this.var = var;
    }
    
    public int getValue(){
        return valueOfVar.getVar(var);
    }
    
    public String toString(){
        return var;
    }
    
    
    
    
    
}
