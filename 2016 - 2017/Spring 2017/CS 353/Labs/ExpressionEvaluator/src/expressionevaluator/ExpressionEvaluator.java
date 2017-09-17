/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expressionevaluator;

/**
 *
 * @author Marco
 */
public class ExpressionEvaluator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    Expression hello = new plusExpression( new multExpression( new preExpression("++", new varExpression("x"),"x"), new numExpression(3)),
                     new plusExpression( new numExpression(5), new setExpression("x", new READexpression())));
    
  //   Expression hello = new condExpression(new lessExpression(new numExpression(9), new numExpression(2)),
    //                                        new numExpression(10), new numExpression(21));
    
    //Expression hello = new multExpression(new plusExpression(new numExpression(2), new numExpression(4)), 
      //                      new minExpression(new numExpression(2), new numExpression(9)));
    
//    Expression hello = new plusExpression(new orExpression(new numExpression(2), new numExpression(3)), new numExpression(9));

        System.out.println(hello.toString());
        System.out.println(hello.getValue());
    
    
    }
    
}
