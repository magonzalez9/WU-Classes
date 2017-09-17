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
public class ExpressionStack {
    
    private ArrayList <Expression> list = new ArrayList <Expression>();
    
    public ExpressionStack(){
        
    }
    
    public boolean empty(){
        return (list.size() == 0);
    }
    
    public Expression getTop(){
        return list.get(0);
    }
    
    public Expression pop(){
        if (list.size() == 0){
            return null;
        }else{
            Expression temp = list.get(0);
            list.remove(0);
            return temp;
        }
    }
    
    public void push(Expression e){
        list.add(0, e);
    }
    
}
