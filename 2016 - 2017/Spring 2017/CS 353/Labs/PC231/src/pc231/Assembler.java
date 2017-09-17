/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc231;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author Marco
 */
public class Assembler {
    
    StringBuilder hexCodes = new StringBuilder();
    ArrayList<String> lines = new ArrayList <String>();
    Hashtable<String, String> labelMap = new Hashtable<String, String>();
    Hashtable<String, Integer> lMap = new Hashtable<String, Integer>();
    Hashtable<String, String> JPIFMap = new Hashtable<String, String>();
    
    public Assembler(){
        setHashmap();
        setJPIFMap();
        
    }
    
    public void setHashmap(){
        labelMap.put("HALT", "0");
        labelMap.put("ZERO", "1");
        labelMap.put("SET", "2");
        labelMap.put("DATA", "3");
        labelMap.put("INC", "4");
        labelMap.put("SHIFT", "5");
        labelMap.put("ADD", "6");
        labelMap.put("SUB", "7");
        labelMap.put("AND", "8");
        labelMap.put("COPY", "9");
        labelMap.put("LOAD", "A");
        labelMap.put("STORE", "B");
        labelMap.put("READ", "C");
        labelMap.put("WRITE", "D");
        labelMap.put("JPIF", "E");
        labelMap.put("JUMP", "F");
        labelMap.put("R0", "0");
        labelMap.put("R1", "1");
        labelMap.put("R2", "2");
        labelMap.put("R3", "3");
        labelMap.put("R4", "4");
        labelMap.put("R5", "5");
        labelMap.put("R6", "6");
        labelMap.put("R7", "7");
        labelMap.put("R8", "8");
        labelMap.put("R9", "9");
        labelMap.put("J0", "A");
        labelMap.put("J1", "B");
        labelMap.put("J2", "C");
        labelMap.put("J3", "D");
        labelMap.put("DR", "E");
        labelMap.put("PC", "F");
        labelMap.put("LZ", "0");
        labelMap.put("GZ", "1");
        labelMap.put("EZ", "2");
        labelMap.put("NZ", "3");
        labelMap.put("DD", "0");
        labelMap.put("HD", "1");
        labelMap.put("AD", "2");
        labelMap.put("r0", "0");
        labelMap.put("r1", "1");
        labelMap.put("r2", "2");
        labelMap.put("r3", "3");
        labelMap.put("r4", "4");
        labelMap.put("r5", "5");
        labelMap.put("r6", "6");
        labelMap.put("r7", "7");
        labelMap.put("r8", "8");
        labelMap.put("r9", "9");
        labelMap.put("j0", "A");
        labelMap.put("j1", "B");
        labelMap.put("j2", "C");
        labelMap.put("j3", "D");
        labelMap.put("RA", "A");
        labelMap.put("RB", "B");
        labelMap.put("RC", "C");
        labelMap.put("RD", "D");
        labelMap.put("Ra", "A");
        labelMap.put("Rb", "B");
        labelMap.put("Rc", "C");
        labelMap.put("Rd", "D");
        labelMap.put("rA", "A");
        labelMap.put("rB", "B");
        labelMap.put("rC", "C");
        labelMap.put("rD", "D");
        labelMap.put("ra", "A");
        labelMap.put("rb", "B");
        labelMap.put("rc", "C");
        labelMap.put("rd", "D");
        labelMap.put("dr", "E");
        labelMap.put("pc", "F");
        labelMap.put("lz", "0");
        labelMap.put("gz", "1");
        labelMap.put("ez", "2");
        labelMap.put("nz", "3");
        labelMap.put("dd", "0");
        labelMap.put("d0", "0");
        labelMap.put("d1", "1");
        labelMap.put("d2", "2");
        labelMap.put("D0", "0");
        labelMap.put("D1", "1");
        labelMap.put("D2", "2");
        labelMap.put("hd", "1");
        labelMap.put("ad", "2");
        labelMap.put("CONST", " ");
         
    }
    
