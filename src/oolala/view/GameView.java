package oolala.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class GameView {

    protected abstract ImageView drawCreature(double x, double y, double width, double height, int index);

    public abstract Pane getMyTurtlePane();

    public abstract void updateCanvas ();


}


