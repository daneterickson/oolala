package oolala.view;

import javafx.scene.image.ImageView;

public class DarwinView extends GameView{

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
}
