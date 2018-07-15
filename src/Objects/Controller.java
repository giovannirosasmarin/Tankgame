package Objects;

import window.tankGame;

import java.awt.*;
import java.util.LinkedList;

public class Controller {

    private LinkedList<Bullet> b = new LinkedList<Bullet> (  );

    Bullet TempBullet;
    tankGame game;
    public Controller(tankGame game){
        this.game = game;
//        addBullet (  new Bullet ( 100,300,game ) );
    }

    public void tick(){
        for (int i= 0;i<b.size ();i++){
            TempBullet = b.get ( i );
            if(TempBullet.getX ()<0)
                removeBullet ( TempBullet );
            TempBullet.tick ();
        }
    }

    public void render(Graphics g){

        for (int i= 0;i<b.size ();i++){
            TempBullet = b.get ( i );

            TempBullet.render (g);
        }
    }
    public void addBullet(Bullet block){
        b.add(block);
    }
    public void removeBullet(Bullet block){
        b.remove (block);
    }


}