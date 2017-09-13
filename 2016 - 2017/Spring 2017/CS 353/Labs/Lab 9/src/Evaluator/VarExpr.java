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
public class VarExpr extends Expression{
    
    String variable;
    
    public VarExpr(String var){
        type = "Var";
        variable = var;
    }
    
    public int getValue(){
        System.out.println(variable);
        return Variables.get(variable);
    }
    
    public String toString(){
        return variable;
    }
    
    public String compile(){
        if (BasicCompiler.alreadyInRam(variable)){
            return ("#" + variable);
        }
        
        String toAdd = "#" + variable;
        BasicCompiler.varCode.add(toAdd);
        BasicCompiler.varCode.add("CONST 0");
        
//        System.out.println("Testing ability to get out the value");
//        int value = BasicCompiler.getValue(variable);
//        System.out.println("The value of varaible " + variable + " is: " + value);
        
        
        return ("#" + variable);
    }
    
    
}
