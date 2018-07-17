package Objects;

import window.tankGame;
import java.awt.*;
import java.util.LinkedList;

public class BulletControl {

    private LinkedList<Bullet> b = new LinkedList<Bullet> (  );

    Bullet TempBullet;
    tankGame game;


    public BulletControl(tankGame game)
    {
        this.game = game;
//        addBullet (  new Bullet ( 100,300,game ) );
    }

    public void update(){
        for (int i= 0;i<b.size ();i++)
        {
            TempBullet = b.get ( i );
            if(TempBullet.getX ()<0)
                removeBullet ( TempBullet );
            TempBullet.update ();
        }
    }

    public void render(Graphics g){

        for (int i= 0;i<b.size ();i++){
            TempBullet = b.get ( i );

            TempBullet.render (g);
        }
    }
    public void addBullet(Bullet b)
    {
       this.b.add(b);
    }
    public void removeBullet(Bullet b)
    {
        this.b.remove (b);
    }


}
