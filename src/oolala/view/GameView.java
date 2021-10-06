package oolala.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class GameView {

    protected abstract ImageView drawCreature(double x, double y, double width, double height, int index);

    public abstract void updateCanvas ();

    /**
     * Getter method to get the Pane with the turtle and lines to be added to the scene in
     * ScreenDisplay
     *
     * @return myTurtlePane
     */
    public abstract Pane getMyCreaturePane();



}


