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
public class AndExpr extends OpExpr{

    //super.super.type = "AND";
    
    public AndExpr(Expression e1, Expression e2){
        
        super(e1, e2, "AND");
    }

    @Override
    public int getValue() {
        if(super.expr2.getValue() == 0 || super.expr1.getValue() == 0){
            return 0;
        }else{
            return 1;
        }
        
    }

    @Override
    public String toString() {
        return ("(" + super.expr1 + "&" + super.expr2 + ")");
    }
    
    public String compile(){
        String R0 = super.expr2.compile();
        String R1 = super.expr1.compile();
        
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
        
        //set the first free register to the value;
               
        String R2 = BasicCompiler.getFreeRegister();
        if (R2 == null){System.out.println("ERROR OUT OF REGISTER SPACE"); return null;}
        
        String J0 = BasicCompiler.getFreeJump();
        if (J0 == null){System.out.println("ERROR OUT OF REGISTER SPACE"); return null;}
             
           
        String toAdd =       
        "SET "  + R2 + ",0 \n" + 
        "DATA #skip \n" + 
        "COPY DR, " + J0 + "\n" + 
        "DATA 1 \n" + 
        "SUB " + R0 + ", " + "DR \n" +
        "JPIF DR" + ",NZ, " + J0 + "\n" +  
        "SUB " + R1 + ", " + "DR \n" +
        "JPIF DR" + ",NZ, " + J0 + "\n" +  
        "SET " + R2 + ", 1 \n" +
        "#skip"; 
        
        BasicCompiler.myCode.add(toAdd);
        BasicCompiler.openRegister(R0);
        BasicCompiler.openRegister(R1);
        BasicCompiler.openRegister(J0);
        BasicCompiler.closeRegister(R2);
        return R2;
        
       
        
    }
    
}
