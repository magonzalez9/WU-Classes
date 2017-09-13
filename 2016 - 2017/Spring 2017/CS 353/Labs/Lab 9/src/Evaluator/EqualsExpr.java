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
public class EqualsExpr extends OpExpr{
    
    public EqualsExpr(Expression e1, Expression e2){
        super(e1, e2, "Equals");
    }

    @Override
    public int getValue() {
        //testing if expr1 is less than expr2, but need to evaluate from right to left. So 
        if (super.expr2.getValue() == super.expr1.getValue()){
            return 1;
        }else{
            return 0;
        }
        
    }

    @Override
    public String toString() {
        return ("(" + super.expr1 + "==" + super.expr2 + ")");
    }
    
    public String compile(){
        String R0 = super.expr2.compile();
        String R1 = super.expr1.compile();
        
        //set the first free register to the value;
               
        if (R1 == null || R0 == null){return null;}
        if (R1.charAt(0) == '#'){
            //R1 something like "# x"
            BasicCompiler.myCode.add("DATA " + R1);
            R1 = BasicCompiler.getFreeRegister();
            BasicCompiler.myCode.add("LOAD " + R1 + ", DR");       
        }
        if (R0.charAt(0) == '#'){
            //R1 something like "# x"
            BasicCompiler.myCode.add("DATA " + R0);
            R0 = BasicCompiler.getFreeRegister();
            BasicCompiler.myCode.add("LOAD " + R0 + ", DR");       
        }
        
        String R2 = BasicCompiler.getFreeRegister();
        if (R2 == null){System.out.println("ERROR OUT OF REGISTER SPACE"); return null;}
        
        String J0 = BasicCompiler.getFreeJump();
        if (J0 == null){System.out.println("ERROR OUT OF REGISTER SPACE"); return null;}
             
           
        String toAdd =       
        "SET "  + R2 + ",1 \n" + 
        "DATA #skip \n" + 
        "COPY DR, " + J0 + "\n" + 
        "SUB " + R1 + ", " + R0 + "\n" +
        "JPIF " + R0 + ",EZ, " + J0 + "\n" +  
        "SET " + R2 + ", 0 \n" +
        "#skip"; 
        
        BasicCompiler.myCode.add(toAdd);
        BasicCompiler.openRegister(R0);
        BasicCompiler.openRegister(R1);
        BasicCompiler.openRegister(J0);
        BasicCompiler.closeRegister(R2);
        return R2;
    }
 
}
