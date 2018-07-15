package Objects;

import Animacion.SpriteSheet;
import window.tankGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private double x;
    private double y;
//    private double vx = 0;
//    private double vy = 0;

    private BufferedImage bullet;

    public Bullet(double x, double y, tankGame game) {

        this.x = x;
        this.y = y;
        SpriteSheet ss = new SpriteSheet ( game.getSpriteSheet ( ) );
        bullet = ss.grabImage ( 2, 1, 32, 32 );
    }

    public void tick() {
        x-=5;

//ticks the x and the velocity
//        x+= vx;
//        y+= vy;


//border collision of player
//        if(x<=0)
//            x=0;
//        if(x>=640-20)
//            x=640-20;
//        if (y<=0)
//            y=0;
//        if(y>=480-32)
//            y=480-32;
    }
        public void render (Graphics g){
            g.drawImage ( bullet, (int) x, (int) y, null );
        }


        public double getX () {
            return x;
        }


        public double getY () {
            return y;
        }


        public void setX ( double x){
            this.x = x;
        }


        public void setY ( double y){
            this.y = y;
        }
//    public void setVx(double vx) {
//        this.vx= vx;
//    }
//
//
//    public void setVy(double vy) {
//        this.vy =vy;
//    }

    }

