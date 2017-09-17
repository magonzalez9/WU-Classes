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
public class setExpression extends Expression{
    Variable store = new Variable();
    String var;
    Expression varValue;
    public setExpression (String var, Expression varValue){
        
        this.var = var;
        this.varValue = varValue;
        
    }
 
    public int getValue(){
        store.setVar(var, varValue.getValue());
        return store.getVar(var);
    }
    
    public String toString(){
        return "("+var+"="+varValue.getValue()+")"; 
    }
    
}
