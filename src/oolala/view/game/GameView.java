package oolala.view.game;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oolala.creatures.Creature;

public abstract class GameView { // make it an interface if I don't make any methods in this class

    public static final double TURTLE_WIDTH = 60;
    public static final double TURTLE_HEIGHT = 80;

    protected Pane myCreaturePane;
    protected Map<Integer, ImageView> myCreatureMap;
    protected String turtleImage = "turtle.png";

    protected ImageView drawCreature(double x, double y, double width,
        double height, int index) { // final will just be double size for turtle
        myCreatureMap.put(index, new ImageView(new Image(turtleImage)));
        ImageView turtle = myCreatureMap.get(index);
        turtle.setFitHeight(height);
        turtle.setFitWidth(width);
        turtle.setX(x);
        turtle.setY(y);
        turtle.setVisible(true);
        turtle.setId("turtle-image");
        myCreaturePane.getChildren().add(turtle);
        return turtle;
    }

    protected ImageView findCurrentTurtle(Creature model, int index) {
        if (!myCreatureMap.containsKey(index)) {
            myCreatureMap.put(index, drawCreature(model.getNewX(),
                model.getNewY(), TURTLE_WIDTH, TURTLE_HEIGHT, index));
        }
        ImageView currentTurtle = myCreatureMap.get(index);
        myCreaturePane.getChildren().remove(currentTurtle);
        currentTurtle = drawCreature(model.getOldX(), model.getOldY(), TURTLE_WIDTH, TURTLE_HEIGHT, index);
        return currentTurtle;
    }

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


