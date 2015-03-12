/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game1;
import javalib.worldimages.Posn;
import java.awt.Color; 

public class Test {
    // This class will be used to generate and run tests.
        
    static public Play randomBlocks(int n) {
        Play testDead = new Play(480,600);
        for (int i=0; i<n; i++) {
            Block b = (new Block(                  
                (new Posn(((((int) (Math.random()*4))+1)*120-60),
                ((int) (Math.random()*12)+1)*50-25)),
                (Block.blockType((((int) (Math.random()*5))+1)))));
            if (!testDead.stop(b)) {
                testDead.add(b);                
            }
        }
        return(testDead);
    }
    
    static public Block Create(int x, int y, Color c) {  
        return(new Block(
                (new Posn((x*120-60),(y*50-25))),
                c));
    }
    
    static public Block aRandomBlock() {
        return(new Block((new Posn(((((int) (Math.random()*4))+1)*120-60),
                ((int) (Math.random()*12)+1)*50-25)),
                (Block.blockType((((int) (Math.random()*5))+1)))));
    }
    
    static public boolean heightMatch() {
        boolean r = true;
        for (int i = 0; i<100; i++) {
            Play test = new Play(480,600);
            for (int j=0; j<11; j++) {
            Block b = (new Block(                  
                (new Posn(180,((int) (Math.random()*12)+1)*50-25)),
                (Block.blockType((((int) (Math.random()*5))+1)))));
                if (!test.stop(b)) {
                    test.add(b);                
                }
            }
            int h1 = test.height();
            if (!test.colorMatch()) {
                if (!(h1>test.height())) { r = false; test.print();}
            } else if (!(h1==test.height())) { r = false;test.print();}                                   
        }
        return(r);
    }
    
    static public boolean heightClear() {
        boolean r = true;
        for (int i=0; i<200; i++) {
            Play test = randomBlocks(50);
            int h1 = test.height();
            boolean f = false;
            Block a;
            a = null;
            if (!(test.checkClear()==null)) { 
                test.clear(test.checkClear());    
                if (!(h1>test.height())) {r = false; test.print();}                
            }
            else { if (!(h1==test.height())) { r=false;test.print(); }}
        }
        return r;        
    }
    

}
