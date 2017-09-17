/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

/**
 *
 * @author Eric Keefe
 */
public class SetExpr extends Expression{
    
    Expression expression;
    String variable;
    
    public SetExpr(String var, Expression e){
        type = "set";
        expression = e;
        variable = var;
    }

   

    
    
    public int getValue(){ 
        //System.out.println("Im here");
        int value = expression.getValue();
        Variables.set(variable, value);
        return value;
    }
    
    public String toString(){
        return ("(" + variable + " = " + expression.toString() + ")");
    }
    
    public String compile(){
        String register = expression.compile();
        if (register == null){return null;}
        int value = expression.getValue();
              
        if (!BasicCompiler.alreadyInRam(variable)){
            String toAdd = "#" + variable;
            BasicCompiler.varCode.add(toAdd);
            BasicCompiler.varCode.add(("CONST " + Integer.toString(value)));
            //String toAd = "SET " + register + ", " + Integer.toString(value);
            //BasicCompiler.myCode.add(toAd);
            return register;
        }
 
        
        
         
        BasicCompiler.setValue(variable, value);
       
        return register;
    }
    
    
    
}
