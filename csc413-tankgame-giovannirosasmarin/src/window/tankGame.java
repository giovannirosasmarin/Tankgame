package window;

//import Objects.Enemy;
import Objects.Enemy;
import Objects.Tank;
import com.sun.corba.se.spi.ior.ObjectId;
import framework.GameEventObservable;
import framework.TankControl;


import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author anthony-pc
 */
public class tankGame extends JFrame {
//    private boolean isrunning = false;
    private final GameEventObservable geobv;
//    private Thread thread;


    public static void main(String[] args) {
        Thread x;
        tankGame trex = new tankGame ();
        trex.init();

        try {
            while (true) {
                trex.geobv.setChanged();

                trex.geobv.notifyObservers();

                Thread.sleep(1000/144);

            }
        } catch (InterruptedException ex) {
            Logger.getLogger( tankGame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public tankGame() {
        this.geobv = new GameEventObservable();


    }

    private void init() {
        setTitle("Tank Game");

        Tank t1 = new Tank(0, 0, 0, 0, (short)0, framework.ObjectId.Tank);
       Enemy t2 = new Enemy (500, 500, 0, 0, (short)0,framework.ObjectId.Enemy);


        try {
            BufferedImage i = ImageIO.read(new File(".\\res\\Tank1.jpg"));
            t1.setImg(i);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


       TankControl tc1 = new TankControl( t1,KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        this.add(t1);

        this.addKeyListener(tc1);
        this.geobv.addObserver(t1);



        TankControl tc2 = new TankControl( t2,KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);

        this.add(t2);

        this.addKeyListener(tc2);

        this.geobv.addObserver(t2);














        this.setSize(800, 800);
        this.setResizable(false);
        setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

//    public synchronized void start(){   //synchronized is used when using threads
//        if(isrunning)
//            return;
//
//        isrunning = true;
//        thread = new Thread ( (Runnable) this );
//        thread.start ();
//    }

//    public void run() {
//        init();
//        this.requestFocus ();
//        long lastTime = System.nanoTime();
//        double amountOfTicks = 60.0;        //ticks per second
//        double ns = 1000000000 / amountOfTicks;
//        double delta = 0;
//        long timer = System.currentTimeMillis();
//        int updates = 0;
//        int frames = 0;
//        while(isrunning){
//            long now = System.nanoTime();
//            delta += (now - lastTime) / ns;
//            lastTime = now;
//            while(delta >= 1){
//                //tick();
//                updates++;
//                delta--;
//            }
//            //  render();  //rendering as fast your computer can but updating at a fixed amount of 60
//            frames++;
//
//            if(System.currentTimeMillis() - timer > 1000){
//                timer += 1000;
//                System.out.println("FPS: " + frames + " TICKS: " + updates);
//                frames = 0;
//                updates = 0;
//            }
//        }
//
//        //System.out.println ("Thread has begun" ); // to check it thread started
//    }

}



