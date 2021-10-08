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

    /**
     * Setter method to change the width of the line drawn on the screen. ScreenDisplay gets the line
     * width from the user and uses this method to set the line width.
     *
     * @param width is the line width
     */
    public abstract void setMyLineWidth(double width);

    /**
     * Setter method to change the creature on the screen. ScreenDisplay gets the creature type
     * from the user and uses this method to set the creature image.
     *
     * @param creature is the creature used in the game
     */
    public abstract void setTurtleImage(String creature);

    public abstract double getX ();

    public abstract double getY ();

}


