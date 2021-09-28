package View;

import Creatures.Creature;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class TurtleView extends GameView {

  public static final String TURTLE_IMAGE = "turtle.png";
  public static final double TURTLE_WIDTH = 10;
  public static final double TURTLE_HEIGHT = 20;
//  public static final Paint SHOW_TURTLE = Color.GREEN;
//  public static final Paint HIDE_TURTLE = Color.TRANSPARENT;

  private Creature myModel;
  private Rectangle myTurtleShape;
  private ImageView myTurtleImage;

  public TurtleView(Creature creature) {
    drawCreature(0, 0, TURTLE_WIDTH, TURTLE_HEIGHT);
    myModel = creature;
  }

  protected void drawCreature(double x, double y, double width,
      double height) { // final will just be double size for turtle
    myTurtleImage = new ImageView(new Image(TURTLE_IMAGE));
    myTurtleImage.setFitHeight(TURTLE_HEIGHT);
    myTurtleImage.setFitWidth(TURTLE_WIDTH);
    myTurtleImage.setVisible(true);
  }

  protected Line drawLine() {
    Line path = new Line(myModel.getOldX(), myModel.getOldY(), myModel.getNewX(),
        myModel.getNewY());
    if (myModel.getPenActivity()) {
      return path;
    }
    return null;
  }

  protected void updateCreature() {
    myTurtleImage.setX(myModel.getNewX() + TURTLE_WIDTH / 2);
    myTurtleImage.setY(myModel.getNewY() + TURTLE_HEIGHT / 2);
    myTurtleImage.setRotate(myModel.getAngle());
    myTurtleImage.setVisible(myModel.getCreatureVisibility());
  }
}
