/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game1;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import javalib.colors.*; 

import java.awt.*;

public class Block {
    int h = 50;
    int w = 120;

    // Posn
    Posn posn;
    protected Color type;

    public Block(Posn p, Color c) {
        posn = p;
        this.type = c;
    }
    
    
    /*
     * Returns a Block of random color on the top of random column
     */
    public Block () {        ;
    }

    
    public Block next() {
        int le = this.posn.y + 50;
        return(new Block((new Posn(this.posn.x, le)),this.type));
    }
    
    public Block left() {
        int le = this.posn.x - 120;
        return(new Block((new Posn(le,this.posn.y)),this.type));
    }
    
    public Block right() {
        int le = this.posn.x + 120;
        return(new Block((new Posn(le,this.posn.y)),this.type));
    }
    
    public boolean inRange() {
        return ((this.posn.x > 0) &&
                (this.posn.x < 480) &&
                (this.posn.y > 0) &&
                (this.posn.y < 600));
        
    }

    /*
    * Returns the type of this block
    */
    public Color type() {
        return this.type;
    }

    /*
    * Returns the Posn of this block
    */
    public Posn posn() {
        return this.posn;
    }
    
    /*
    * return a color conrresponding to the given integer from 1 to 5
    */    
    static Color blockType(int t) {
        if (t==1) {
            return Color.white;
        } else
        if (t==2) {
            return Color.lightGray;
        } else
        if (t==3) {
            return Color.gray;
        } else
        if (t==4) {
            return Color.darkGray;
        } else {
            return Color.black;
        }            
    }
    
    public Block changeColor() {
        if (this.type==Color.white) {
            this.type = Color.lightGray;            
            return(this);
        } else
        if (this.type==Color.lightGray) {
            this.type = Color.gray;
            return(this);
        } else
        if (this.type==Color.gray) {
            this.type = Color.darkGray;
            return(this);
        } else
        if (this.type == Color.darkGray) {
            this.type = Color.black;
            return(this);
        } else return(this);
    }
    
    /*
    * Returns if Block b has the same color as this block.
    */
    public boolean isSameType(Block b) {
        return this.type.equals(b.type());
    }



    public WorldImage draw() {
        //Return a RectangleImage of the block
        return new RectangleImage(this.posn, this.w, this.h, this.type);
    }

    /*
    * Returns a boolean if this block is the same as Block b
     */
    public boolean equals(Block b) {
        return ((this.posn.x == b.posn.x) && (this.posn.y == b.posn.y) &&
                (this.type.equals(b.type())));
    }

}
