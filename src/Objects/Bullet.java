package Objects;

import Animacion.SpriteSheet;
import window.tankGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet {

    private double x;
    private double y;
  private int vx = 0;
   private int vy = 0;
    private final int r = 5;
  private short angle;


    private BufferedImage bullet;

    public Bullet(double x, double y, short angle, tankGame game)
    {

        this.x = x;
        this.y = y;

        this.angle =angle;

//        this.angle=angle;
//        this.vx = vx;
//        this.vy = vy;
        SpriteSheet ss = new SpriteSheet ( game.getSpriteSheet ( ) );
        bullet = ss.grabImage ( 2, 1, 32, 32 );
    }

    public void update()
    {

        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x+= vx;
        y+= vy;







    }
        public void render (Graphics g)
        {

            AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
            rotation.rotate(Math.toRadians(angle), bullet.getWidth() / 2, bullet.getHeight() / 2);
            Graphics2D graphic2D = (Graphics2D) g;
            graphic2D.drawImage( bullet, rotation, null);
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

