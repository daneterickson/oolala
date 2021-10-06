package oolala.view;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class GameView {

    protected abstract ImageView drawCreature(double x, double y, double width, double height, int index);

    public abstract void updateCanvas ();

    /**
     * Getter method to get the Pane with the creature and lines to be added to the scene in
     * ScreenDisplay
     *
     * @return myCreaturePane
     */
    public abstract Node getMyCreaturePane();

}


