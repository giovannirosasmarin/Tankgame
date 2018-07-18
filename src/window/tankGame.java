package window;
import Animacion.BufferedImageLoader;
import Objects.*;
import TankGameExtras.Health;
import framework.keyInput;
import java.awt.*; //canvas
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class tankGame extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage ( WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB ); //buffer all the window
    private BufferedImage spriteSheet = null; //tank.png spritesheet
    private BufferedImage background = null;// background of the window




    private BufferedImage leftHalf = null;
    private BufferedImage rightHalf = null;


    private Health hp,hp2;
    private Tank p;
    private BulletControl bc;
    private Enemy p2;
    private Wall w1;
    private static int W, H;
    private static int sw, sh;


//---------------------------------------------------------------------------------------

    private void init()
    {
        requestFocus ();
        W =getWidth ();
        H =getHeight ();
        BufferedImageLoader loader = new BufferedImageLoader ();
        try{
            spriteSheet = loader.loadImage (".\\res\\tank.png");//tank.png spritesheet
            background = loader.loadImage (".\\res\\back.png");// background of the window

        }catch (IOException e){
            e.printStackTrace ();
        }



        p = new Tank (1180, 860, 0, 0, (short)-90,this);// Tank
        p2 = new Enemy(100, 100, 0, 0, (short)90,this);  //enemy
        hp = new Health ( 100,36,this );
        hp2 = new Health ( 1050,36,this );
        bc = new BulletControl (this);   //bullet
        w1 = new Wall ( 32,100,(short)0,this ); //walls

        addKeyListener ( new keyInput (this) ); //adding key Listener for the key input

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
    private synchronized void stop()
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



//---------------------------------------------------------------------------------------

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
//            System.out.println ("Working"); //first test of run before implementing all the game
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
        Graphics2D g2d= (Graphics2D)g;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////

//        g.drawImage ( image,0,0,getWidth (),getHeight (),this );      //old background color green it was black at first
//        g.setColor ( Color.green );
//        g.fillRect ( 0,0,800,800 );

        g.drawImage ( background,0,0,getWidth (),getHeight (),null); // background of the window

        g.drawImage ( leftHalf,0,0,0,0,null);
        g.drawImage ( rightHalf,0,0,getWidth ()/2,0,null);


//        g.drawImage ( player,100,100,this );

//Tank
        p.render ( g );
//Enemy
        p2.render ( g );
//bullet
        bc.render ( g );
//walls
        w1.render ( g );

        hp.render ( g );

        hp2.render ( g );

        g2d.scale ( .2,.2); // for mini map scale ?????
//        g2d. drawImage (  )

        /////////////////////////////////////////////////////////////////////////////////////////////////////////

        g.dispose ();
        bs.show ();

    }
//---------------------------------------------------------------------------------------
    private void update()
    { //everything in the game that updates

//tank
        p.update ();
//enemy
        p2.update ();
//bullet
        bc.update ();
//walls
        w1.update ( );

        hp.update ( );

        hp2.update ();

    }
//--------------------------------------------------------------------------------------------------

    public static void main (String args[])
    {
        new Window ( 1280, 960,"Tank Game", new tankGame () ); // initializing the window

    }
    //---------------------------------------------------------------------------------------
    //key pressed called in keyInput
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
//enemy
        if(key == KeyEvent.VK_D) {
            p2.toggleRightPressed();
        } else if(key == KeyEvent.VK_A) {
            p2.toggleLeftPressed();
        } else if(key == KeyEvent.VK_S) {
            p2.toggleDownPressed();
        } else if(key == KeyEvent.VK_W) {
            p2.toggleUpPressed();
        } else if(key == KeyEvent.VK_SPACE){
            bc.addBullet ( new Bullet (p2.getX (),p2.getY(), p2.getAngle (), this) );
        }
//tank
        if(key == KeyEvent.VK_RIGHT) {
            p.toggleRightPressed();
        } else if(key == KeyEvent.VK_LEFT) {
            p.toggleLeftPressed();
        } else if(key == KeyEvent.VK_DOWN) {
            p.toggleDownPressed();
        } else if(key == KeyEvent.VK_UP) {
            p.toggleUpPressed();
        } else if(key == KeyEvent.VK_SHIFT){
            bc.addBullet ( new Bullet (p.getX (),p.getY(),p.getAngle (),this) );
        }
    }

    //key release  called in keyInput
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
//enemy
        if(key == KeyEvent.VK_D) {
            p2.unToggleRightPressed ();
        } else if(key == KeyEvent.VK_A) {
            p2.unToggleLeftPressed ();
        } else if(key == KeyEvent.VK_S) {
            p2.unToggleDownPressed ();
        } else if(key == KeyEvent.VK_W) {
            p2.unToggleUpPressed ();
        }
//tank
        if(key == KeyEvent.VK_RIGHT) {
            p.unToggleRightPressed ();
        } else if(key == KeyEvent.VK_LEFT) {
            p.unToggleLeftPressed ();
        } else if(key == KeyEvent.VK_DOWN) {
            p.unToggleDownPressed ();
        } else if(key == KeyEvent.VK_UP) {
            p.unToggleUpPressed ();
        }
    }
//---------------------------------------------------------------------------------------------------------

    public BufferedImage getSpriteSheet()
    {
        return spriteSheet;
    }



}
