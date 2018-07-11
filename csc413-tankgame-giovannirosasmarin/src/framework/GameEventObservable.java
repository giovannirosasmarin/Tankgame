package framework;

import java.util.Observable;

/**
 *
 * @author anthony-pc
 */
public class GameEventObservable extends Observable {

    public GameEventObservable() {
    }

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }


}