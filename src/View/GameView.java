package View;

import javafx.scene.image.ImageView;

public abstract class GameView {

    protected abstract ImageView drawCreature(double x, double y, double width, double height, int index);

    public abstract void updateCanvas ();


}


