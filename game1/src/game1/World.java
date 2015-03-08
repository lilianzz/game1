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
import javalib.worldcanvas.*;
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
        WorldCanvas c = new WorldCanvas(480, 600);      
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
    
    public WorldEnd worldEnds() {
        boolean over = (height > 11);
        return(new WorldEnd(over,this.lastImage("Reach the ceiling, End!")));
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
                System.out.println("bb"+this.live.posn.y+(!d.stop(live.next())));
                this.live = live.next();
            } else {
                System.out.println("cccc");
                System.out.println(this.live.posn.y);                
                d.add(this.live);          
                while (!d.colorMatch()) {}
                while (d.checkClear(live)) {
                    d.clear(live);
                    score = score + 5;
                    while (!d.colorMatch()) {}
                }                
                live = new Block((new Posn(((((int) (Math.random()*4))+1)*120-60),25)),(Block.blockType((((int) (Math.random()*3))+1))));
            }      
        System.out.println("resel"+this.live.posn.y);
        return(new World(this.score, this.live, this.d, this.height));        
    }

    //  World onKeyEvent()
        /*  
        If direction keys are pressed, check for collision— if a move does not collide, move a the correct direction
        */
    
    @SuppressWarnings("empty-statement")
    public World onKeyEvent(String k) {
        if (k.equals("left")) {
            if (live.left().inRange()) {
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
