package Objects;

import Animacion.SpriteSheet;
import window.tankGame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author anthony-pc
 */

//Modified to fit with my keyInput and main tankGame class
public class Tank  {

    private double x;
    private double y;
    private final int r = 3;
    private int vx;
    private int vy;
    private short angle;
    private BufferedImage Tank1;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;

    public Tank(double x, double y, int vx, int vy, short angle, tankGame game)
    {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;

        SpriteSheet ss =new SpriteSheet ( game.getSpriteSheet ());
        Tank1 = ss.grabImage ( 1,1,32,32 );

    }
    public void update()
    {
        // x++;   //test to make it move just to the right
        if (this.UpPressed)
        {
            vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
            x+= vx;
            y+= vy;
            getAngle ();
        }

        if (this.DownPressed)
        {
            vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
            x -= vx;
            y -= vy;
            getAngle ();
        }

        if (this.LeftPressed)
        {
            this.angle -= 3;
            getAngle ();
        }
        if (this.RightPressed)
        {
            this.angle += 3;
            getAngle ();
        }

//border collision of player with the window
        if(x<=0) //
            x=0;
        if(x>=1280-20)
            x=12800-20;
        if (y<=0)
            y=0;
        if(y>=960-20)
            y=960-20;
    }

    public void render (Graphics g)
    {

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), Tank1.getWidth() / 2, Tank1.getHeight() / 2);
        Graphics2D graphic2D = (Graphics2D) g;
        graphic2D.drawImage( Tank1, rotation, null);


    }

    public double  getX()
    {
        return x;
    }


    public  double getY()
    {
        return y;
    }


    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setVx(int vx)
    {
        this.vx = vx;
    }

    public void setVy(int vy)
    {
        this.vy = vy;
    }

    public short getAngle(){
        return angle;
    }
    public void setAngle(short angle)
    {
        this.angle = angle;
    }


    public void toggleUpPressed()
    {
        this.UpPressed = true;
    }

    public void toggleDownPressed()
    {
        this.DownPressed = true;
    }

    public void toggleRightPressed()
    {
        this.RightPressed = true;
    }

    public void toggleLeftPressed()
    {
        this.LeftPressed = true;
    }

    public void unToggleUpPressed()
    {
        this.UpPressed = false;
    }

    public void unToggleDownPressed()
    {
        this.DownPressed = false;
    }

    public void unToggleRightPressed()
    {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed()
    {
        this.LeftPressed = false;
    }

}
