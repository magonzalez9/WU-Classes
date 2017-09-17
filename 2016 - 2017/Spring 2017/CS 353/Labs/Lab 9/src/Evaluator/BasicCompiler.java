/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

import java.util.ArrayList;

/**
 *
 * @author Eric Keefe
 */
public class BasicCompiler {
    
    Expression myExpr;
    public static ArrayList <String> myCode = new ArrayList <String>();
    public static ArrayList<String> varCode = new ArrayList <String>();
    public static int[] freeList = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}; //keeping track of which normal registers are open
    public static int[] jumpList = {1, 1, 1, 1}; //keeping track of which jump registers are open
    //intitally all registers are free so are set to 1
    //we are not using the program counter for this, and i'm assuming the data register is always free (to be used temporarily within a function)
    
       
    public BasicCompiler(Expression e){
        myExpr = e;
        String result = myExpr.compile(); //should return the resulting register
        myCode.add("WRITE " + result + ", DD");
        myCode.add("HALT");
        //add in all the variables
    } 
    
    public void print(){
        for (int i = 0; i < myCode.size(); i ++){
            System.out.println(myCode.get(i));
        }
        for (int i = 0; i < varCode.size(); i++){
            System.out.println(varCode.get(i));
        }
        System.out.println("printing out the free register list!");
        for (int i = 0; i < freeList.length; i ++){
            System.out.println(freeList[i]);
        }
    }
    
    public static void openRegister(String register){
        int reg = Integer.parseInt(register.substring(1, register.length())); //getting the index of the register
        BasicCompiler.freeList[reg] = 1; //opening this register as the result is saved in R1
    }
    
    public static void closeRegister(String register){
        int reg = Integer.parseInt(register.substring(1, register.length())); //getting the index of the register
        BasicCompiler.freeList[reg] = 0; //opening this register as the result is saved in R1
    }
    
    public static String getFreeRegister(){
        for (int i = 0; i < freeList.length; i++){
            if (freeList[i] == 1){
                freeList[i] = 0; //closing it cause it's about to be in use
                return ("R" + i);
                
            }
        }
        return null;
    }
    
    public static String getFreeJump(){
        for (int i = 0; i < jumpList.length; i++){
            if (jumpList[i] == 1){
                jumpList[i] = 0; //closing it cause it's about to be in use
                return ("J" + i);
                
            }
        }
        return null;
    }
    
    public static boolean alreadyInRam(String var){
        for (int i = 0; i < varCode.size(); i++){
            String line = varCode.get(i);
            if (line.charAt(0) == '#'){
                if (line.substring(1, line.length()).equals(var)){
                    //System.out.println("Im not reputting it in RAM since I've already encounterd this variable");
                    return true;
                }
            }
            
        }
        return false;
    }
    
    public static int getValue(String var){
        for (int i = 0; i < varCode.size(); i++){
            String line = varCode.get(i);
            if (line.charAt(0) == '#'){
                if (line.substring(1, line.length()).equals(var)){
                    //System.out.println("Im not reputting it in RAM since I've already encounterd this variable");
                    String nextLine = varCode.get(i + 1);
                    if (nextLine == null){System.out.println("weird, someting has gone wrong!!");}
                    String number = nextLine.substring(6, nextLine.length());
                    return Integer.parseInt(number);
                    
                }
            }
            
        }
        return -1;
    }
    
    public static void setValue(String var, int value){
        for (int i = 0; i < varCode.size(); i++){
            String line = varCode.get(i);
            if (line.charAt(0) == '#'){
                if (line.substring(1, line.length()).equals(var)){
                    //System.out.println("Im not reputting it in RAM since I've already encounterd this variable");
                    String nextLine = "CONST " + Integer.toString(value);
                    varCode.set(i+1, nextLine);
                    
                }
            }
            
        }
    }
        
    
}
