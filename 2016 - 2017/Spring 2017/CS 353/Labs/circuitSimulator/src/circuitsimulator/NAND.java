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
public class NAND extends BinGate{
      
   boolean gateOne;
   boolean gateTwo;
   
   public boolean NANDgate(boolean gateOne, boolean gateTwo){
       
       if(gateOne && gateTwo == true){
           
           return false;
           
       } else {
           
           return true;
           
       }
       
       
   }
   
}
