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
  public static final double TURTLE_WIDTH = 10;
  public static final double TURTLE_HEIGHT = 20;

  private Creature myModel;
  private ImageView myTurtleImage;
  private Pane myTurtlePane;

  /**
   * Constructor to create a TurtleView, which is the turtle and lines that go on the canvas in the
   * ScreenDisplay class
   *
   * @param creature is the Creature object that is used as the model for this view class
   * @param x        is the starting x position of the turtle
   * @param y        is the starting y position of the turtle
   */
  public TurtleView(Creature creature, double x, double y) { // x and y based on canvas/scene size
    myModel = creature;
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

  private void drawCreature(double x, double y, double width,
      double height) { // final will just be double size for turtle
    myTurtleImage = new ImageView(new Image(TURTLE_IMAGE));
    myTurtleImage.setFitHeight(width);
    myTurtleImage.setFitWidth(height);
    myTurtleImage.setX(x);
    myTurtleImage.setY(y);
    myTurtleImage.setVisible(true);
    myTurtlePane.getChildren().add(myTurtleImage);
  }

  private void drawLine() {
    Line path = new Line(myModel.getOldX(), myModel.getOldY(), myModel.getNewX(),
        myModel.getNewY());
    if (myModel.getPenActivity()) {
      myTurtlePane.getChildren().add(path);
    }
  }

  private void updateCreature() {
    myTurtleImage.setX(myModel.getNewX() + TURTLE_WIDTH / 2);
    myTurtleImage.setY(myModel.getNewY() + TURTLE_HEIGHT / 2);
    myTurtleImage.setRotate(myModel.getAngle());
    myTurtleImage.setVisible(myModel.getCreatureVisibility());
  }
}
