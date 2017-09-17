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
public class XNOR extends BinGate{
    
    boolean gateOne;
    boolean gateTwo;
    
    public boolean XNORgate(boolean gateOne, boolean gateTwo){
        
        if( (gateOne==false)&&(gateTwo==true) ){
    return false;
} else if((gateOne==true)&&(gateTwo==false)){
    return false;
    }
else return true;

    }
    
}
