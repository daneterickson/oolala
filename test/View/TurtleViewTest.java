package View;

import Creatures.Creature;
import Games.TurtleGame;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import oolala.Main;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class TurtleViewTest extends DukeApplicationTest {

  public static final int originX = Main.ORIGIN_X;
  public static final int originY = Main.ORIGIN_Y;

  private TextArea myTextField;
  private Button myRunButton;

  TurtleGame myTurtleGame;
  TurtleView myTurtleView;
  ScreenDisplay myDisplay;

  @Override
  public void start (Stage stage) {
    myTurtleGame = new TurtleGame(originX, originY);
    myTurtleView = new TurtleView(myTurtleGame, originX, originY);
    myDisplay = new ScreenDisplay(myTurtleView, myTurtleGame, "English", originX, originY);
    stage.setScene(myDisplay.setupDisplay(Main.BACKGROUND));
    stage.setTitle(Main.TITLE);
    stage.show();

//    myTextField = lookup("commands").query();
//    myTextField.clear();
    myRunButton = lookup("Run").query();
  }
//
//  @Test
//  void testDrawCreatureLocation () {
//    TurtleView turtle = new TurtleView(myTurtleGame, originX, originY);
//    ImageView turtleImage = turtle.getMyTurtleMap().get(1);
//    double expectedX = originX;
//    double expectedY = originY;
//    assertEquals(expectedX, turtleImage.getX());
//    assertEquals(expectedY, turtleImage.getY());
//  }
//
//  @Test
//  void testDrawCreatureVisibility () {
//    TurtleView turtle = new TurtleView(myTurtleGame, originX, originY);
//    ImageView turtleImage = turtle.getMyTurtleMap().get(1);
//    assertEquals(true, turtleImage.isVisible());
//  }
//
//  @Test
//  void testDrawCreatureSize () {
//    TurtleView turtle = new TurtleView(myTurtleGame, originX, originY);
//    ImageView turtleImage = turtle.getMyTurtleMap().get(1);
//    double expectedHeight = turtle.TURTLE_HEIGHT;
//    double expectedWidth = turtle.TURTLE_WIDTH;
//    assertEquals(expectedHeight, turtleImage.getFitHeight());
//    assertEquals(expectedWidth, turtleImage.getFitWidth());
//  }
//
//  @Test
//  void testUpdateCreatureLocation () { // assume model functions properly
//    int distance = 10;
//    TurtleView turtle = new TurtleView(myTurtleGame, originX, originY);
//    ImageView turtleImage = turtle.getMyTurtleMap().get(1);
//    Creature creature = myTurtleGame.getCreaturesMap().get(1);
//    double expectedX = originX;
//    double expectedY = originY - distance;
//
//    creature.reset(); // reset location and angle to original values
//    creature.move(distance); // moves 10 forward
//    turtle.updateCanvas();
//    assertEquals(expectedX, turtleImage.getX());
//    assertEquals(expectedY, turtleImage.getY());
//  }

  @Test
  void testAddTurtle () {
    int distance = 100;
    String command = "fd " + distance;
//    clickOn(myTextField).write(command).clickOn(myRunButton);
    clickOn(myRunButton);
    double expectedX = originX;
    double expectedY = originY - distance;
    ImageView turtleImage = myTurtleView.getMyTurtleMap().get(1);

//    assertEquals(expectedX, turtleImage.getX());
//    assertEquals(expectedY, turtleImage.getY());
  }

}
