/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

public class PostExpr extends IncExpr{
    
    
    
    public PostExpr(String var, String op){
        super(var, op, "Post");
    }
           
    public int getValue(){
        if (super.operation.equals("++")){
            int value = Variables.get(super.variable);
            Variables.set(super.variable, (value + 1));
            return value;
        
            //return the value of super.variable
            
        }else if (super.operation.equals("--")){
            int value = Variables.get(super.variable);
            Variables.set(super.variable, (value  - 1));
            return value;
        }
        else{
            System.out.println("Invalid input!!");
            return -1;
        }
    } 

    @Override
    public String toString() {
        return ("(" + super.variable + super.operation + ")");
    }
    
    public String compile(){
        String register = BasicCompiler.getFreeRegister();
        if (register == null){return null;}
        
        if (!BasicCompiler.alreadyInRam(super.variable)){
            String toAdd = "#" + variable;
            BasicCompiler.varCode.add(toAdd);
            BasicCompiler.varCode.add("CONST 1");
            String toAd = "SET " + register + ", " + "0";
            BasicCompiler.myCode.add(toAd);
            return register;
        }
        
                
        int value = BasicCompiler.getValue(super.variable);
        //needs to store the value of the variable in a register
        String toAdd = "SET " + register + ", " + Integer.toString(value);
        BasicCompiler.myCode.add(toAdd);
        int newValue = value + 1;
        //then needs to update the RAM 
        BasicCompiler.setValue(super.variable, newValue);
        //return the register name
        return register;
    }
    
    
}
