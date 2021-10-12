package oolala.view.game;

import java.util.Map;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import oolala.creatures.Creature;

public abstract class GameView {

    public static final double CREATURE_WIDTH = 60;
    public static final double CREATURE_HEIGHT = 80;

    protected Pane myCreaturePane;
    protected Map<Integer, ImageView> myCreatureMap;
    protected String creatureImage;

    public ImageView drawCreature(double x, double y, int index, String type) {
        myCreatureMap.put(index, new ImageView(new Image(getImage(type))));
        ImageView turtle = myCreatureMap.get(index);
        turtle.setFitHeight(CREATURE_HEIGHT);
        turtle.setFitWidth(CREATURE_WIDTH);
        turtle.setX(x);
        turtle.setY(y);
        turtle.setVisible(true);
        turtle.setId("turtle-image");
        myCreaturePane.getChildren().add(turtle);
        return turtle;
    }

    private String getImage(String type) {
        switch (type) {
            case "cat" -> creatureImage = "cat.png";
            case "dog" -> creatureImage = "dog.png";
            case "turtle" -> creatureImage = "turtle.png";
        }
        return creatureImage;
    }

    protected ImageView findCurrentTurtle(Creature model, int index, String type) {
        if (!myCreatureMap.containsKey(index)) {
            myCreatureMap.put(index, drawCreature(model.getNewX(),
                model.getNewY(), index, type));
        }
        ImageView currentTurtle = myCreatureMap.get(index);
        myCreaturePane.getChildren().remove(currentTurtle);
        currentTurtle = drawCreature(model.getOldX(), model.getOldY(),
            index, type);
        return currentTurtle;
    }

    public abstract void updateCanvas ();


    protected void drawLine(Creature currentModel, int index, double lineWidth, double startX, double startY, double endX, double endY) {
        Line path = new Line(startX, startY, endX, endY);
        path.setStrokeWidth(lineWidth);
        path.setId("line" + index);
        if (currentModel.getPenActivity()) {
            myCreaturePane.getChildren().add(path);
        }
    }

    /**
     * Getter method to get the Pane with the creature and lines to be added to the scene in
     * ScreenDisplay
     *
     * @return myCreaturePane
     */
    public Node getMyCreaturePane() { return myCreaturePane; }

    /**
     * Getter method to get the creature on the screen - Used for testing
     *
     * @return turtleImage is the URL of the creature Image on the screen
     */
    public String getCreatureImage() {
        return creatureImage;
    }

    /**
     * Getter method to get the Map with the indices and ImageViews of the creatures to be added to
     * the scene in ScreenDisplay
     *
     * @return myCreaturePane
     */
    public Map<Integer, ImageView> getMyCreatureMap() { return myCreatureMap; }

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
    public void setCreatureImage(String creature) {
        creatureImage = creature + ".png";
    }

    public abstract double getX ();

    public abstract double getY ();

}


