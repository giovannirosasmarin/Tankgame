package Objects;

import framework.GameObject;
import framework.ObjectId;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Observable;

/**
 *
 * @author anthony-pc
 */
public class Enemy extends GameObject {
    private float width =48 , height =96;

    public Enemy(int x, int y, int vx, int vy, short angle, ObjectId id) {
        super ( x, y, vx, vy, angle, id );
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor ( Color.BLUE );
        g.fillRect ( (int)x,(int)y,(int)width, (int)height );

        Graphics2D g2d= (Graphics2D)g;

        System.out.println(this.toString());
    }

    @Override
    public void update(Observable o1, Object o2) {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        this.repaint();
    }

    private void rotateLeft() {
        this.angle -= 3;
    }

    private void rotateRight() {
        this.angle += 3;
    }

    private void moveBackwards() {
        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }

    private void checkBorder() {
        if (x < 0) {
            x = 0;
        }
        if (x >= 740) {
            x = 740;
        }
        if (y < 0) {
            y = 0;
        }
        if (y >= 720) {
            y = 720;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

}
