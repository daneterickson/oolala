package oolala.view;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class GameView {

    protected abstract ImageView drawCreature(double x, double y, double width, double height, int index);

    public abstract void updateCanvas ();

    public abstract Node getMyTurtlePane();

}


