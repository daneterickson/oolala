package oolala.view.game;

import oolala.creatures.Creature;
import oolala.games.TurtleGame;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * TurtleView class creates the visual turtle and lines that go on the canvas in the IDE
 *
 * @author Dane Erickson
 */
public class TurtleView extends GameView {

//  private String turtleImage = "turtle.png";
  private double myLineWidth = 1;

  private TurtleGame myModel;
//  private Pane myTurtlePane;
//  private Map<Integer, ImageView> myTurtleMap;
  private double newX;
  private double newY;

  /**
   * Constructor to create a TurtleView, which is the turtle and lines that go on the canvas in the
   * ScreenDisplay class
   *
   * @param game is the oolala.Creature object that is used as the model for this view class
   * @param x    is the starting x position of the turtle
   * @param y    is the starting y position of the turtle
   */
  public TurtleView(TurtleGame game, double x, double y) { // x and y based on canvas/scene size
    myModel = game;
    myCreaturePane = new Pane();
    myCreatureMap = new HashMap<>();
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
    return myCreaturePane;
  }

//  private ImageView drawCreature(double x, double y, double width,
//      double height, int index) { // final will just be double size for turtle
//    myTurtleMap.put(index, new ImageView(new Image(turtleImage)));
//    ImageView turtle = myTurtleMap.get(index);
//    turtle.setFitHeight(height);
//    turtle.setFitWidth(width);
//    turtle.setX(x);
//    turtle.setY(y);
//    turtle.setVisible(true);
//    turtle.setId("turtle-image");
//    myTurtlePane.getChildren().add(turtle);
//    return turtle;
//  }

  /**
   * Getter method to get the myTurtleImage, which is the actual turtle on the screen
   *
   * @return myTurtleImage
   */
  public Map<Integer, ImageView> getMyTurtleMap() {
    return myCreatureMap;
  }

  /**
   * Iterates through each turtle on the canvas. Calls updateCreature to update the position,
   * orientation, and visibility of the turtle on the screen Calls drawLine to draw the line that
   * follows the turtle using the old and new position from the model
   */
  @Override
  public void updateCanvas() {
    for (int i : myModel.getActiveIndices()) {
      Creature currentModel = myModel.getCreaturesMap().get(i);
      ImageView currentTurtle = findCurrentTurtle(currentModel, i);
      drawLine(currentModel, i);
      updateCreature(currentModel, currentTurtle);
    }
  }

  @Override
  protected void drawLine(Creature currentModel, int index) {
    Line path = new Line(currentModel.getOldX() + TURTLE_WIDTH / 2,
        currentModel.getOldY() + TURTLE_HEIGHT / 2,
        currentModel.getNewX() + TURTLE_WIDTH / 2, currentModel.getNewY() + TURTLE_HEIGHT / 2);
    path.setStrokeWidth(myLineWidth);
    path.setId("line" + index);
    if (currentModel.getPenActivity()) {
      myCreaturePane.getChildren().add(path);
    }
  }

  /**
   * Update the position of the creature on the screen.
   * This is called directly to update the creature's start position if the user changes it from default,
   * and it is called in updateCanvas to move the creature for each command.
   *
   * @param currentModel is the current Creature object being moved
   * @param currentTurtle is the current ImageView of the creature on the screen being moved
   */
  public void updateCreature(Creature currentModel, ImageView currentTurtle) {
    if (currentModel.getStatusStamp()) {
      drawCreature(currentModel.getOldX(), currentModel.getOldY(), TURTLE_WIDTH, TURTLE_HEIGHT,
          -1).setRotate(currentModel.getAngle());
    }
    newX = currentModel.getNewX();
    currentTurtle.setX(newX);
    newY = currentModel.getNewY();
    currentTurtle.setY(newY);
    currentTurtle.setRotate(currentModel.getAngle());
    currentTurtle.setVisible(currentModel.getCreatureVisibility());
  }

  /**
   * Setter method to change the width of the line drawn on the screen. ScreenDisplay gets the line
   * width from the user and uses this method to set the line width.
   *
   * @param width is the line width
   */
  @Override
  public void setMyLineWidth(double width) {
    myLineWidth = width;
  }

  /**
   * Getter method to get the width of the line drawn on the screen - Used for testing
   *
   * @return myLineWidth is the line width
   */
  public double getMyLineWidth() {
    return myLineWidth;
  }

  /**
   * Setter method to change the creature on the screen. ScreenDisplay gets the creature type
   * from the user and uses this method to set the creature image.
   *
   * @param creature is the creature used in the game
   */
  @Override
  public void setTurtleImage(String creature) {

    turtleImage = creature + ".png";
  }

  /**
   * Getter method to get the creature on the screen - Used for testing
   *
   * @return turtleImage is the URL of the creature Image on the screen
   */
  public String getTurtleImage() {
    return turtleImage;
  }

  @Override
  public double getX() {
    return newX;
  }

  @Override
  public double getY() {
    return newY;
  }
}
