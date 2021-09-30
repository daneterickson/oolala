package View;

import Creatures.Creature;
import javafx.stage.Stage;
import oolala.Main;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class TurtleViewTest extends DukeApplicationTest {

  public static final int originX = Main.ORIGIN_X;
  public static final int originY = Main.ORIGIN_Y;

  Creature myTurtleModel;
  TurtleView myTurtleView;
  ScreenDisplay myDisplay;

  @Override
  public void start (Stage stage) {
    myTurtleModel = new Creature(originX, originY);
    myTurtleView = new TurtleView(myTurtleModel, originX, originY);
    myDisplay = new ScreenDisplay(myTurtleView);
    stage.setScene(myDisplay.setupDisplay(Main.BACKGROUND));
    stage.setTitle(Main.TITLE);
    stage.show();
  }

  @Test
  void testDrawCreatureLocation () {
    TurtleView turtle = new TurtleView(myTurtleModel, originX, originY);
    double expectedX = originX;
    double expectedY = originY;
    assertEquals(expectedX, turtle.getMyTurtleImage().getX());
    assertEquals(expectedY, turtle.getMyTurtleImage().getY());
  }

  @Test
  void testDrawCreatureVisibility () {
    TurtleView turtle = new TurtleView(myTurtleModel, originX, originY);
    assertEquals(true, turtle.getMyTurtleImage().isVisible());
  }

  @Test
  void testDrawCreatureSize () {
    TurtleView turtle = new TurtleView(myTurtleModel, originX, originY);
    double expectedHeight = turtle.TURTLE_HEIGHT;
    double expectedWidth = turtle.TURTLE_WIDTH;
    assertEquals(expectedHeight, turtle.getMyTurtleImage().getFitHeight());
    assertEquals(expectedWidth, turtle.getMyTurtleImage().getFitWidth());
  }

  @Test
  void testUpdateCreatureLocation () { // assume model functions properly
    int distance = 10;
    TurtleView turtle = new TurtleView(myTurtleModel, originX, originY);
    double expectedX = originX;
    double expectedY = originY + distance;

    myTurtleModel.reset(); // reset location and angle to original values
    myTurtleModel.move(distance); // moves 10 forward
    turtle.updateCreature();
    assertEquals(expectedX, turtle.getMyTurtleImage().getX());
    assertEquals(expectedY, turtle.getMyTurtleImage().getY());
  }

}
