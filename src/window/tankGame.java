package window;
import Animacion.BufferedImageLoader;
import Objects.Bullet;
import Objects.Controller;
import Objects.Enemy;
import Objects.Tank;
import framework.KeyInput;

import javax.swing.*;
import java.awt.*; //canvas


import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class tankGame extends Canvas implements Runnable {


    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12*9;
    public static final int SCALE = 2;
    public final String TITLE = "2D Space Game";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage ( WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB ); //buffer all the window
    private BufferedImage spriteSheet = null; //tank.png spritesheet

    //temp

    //    private BufferedImage player;
    private Tank p;
    private Controller c;
    private Enemy p2;
//---------------------------------------------------------------------------------------

    public void init(){
        requestFocus ();
        BufferedImageLoader loader = new BufferedImageLoader ();
        try{
            spriteSheet = loader.loadImage (".\\res\\tank.png");//tank.png spritesheet
        }catch (IOException e){
            e.printStackTrace ();
        }
//        SpriteSheet ss =new SpriteSheet ( spriteSheet );
//        player = ss.grabImage ( 1,1,32,32 );


        addKeyListener ( new KeyInput (this) );

        p = new Tank (600, 400, 0, 0, (short)0,this);
        p2 = new Enemy(0, 0, 0, 0, (short)0,this);

        c = new Controller (this);
    }

//---------------------------------------------------------------------------------------

    private synchronized void start(){
        if(running) //if running its true it will get out of the method
            return;

        running = true;
        thread = new Thread(this);
        thread.start ();
    }

    //---------------------------------------------------------------------------------------
    //not needed has much
    private synchronized void stop(){
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
    public void run() {

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
            if (delta >= 1) {

                tick();
                updates++;

                delta--; //make delta back to zero
            }
            render();
            frames ++;

            if(System.currentTimeMillis () - timer >1000){
                timer +=1000;
                System.out.println ( updates + " Ticks, Fps " + frames );
                updates = 0; //just to rest
                frames = 0;//just to rest
            }
//            System.out.println ("Working");
        }
        stop ();
    }
//---------------------------------------------------------------------------------------
    private void tick(){ //everything in the game that updates
//tank
        p.tick ();
//enemy
        p2.tick ();
//bullet
        c.tick ();
    }
    //---------------------------------------------------------------------------------------
    private void render(){//everything in the game that renders

        BufferStrategy bs = this.getBufferStrategy (); //this refering to canvas  //getBufferStrategy returns a bufferStrategy

        if(bs == null ){
            createBufferStrategy ( 3 );
            return;
        }

        Graphics g = bs.getDrawGraphics (); //draws are buffers
        ////////////////////////////////////


        g.drawImage ( image,0,0,getWidth (),getHeight (),this );

//        g.drawImage ( player,100,100,this );


//Tank
        p.render ( g );
//Enemy
        p2.render ( g );
//bullet
        c.render ( g );

        ////////////////////////////////
        g.dispose ();
        bs.show ();

    }
//--------------------------------------------------------------------------------------------------
    //key pressed called in keyInput
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            p.toggleRightPressed();
        } else if(key == KeyEvent.VK_LEFT) {
            p.toggleLeftPressed();
        } else if(key == KeyEvent.VK_DOWN) {
            p.toggleDownPressed();
        } else if(key == KeyEvent.VK_UP) {
            p.toggleUpPressed();
        } else if(key == KeyEvent.VK_SHIFT){
            c.addBullet ( new Bullet (p.getX(),p.getY(),this) );
        }



        if(key == KeyEvent.VK_D) {
            p2.toggleRightPressed();
        } else if(key == KeyEvent.VK_A) {
            p2.toggleLeftPressed();
        } else if(key == KeyEvent.VK_S) {
            p2.toggleDownPressed();
        } else if(key == KeyEvent.VK_W) {
            p2.toggleUpPressed();
        } else if(key == KeyEvent.VK_SPACE){
            c.addBullet ( new Bullet (p2.getX (),p2.getY(),this) );
        }

    }

    //key release  called in keyInput
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            p.unToggleRightPressed ();
        } else if(key == KeyEvent.VK_LEFT) {
            p.unToggleLeftPressed ();
        } else if(key == KeyEvent.VK_DOWN) {
            p.unToggleDownPressed ();
        } else if(key == KeyEvent.VK_UP) {
            p.unToggleUpPressed ();
        }



        if(key == KeyEvent.VK_D) {
            p2.unToggleRightPressed ();
        } else if(key == KeyEvent.VK_A) {
            p2.unToggleLeftPressed ();
        } else if(key == KeyEvent.VK_S) {
            p2.unToggleDownPressed ();
        } else if(key == KeyEvent.VK_W) {
            p2.unToggleUpPressed ();
        }
    }
//---------------------------------------------------------------------------------------------------------

    public static void main (String args[])
    {
        tankGame game = new tankGame();

//WINDOW
        game.setPreferredSize ( new Dimension ( WIDTH*SCALE, HEIGHT *SCALE ) );
        game.setMaximumSize ( new Dimension ( WIDTH*SCALE, HEIGHT *SCALE ) );
        game.setMinimumSize ( new Dimension ( WIDTH*SCALE, HEIGHT *SCALE )) ;

        JFrame frame = new JFrame ( game.TITLE );  //Title in the top of the window
        frame.add ( game); //take the dimension
        frame.pack ();//
        frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );  //closes the game so it does not run in the background  "x" button to work
        frame.setResizable ( false ); //false so it does not go bigger window
        frame.setLocationRelativeTo ( null ); //to be in the center
        frame.setVisible ( true ); // to be able to see the window

        game.start (); //if not call in the main method it will not run // thr game loop (run method)

    }
    //---------------------------------------------------------------------------------------
    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
}
