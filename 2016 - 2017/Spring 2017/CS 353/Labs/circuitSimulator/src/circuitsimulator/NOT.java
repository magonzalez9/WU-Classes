/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

/**
 *
 * @author Marco
 */
public class NOT extends MonGate{
    
    public boolean NOTgate(boolean gateOne){
        if (gateOne == true){
            return false;
        } else{
            return true; 
        }
    }
    
}
