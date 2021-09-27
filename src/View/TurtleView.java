package View;

import Creatures.Creature;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class TurtleView extends GameView{

  public static final double TURTLE_WIDTH = 10;
  public static final double TURTLE_HEIGHT = 20;
  public static final Paint SHOW_TURTLE = Color.GREEN;
  public static final Paint HIDE_TURTLE = Color.TRANSPARENT;

  private Creature myModel;
  private Rectangle myTurtle;

  public TurtleView(Creature creature) {
    myTurtle = drawCreature(0,0,TURTLE_WIDTH, TURTLE_HEIGHT);
    myModel = creature;;
  }

  protected Rectangle drawCreature (double x, double y, double width, double height) { // final will just be double size for turtle
    Rectangle tmp = new Rectangle(x, y, width, height);
    tmp.setFill(SHOW_TURTLE);
    return tmp;
  }

  protected Line drawLine () {
    Line path = new Line(myModel.getOldX(), myModel.getOldY(), myModel.getNewX(), myModel.getNewY());
    if (myModel.getPenActivity()) return path;
    return null;
  }

  protected void updateCreature () {
    myTurtle.setX(myModel.getNewX() + TURTLE_WIDTH/2);
    myTurtle.setY(myModel.getNewY() + TURTLE_HEIGHT/2);
    myTurtle.setRotate(myModel.getAngle());
    if (myModel.getCreatureVisibility()) myTurtle.setFill(HIDE_TURTLE);
    else myTurtle.setFill(SHOW_TURTLE);
  }
}