    public void setJPIFMap(){
        JPIFMap.put("j0", "0");
        JPIFMap.put("j1", "1");
        JPIFMap.put("j2", "2");
        JPIFMap.put("j3", "3");
        JPIFMap.put("J0", "0");
        JPIFMap.put("J1", "1");
        JPIFMap.put("J2", "2");
        JPIFMap.put("J3", "3");
        JPIFMap.put("rA", "0");
        JPIFMap.put("rB", "1");
        JPIFMap.put("rC", "2");
        JPIFMap.put("rD", "3");
        JPIFMap.put("RA", "0");
        JPIFMap.put("RB", "1");
        JPIFMap.put("RC", "2");
        JPIFMap.put("RD", "3");
        JPIFMap.put("ra", "0");
        JPIFMap.put("rb", "1");
        JPIFMap.put("rc", "2");
        JPIFMap.put("rd", "3");
        JPIFMap.put("Ra", "0");
        JPIFMap.put("Rb", "1");
        JPIFMap.put("Rc", "2");
        JPIFMap.put("Rd", "3");
    }
    
    public void run(String pro){
        
        String[] linesOfCode = pro.split(System.getProperty("line.separator"));
        
        for (int i = 0; i<linesOfCode.length;i++){
            lines.add(linesOfCode[i]);
        }
        
    }
   
    public void cleanUp(){
        for (int i = 0; i < lines.size(); i ++){
            String line = lines.get(i);
            //System.out.println(line);
            line = line.trim();
            if (line.length() > 0){
                if (line.charAt(0) == ';'){
                    line = "";
                }else{
                    String[] splitLine = line.split(";");
                    line = splitLine[0];
                }
            }
            
            line = line.trim();
            if (line.equals("")){
                
                lines.remove(i);
                i--;
            }else{
                //convert OPCODES to all capitals.
                if (line.charAt(0) != '#'){
                    //System.out.println("The line we are modifying is: " + line);
                    
                    StringBuilder copy = new StringBuilder(line);
                    int counter = 0;
                    while(true){
                        if (Character.isWhitespace(line.charAt(counter)) || line.length() == counter + 1){
                            line = copy.toString();
                            break;
                        }else{
                            Character replacement = Character.toUpperCase(line.charAt(counter));
                            copy.setCharAt(counter, replacement);
                        }
                        counter ++;
                        //loop through the line converting characters to caps until you hit a space
                        
                    }
                }else{
                    //put the label in the hashmap,delelete that line, then subtract one from i. 
                    System.out.println("the line we are removing is: " + lines.get(i));
                    lMap.put(line, i);
                    lines.remove(i); 
                    i--;
                    continue;
                    
                }
                lines.set(i, line);
            }
          
                       
        }
        
    }
    
