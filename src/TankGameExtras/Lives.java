package TankGameExtras;

import Animacion.SpriteSheet;
import window.tankGame;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Lives {

        private double x;
        private double y;
        private int vx = 0;
        private int vy = 0;



        private BufferedImage bullet;
        private float width =100 , height =32;

        public Lives(double x, double y, tankGame game)
        {

            this.x = x;
            this.y = y;



//        this.angle=angle;
//        this.vx = vx;
//        this.vy = vy;
            SpriteSheet ss = new SpriteSheet ( game.getSpriteSheet ( ) );
            bullet = ss.grabImage ( 2, 1, 32, 32 );
        }

        public void update()
        {




        }
        public void render (Graphics g)
        {


            Graphics2D graphic2D = (Graphics2D) g;
            g.setColor ( Color.BLUE );

            g.fillOval ( (int)x,(int)y,(int)width, (int)height );


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


