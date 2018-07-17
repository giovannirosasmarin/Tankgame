package Objects;

import window.tankGame;
import java.awt.*;


public class Wall {

    private double x;
    private double y;
    tankGame game;

    public Wall(double x, double y, tankGame game)
    {

        this.x = x;
        this.y = y;
        this.game =game;


    }

    public void update()
    {



    }

    public void render(Graphics g)
    {

        g.setColor ( Color.BLACK );
        g.fillRect  ( (int) x, (int) y, 98, 32 );
    }



    public Rectangle getBounds() {
        return new Rectangle ( (int) x, (int) y, 32, 32 );
    }

    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }


    public void setX(double x) {
        this.x = x;
    }


    public void setY(double y) {
        this.y = y;
    }

}

