/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluator;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eric Keefe
 */
public class Parser {
    
    private boolean operator = false; //assumption that we are initialy looking for a variable
    private boolean prefixMatch = false; //we are not matching a prefix right now.
    private String prefixType = "++";
    private int multID = 0;
       
    ExpressionStack ES = new ExpressionStack();
    OpStack OS = new OpStack();
    
    public Parser(){
        
    }
    
    
    
    public Expression getExpression(String[] parsed){
        
        
        
        for (int i = 0; i < parsed.length; i++){
            //String token = Character.toString(split.charAt(i));
            String token = parsed[i];
            
            //System.out.println("The token we are at is: ");
            //System.out.println(":" + token + ":");
                        
            if (prefixMatch){ //if we're looking for a prefix match 
                if (isVar(token)){
                    //System.out.println("Im where I want to be");
                    System.out.println(token);
                    System.out.println(prefixType);
                    ES.push(new PreExpr(token, prefixType));
                        prefixMatch = false; //we are no longer looking for a prefix match
                }else{
                    //System.out.println("error, prefix expression without matching varaible");
                    return null;
                }
                continue;
            }
            
            if (isNum(token)){
                //if (operator){System.out.println("INVALID INPUT!"); return null;} //we are looking for an argument
                ES.push(new NumExpr(Integer.parseInt(token)));
                operator = !operator;      
            }else if(token.equals("(")){
                OS.push("("); //don't need to check for reduce 
            }else if(token.toLowerCase().equals("read")){
                ES.push(new ReadExpr());
            }else if(token.toLowerCase().equals("write")){
                OS.push("w");
                //for loop check if conditional is below it, and if so reduce the stack
            }else if (token.equals("=")){
                OS.push("=");
            }
            else if(token.equals("?")){
                String popped = OS.pop();
                while (true){
                    if (popped == null){break;}
                    if (popped.equals("(") || popped.equals("?")){break;}
                    if (reduceOnToken(popped) == -1){
                        return null;
                    }
                    popped = OS.pop();
           
                }
                //check that the expression stack is 
                if (ES.empty()){System.out.println("Error, conditional with no expression before!"); return null;}
                OS.push("?");
                
            }else if(token.equals(":")){
                //push the : and reduce everything before until hitting a question mark. if you reach the end before hitting a ? mark return an error
                String popped = OS.pop();
                while (true){
                    if (popped == null){System.out.println("ERROR!!!!!");return null;}
                    if (popped.equals("(") || popped.equals(":") || popped.equals(")")){System.out.println("ERROR!!!!!");return null;}
                    if (popped.equals("?")){break;}
                    if (reduceOnToken(popped) == -1){ //otherwise reduce once
                        return null;
                    }
                    popped = OS.pop();
           
                }
                OS.push(":");
            }
            
            else if(token.equals(")")){
                //reduce the operator stack until we see a right parenthesis
                String popped = OS.pop();
                while (true){
                    
                    if (popped == null){System.out.println("Error: right paren without matching left paren"); return null;}
                    if (popped.equals("(")){break;}
                    if (reduceOnToken(popped) == -1){
                        return null;
                    }
                    
                    popped = OS.pop();

                
                }
                
            }else if(isVar(token)){
                //if (operator){System.out.println("INVALID INPUT!"); return null;} //we are looking for an argument
                ES.push(new VarExpr(token));
                operator = !operator;
            }else if (isPeeExpr(token)){
                if (ES.empty()){
                    System.out.println("Im empty");
                    prefixMatch = true;
                    prefixType = token;
                    continue;
                }
                System.out.println("I'm here");
                
                if (ES.getTop().type.equals("Var")){
                    //we have a postfix expression
                    ES.push(new PostExpr(ES.pop().toString(), token));
                    
                }else{
                    //we have a prefix expression
                    prefixMatch = true;
                    prefixType = token;
                }
                
            }else if(isBinary(token)){
                //if (!operator){System.out.println("INVALID INPUT!"); return null;}  //we are looking for an operator           
                OS.push(token);
                while(true){
                    String popped = OS.reduce();
                    if (popped == null){
                        break;
                    }
                    Expression e1 = ES.pop();
                    Expression e2 = ES.pop();
                    //System.out.println(e1);
                    //System.out.println(e2);
                    if (e1 == null || e2 == null){
                        System.out.println("ERROR 1, BAD INPUT!");
                        return null;
                    }
                    ES.push(combineTwo(popped, e1, e2));
                }
                operator = !operator;
            }else{
                System.out.println("Error 3! Invalid input!");
                return null;
            }
        }
        
        
        //reducing remaining elements in operation stack and argument stack    
        String popped = OS.pop();
        while (popped != null){
            
            if (popped.equals("(") || popped.equals("(")){
                System.out.println("Error, mismatched parenthesis");
            }
            
            if (reduceOnToken(popped) == -1){ //this has the side effect of making one reduction on the current operator popped
                return null;
            }
            popped = OS.pop();
            
        }
        Expression myExpr = ES.pop();
        if (ES.empty()){
            return myExpr;
        }else{
            return null;
        }
                    
                    
        
    }
    
       
    public int reduceOnToken(String popped){
        if (popped.equals(":")){
            Expression e1 = ES.pop();
            Expression e2 = ES.pop();
            Expression e3 = ES.pop();
            if (e1 == null || e2 == null || e3 == null){System.out.println("ERRRORORORORORO!!"); return -1;}
            ES.push(new CondExpr(e3, e2, e1));
            
        }else if (isBinary(popped)){
            Expression e1 = ES.pop();
            Expression e2 = ES.pop();
            if (e1 == null || e2 == null){
                System.out.println("ERROR 4" + "e1 is: " + e1 + "e2 is: " + e2);
                return -1;
            }

            ES.push(combineTwo(popped, e1, e2));
        }else{
            Expression e1 = ES.pop();
            if (e1 == null){
                System.out.println("ERROR 5" + "e1 is: ");
                return -1;
            }
            ES.push(combineOne(popped, e1));             
        }
        return 0;
        
    }
    
