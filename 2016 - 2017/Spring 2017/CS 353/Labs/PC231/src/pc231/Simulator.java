/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc231;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco
 */
public class Simulator {
    
    StringBuilder output = new StringBuilder();
    int[] RAM = new int[256];
    int[] reg = new int [16];
    
    
          
    //Recieve String save it to RAM
    public Simulator(String program){

        StringBuilder lines = new StringBuilder();
        lines.append(program);
        
        String input = lines.toString();
        String[] codes = input.split("\\s+");
        
        for (int i = 0; i < codes.length; i++){
            int decimal = Integer.parseInt(codes[i], 16);
            RAM[i] = decimal;
        }

    }
    
    //0-9: registers
    //10-13: jump registers
    //14: data register
    //15: works as the pointer in RAM 
    public void runProgram(){
        boolean testing = false;
        int temp;
        int counter = 0;
        int code = RAM[reg[15]];
        int register = (code>>>4)&15;
        int value;
        int oldValue;
        int sign;
        int reg1;
        int reg2;
        int device;
        while ((RAM[reg[15]]>>>8) != 0){
            code = RAM[reg[15]];
            register = (code>>>4)&15;
            //switching on the opcodes
            switch(RAM[reg[15]++]>>>8){
                
                case 1: //ZERO
                    
                    temp = reg[register];
                    reg[register] = 0;
                    
                    if (testing){
                        System.out.println("Executing   ZERO instruction ;");
                        System.out.println("Register " + register + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[register]));
                    }
                    break;
                case 2: //SET
                    
                    value = (code&15);
                    temp = reg[register];
                    reg[register] = ((reg[register]>>>4)<<4) + value; 
                    if (testing){
                        System.out.println("Executing   SET instruction ;");
                        System.out.println("Register " + register + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[register]));
                    }
                    //shift right 4 to knock out 4 lowest bits then shift back 4
                    //then add in value
                    break;
                case 3: //DATA
                    value = (code&255);
                    temp = reg[14];
                    reg[14] = value;
                    if (testing){
                        System.out.println("Executing   DATA instruction ;");
                        System.out.println("Register " +  "14: " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[14]));
                    }
                    break;
                case 4: //INC
                    //System.out.println("Code is: " + code);
                    value = (code&15);
                    temp = reg[register];
                    //isolate sign bit
                    //System.out.println("Original value is: " + value);
                    int test = value & 7;
                    //System.out.println("test is: " + test);
                    sign = ((value & 8)>>>3);
                    //System.out.println("sign is: " + sign);
                    //and with 8 to isolate sign bit, then shift left three times
                    value = (value & 7) + 1;
                    //System.out.println("Updated Value is: " + value);
                    if (sign == 1){
                        reg[register] = (convertBack((convertTo(reg[register]) - value)) & 4095); 
                        //reg[register] -= value; // can't just do this
                    }else{
                        reg[register] = (convertBack((convertTo(reg[register]) + value)) & 4095); 
                        //reg[register] += value; //can't just do this
                        
                    }
                    if (testing){
                        System.out.println("Executing   INC instruction ;");
                        System.out.println("Register " + register + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[register]));
                    }
                    break;
                case 5: //SHIFT
                    
                    value = (code&15);
                    temp = reg[register];
                    //isolate sign bit
                    sign = ((value & 8)>>>3);
                    //and with 8 to isolate sign bit, then shift left three times
                    value = (value & 7) + 1;
                    if (sign == 1){
                        reg[register] = ((reg[register] << value) & 4095); //anding with 4095 to prevent more than 8 bits;
                    }else{
                        reg[register] = ((reg[register] &4095) >>> value);
                    }
                    if (testing){
                        System.out.println("Executing   SHIFT instruction ;");
                        System.out.println("Register " + register + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[register]));
                    }
                    break;
                case 6: //ADD
                    reg1 = (code>>>4)&15;
                    reg2 = (code&15);
                    temp = reg[reg2];
                    if (testing){
                        System.out.println(" reg1Value: " + reg[reg1] + " reg2Value: " + reg[reg2]);
                    } 
                    reg[reg2] = (convertBack(convertTo(reg[reg1]) + convertTo(reg[reg2])) & 4095); 
                    if (testing){
                        System.out.println("Executing   ADD instruction ;");
                        System.out.println("Register " + reg2 + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[reg2]));
                    }
                    break;
                case 7: //SUB
                    reg1 = (code>>>4)&15;
                    reg2 = (code&15);
                    temp = reg[reg2];
                    if (testing){
                        System.out.println("reg1Value: " + reg[reg1] + "reg2Value: " + reg[reg2]);
                    }
                    reg[reg2] = (convertBack(convertTo(reg[reg2]) - convertTo(reg[reg1])) & 4095); 
                    if (testing){
                        System.out.println("Executing   SUB instruction ;");
                        System.out.println("Register " + reg2 + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[reg2]));
                    }
                    break;
                case 8: //AND
                    reg1 = (code>>>4)&15;
                    reg2 = (code&15);
                    temp = reg[reg2];
                    if (testing){
                        System.out.println("reg1Value: " + reg[reg1] + "reg2Value: " + reg[reg2]);
                    }
                    reg[reg2] = reg[reg1] & reg[reg2];
                    if (testing){
                        System.out.println("Executing   AND instruction ;");
                        System.out.println("Register " + reg2 + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[reg2]));
                    }
                    break;
                case 9: //COPY
                    reg1 = (code>>>4)&15;
                    reg2 = (code&15);
                    temp = reg[reg2];
                    reg[reg2] = reg[reg1];
                    if (testing){
                        System.out.println("Executing   COPY instruction ;");
                        System.out.println("Register " + reg2 + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[reg2]));
                    }
                    break;
                case 10: //LOAD
                    reg1 = (code>>>4)&15;
                    reg2 = (code&15);
                    temp = reg[reg1];
                    reg[reg1] = RAM[reg[reg2]];
                    if (testing){
                        System.out.println("Executing   LOAD instruction ;");
                        System.out.println("Register " + reg1 + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(reg[reg1]));
                    }
                    break;
                case 11: //STORE
                    reg1 = (code>>>4)&15;
                    reg2 = (code&15);
                    temp = RAM[reg[reg2]];
                    RAM[reg[reg2]] = reg[reg1];
                    if (testing){
                        System.out.println("Executing   STORE instruction ;");
                        System.out.println("RAM Location " + reg[reg2] + ": " + Integer.toHexString(temp) + "=>" + Integer.toHexString(RAM[reg[reg2]]));
                    }
                    break;
                case 12: //READ
                    device = code & 3; //get the device
                    //System.out.println("I'm here");204 218 701 D10
                    
                    String line;  // A local variable to hold the input
                    
                    try { 
                        
                        switch(device){
                        case 0:
                            line = JOptionPane.showInputDialog(null, "Enter a number, please:");
                            reg[register] = convertBack(Integer.parseInt(line, 10));
                            if (testing){
                                System.out.println("Executing   READ instruction ;");
                                System.out.println("The decimal read in was " + Integer.toHexString(reg[register]));
                            }
                            break;
                        case 1:
                            line = JOptionPane.showInputDialog(null, "Enter a number, please:");
                            reg[register] = Integer.parseInt(line, 16);
                            if (testing){
                                System.out.println("Executing   READ instruction ;");
                                System.out.println("The hex read in hex was " + Integer.toHexString(reg[register]));
                            }
                            break;
                        case 2:
                            line = JOptionPane.showInputDialog(null, "Enter a single ascii character, please:");
                            reg[register] = (((int) (line.charAt(0))) & 255);
                            if (testing){
                                System.out.println("Executing   READ instruction ;");
                                System.out.println("The ascii letter read in hex was " + Integer.toHexString(reg[register]));
                            }
                            break;
                        }
                    }
                    
                    catch (Exception e) { 
                        System.out.println(e + "Invalid Input!!");
                    }
                    break;
                case 13: //WRITE
                    device = code & 3; //get the device
                    //System.out.println("device is: " + device);
                    value = reg[register];
                    //System.out.println("actual unconveted value is: " + value);
                    try { 
                        switch(device){
                        case 0:
                            //Store for GUII Purposes
                            output.append("Decimal value: ").append(Integer.toString(convertTo(value))).append("\n");
                            
                            System.out.println("Decimal value: " + Integer.toString(convertTo(value))); // can't just do this
                            //String Builder
                            break;
                        case 1:
                            //Store for GUII Purposes
                            output.append("Hex value: ").append(Integer.toHexString(value)).append("\n");
                            
                            System.out.println("Hex value: " + Integer.toHexString(value));
                            break;
                        case 2:
                            
                            char c = (char) value;
                            //Store for GUII Purposes
                            output.append("Character: ").append(c).append("\n");
                            
                            System.out.println("Character: " + c);
                            break;
                        }
                    }
                    catch (Exception e) { 
                        System.out.println(e + "Invalid Input!!");
                    }
                    System.out.println("Executing   WRITE instruction ;");
                    break;
                    
                case 14: //JPIF
                    //we already have the register
                    if (testing){
                        System.out.println("Executing   JPIF instruction ;");
                    }
                    value = reg[register]; //this is the value we are testing
                    device = ((code & 12) >>>2); //get the device
                    //get the jumpregister
                    
                    reg1 = (code & 3) + 10; //J0 is coded as 0, so we need to add 10, since J0 is register 10.  
                    switch(device){
                        case 0: // LZ
                            if (convertTo(value) < 0){
                                 
                                reg[15] = reg[reg1];
                                if (testing){
                                    System.out.println("Jumping to adress: " + Integer.toHexString(reg[15]));
                                }
                            }else{
                                if (testing){
                                    System.out.println("Condition Failed: not jumping anywhere");
                                }
                            }
                            break;
                        case 1: //GZ
                            if (convertTo(value) > 0){
                                 
                                reg[15] = reg[reg1];
                                if (testing){
                                    System.out.println("Jumping to adress: " + Integer.toHexString(reg[15]));
                                }
                            }else{
                                if (testing){
                                    System.out.println("Condition Failed: not jumping anywhere");
                                }
                            }
                            break;
                        case 2: //EZ
                            if (convertTo(value) == 0){
                                 
                                reg[15] = reg[reg1];
                                if (testing){
                                    System.out.println("Jumping to adress: " + Integer.toHexString(reg[15]));
                                }
                            }else{
                                if (testing){
                                    System.out.println("Condition Failed: not jumping anywhere");
                                }
                            }
                            break;
                        case 3: //NZ
                            if (convertTo(value) != 0){
                                 
                                reg[15] = reg[reg1];
                                if (testing){
                                    System.out.println("Jumping to adress: " + Integer.toHexString(reg[15]));
                                }
                            }else{
                                if (testing){
                                    System.out.println("Condition Failed: not jumping anywhere");
                                }
                            }
                            break;
                    }
                    break;
                case 15: //JUMP
                    value = code & 255; //this is the location in RAM we are jumping to
                    reg[15] = value;
                    if (testing){
                        System.out.println("Executing   JUMP instruction ;");
                        System.out.println("jumping to adress " + Integer.toHexString(reg[15]));
                    }
                    break;
                default: System.out.println("ERROR");
                        break;
            }
        }
        
        
        
            
        
                
    }
    
    //method to convert from a number in decimal to a corresponding negative or positive number
    //eg. 100000001111 is converted to -255
    public int convertTo(int input){
        //isolate sign bit
        int sign = (input & 2048) >> 11;
        int value = input & 2047;
        if (sign == 1){value *= -1;}
        return value;   
        
    }
    
    //method to convert from a number in decimal to corresponding positive number, with corresponding sign bit    
    //e.g. -255 is converted to 100000001111
    public int convertBack(int input){
        if (input > 0){
            input = input & 2047; //making sure a positive number uses a maximum of 11 bits, otherwise it's too big.
        }else if (input < 0 ){
            input*=-1;
            input+=2048;
        }  
        return input;   
    }
    
    public String returnData(){
        
        return"d";
    }
   @Override
  public String toString() {
      
    return output.toString();
    
  }

    
}
