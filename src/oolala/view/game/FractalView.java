package oolala.view.game;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import oolala.creatures.Creature;
import oolala.games.FractalGame;

public class FractalView extends GameView {

  private FractalGame myModel;
  private Pane myFractalPane;

  public FractalView(FractalGame game) {
    myModel = game;
    myFractalPane = new Pane();
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
      drawLine(currentModel, i);
    }
  }

  @Override
  protected void drawLine(Creature currentModel, int index) {
    Line path = new Line(currentModel.getOldX(), currentModel.getOldY(), currentModel.getNewX(),
        currentModel.getNewY());
    path.setId("line" + index);
    if (currentModel.getPenActivity()) {
      myFractalPane.getChildren().add(path);
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
    return myFractalPane;
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
