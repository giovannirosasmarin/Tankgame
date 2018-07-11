package framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

//import Objects.Enemy;
import Objects.Tank;
import Objects.Enemy;

/**
 *
 * @author anthony-pc
 */
public class TankControl extends Observable implements KeyListener {
    private Enemy t2;
    private Tank t1;

    private  int up;
    private  int down;
    private int right;
    private  int left;


    private  int vkW;
    private  int vkS;
    private  int vkA;
    private int vkD;



    public TankControl(Tank id, int up, int down, int left, int right) {
        this.t1 =   id;

        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;


    }
    public TankControl(Enemy t2, int vkW, int vkS, int vkA, int vkD) {
        this.t2 = t2;
        this.vkW = vkW;
        this.vkS = vkS;
        this.vkA= vkA;
        this.vkD = vkD;


    }


    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();


        if (keyPressed == up) {
            this.t1.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.t1.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.t1.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.t1.toggleRightPressed();
        }
        if (keyPressed == vkW) {
            this.t2.toggleUpPressed();
        }
        if (keyPressed == vkS) {
            this.t2.toggleDownPressed();
        }
        if (keyPressed == vkA) {
            this.t2.toggleLeftPressed();
        }
        if (keyPressed == vkD) {
            this.t2.toggleRightPressed();
        }




    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == up) {
            this.t1.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.t1.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.t1.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.t1.unToggleRightPressed();
        }

        if (keyReleased  == vkW) {
            this.t2.unToggleUpPressed();
        }
        if (keyReleased == vkS) {
            this.t2.unToggleDownPressed();
        }
        if (keyReleased  == vkA) {
            this.t2.unToggleLeftPressed();
        }
        if (keyReleased  == vkD) {
            this.t2.unToggleRightPressed();
        }




    }
}
