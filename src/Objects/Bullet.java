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

    public Bullet(double x, double y, tankGame game)
    {

        this.x = x;
        this.y = y;
        SpriteSheet ss = new SpriteSheet ( game.getSpriteSheet ( ) );
        bullet = ss.grabImage ( 2, 1, 32, 32 );
    }

    public void update()
    {
        x-=5;


    }
        public void render (Graphics g)
        {

            g.drawImage ( bullet, (int) x, (int) y, null );
            Graphics2D g2d= (Graphics2D)g;
        }


        public double getX ()
        {
            return x;
        }


        public double getY ()
        {
            return y;
        }


        public void setX ( double x)
        {
            this.x = x;
        }


        public void setY ( double y)
        {
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

