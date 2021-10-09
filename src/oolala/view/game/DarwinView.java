package oolala.view.game;

import java.util.HashMap;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oolala.creatures.Creature;
import oolala.games.DarwinGame;


public class DarwinView extends GameView {

  private Pane myDarwinPane;
  private DarwinGame myModel;

  public DarwinView (DarwinGame game, double x, double y) { // x and y based on canvas/scene size
    myModel = game;
    myDarwinPane = new Pane();
    myCreatureMap = new HashMap<>();
    drawCreature(x, y, TURTLE_WIDTH, TURTLE_HEIGHT, 1);
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
      updateCreature(currentModel, currentTurtle);
    }
  }

  @Override
  protected void drawLine(Creature currentModel, int index) {

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
//    if (currentModel.getStatusStamp()) {
//      drawCreature(currentModel.getOldX(), currentModel.getOldY(), TURTLE_WIDTH, TURTLE_HEIGHT,
//          -1).setRotate(currentModel.getAngle());
//    }
//    newX = currentModel.getNewX();
//    currentTurtle.setX(newX);
//    newY = currentModel.getNewY();
//    currentTurtle.setY(newY);
//    currentTurtle.setRotate(currentModel.getAngle());
//    currentTurtle.setVisible(currentModel.getCreatureVisibility());
  }

  /**
   * Getter method to get the Pane with the creature and lines to be added to the scene in
   * ScreenDisplay
   *
   * @return myDarwinPane
   */
  @Override
  public Pane getMyCreaturePane() {
    return myDarwinPane;
  }
  /**
   * Setter method to change the width of the line drawn on the screen. ScreenDisplay gets the line
   * width from the user and uses this method to set the line width.
   *
   * @param width is the line width
   */
  @Override
  public void setMyLineWidth(double width) {

  }

  /**
   * Setter method to change the creature on the screen. ScreenDisplay gets the creature type
   * from the user and uses this method to set the creature image.
   *
   * @param creature is the creature used in the game
   */
  @Override
  public void setTurtleImage(String creature) {

  }
  @Override
  public double getX() {
    return 1.0;
  }

  @Override
  public double getY() {
    return 1.0;
  }
}
