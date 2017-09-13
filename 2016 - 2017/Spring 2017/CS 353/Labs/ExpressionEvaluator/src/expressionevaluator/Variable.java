/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionevaluator;

import java.util.Hashtable;

/**
 *
 * @author Marco
 */
public class Variable {
    
    static Hashtable<String,Integer> vars = new Hashtable();
   
    public void setVar(String key, int value){      
        vars.put(key, value);
    }
    
    public int getVar(String key){
        
        return vars.get(key);
        
    }
    
}
