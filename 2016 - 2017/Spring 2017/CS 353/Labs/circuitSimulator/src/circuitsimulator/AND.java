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
public class AND extends BinGate {

    boolean gateOne;
    boolean gateTwo;
    
    public AND(){
        
    }
 
    public boolean ANDgate(boolean gateOne, boolean gateTwo){
        
     if (((gateOne && gateTwo) == true)){
         
         return true;
            
        } else {
         
         return false;
     }
     
    }
    
    
}