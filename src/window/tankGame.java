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

    TankGameObjectHandler objectHandler;
//---------------------------------------------------------------------------------------

    public void init()
    {
        requestFocus ();

        WIDTH =getWidth ();
        HEIGHT =getHeight ();


        BufferedImageLoader loader = new BufferedImageLoader ();
        try{
            spriteSheet = loader.loadImage (".\\res\\tank.png");//tank.png spritesheet
            background = loader.loadImage (".\\res\\back.png");// background of the window
        }catch (IOException e){
            e.printStackTrace ();
        }


        objectHandler = new TankGameObjectHandler ();

        objectHandler.addObject ( new Tank ( 1180, 860, 0, 0, (short)-90, objectHandler,ObjectId.Tank,this ) );
        objectHandler.addObject ( new Enemy ( 150, 150, 0, 0, (short)90,objectHandler,ObjectId.Enemy,this ) );
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
        final double amountOfTicks = 60.0; // it will update 60 times
        double ns = 1000000000 / amountOfTicks;
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

        g.setColor ( Color.gray );
        g.fillRect ( 40,36 ,200,25);

        g.setColor ( Color.green );
        g.fillRect ( 40,36 ,Health,25);

        g.setColor ( Color.white );
        g.drawRect ( 40,36 ,200,25);



        g.setColor ( Color.gray );
        g.fillRect ( 1000,36 ,200,25);

        g.setColor ( Color.green );
        g.fillRect ( 1000,36 ,HealthEnemy,25);

        g.setColor ( Color.white );
        g.drawRect ( 1000,36 ,200,25);
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
