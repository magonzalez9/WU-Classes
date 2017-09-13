/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

/**
 *
 * @author Marsha
 */
public class PlusExpr extends OpExpr{
    
    public PlusExpr(Expression e1, Expression e2){
        super(e1, e2, "plus");
    }

    @Override
    public int getValue() {
        return super.expr2.getValue() + super.expr1.getValue(); //evaluation from left to right
    }

    @Override
    public String toString() {
        return ("(" + super.expr1 + "+" + super.expr2 + ")");
    }
    
    public String compile(){
        String R2 = super.expr2.compile(); //compiling e2 should update our total code, and return the register used
        String R1 = super.expr1.compile(); //compiling e1 should update our total program, and return the register used
        
        if (R1 == null || R2 == null){return null;}
        if (R1.charAt(0) == '#'){
            //R1 something like "# x"
            BasicCompiler.myCode.add("DATA " + R1);
            R1 = BasicCompiler.getFreeRegister();
            BasicCompiler.myCode.add("LOAD " + R1 + ", DR");       
        }
        if (R2.charAt(0) == '#'){
            //R1 something like "# x"
            BasicCompiler.myCode.add("DATA " + R2);
            R2 = BasicCompiler.getFreeRegister();
            BasicCompiler.myCode.add("LOAD " + R2 + ", DR");       
        }
        
        
        
        
        
        String toAdd = "ADD " + R1 + ", " + R2;
        BasicCompiler.myCode.add(toAdd);
        //R2 is now free 
        int reg2 = Integer.parseInt(R2.substring(1, R2.length())); //getting the index of R2
        BasicCompiler.freeList[reg2] = 0; //closing the reigister holding the result
        int reg1 = Integer.parseInt(R1.substring(1, R1.length()));
        BasicCompiler.freeList[reg1] = 1; //opened the other register
        return R2; //returning R2, since we return the register that the result was put in
    }
    
 
}
