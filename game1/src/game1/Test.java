/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game1;
import javalib.worldimages.Posn;
import java.awt.Color; 

public class Test {
    //run tests.
        
    static public Play randomBlocks() {
        Play testDead = new Play(480,600);
        int k;
        Block b;
        for (int i=0; i<4; i++) {
            k = ((int) (Math.random()*12));
            for (int j = 0; j<k; j++) {
                b = createBlock(i,j,
                (Block.blockType((((int) (Math.random()*5))+1))));
                testDead.add(b);           
            }
        }
        return(testDead);
    }
    
    static public Block createBlock(int x, int y, Color c) {  
        return(new Block(
                (new Posn((x*120+60),(575-y*50))),
                c));
    }
    
    static public Block aRandomBlock() {
        return(new Block((new Posn(((((int) (Math.random()*4))+1)*120-60),
                ((int) (Math.random()*12)+1)*50-25)),
                (Block.blockType((((int) (Math.random()*5))+1)))));
    }
    
    static public boolean heightMatch() {
        boolean r = true;
        for (int i = 0; i<200; i++) {
            Play test = new Play(480,600);
            for (int j=0; j<10; j++) {
            Block b = (new Block(                  
                (new Posn(180,(575-j*50))),
                (Block.blockType((((int) (Math.random()*5))+1)))));               
                test.add(b);                
                
            }
            int h1 = test.height();
            if (!test.colorMatch()) {
                if (!(h1>test.height())) { r = false; test.print();}
            } else if (!(h1==test.height())) { r = false;test.print();}                                   
        }
        return(r);
    }
    
    static public boolean leftRightEquals() {
        boolean r = true;
        for (int i = 0; i<200;i++) {
            Block test = aRandomBlock();
            Block t1 = test.left().right();
            Block t2 = test.right().left();
            if (!((test.equals(t1) ) &&(t1.equals(t2)))) { r = false; test.print();} 
        }
        return r;
    }
    
    static public boolean changeColorSame() {
        boolean r=true;
        Play test;
        for (int i=0;i<200;i++) {
            test = new Play(480,600);
            Block t1 = aRandomBlock();            
            Block t2 = new Block(t1.next().posn, (Block.blockType((((int) (Math.random()*5))+1))));
            test.add(new Block(t1.posn,t1.type));
            test.add(new Block(t2.posn,t2.type));         
            test.colorMatch();            
            if (!((test.landscape.size()==1) == ((t1.isSameType(t2)) && !(t1.type == Color.black)))) {
                r=false;
                t1.print();t2.print();test.print();
            }         
        }
        return(r);
    }
    
    static public boolean afterMoveInRange(){
        boolean r=true;
        for (int i=0; i<200;i++) {            
            Block test = aRandomBlock();
            if ((test.left().inRange() == (test.posn.x == 60)) ||
                (test.right().inRange() == (test.posn.x == 420)) ||
                (test.next().inRange() == (test.posn.y == 575))) {
                r = false;          
                test.print();
            }
        }
        return(r);
    }    
 
    static public boolean heightClear() {
        boolean r = true;
        Play test;
        for (int i = 0; i<200; i++) {
            test = randomBlocks();
            int h1 = test.height();          
            if (!(test.checkClear() == (null))) {
                test.clear(test.checkClear()); 
                if (!(h1>test.height())) { r = false; test.print();}
            } else     
            if (!(h1==test.height())) { r = false;test.print();}                                   
        }
        return(r);
    }    
    
    static public boolean case1() {
        Play test = new Play(480,600);
        test.add(createBlock(2,0,Color.darkGray));        
        test.add(createBlock(2,1,Color.gray));
        test.add(createBlock(2,2,Color.lightGray));
        test.add(createBlock(2,3,Color.white));
        test.add(createBlock(2,4,Color.white));
        while (!test.colorMatch()) {}        
        System.out.println("group of dead blocks is: (Should print size 1 and a black block) ");
        test.print();
        if (!(test.landscape.size() ==1)) {return(false);} else {return(true);}        
    }
    
    static public boolean stopAdd() {
        boolean r = true;
        for (int i=0; i<200; i++) {
            Play test = randomBlocks();
            int s1 = test.landscape.size();           
            Block a = aRandomBlock();
            boolean t = test.stop(a);
            if (!t) {test.add(a);}           
            if ((!t) && (s1 == test.landscape.size())) {r = false; test.print();}
        }
        return r;        
    }   
    
    static public boolean case2() {
        Play test = new Play(480,600);
        test.add(createBlock(0,0,Color.darkGray));        
        test.add(createBlock(1,0,Color.darkGray));
        test.add(createBlock(2,0,Color.darkGray));
        test.add(createBlock(3,0,Color.gray));
        test.add(createBlock(0,1,Color.white));        
        test.add(createBlock(1,1,Color.white));        
        test.add(createBlock(3,1,Color.white));   
        test.add(createBlock(3,2,Color.gray));          
        Block live = (createBlock(2,1,Color.white));
        test.add(live);
        int score = 0;
        while (!test.colorMatch()) {}
        while (!(test.checkClear() == (null))) {
            test.clear(test.checkClear());
            score = score+5;
            while (!test.colorMatch()) {}
        }
        System.out.println("Score: "+ score+"  (Should be 10)");
        System.out.println("group of dead blocks is: (Should be empty and "
                + "only show size is 0) ");
        test.print();
        if (score == 10) {return(true);} else {test.print();return(false);}        
    }

}
