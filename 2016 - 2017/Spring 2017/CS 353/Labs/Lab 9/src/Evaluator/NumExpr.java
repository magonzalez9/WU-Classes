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
public class NumExpr extends Expression{
    
    int value;
    
    public NumExpr(int v){
        value = v;
        type = "Num";
    }
    
    public int getValue(){
        return value;
    }
    
    public String toString(){
        return Integer.toString(value);
    }
    
    public String compile(){
        //set the first free register to the value;
        int register = -1;
        for (int i = 0; i < BasicCompiler.freeList.length; i++){
            if (BasicCompiler.freeList[i] == 1){
                register = i;
                break;
            }
        }
        //if there are no free registers return an error
        if (register == -1){return null;}
        //close that regiseter
        BasicCompiler.freeList[register] = 0;
        //add the appropriate phrase
        String toAdd = "SET R" + Integer.toString(register) + ", " + Integer.toString(value);
        BasicCompiler.myCode.add(toAdd);
        //return the register
        return ("R" + Integer.toString(register));
    }
    
    
}
