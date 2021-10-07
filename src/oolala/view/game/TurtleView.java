package oolala.view.game;

import oolala.creatures.Creature;
import oolala.games.TurtleGame;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * TurtleView class creates the visual turtle and lines that go on the canvas in the IDE
 *
 * @author Dane Erickson
 */
public class TurtleView extends GameView {

  public static final String TURTLE_IMAGE = "turtle.png";
  public static final double TURTLE_WIDTH = 60;
  public static final double TURTLE_HEIGHT = 80;

  private TurtleGame myModel;
  private Pane myTurtlePane;
  private Map<Integer, ImageView> myTurtleMap;

  /**
   * Constructor to create a TurtleView, which is the turtle and lines that go on the canvas in the
   * ScreenDisplay class
   *
   * @param game is the oolala.Creature object that is used as the model for this view class
   * @param x        is the starting x position of the turtle
   * @param y        is the starting y position of the turtle
   */
  public TurtleView(TurtleGame game, double x, double y) { // x and y based on canvas/scene size
    myModel = game;
    myTurtlePane = new Pane();
    myTurtleMap = new HashMap<>();
    drawCreature(x, y, TURTLE_WIDTH, TURTLE_HEIGHT, 1);
  }

  /**
   * Getter method to get the Pane with the turtle and lines to be added to the scene in
   * ScreenDisplay
   *
   * @return myTurtlePane
   */
  @Override
  public Pane getMyCreaturePane() {
    return myTurtlePane;
  }

  @Override
  protected ImageView drawCreature(double x, double y, double width,
      double height, int index) { // final will just be double size for turtle
    myTurtleMap.put(index, new ImageView(new Image(TURTLE_IMAGE)));
    ImageView turtle = myTurtleMap.get(index);
    turtle.setFitHeight(height);
    turtle.setFitWidth(width);
    turtle.setX(x);
    turtle.setY(y);
    turtle.setVisible(true);
    turtle.setId("turtle-image");
    myTurtlePane.getChildren().add(turtle);
    return turtle;
  }

  /**
   * Getter method to get the myTurtleImage, which is the actual turtle on the screen
   * @return myTurtleImage
   */
  public Map<Integer, ImageView> getMyTurtleMap() {
    return myTurtleMap;
  }

  /**
   * Iterates through each turtle on the canvas.
   * Calls updateCreature to update the position, orientation, and visibility of the turtle on the screen
   * Calls drawLine to draw the line that follows the turtle using the old and new position from the model
   */
  @Override
  public void updateCanvas () {
    for (int i : myModel.getActiveIndices()) {
      Creature currentModel = myModel.getCreaturesMap().get(i);
      if (!myTurtleMap.containsKey(i)) myTurtleMap.put(i, drawCreature(currentModel.getNewX(),
          currentModel.getNewY(), TURTLE_WIDTH, TURTLE_HEIGHT, i));
      ImageView currentTurtle = myTurtleMap.get(i);
      drawLine(currentModel, i);
      updateCreature(currentModel, currentTurtle);
    }
  }

  private void drawLine(Creature currentModel, int index) {
    Line path = new Line(currentModel.getOldX() + TURTLE_WIDTH/2, currentModel.getOldY() + TURTLE_HEIGHT/2,
        currentModel.getNewX() + TURTLE_WIDTH/2, currentModel.getNewY() + TURTLE_HEIGHT/2);
    path.setId("line" + index);
    if (currentModel.getPenActivity()) {
      myTurtlePane.getChildren().add(path);
    }
  }

  private void updateCreature(Creature currentModel, ImageView currentTurtle) {
    if (currentModel.getStatusStamp()) {
      drawCreature(currentModel.getOldX(), currentModel.getOldY(), TURTLE_WIDTH, TURTLE_HEIGHT,
          -1).setRotate(currentModel.getAngle());
    }
    currentTurtle.setX(currentModel.getNewX());
    currentTurtle.setY(currentModel.getNewY());
    currentTurtle.setRotate(currentModel.getAngle());
    currentTurtle.setVisible(currentModel.getCreatureVisibility());
  }

}
