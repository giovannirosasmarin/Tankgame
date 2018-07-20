package window;


import WallStuff.WallAbstract;
import WallStuff.Wall;
import WallStuff.wallId;

import java.awt.*;
import java.util.LinkedList;

public class WallConstructor// it gets the objects in the game
    {
        private tankGame game;
        public LinkedList<WallAbstract> w = new LinkedList<WallAbstract> (  );

        private WallAbstract tempWalls;

        public void update()
        {
            for (int i = 0; i< w.size (); i++)  //goes throw the list of object that we have in each update
            {
                tempWalls = w.get ( i );

                tempWalls.update ( w );
            }
        }

        public void render(Graphics g)
        {
            for (int i = 0; i< w.size (); i++)
            {
                tempWalls = w.get ( i );

                tempWalls.render ( g );
            }
        }

        public void addObject(WallAbstract object){
            this.w.add(object); //"this." referes to the object linkedList
        }

        public void  removeObject(WallAbstract object){
            this.w.remove (object);
        }

        public void createWalls()
        {

            for(int xx = 0; xx < tankGame.W; xx += 32)//Top
                addObject(new Wall (xx,tankGame.H-970 , wallId.Wall,game));

            for(int xx = 0; xx < tankGame.W; xx += 32)//bottom
                addObject(new Wall (xx, tankGame.H-32, wallId.Wall,game));

            for(int xx = 0; xx < tankGame.H+32; xx += 32)  //right
                addObject(new Wall ( tankGame.W-32, xx, wallId.Wall,game));

            for(int xx = 0; xx < tankGame.H+32; xx += 32)  //left
                addObject(new Wall(0, xx, wallId.Wall,game));

            for(int xx = 800; xx< tankGame.W-200; xx += 32) //middle block horizontal
                addObject(new Wall(xx,300, wallId.Wall,game));

            for(int xx = 800; xx< tankGame.W-200; xx += 32) //middle block horizontal
                addObject(new Wall(xx,150, wallId.Wall,game));

            for(int xx = 800; xx< tankGame.W-200; xx += 32) //middle block horizontal
                addObject(new Wall(xx,700, wallId.Wall,game));

            for(int xx = 100; xx< tankGame.W-1090; xx += 32) //middle block horizontal
                addObject(new Wall(xx,450, wallId.Wall,game));

            for(int xx = 400; xx< tankGame.W-770; xx += 32) //middle block horizontal
                addObject(new Wall(xx,700, wallId.Wall,game));


            for(int xx = 100; xx < tankGame.H-500; xx += 32) //middle block vertical top rigth
                addObject(new Wall(tankGame.W-400, xx, wallId.Wall,game));

            for(int xx = 100; xx < tankGame.H-300; xx += 32) //middle block vertical middle
                addObject(new Wall(tankGame.W-1000, xx, wallId.Wall,game));

            for(int xx = 400; xx < tankGame.H-100; xx += 32) //middle block vertical left far
                addObject(new Wall(tankGame.W-700, xx, wallId.Wall,game));


        }

        public void createBreakableWall()
    {

//        for(int xx = 0; xx < tankGame.W; xx += 32)//Top
//            addObject(new Wall (xx,tankGame.H-970 , wallId.Wall,game));
//
//        for(int xx = 0; xx < tankGame.W; xx += 32)//bottom
//            addObject(new Wall (xx, tankGame.H-32, wallId.Wall,game));
//
//        for(int xx = 0; xx < tankGame.H+32; xx += 32)  //right
//            addObject(new Wall ( tankGame.W-32, xx, wallId.Wall,game));
//
//        for(int xx = 0; xx < tankGame.H+32; xx += 32)  //left
//            addObject(new Wall(0, xx, wallId.Wall,game));
//
//        for(int xx = 800; xx< tankGame.W-200; xx += 32) //middle block horizontal
//            addObject(new Wall(xx,300, wallId.Wall,game));
//
//        for(int xx = 800; xx< tankGame.W-200; xx += 32) //middle block horizontal
//            addObject(new Wall(xx,150, wallId.Wall,game));
//
//        for(int xx = 800; xx< tankGame.W-200; xx += 32) //middle block horizontal
//            addObject(new Wall(xx,700, wallId.Wall,game));
//
//        for(int xx = 100; xx< tankGame.W-1090; xx += 32) //middle block horizontal
//            addObject(new Wall(xx,450, wallId.Wall,game));
//
//        for(int xx = 400; xx< tankGame.W-770; xx += 32) //middle block horizontal
//            addObject(new Wall(xx,700, wallId.Wall,game));
//
//
//        for(int xx = 100; xx < tankGame.H-500; xx += 32) //middle block vertical top rigth
//            addObject(new Wall(tankGame.W-400, xx, wallId.Wall,game));
//
//        for(int xx = 100; xx < tankGame.H-300; xx += 32) //middle block vertical middle
//            addObject(new Wall(tankGame.W-1000, xx, wallId.Wall,game));
//
//        for(int xx = 400; xx < tankGame.H-100; xx += 32) //middle block vertical left far
//            addObject(new Wall(tankGame.W-700, xx, wallId.Wall,game));





    }

    }
