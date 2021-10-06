package oolala.view;

import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oolala.creatures.Creature;

public class FractalView extends GameView{

  private Pane myFractalPane;

  @Override
  protected ImageView drawCreature(double x, double y, double width,
      double height, int index) { // final will just be double size for turtle
    return null;
  }

  /**
   * Iterates through each turtle on the canvas.
   * Calls updateCreature to update the position, orientation, and visibility of the turtle on the screen
   * Calls drawLine to draw the line that follows the turtle using the old and new position from the model
   */
  @Override
  public void updateCanvas () {
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
}
