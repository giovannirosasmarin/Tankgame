package framework;

import window.tankGame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyInput extends KeyAdapter {

    tankGame game;
    public keyInput(tankGame game){
        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        game.keyPressed ( e );
    }
    public void keyReleased(KeyEvent e){
        game.keyReleased ( e );
    }
}