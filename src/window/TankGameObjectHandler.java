package window;

import Objects.*;
import java.awt.*;
import java.util.LinkedList;

public class TankGameObjectHandler// it gets all the objects in the tank game
    {

        private tankGame game;
        public LinkedList<TankGameObjects> object = new LinkedList<TankGameObjects> (  ); //objects in tank game

        private LinkedList<Bullet> b = new LinkedList<Bullet> (  ); //all the bullets shoot
        Bullet TempBullet;

        private LinkedList<BulletTank> bt = new LinkedList<BulletTank> (  ); //all the bullets shoot by tank
        BulletTank TempBulletTank;


        private LinkedList<PowerUp> p= new LinkedList<> (  ); //the health power ups in the game
        PowerUp powerUp;

        private LinkedList<BreakableWall> bw= new LinkedList<> (  ); //breakableWalls made one by one blocks
        BreakableWall Tempwall;


        private TankGameObjects tempObject;

        public void update()
        {
            for (int i = 0; i< object.size (); i++)  //goes through the list of object that we have in each update
            {
                tempObject = object.get ( i );

                tempObject.update ( object );
            }

            for (int i= 0;i<b.size ();i++)//goes through the list of Enemy bullets that we have in each update
            {
                TempBullet = b.get ( i );

                if(TempBullet.getX () ==0 || TempBullet.getY ()==1200||TempBullet.getR ()==0 )
                    removeBullet ( TempBullet );
                TempBullet.update (object);
            }

            for (int i= 0;i<bt.size ();i++)
                                                //goes through the list of Tank bullets that we have in each update
            {
                TempBulletTank = bt.get ( i );

                if(TempBulletTank.getX () ==0 || TempBulletTank.getY ()==1200||TempBulletTank.getR ()==0 )
                    removeBulletTank ( TempBulletTank );
                TempBulletTank.update (object);
            }

            for (int i= 0;i<p.size ();i++)    //goes through the list of health power ups  that we have in each update
            {
               powerUp = p.get ( i );

                if(powerUp.getX () ==1300 )
                    removePower ( powerUp );
                powerUp.update (object);

            }

            for (int i= 0;i<bw.size ();i++)     //goes through the list of breakable walls  that we have in each update
            {
                Tempwall = bw.get ( i );

                if(Tempwall.getX () ==1300 )
                    removeWall ( Tempwall );
                Tempwall.update (object);

            }


        }

        public void render(Graphics g)
        {
            for (int i = 0; i< object.size (); i++)
            {
                tempObject = object.get ( i );

                tempObject.paint ( g );
            }

            for (int i= 0;i<b.size ();i++)
            {
                TempBullet = b.get ( i );

                TempBullet.paint (g);
            }

            for (int i= 0;i<bt.size ();i++)
            {
                TempBulletTank = bt.get ( i );

                TempBulletTank.paint (g);
            }
            for (int i= 0;i<p.size ();i++)
            {
                powerUp= p.get ( i );

                powerUp.paint (g);
            }


            for (int i= 0;i<bw.size ();i++)
            {
                Tempwall = bw.get ( i );

                Tempwall.paint (g);

            }

        }

        public void addObject(TankGameObjects object)
        {
            this.object.add(object); //"this." referes to the object linkedList

        }

        public void  removeObject(TankGameObjects object)
        {
            this.object.remove (object);
        }


        public void addBullet(Bullet b)
        {
            this.b.add(b);
        }
        public void removeBullet(Bullet b)
        {
            this.b.remove (b);
        }
        public void addBulletTank(BulletTank bt)
        {
            this.bt.add(bt);
        }
        public void removeBulletTank(BulletTank bt)
        {
            this.bt.remove (bt);

        }
        public void addPower(PowerUp p)
        {
            this.p.add(p);
        }
        public void removePower(PowerUp p)
        {
            this.p.remove (p);

        }
        public void addWall(BreakableWall bw)
        {
            this.bw.add(bw);
        }
        public void removeWall(BreakableWall bw)
        {
            this.bw.remove (bw);

        }


        public void Walls()
        {


            for(int xx = 0; xx < tankGame.WIDTH; xx += 32)//Top
                addObject(new Wall (xx,tankGame.HEIGHT-931 , ObjectId.Wall,game));

            for(int xx = 0; xx < tankGame.WIDTH; xx += 32)//bottom
                addObject(new Wall (xx, tankGame.HEIGHT-32, ObjectId.Wall,game));

            for(int xx = 0; xx < tankGame.HEIGHT+32; xx += 32)  //right
                addObject(new Wall ( tankGame.WIDTH-32, xx, ObjectId.Wall,game));

            for(int xx = 0; xx < tankGame.HEIGHT+32; xx += 32)  //left
                addObject(new Wall(0, xx, ObjectId.Wall,game));

            for(int xx = 800; xx< tankGame.WIDTH-200; xx += 32) //middle block horizontal
                addObject(new Wall(xx,288,ObjectId.Wall,game));

            for(int xx = 800; xx< tankGame.WIDTH-200; xx += 32) //middle block horizontal
                addObject(new Wall(xx,128, ObjectId.Wall,game));

            for(int xx = 800; xx< tankGame.WIDTH-200; xx += 32) //middle block horizontal
                addObject(new Wall(xx,700, ObjectId.Wall,game));

            for(int xx = 100; xx< tankGame.WIDTH-1090; xx += 32) //middle block horizontal
                addObject(new Wall(xx,450,ObjectId.Wall,game));

            for(int xx = 384; xx< tankGame.WIDTH-780; xx += 32) //middle block horizontal
                addObject(new Wall(xx,700, ObjectId.Wall,game));


            for(int xx = 128; xx < tankGame.HEIGHT-500; xx += 32) //middle block vertical top rigth
                addObject(new Wall(900, xx,ObjectId.Wall,game));

            for(int xx = 100; xx < tankGame.HEIGHT-300; xx += 32) //middle block vertical left far
                addObject(new Wall(288, xx, ObjectId.Wall,game));

            for(int xx = 412; xx < tankGame.HEIGHT-100; xx += 32)   //middle block vertical middle
                addObject(new Wall(tankGame.WIDTH-700, xx, ObjectId.Wall,game));


        }


    }
