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

    private int x;
    private int y;
    private final int r = 3;
    private int vx;
    private int vy;
    private short angle;
    private BufferedImage Tank1;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;

    public Tank(int x, int y, int vx, int vy, short angle, tankGame game) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;

        SpriteSheet ss =new SpriteSheet ( game.getSpriteSheet ());
        Tank1 = ss.grabImage ( 1,1,32,32 );

    }
    public void tick(){
        // x++;
        if (this.UpPressed) {
            vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
            x+= vx;
            y+= vy;
        }

        if (this.DownPressed) {
            vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
            vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
            x -= vx;
            y -= vy;
        }

        if (this.LeftPressed) {
            this.angle -= 3;
        }
        if (this.RightPressed) {
            this.angle += 3;
        }

//border collision of player
        if(x<=0)
            x=0;
        if(x>=640-20)
            x=640-20;
        if (y<=0)
            y=0;
        if(y>=480-32)
            y=480-32;
    }
    public double  getX() {
        return x;
    }


    public  double getY() {
        return y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public void setAngle(short angle) {
        this.angle = angle;
    }


    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {
        this.DownPressed = true;
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }


    public void render (Graphics g) {



        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), Tank1.getWidth() / 2, Tank1.getHeight() / 2);
        Graphics2D graphic2D = (Graphics2D) g;
        graphic2D.drawImage( Tank1, rotation, null);


    }












}
