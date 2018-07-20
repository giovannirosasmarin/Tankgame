package WallStuff;

import java.awt.*;
import java.util.LinkedList;

public abstract class WallAbstract {


    protected int x;
    protected int y;
    protected wallId id;


        public WallAbstract(int x, int y, wallId id )
        {
            this.id = id;
            this.x = x;
            this.y = y;

        }
        public abstract void update(LinkedList<WallAbstract> object);


        public abstract void render(Graphics g);


        public double  getX()
        {
            return x;
        }


        public  double getY()
        {
            return y;
        }


        public void setX(int x)
        {
            this.x = x;
        }
    public wallId getId() {
        return id;
    }
        public void setY(int y)
        {
            this.y = y;
        }

    }


