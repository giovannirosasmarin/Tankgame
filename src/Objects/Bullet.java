package Objects;

import Animacion.SpriteSheet;
import window.TankGameObjectHandler;
import window.tankGame;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Bullet extends TankGameObjects{

    private int r = 5;
    private BufferedImage bullet;
    public TankGameObjectHandler objectHandler;
    tankGame game;
    private int lives;


    public Bullet(int x, int y,short angle, TankGameObjectHandler handler, ObjectId id, tankGame game)
    {
        super ( x, y,0,0, angle, id );
        this.objectHandler = handler;
        this.game = game;
        SpriteSheet ss = new SpriteSheet ( game.getSpriteSheet ( ) );
        bullet = ss.grabImage ( 2, 1, 32, 32 );
    }


    @Override
    public void update(LinkedList<TankGameObjects> object)
    {
        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x+= vx;
        y+= vy;


        Collision ( object );
    }
    @Override
    protected void Collision(LinkedList<TankGameObjects> object)
    {
        for (int i=0;i<objectHandler.object.size ();i++)
        {
            TankGameObjects tempObject = objectHandler.object.get ( i );

            if(tempObject .getId ()== ObjectId.Wall)
            {

                if(getBoundsTop ().intersects ( tempObject.getBounds () )||getBounds ().intersects ( tempObject.getBounds () )||
                        getBoundsRight ().intersects ( tempObject.getBounds () )||getBoundsLeft ().intersects ( tempObject.getBounds () ))
                {

                    r=0;
                    y=1200;
                    x=0;
                }
            }
            if(tempObject .getId ()== ObjectId.Tank)
            {

                if(getBoundsTop ().intersects ( tempObject.getBounds () )||getBoundsTop ().intersects ( tempObject.getBoundsLeft () )||
                        getBoundsTop ().intersects ( tempObject.getBoundsRight () )||getBoundsTop ().intersects ( tempObject.getBoundsTop () )){

                    tankGame.Health -=10;
                    r=0;
                    y=1200;
                    x=0;
                    if (tankGame.Health == 0)
                    {
                        tempObject.setX ( 1180 );
                        tempObject.setY ( 860 );
                        tankGame.Health = 100 * 2;
                        lives++;
                        System.out.println ( "Destroyed" );
                    }

                }

                if(getBounds ().intersects ( tempObject.getBounds () )||getBounds ().intersects ( tempObject.getBoundsLeft () )||
                        getBounds ().intersects ( tempObject.getBoundsRight () )||getBounds().intersects ( tempObject.getBoundsTop () )){

                    tankGame.Health -=10;
                    r=0;
                    y=1200;
                    x=0;
                    if (tankGame.Health == 0)
                    {
                        tempObject.setX ( 1180 );
                        tempObject.setY ( 860 );
                        tankGame.Health = 100 * 2;

                        System.out.println ( "Destroyed" );
                    }


                }
                //right
                if(getBoundsRight ().intersects ( tempObject.getBounds () )||getBoundsRight ().intersects ( tempObject.getBoundsLeft () )||
                        getBoundsRight ().intersects ( tempObject.getBoundsRight () )||getBoundsRight ().intersects ( tempObject.getBoundsTop () )){

                    tankGame.Health -=10;
                    r=0;
                    y=1200;
                    x=0;
                    if (tankGame.Health == 0)
                    {
                        tempObject.setX ( 1180 );
                        tempObject.setY ( 860 );
                        tankGame.Health = 100 * 2;

                        System.out.println ( "Destroyed" );
                    }
                }

                //left
                if(getBoundsLeft ().intersects ( tempObject.getBounds () )||getBoundsLeft ().intersects ( tempObject.getBoundsLeft () )||
                        getBoundsLeft ().intersects ( tempObject.getBoundsRight () )||getBoundsLeft ().intersects ( tempObject.getBoundsTop () )){

                    tankGame.Health -=10;
                    r=0;
                    y=1200;
                    x=0;

                     if(tankGame.Health == 0)
                     {

                            tempObject.setX ( 1180 );
                            tempObject.setY ( 860 );
                            tankGame.Health = 100 * 2;

                            System.out.println ( "Destroyed" );

                      }
                }
            }
       }
   }


@Override
    public Rectangle getBounds() {
        return new Rectangle ( (int)(x+4)+(bullet.getWidth ()/2)-((bullet.getWidth ()/2)/2),(int)y+(bullet.getHeight ()/2),bullet.getWidth ()/4,bullet.getHeight ()/4 );
    }
    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle ( (int)(x+4)+(bullet.getWidth ()/2)-((bullet.getWidth ()/2)/2),(int)y+bullet.getHeight ()/4 ,bullet.getWidth ()/4,bullet.getHeight ()/4 );
    }
    @Override
    public Rectangle getBoundsRight() {
        return new Rectangle ( (int)x+bullet.getWidth ()-9,(int)y+10,5,bullet.getHeight ()-20 );
    }

    @Override
    public Rectangle getBoundsLeft() {
        return new Rectangle ( (int)x+4,(int)y+10,5,bullet.getHeight ()-20 );
    }

    //big rectangles not good for the small bullet that I drew
//@Override
//public Rectangle getBounds() {
//    return new Rectangle ( (int)x+(bullet.getWidth ()/2)-((bullet.getWidth ()/2)/2),(int)y+(bullet.getHeight ()/2),bullet.getWidth ()/2,bullet.getHeight ()/2 );
//}
//    @Override
//    public Rectangle getBoundsTop() {
//        return new Rectangle ( (int)x+(bullet.getWidth ()/2)-((bullet.getWidth ()/2)/2),(int)y,bullet.getWidth ()/2,bullet.getHeight ()/2 );
//    }
//    @Override
//    public Rectangle getBoundsRight() {
//        return new Rectangle ( (int)x+bullet.getWidth ()-5,(int)y+3,5,bullet.getHeight ()-5 );
//    }
//
//    @Override
//    public Rectangle getBoundsLeft() {
//        return new Rectangle ( (int)x,(int)y+3,5,bullet.getHeight ()-5 );
//    }


    @Override
    public void render (Graphics g)
        {

            AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
            rotation.rotate(Math.toRadians(angle), bullet.getWidth() / 2, bullet.getHeight() / 2);
            Graphics2D g2d = (Graphics2D) g;

            g2d.drawImage( bullet, rotation, null);

            //this gives the bounds of the player
//            g.setColor (Color.red );
//            g2d.draw (getBounds()  );
//            g2d.draw ( getBoundsTop() );
//            g2d.draw (  getBoundsRight());
//            g2d.draw ( getBoundsLeft() );

       }



    }

