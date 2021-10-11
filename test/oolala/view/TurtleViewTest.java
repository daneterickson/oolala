package oolala.view;

import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import oolala.creatures.Creature;
import oolala.games.TurtleGame;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import oolala.Main;
import oolala.view.game.TurtleView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class TurtleViewTest extends DukeApplicationTest {

  public static final int originX = Main.ORIGIN_X;
  public static final int originY = Main.ORIGIN_Y;

  private TextInputControl myTextField;
  private Button myRunButton;

  private TurtleGame myTurtleGame;
  private TurtleView myTurtleView;
  private ScreenDisplay myDisplay;

  @Override
  public void start (Stage stage) {
    myTurtleGame = new TurtleGame(originX, originY);
    myTurtleView = new TurtleView(myTurtleGame, originX, originY);
    myDisplay = new ScreenDisplay("English", originX, originY);
    stage.setScene(myDisplay.setupDisplay(Main.BACKGROUND));
    stage.setTitle(Main.TITLE);
    stage.show();

    myTextField = lookup("#CommandBox").query();
    myTextField.clear();
    myRunButton = lookup("Run").query();
  }

  @Test
  void testDrawCreatureLocation () {
    TurtleView turtle = new TurtleView(myTurtleGame, originX, originY);
    ImageView turtleImage = turtle.getMyTurtleMap().get(1);
    double expectedX = originX;
    double expectedY = originY;
    assertEquals(expectedX, turtleImage.getX());
    assertEquals(expectedY, turtleImage.getY());
  }

  @Test
  void testDrawCreatureVisibility () {
    TurtleView turtle = new TurtleView(myTurtleGame, originX, originY);
    ImageView turtleImage = turtle.getMyTurtleMap().get(1);
    assertEquals(true, turtleImage.isVisible());
  }

  @Test
  void testDrawCreatureSize () {
    TurtleView turtle = new TurtleView(myTurtleGame, originX, originY);
    ImageView turtleImage = turtle.getMyTurtleMap().get(1);
    double expectedHeight = turtle.TURTLE_HEIGHT;
    double expectedWidth = turtle.TURTLE_WIDTH;
    assertEquals(expectedHeight, turtleImage.getFitHeight());
    assertEquals(expectedWidth, turtleImage.getFitWidth());
  }

  @Test
  void testUpdateCreatureLocation () { // assume model functions properly
    int distance = 10;
    TurtleView turtle = new TurtleView(myTurtleGame, originX, originY);
    ImageView turtleImage = turtle.getMyTurtleMap().get(1);
    Creature creature = myTurtleGame.getCreaturesMap().get(1);
    double expectedX = originX;
    double expectedY = originY - distance;

    creature.reset(myTurtleGame); // reset location and angle to original values
    creature.move(distance, null); // moves 10 forward
    turtle.updateCanvas();
    assertEquals(expectedX, turtleImage.getX());
    assertEquals(expectedY, turtleImage.getY());
  }

  @Test
  void testMoveTurtleForward () {
    testMoveTurtle(100, 0, "fd");
  }

  @Test
  void testMoveTurtleBackward () {
    testMoveTurtle(100, 0, "bk");
  }

  @Test
  void testMoveTurtleXDirection () {
    testMoveTurtle(100, 90, "fd");
  }

  @Test
  void testMoveTurtleXYDirection () {
    testMoveTurtle(100, 45, "fd");
  }

  @Test
  void testAddTurtle () {
    String command = "tell 2";
    clickOn(myTextField);
    writeInputTo(myTextField, command);
    clickOn(myRunButton);
    assertEquals(2, myTurtleView.getMyTurtleMap().size());
  }

  @Test
  void testAddAndMoveTurtle () {
    String command = "fd 100\ntell 2\nrt 90\nfd 100";
    clickOn(myTextField);
    writeInputTo(myTextField, command);
    clickOn(myRunButton);
    assertEquals(2, myTurtleView.getMyTurtleMap().size());
    assertEquals(originY - 100, myTurtleView.getMyTurtleMap().get(1).getY());
    assertEquals(originX + 100, myTurtleView.getMyTurtleMap().get(2).getX());
  }

  @Test
  void testStampTurtle () {
    String command = "fd 100\nstamp\nfd 100";
    clickOn(myTextField);
    writeInputTo(myTextField, command);
    clickOn(myRunButton);
    assertEquals(originY - 200, myTurtleView.getMyTurtleMap().get(1).getY());
    assertEquals(originY - 100, myTurtleView.getMyTurtleMap().get(-1).getY());
  }

  private void testMoveTurtle(int distance, int angle, String direction) {
    String command = String.format("rt %d\n%s %d", angle, direction, distance);
    clickOn(myTextField);
    writeInputTo(myTextField, command);
    clickOn(myRunButton);
    double expectedX;
    double expectedY;
    double sinDistance = distance * Math.sin(Math.toRadians(angle));
    double cosDistance = distance * Math.cos(Math.toRadians(angle));
    if (direction.equals("fd")) {
      expectedX = originX + sinDistance;
      expectedY = originY - cosDistance;
    }
    else {
      expectedX = originX - sinDistance;
      expectedY = originY + cosDistance;
    }
    ImageView turtleImage = myTurtleView.getMyTurtleMap().get(1);
    assertEquals(expectedX, turtleImage.getX());
    assertEquals(expectedY, turtleImage.getY());
  }
}
