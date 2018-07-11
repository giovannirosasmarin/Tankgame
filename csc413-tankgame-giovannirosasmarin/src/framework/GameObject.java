package framework;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import java.awt.*;
import javax.swing.JPanel;

    /**
     *
     * @author anthony-pc
     */
    public abstract class GameObject extends JComponent implements Observer {

        protected int x;
        protected int y;
        protected final int r = 2;
        protected int vx;
        protected int vy;
        protected short angle;
        protected BufferedImage img;
        protected boolean UpPressed;
        protected boolean DownPressed;
        protected boolean RightPressed;
        protected boolean LeftPressed;
        protected ObjectId id;

        public GameObject(int x, int y, int vx, int vy, short angle, ObjectId id) {
            this.x = x;
            this.vx = vx;
            this.y = y;
            this.vy = vy;
            this.angle = angle;
            this.id = id;

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

        public void setImg(BufferedImage img) {
            this.img = img;
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

        public ObjectId getId() {
            return id;
        }

    }
