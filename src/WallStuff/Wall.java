package WallStuff;

import window.tankGame;

import java.awt.*;
import java.util.LinkedList;

public class Wall extends WallAbstract {

    public Wall(int x, int y, wallId id, tankGame game) {
        super ( x, y, id );
    }

    @Override
    public void update(LinkedList<WallAbstract> object) {

    }

    @Override
    public void render(Graphics g) {

//        g.setColor ( Color.WHITE);
//        g.drawRect ( (int) x, (int) y, 32, 32 );

//        int R = (int) (Math.random( )*256);
//        int G = (int)(Math.random( )*256);
//        int B= (int)(Math.random( )*256);
//        Color randomColor = new Color(R, G, B);

        Color brown = new Color(192,128,64);
        g.setColor(brown);
        g.fillRect  ( (int) x, (int) y, 32, 32 );



    }


    public Rectangle getBounds() {
        return new Rectangle ( (int)x,(int) y, 32,32 );
    }


}
