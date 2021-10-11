package oolala.view.game;

import javafx.scene.layout.Pane;
import oolala.creatures.Creature;
import oolala.games.FractalGame;

public class FractalView extends GameView {

  private FractalGame myModel;
  private double myLineWidth = 1;

  public FractalView(FractalGame game) {
    myModel = game;
    myCreaturePane = new Pane();
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
      drawLine(currentModel, i, myLineWidth, currentModel.getOldX(), currentModel.getOldY(), currentModel.getNewX(),
          currentModel.getNewY());
    }
  }

  /**
   * Getter method to get the Pane with the creature and lines to be added to the scene in
   * ScreenDisplay
   *
   * @return myFractalPane
   */
  @Override
  public Pane getMyCreaturePane() {
    return myCreaturePane;
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
//   * @param creature is the creature used in the game
   */
//  @Override
//  public void setTurtleImage(String creature) {
//
//  }
  @Override
  public double getX() {
    return 1.0;
  }

  @Override
  public double getY() {
    return 1.0;
  }
}
