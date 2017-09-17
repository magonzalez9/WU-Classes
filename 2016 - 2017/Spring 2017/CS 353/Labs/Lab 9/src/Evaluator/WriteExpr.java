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
public class WriteExpr extends Expression{
    
    Expression e1;
    
    public WriteExpr(Expression e){
        type = "Write";
        e1 = e;
        
    }
    
    public int getValue(){
        int value = e1.getValue();
        System.out.println(value);
        return value;
        
    }
    
    public String toString(){
        return ("(" + "WRITE " + e1.toString() + ")");
    }
    
    public String compile(){
        String R1 = e1.compile(); //compiling e1 should update our total program, and return the register used
        
        if (R1 == null){return null;}
        if (R1.charAt(0) == '#'){
            //R1 something like "# x"
            BasicCompiler.myCode.add("DATA " + R1);
            R1 = BasicCompiler.getFreeRegister();
            BasicCompiler.myCode.add("LOAD " + R1 + ", DR");       
        }
        String toAdd = "WRTIE " + R1 + ", DD";        
        BasicCompiler.myCode.add(toAdd);
        return R1;
    }
    
}
