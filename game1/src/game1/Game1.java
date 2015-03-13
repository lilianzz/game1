/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game1;

import javalib.worldimages.Posn;

/**
 *
 * @author 栗粒盐
 */
public class Game1 {

    /**
     * @param args the command line arguments
     */
    static final int w = 480;
    static final int h = 600;
    
    
    public static void main(String[] args) {        

    /* Main method */
        World game = new World(0,
                new Block(new Posn(((((int) (Math.random()*4))+1)*120-60),25), (Block.blockType((((int) (Math.random()*5))+1)))), 
                new Play(480,600),
                0);
        game.bigBang(w, h, 0.2);
    System.out.println("Whether passed the test for height and clear?:  "+Test.heightClear());
    System.out.println("Whether passed the test for height and colorMatch?:  "+Test.heightMatch());
    System.out.println("Whether passed the leftRightEquals test?:  "+Test.leftRightEquals());
    System.out.println("Whether passed the chageColorSame test?:  "+Test.changeColorSame());
    System.out.println("Whether passed the afterMoveInRange test?:  "+Test.afterMoveInRange());
    System.out.println("Whether passed the stopAdd test?:  "+Test.stopAdd());
    System.out.println("Whether passed the case1 test?:  "+Test.case1());
    }

}
