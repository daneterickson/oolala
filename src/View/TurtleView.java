package View;

import Creatures.Creature;
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

  private Creature myModel;
  private ImageView myTurtleImage;
  private Pane myTurtlePane;

  /**
   * Constructor to create a TurtleView, which is the turtle and lines that go on the canvas in the
   * ScreenDisplay class
   *
   * @param creatureModel is the Creature object that is used as the model for this view class
   * @param x        is the starting x position of the turtle
   * @param y        is the starting y position of the turtle
   */
  public TurtleView(Creature creatureModel, double x, double y) { // x and y based on canvas/scene size
    myModel = creatureModel;
    myTurtlePane = new Pane();
    drawCreature(x, y, TURTLE_WIDTH, TURTLE_HEIGHT);
  }

  /**
   * Getter method to get the Pane with the turtle and lines to be added to the scene in
   * ScreenDisplay
   *
   * @return myTurtlePane
   */
  public Pane getMyTurtlePane() {
    return myTurtlePane;
  }

  @Override
  protected void drawCreature(double x, double y, double width,
      double height) { // final will just be double size for turtle
    myTurtleImage = new ImageView(new Image(TURTLE_IMAGE));
    myTurtleImage.setFitHeight(height);
    myTurtleImage.setFitWidth(width);
    myTurtleImage.setX(x);
    myTurtleImage.setY(y);
    myTurtleImage.setVisible(true);
    myTurtleImage.setId("turtle-image");
    myTurtlePane.getChildren().add(myTurtleImage);
  }

  /**
   * Getter method to get the myTurtleImage, which is the actual turtle on the screen
   * @return myTurtleImage
   */
  public ImageView getMyTurtleImage() {
    return myTurtleImage;
  }

  /**
   * Draws the line that follows the turtle using the old and new position from the model
   */
  @Override
  public void drawLine() {
    Line path = new Line(myModel.getOldX(), myModel.getOldY(), myModel.getNewX(),
        myModel.getNewY());
    path.setId("line");
    if (myModel.getPenActivity()) {
      myTurtlePane.getChildren().add(path);
    }
  }

  /**
   * Updates the position, orientation, and visibility of the turtle on the screen
   */
  @Override
  public void updateCreature() {
    myTurtleImage.setX(myModel.getNewX() - TURTLE_WIDTH / 2);
    myTurtleImage.setY(myModel.getNewY() - TURTLE_HEIGHT / 2);
    myTurtleImage.setRotate(myModel.getAngle());
    myTurtleImage.setVisible(myModel.getCreatureVisibility());
  }
}
