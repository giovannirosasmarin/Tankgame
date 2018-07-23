package window;
import Animacion.BufferedImageLoader;
import Objects.*;
//import framework.KeyInput;
import framework.keyInput;

import java.awt.*; //canvas
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class tankGame extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

    private BufferedImage spriteSheet = null; //tank.png spritesheet
    private BufferedImage background = null;// background of the window

    public static int WIDTH, HEIGHT;
    public static  int Health = 100*2;
    public static  int HealthEnemy = 100*2;

    public static int LifeCount =1*30;
    public static int LifeCount2=1*30;
    public static int LifeCount3 =1*30;

    public static int LifeCountEnemy =1*30;
    public static int LifeCountEnemy2 =1*30;
    public static int LifeCountEnemy0 =1*30;

    TankGameObjectHandler objectHandler;
//---------------------------------------------------------------------------------------

    public void init()
    {
        requestFocus ();

        WIDTH =getWidth ();
        HEIGHT =getHeight ();
     //   C:\Users\geo62\csc413-tankgame-giovannirosasmarin\res\back.png
        //back.png
        BufferedImageLoader loader = new BufferedImageLoader ();
        try{
            spriteSheet = loader.loadImage (".\\res\\tank.png");//tank.png spritesheet
            background = loader.loadImage (".\\res\\back.png");// background of the window
        }catch (IOException e){
            e.printStackTrace ();
        }


        objectHandler = new TankGameObjectHandler ();

//        objectHandler.addObject ( new Tank ( 1180, 860, 0, 0, (short)-90, objectHandler,ObjectId.Tank,this ) );
        objectHandler.addObject ( new Tank ( 100, 100, 0, 0, (short)-90, objectHandler,ObjectId.Tank,this ) );
        objectHandler.addObject ( new Enemy ( 150, 150, 0, 0, (short)90,objectHandler,ObjectId.Enemy,this ) );
//        objectHandler.addObject ( new Health1 ( 100,70,objectHandler,ObjectId.Enemy,this ) );
//        objectHandler.addObject ( new Health ( 140,70,objectHandler,ObjectId.Enemy,this ) );
//        objectHandler.addObject ( new Health ( 180,70,objectHandler,ObjectId.Enemy,this ) );

//        objectHandler.addBullet ( new Bullet ( (int)200,(int)200,(short)90, objectHandler,ObjectId.Bullet,this ) );
        objectHandler.createLevel ();

        addKeyListener ( new keyInput ( objectHandler,this) );
    }

//---------------------------------------------------------------------------------------

   public synchronized void start()
   {
        if(running) //if running its true it will get out of the method
            return;

        running = true;
        thread = new Thread(this);
        thread.start ();
    }

    //---------------------------------------------------------------------------------------
    //not needed has much
    public synchronized void stop()
    {
        if(!running) //if running its false it will get out of the method
            return;

        running = false;
        try {
            thread.join ();
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }
        System.exit ( 1 );
    }
//--------------------------------------------------------------------------------------

    @Override // implemented runnable
    public void run()
    {

        init();

        long lastTime = System.nanoTime ();
        final double amountOfTicks = 50.0; // it will update 60 times
        double ns = 1000000000/ amountOfTicks;
        double delta = 0; //time that has passed  //going to catch up
        int updates = 0;
        int frames = 0 ;
        long timer = System.currentTimeMillis ();

        while(running){//loop of the game
            long now = System.nanoTime ();
            delta += (now - lastTime )/ns;
            lastTime = now; //equaling the last time to the time now
            if (delta >= 1)
            {
                update ();
                updates++;
                delta--; //make delta back to zero
            }
            render();
            frames ++;

            if(System.currentTimeMillis () - timer >1000)
            {
                timer +=1000;
                System.out.println ( updates + " Ticks, Fps " + frames );
                updates = 0; //just to rest
                frames = 0;//just to rest
            }
        }
        stop ();
    }

    //---------------------------------------------------------------------------------------
    private void render()//everything in the game that renders
    {
        BufferStrategy bs = this.getBufferStrategy (); //this refering to canvas  //getBufferStrategy returns a bufferStrategy

        if(bs == null )
        {
            createBufferStrategy ( 3 );
            return;
        }

        Graphics g = bs.getDrawGraphics (); //draws are buffers
        ////////////////////////////////////

        g.drawImage ( background,0,0,getWidth (),getHeight (),null); // background of the window

        objectHandler.render ( g );

        //Health Tank

        g.setColor ( Color.gray );
        g.fillRect ( 40, 36, 200, 25 );
        g.setColor ( Color.green );
        g.fillRect ( 40, 36, Health, 25 );
        g.setColor ( Color.white );
        g.drawRect ( 40, 36, 200, 25 );



        //Health enemy
        g.setColor ( Color.gray );
        g.fillRect ( 1000,36 ,200,25);
        g.setColor ( Color.green );
        g.fillRect ( 1000,36 ,HealthEnemy,25);
        g.setColor ( Color.white );
        g.drawRect ( 1000,36 ,200,25);



////Lives for tank ???? can figure it out still
        g.setColor(Color.gray);
        g.fillOval ( 40, 63,30,30 );
        g.setColor(Color.blue);
        g.fillOval ( 40, 63,LifeCount,30 );
        g.setColor(Color.WHITE);
        g.drawOval ( 40, 63,30,30 );

        g.setColor(Color.gray);
        g.fillOval ( 80, 63,30,30 );
        g.setColor(Color.blue);
        g.fillOval ( 80, 63,LifeCount2,30 );
        g.setColor(Color.WHITE);
        g.drawOval ( 80, 63,30,30 );
//Enemy Lives
        g.setColor(Color.gray);
        g.fillOval ( 120, 63,30,30 );
        g.setColor(Color.blue);
        g.fillOval ( 120, 63,LifeCount3,30 );
        g.setColor(Color.WHITE);
        g.drawOval ( 120, 63,30,30 );

        g.setColor(Color.gray);
        g.fillOval ( 1000, 63,30,30 );
        g.setColor(Color.blue);
        g.fillOval ( 1000, 63,LifeCountEnemy0,30 );
        g.setColor(Color.WHITE);
        g.drawOval ( 1000, 63,30,30 );

        g.setColor(Color.gray);
        g.fillOval ( 1040, 63,30,30 );
        g.setColor(Color.blue);
        g.fillOval ( 1040, 63,LifeCountEnemy,30 );
        g.setColor(Color.WHITE);
        g.drawOval ( 1040, 63,30,30 );

        g.setColor(Color.gray);
        g.fillOval ( 1080, 63,30,30 );
        g.setColor(Color.blue);
        g.fillOval ( 1080, 63,LifeCountEnemy2,30 );
        g.setColor(Color.WHITE);
        g.drawOval ( 1080, 63,30,30 );

        ////////////////////////////////
        g.dispose ();
        bs.show ();

    }
//---------------------------------------------------------------------------------------
    private void update()//everything in the game that updates
    {
        objectHandler.update();
    }
//--------------------------------------------------------------------------------------------------

    public static void main (String args[])
    {
        new Window ( 1280, 960,"Tank Game", new tankGame () ); // initializing the window

    }
//---------------------------------------------------------------------------------------

    public BufferedImage getSpriteSheet()
    {
        return spriteSheet;
    }


}
