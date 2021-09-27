package View;

import Creatures.Creature;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TurtleView extends GameView{

  public static final double TURTLE_RADIUS = 10;

  private Creature myModel;
  private Node myTurtle;
  private List<Integer> myHistoryX;
  private List<Integer> myHistoryY;

  public TurtleView() {
    myTurtle = drawCreature(0,0,TURTLE_RADIUS); // draw creature?
    myHistoryX = new ArrayList<>();
    myHistoryY = new ArrayList<>();
    myHistoryX.add(0);
    myHistoryY.add(0);
  }

  protected Node drawCreature (double x, double y, double size) { // double x, double y, double size for parameters?
    Creature myModel = new Creature();
    return new Circle(x, y, size);
  }

  protected Line drawLine (boolean penActive) {
    int currentIndex = myHistoryX.size();
    Line path = new Line(myHistoryX.get(currentIndex-1), myHistoryY.get(currentIndex-1),
        myHistoryX.get(currentIndex), myHistoryY.get(currentIndex));
    if (penActive) return path;
    return null;
  }

  protected void updateCreature (int distance) {
    myModel.move(distance);

  }
}