    public void getHexCodes(){
        for (int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            String hex = "";

            //checking for HALT
            if (line.charAt(0) == 'h' || line.charAt(0) == 'H' ){
                
                hex = "000 ";
                hexCodes.append(hex);
                
                continue;
            }
     
            String[] splitLine = line.split("\\s+", 2);
            String opcode = splitLine[0];
            String rest = splitLine[1];
            String[] splitted;
            String digitTwo;
            String digitThree;
            String digitOne = labelMap.get(opcode);
            int value;
            int value2;
            
            hex = hex + digitOne;
            switch(hex){
                case "1": //ZERO RRRR ----
                    hex = hex + labelMap.get(rest) + "0";
                    break;
                case "2": //SET RRRR BBBB
                    splitted = rest.split(",", 2);
                    hex = hex + labelMap.get(splitted[0].trim());
                    hex = hex + Integer.toHexString(Integer.parseInt(splitted[1].trim(), 10));
                    break;
                case "3": //DATA BBBB BBBB
                       
                    
                case "F": //JUMP AAAA AAAA
          
                    if (rest.charAt(0) == '\''){
                        value = (int) rest.charAt(1); 
                        digitThree = Integer.toHexString(value);
                        
                        
                    }
                    else if (rest.charAt(0) == '#'){
                        digitThree = Integer.toHexString(lMap.get(rest));
                        
                    }
                    else if (rest.charAt(0) == 'x'){
                        rest = rest.substring(1, rest.length()).toLowerCase();
                        digitThree = Integer.toHexString(Integer.parseInt(rest.trim(), 16));
                    }else{
                        digitThree = Integer.toHexString(Integer.parseInt(rest.trim(), 10));
                    }
                    //System.out.println(rest);
                    
                    if (digitThree.length() > 2){

                        System.out.println(digitThree);
                        hex = hex + "ERROR!!";
                                
                    }else if(digitThree.length() >1){
                        hex = hex + digitThree;
                    }else{
                        hex = hex + "0" + digitThree;
                    }

                    break;
                case "4": //INC RRRR sNNN
                case "5": //SHIFT RRRR sNNN
                    splitted = rest.split(",", 2);
                    hex = hex + labelMap.get(splitted[0].trim());
                    value = Integer.parseInt(splitted[1].trim(), 10);
                    if (value >= 0){
                        
                    }else{
                        value *= -1;
                        value += 8;
                    }
                    value -= 1;
                    digitThree = Integer.toHexString(value);
                    if (digitThree.length() > 1){
                        hex = hex + "ERROR!";
                    }else{
                        hex = hex + digitThree;
                    }
                    break;
                case "6": //ADD RRR1 RRR2
                case "7": //SUB RRR1 RRR2
                case "8": //AND RRR1 RRR2
                case "9": //COPY RRR1 RRR2
                case "A": //LOAD RRR1 RRR2
                case "B": //STORE RRR1 RRR2
                    splitted = rest.split(",", 2);
                    hex = hex + labelMap.get(splitted[0].trim()) + labelMap.get(splitted[1].trim());
                    break;
                case "C": //READ RRRR DDDD
                case "D": //WRiTE RRRR DDDD
                    splitted = rest.split(",", 2);
                    hex = hex + labelMap.get(splitted[0].trim()) + labelMap.get(splitted[1].trim());
                    break;
                case "E": //JPIF RRRR CCJJ
                    splitted = rest.split(",", 3);
                    hex = hex + labelMap.get(splitted[0].trim());
                    digitTwo = labelMap.get(splitted[1].trim());
                    digitThree = JPIFMap.get(splitted[2].trim());
                    value = Integer.parseInt(digitTwo);
                    value *= 4;
                    value += Integer.parseInt(digitThree, 16);
                    hex = hex + Integer.toHexString(value);
                    break;
                case " ":
                    if (rest.charAt(0) == '\''){
                        //System.out.println("I'm here");
                        value = (int) rest.charAt(1); 
                        digitThree = Integer.toHexString(value);
                        
                        
                    }
                    else if (rest.charAt(0) == '#'){
                        digitThree = Integer.toHexString(lMap.get(rest));
                        
                    }
                    else if (rest.charAt(0) == 'x'){
                        rest = rest.substring(1, rest.length()).toLowerCase();
                        digitThree = Integer.toHexString(Integer.parseInt(rest.trim(), 16));
                    }else{
                        value = Integer.parseInt(rest.trim(), 10);
                        if (value < 0){
                            value *= -1;
                            value += 2048;
                        }    
                        digitThree = Integer.toHexString(value);
                        
                        
                    }
                    //System.out.println(rest);
                    hex = "";
                    if (digitThree.length() > 3){
                        hex = hex + "ERROR!!";
                                
                    }else if(digitThree.length() > 2){
                        hex = hex + digitThree;
                    }else if(digitThree.length() > 1){
                        hex = hex + "0" + digitThree;
                    
                    }else{
                        hex = hex + "00" + digitThree;
                    }
                    break;       
                    
            }
            hex = hex + " ";
            hex = hex.toUpperCase();
            hexCodes.append(hex);


        }
        String answer = hexCodes.toString().trim();
        System.out.println("the hex codes generated were: " + answer + "\n LINES: "+lines.get(2));
    }
   
    @Override
   public String toString(){
       
       String[] goodHex = hexCodes.toString().split(" ");
       StringBuilder finalHex = new StringBuilder();
 
       // Makes hex look PRETTY for GUI
      for (int i =0; i< goodHex.length; i++){
           if (i == 9 || i == 19 || i == 29 || i ==39|| i == 49|| i == 59){
               finalHex.append(goodHex[i]+" ").append(System.getProperty("line.separator"));
           } else {
           finalHex.append(goodHex[i]+" ");
           }
       }
        return finalHex.toString();
    }
}

