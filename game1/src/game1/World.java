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
import javalib.worldimages.*;
import javalib.worldimages.WorldImage;

public class World extends javalib.funworld.World {

    int score = 0;
    Block live = new Block(
            (new Posn(((((int) (Math.random()*4))+1)*120-60),25)),
            (Block.blockType((((int) (Math.random()*5))+1)))
    );
    Play d;
    int height = 0;
    
    
    //  Holds the state consisting of the playfield and the player piece
    //  TODO: Should the player piece be inside of the PlayField?

    /* Constructor */
    public World(int a, Block b, Play c, int d) {
        this.score = a;
        this.live = b;
        this.d = c;
        this.height = d;
    }
    
    public WorldImage makeImage() {
        WorldImage pic = new RectangleImage((new Posn(240,300)),480,600,Color.blue);
        WorldImage com = new OverlayImages(pic,this.live.draw());
        if (!(this.d.draw() == null)) {
            com = new OverlayImages(com,this.d.draw());
        }
        TextImage s = new TextImage(
                new Posn(430,20),
                "score:"+ Integer.toString(this.score),
                20,
                Color.white
        );
        return(new OverlayImages(com,s));
    }
    public WorldImage makeEnd() {
        WorldImage pic = new RectangleImage((new Posn(240,300)),480,600,Color.blue);
        WorldImage com = new OverlayImages(pic,this.live.draw());
        if (!(this.d.draw() == null)) {
            com = new OverlayImages(com,this.d.draw());
        }
        TextImage s = new TextImage(
                new Posn(430,20),
                "score:"+ Integer.toString(this.score),
                20,
                Color.white
        );
        TextImage s2 = new TextImage(
                new Posn(200,300),
                "GAME OVER Score:"+ Integer.toString(this.score),
                40,
                Color.white
        );        
        return(new OverlayImages(new OverlayImages(com,s),s2));
    }
    
    public WorldEnd worldEnds() {
        boolean over = (height > 11);
        return(new WorldEnd(over,makeEnd()));
    }
    
    //  World onTick()
    //  Block drops
    //  Check for clear & color blend
    public World onTick() {
        /*if (d.match(live.next()) && !(live.type.equals(Color.BLACK))) {
            System.out.println('a');
            d.changeColor(live.next());
            while (d.checkClear(live.next())) {            
                d.clear(live.next());
                score = score + 5;
                while (!d.colorMatch()) {}
            }
            while (!d.colorMatch()) {}
            live = new Block((new Posn(((((int) (Math.random()*4))+1)*120-60),25)),(Block.blockType((((int) (Math.random()*5))+1))));                        
        } else*/
            if (!d.stop(live.next())) {
                this.live = live.next();
            } else {         
                d.add(this.live);          
                while (!d.colorMatch()) { live = live.next(); System.out.println(live.posn.y);}
                while (!(d.checkClear() == (null))) {
                    d.clear(d.checkClear());
                    score = score+5;
                    while (!d.colorMatch()) {}
                }
                /*while (d.checkClear(live)) {
                    d.clear(live);
                    score = score + 5;                    
                    while (!d.colorMatch()) {live = live.next();}
                }     */           
                live = new Block((new Posn(((((int) (Math.random()*4))+1)*120-60),25)),(Block.blockType((((int) (Math.random()*5))+1))));
                height = d.height();
            }      
        return(new World(this.score, this.live, this.d, this.height));        
    }

    //  World onKeyEvent()
        /*  
        Move as directed if it is not stopped by the dead blocks or out of range
        */
    
    @SuppressWarnings("empty-statement")
    public World onKeyEvent(String k) {
        if (k.equals("left")) {
            if (!d.stop(this.live.left())) {
                this.live = this.live.left();            
            }  
            return(this);
        } else
        if (k.equals("right")) {
            if (!d.stop(this.live.right())) {
                this.live = this.live.right();            
            }
            return(this);
        } else
            return(this);
    } 
}
