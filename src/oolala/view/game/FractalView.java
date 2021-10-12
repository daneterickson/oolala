package oolala.view.game;

import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import oolala.creatures.Creature;
import oolala.games.FractalGame;

/**
 * FractalView class creates the visual for fractals that go on the canvas in the IDE
 *
 * @author Dane Erickson
 */
public class FractalView extends GameView {

  private FractalGame myModel;
  private double myLineWidth = 1;

  /**
   * Constructor to create a DarwinView, which is the creatures that go on the canvas in the
   * ScreenDisplay class
   *
   * @param game is the oolala.Creature object that is used as the model for this view class
   */
  public FractalView(FractalGame game) {
    myModel = game;
    myCreaturePane = new Pane();
    myCreatureMap = new HashMap<>();
    creatureImage = "turtle.png";
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
      drawLine(currentModel, i, myLineWidth, currentModel.getOldX(), currentModel.getOldY(),
          currentModel.getNewX(),
          currentModel.getNewY());
    }
  }

  /**
   * Draws a leaf at the end of each Fractal and displays it.
   */
  public void drawLeaves() {
    Pane leafPane = new Pane();
    for (int index : myModel.getCreaturesMap().keySet()) {
      Creature creature = myModel.getCreaturesMap().get(index);
      Node leaf = drawCreature(creature.getNewX(), creature.getNewY(), index, creatureImage);
      leafPane.getChildren().add(leaf);
    }
    myCreaturePane.getChildren().add(leafPane);
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

  @Override
  public double getX() {
    return 1.0;
  }

  @Override
  public double getY() {
    return 1.0;
  }
}