    public Expression combineOne(String popped, Expression e1){
        switch(popped){
            case "w":
                return new WriteExpr(e1);
                 

            default:
                System.out.println("ERROR 2!");
                return null;

            }
    }
    
    public Expression combineTwo(String popped, Expression e1, Expression e2){
              
        switch(popped){
            case "+":
                return (new PlusExpr(e2, e1));
            case "-":
                return (new MinExpr(e2, e1));
            case "*":
                return (new MultExpr(e2, e1, (multID++)));
            case ">":
                return (new GreaterExpr(e2, e1));
            case "<":
                return (new LessExpr(e2, e1));
            case "==":
                return (new EqualsExpr(e2, e1));
            case "&":
                return new AndExpr(e2, e1);
            case "|":
                return new OrExpr(e2, e1);
            case "=":
                if (e2.type.equals("Var")){
                    return new SetExpr(e2.toString(), e1);
                }else{
                    System.out.println("Error, set expression without variable on left");
                    return null;
                }
                
            default:
                System.out.println("ERROR 2!");
                return null;

            }
    }
    
    public boolean isPeeExpr(String token){
        return (token.equals("++") || token.equals("--"));
    }
    
    public boolean isNum(String token){
        String pattern = "[0-9]+"; 
        
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(token);
      
        return (m.find());
    }
    
    public boolean isVar(String token){
        String pattern = "[a-z]+"; 
        
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(token);
      
        return (m.find());
    }
    
    public boolean isBinary(String token){
        return (token.equals("+") || token.equals("*") || token.equals("-") || token.equals(">") 
                || token.equals("<") || token.equals("==") || token.equals("&") || token.equals("|") || token.equals("="));
        
        
    }
    
    
    
}
