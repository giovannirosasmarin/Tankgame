package framework;

import window.tankGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    tankGame game;
    public KeyInput(tankGame game){
        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        game.keyPressed ( e );
    }
    public void keyReleased(KeyEvent e){
game.keyReleased ( e );
    }
}
