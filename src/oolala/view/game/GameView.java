package oolala.view.game;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oolala.creatures.Creature;

public abstract class GameView { // make it an interface if I don't make any methods in this class

    public abstract void updateCanvas ();

    protected abstract void drawLine(Creature currentModel, int index);


        /**
         * Getter method to get the Pane with the creature and lines to be added to the scene in
         * ScreenDisplay
         *
         * @return myCreaturePane
         */
    public abstract Node getMyCreaturePane();

}


