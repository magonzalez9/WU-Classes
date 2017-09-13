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
public class XOR extends BinGate{
    
    boolean gateOne;
    boolean gateTwo;
    
public boolean XORgate(boolean gateOne, boolean gateTwo){
      
if( (gateOne==false)&&(gateTwo==true) ){
    return true;
} else if((gateOne==true)&&(gateTwo==false)){
    return true;
    }
else return false;
}
}
