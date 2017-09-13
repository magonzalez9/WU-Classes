/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

public class PreExpr extends IncExpr{
    
    
    
    public PreExpr(String var, String op){
        super(var, op, "Pre");
    }
           
    public int getValue(){
        if (super.operation.equals("++")){
            int value = Variables.get(super.variable) + 1;
            Variables.set(super.variable, value);
            return value;
        
            //return the value of super.variable
            
        }else if (super.operation.equals("--")){
            int value = Variables.get(super.variable) - 1;
            Variables.set(super.variable, value);
            return value;
        }
        else{
            System.out.println("Invalid input!!");
            return -1;
        }
    } 

    @Override
    public String toString() {
        return ("(" + super.operation + super.variable + ")");
    }
    
    public String compile(){
        String register = BasicCompiler.getFreeRegister();
        if (register == null){return null;}
        
        //check if the value already exists. 
        //if it doesn't exist, need to put 1 in RAM
        if (!BasicCompiler.alreadyInRam(super.variable)){
            String toAdd = "#" + variable;
            BasicCompiler.varCode.add(toAdd);
            BasicCompiler.varCode.add("CONST 1");
            String toAd = "SET " + register + ", " + "1";
            BasicCompiler.myCode.add(toAd);
            return register;
        }
        
        
        
        int value = BasicCompiler.getValue(super.variable) + 1;
        //needs to store the value of the variable in a register
        String toAdd = "SET " + register + ", " + Integer.toString(value);
        BasicCompiler.myCode.add(toAdd);
        //then needs to update the RAM 
        BasicCompiler.setValue(super.variable, value);
        //return the register name
        return register;
        
    }
    
    
}
