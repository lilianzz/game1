/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game1;

/**
 *
 * @author 栗粒盐
 */
import java.awt.Color; 
import java.util.ArrayList;
import javalib.worldimages.*; 
import javalib.worldimages.WorldImage;

public class Play  {
    // Rectangular

    // implementation of the playing field
    // An ArrayList of Blocks
    int width;
    int height;
    ArrayList<Block> landscape;
    
    
    public Play (int w, int h) {
        this.width = w;
        this.height = h;
        landscape = new ArrayList<>(w*h);
    }
    
    public boolean match(Block b) {
        boolean r = false;
            for (Block landscape1 : this.landscape) {
                System.out.println(b.posn.y +""+b.posn.x+""+landscape1.posn.y+""+landscape1.posn.x);
                if (landscape1.equals(b)) {
                    r = true;
                    System.out.println("bump!!!!!!");
                }
            }
            return r;
    }

    public int height() {
        return this.height;
    }
    
    public boolean add(Block b) {
        System.out.println(b.posn.y);
        this.landscape.add(b);
        return true;
    }
    
    public boolean changeColor(Block b) {
        for (Block landscape1 : this.landscape) {
                if (landscape1.equals(b)) {
                    landscape1.changeColor();
                    System.out.println("bump!!!!!!");
                }
            }
        return(true);
    }
    
       
    public boolean colorMatch() {
        int t = this.landscape.size();
        for (int i=0;i<this.landscape.size();i++) {
            for (int j = 0; j<this.landscape.size();j++){
                if ((this.landscape.get(i).next().equals(this.landscape.get(j))) &&
                        !(this.landscape.get(j).type.equals(Color.BLACK))){
                    Block temp = this.landscape.get(j);
                    this.landscape.set(i, this.landscape.get(i).changeColor());
                    this.landscape.remove(j);
                    for (int k = 0; k<this.landscape.size();k++){
                        if ((this.landscape.get(k).posn.x == temp.posn.x) &&
                                (this.landscape.get(k).posn.y < temp.posn.y)){
                            this.landscape.set(k, this.landscape.get(k).next());                                                        
                        }
                    }
                    j--;
                    if (i == this.landscape.size()) { break;}
                }
            }
        }
        if (this.landscape.size()<t) {return(false);} else {return(true);}
    }
    
        
    public boolean checkClear(Block b) {
        int t = 0;
        for (Block landscape1 : this.landscape) {
            if ((landscape1.posn.y == b.posn.y) && (landscape1.isSameType(b))) {
                t++;
            }
        }
        if (t==4) {
            return(true);
        }else
            return(false);
    }
    
    public boolean clear(Block b) {
        for (int i=0;i<this.landscape.size();i++) {
            if ((this.landscape.get(i).posn.y == b.posn.y) &&
                    (this.landscape.get(i).isSameType(b))) {
                this.landscape.remove(i);
                i--;
            } else
                if (this.landscape.get(i).posn.y < b.posn.y) {
                    this.landscape.set(i, this.landscape.get(i).next());
                }
        }
        return(true);
    }
    
    public WorldImage draw() {        
        WorldImage t = new RectangleImage(new Posn(0,0),1,1,Color.blue);             
        for (Block landscape1 : this.landscape) {
            t = new OverlayImages(t,landscape1.draw());
        }
        return t;  
                    
    }
    
    public boolean stop(Block b) {
        if (!b.inRange()) {
            return true;       
        } else {
            boolean r = false;
            for (Block landscape1 : this.landscape) {
                System.out.println(b.posn.y +""+b.posn.x+""+landscape1.posn.y+""+landscape1.posn.x);
                if ((landscape1.posn.x == b.posn.x) && (landscape1.posn.y == b.posn.y)) {
                    r = true;
                    System.out.println("bump!!!!!!");
                }
            }
            return r;
        }
    }

}
