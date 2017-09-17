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
public class MultExpr extends OpExpr{
    
    int id;
    
    public MultExpr(Expression e1, Expression e2, int id){
        super(e1, e2, "Mult");
        this.id = id;
    }

    @Override
    public int getValue() {
        return super.expr2.getValue() * super.expr1.getValue(); //evaluation from left to right
    }

    @Override
    public String toString() {
        return ("(" + super.expr1 + "*" + super.expr2 + ")");
    }
    
    public String compile(){
        id += 66;
        char letter = (char) id;
        System.out.println("The letter we are using is: " + letter);
        
        String le = Character.toString(letter);
        
        String R0 = super.expr2.compile(); //compiling e2 should update our total code, and return the register used
        String R1 = super.expr1.compile(); //compiling e1 should update our total program, and return the register used
        //need to get two more registers to use, and just call them R2 and R3
        //need to get two jump registers to use and just call them J0 and J1
        
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
        
        
        
        int free1 = -1;
        int free2 = -1;
        
        for (int i = 0; i < BasicCompiler.freeList.length; i++){
            if (BasicCompiler.freeList[i] == 1){
                if (free1 == -1){
                    free1 = i;
                }else{
                    free2 = i;
                    break;
                }
            }
        }
        
        if (free1 == -1 || free2 == -1){System.out.println("ERROR, no free register for multiply"); return null;}
        
        String R2 = "R" + free1;
        String R3 = "R" + free2;
        
        int jump1 = -1;
        int jump2 = -1;
        
        for (int i = 0; i < BasicCompiler.jumpList.length; i++){
            if (BasicCompiler.jumpList[i] == 1){
                if (jump1 == -1){
                    jump1 = i;
                }else{
                    jump2 = i;
                    break;
                }
            }
        }
        if (jump1 == -1 || jump2 == -1){System.out.println("ERROR, no free register for multiply"); return null;}
        
        String J0 = "J" + jump1;
        String J1 = "J" + jump2;
        
        //add the multiplication String to our total code
        
        //BasicCompiler.myCode.add(
        
    String toAdd =  
    "DATA #skip" + le +  "\n" + 
    "COPY DR, " + J0 + "\n" +
    "DATA #multi" + le +  "\n" +
    "COPY DR," + J1 + "\n" + 

    "ZERO " + R2 + "\n" + 
    "DATA 1 \n" +          
    
    "#multi" + le +  "\n" + 
    "COPY DR," + R3 + "\n" +     
    
    "AND " + R1 + "," + R3 + "\n" +
    "JPIF " + R3 + ",EZ," + J0 + "\n" +
    "ADD " + R0 + "," + R2 + "\n" +
    
    "#skip" + le + "\n" +
    "SHIFT " + R0 + ",-1 \n" +
    "SHIFT " + R1 + ",1 \n" +
    "JPIF " + R1 + ",NZ," + J1 +  "\n";
    
    
    BasicCompiler.myCode.add(toAdd); 



//although we used all the registers temporarily, the only register we are returning is R2, so we only need to close R2 
int reg2 = Integer.parseInt(R2.substring(1, R2.length())); //getting the index of R2
BasicCompiler.freeList[reg2] = 0; //closing the reigister holding the result

//open the register that is R1
int reg1 = Integer.parseInt(R1.substring(1, R1.length()));
BasicCompiler.freeList[reg1] = 1; //opened the other register

//also open the register that was R0
int reg0 = Integer.parseInt(R0.substring(1, R0.length()));
BasicCompiler.freeList[reg0] = 1; //opened the other register

return R2; //this isn't returning "R2", it's returning whatever register R2 is    
        
    }
}
