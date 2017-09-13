/*
 * Convert numeral into two's compliment form into an array of integers. 
 * Then add those two numbers together and make sure they add up properly. 
 */
package twoscompliment;

import java.util.ArrayList;

/**
 *
 * @author Marco
 */
public class dataManagement {
    private String inputA, inputB; 
    
    ArrayList<Integer> A = new ArrayList<>();
    
    ArrayList<Integer> B = new ArrayList<>();
    
    ArrayList<Integer> remainder = new ArrayList<>(7);
    
    ArrayList<Integer> sumTotal = new ArrayList<>();
    
    ArrayList<Integer> numeralTotal = new ArrayList<>();
    
    // Default Constructor... 
    public dataManagement(){
        
    }
    
    
    public ArrayList<Integer> convertInputA(String inputA){
        
       int base = 2;
       int wholeNumber;
       int remainder;
       int wholeTotal =  Integer.parseInt(inputA);
       
       if(wholeTotal < 0 ){
           
           wholeTotal = ~wholeTotal+2;
           
            do {
             
             remainder = wholeTotal % base;
                A.add(0, ~remainder+2);
           
             wholeNumber = wholeTotal/base;
             wholeTotal = wholeNumber+1;
         
       } while (A.size() < 8 ); 
        
         } else {
           
                do {

                    remainder = wholeTotal % base;
                    A.add(0,remainder);

                     wholeNumber = wholeTotal/base;
                     wholeTotal = wholeNumber;

                } while (A.size() < 8 ); 
       
         }
    
       return A;
    }
    
    public ArrayList<Integer> converInputB(String inputB){
        
       int base = 2;
       int wholeNumber;
       int remainder;
   
       int wholeTotal =  Integer.parseInt(inputB);
       
       if(wholeTotal < 0 ){
           
           wholeTotal = ~wholeTotal+2;
           
            do {
             
             remainder = wholeTotal % base;
                B.add(0, ~remainder+2);
           
             wholeNumber = wholeTotal/base;
             wholeTotal = wholeNumber+1;
         
       } while (B.size() < 8 ); 
            
       } else {
       do {
           
           remainder = wholeTotal % base;
           B.add(0,remainder);
           
            wholeNumber = wholeTotal/base;
            wholeTotal = wholeNumber;
         
       } while (B.size() < 8 ); 
       
      }
       return B;
    
        }
    
    public ArrayList<Integer> addInputs(){
        
        int sum =0;
        int numA;
        int numB;
        int carry;
        
        for (int i = 0; i <=7; i++){
            remainder.add(i,0);
        }
      
        for (int i = 7; i > 0;i--){
            
            numA = A.get(i);
            numB = B.get(i);
  
         if ((numA == 1 && numB == 1) || (remainder.get(i) == 1 && numA == 1) || (remainder.get(i) ==1 && numB == 1) ){
             remainder.set(i-1,1);
           }else{
             remainder.set(i-1,0);
         }
          
        }
        
         
            for (int i = 7; i >= 0; i--){
                
            numA = A.get(i);
            numB = B.get(i);
            carry = remainder.get(i);
            
            sum =  numA+numB+carry;
         
                switch (sum){
                    case 0: sumTotal.add(0,0); 
                        break;
                    case 1: sumTotal.add(0,1);
                        break;
                    case 2: sumTotal.add(0,0);

                        break;
                    case 3: sumTotal.add(0,1);

                        break;
                } 

                //if sign(a) == (b) && sign(a) != sign(b)... sumTotal.clear....sumtoal.add2
                
            }
            
            if ((A.get(0) == B.get(0)) && (sumTotal.get(0) != (A.get(0))) ){
                sumTotal.clear();
                sumTotal.add(404);
            }
        return sumTotal;
        
        
    }
    
    public int converOutput(){
        
        
        int total = 0;
        int base = 2;
          if (sumTotal.get(0) == 1){
           
           for (int i = 0; i <=7 ; i++){
            
           total = total * base + (sumTotal.get(i)-1);
           
         }   
          } else {
              
        for (int i = 0; i <=7 ; i++){
            
           total = total * base + sumTotal.get(i);
           
         }  
        
          }
          
          if (total < 0){
              total = total-1;
          }
             
        return total;
}
   
    
}
