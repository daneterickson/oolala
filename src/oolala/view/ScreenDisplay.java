package oolala.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oolala.games.DarwinGame;
import oolala.games.FractalGame;
import oolala.games.Game;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;

import java.util.ResourceBundle;
import oolala.games.TurtleGame;
import oolala.view.canvas.CanvasDisplay;
import oolala.view.canvas.DarwinCanvasDisplay;
import oolala.view.canvas.FractalCanvasDisplay;
import oolala.view.canvas.TurtleCanvasDisplay;
import oolala.view.game.DarwinView;
import oolala.view.game.FractalView;
import oolala.view.game.GameView;
import oolala.view.game.TurtleView;

/**
 * Class that setups the display for all the games such as setting up canvas, command box, and the
 * game itself.
 *
 * @author: Evelyn Cupil-Garcia, Dane Erickson
 */
public class ScreenDisplay {

  private static final int MY_PADDING = 20;
  private TextArea myCommandBox;
  private int myStartX;
  private int myStartY;
  private Game myGame;
  private GameView myGameView;
  private ScreenDisplayComponents myDisplayComponents;
  private CanvasDisplay myCanvasDisplay;
  private String compileCommand;
  private Timeline myAnimation;
  private VBox myRoot;
  private ResourceBundle myResources;
  private boolean hasCommand;
  private int commandIndex;
  private int framesPerSecond = 8;
  private double secondDelay = 1.0 / framesPerSecond;

  public static final String DEFAULT_RESOURCE_PACKAGE = "oolala.view.resources.";
  public static final String DEFAULT_STYLESHEET =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";

  /**
   * Constructor for ScreenDisplay that initializes its resources, ScreenDisplayComponents
   *
   * @param language what language property will be used (English or Spanish)
   * @param startX   start x-value for Turtle/Darwin
   * @param startY   start y-value for Turtle/Darwin
   */
  public ScreenDisplay(String language, int startX, int startY) {
    myDisplayComponents = new ScreenDisplayComponents(language);
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    myStartX = startX;
    myStartY = startY;
    hasCommand = false;
    commandIndex = 0;
  }

  /**
   * Getter that gets the current Game
   *
   * @return myGame
   */
  public Game getGame() {
    return myGame;
  }

  /**
   * Getter that gets the current GameView
   *
   * @return myGameView
   */
  public GameView getGameView() {
    return myGameView;
  }

  /**
   * Method that sets up the initial display that asks the user to choose a game.
   *
   * @param background Set's the scene's background color
   * @return scene that shows the 3 options of games to choose from and its associated label.
   */
  public Scene setupDisplay(Paint background) {
    myRoot = new VBox();
    myRoot.setId("Pane");
    myRoot.setPadding(new Insets(MY_PADDING, MY_PADDING, MY_PADDING, MY_PADDING));
    Node chooseGame = myDisplayComponents.makeLabel("ChooseGame");
    myRoot.getChildren().addAll(chooseGame, setupGameModesPanel());
    Scene scene = new Scene(myRoot, background);
    scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
    return scene;
  }

  /**
   * Method that sets up the buttons to switch between games
   *
   * @return panel of game button options
   */
  private Node setupGameModesPanel() {
    HBox panel = new HBox();
    panel.setId("GameModePanel");
    Node turtleMode = myDisplayComponents.makeButton("Turtle",
        value -> setCanvas(TurtleCanvasDisplay.class));
    Node fractalMode = myDisplayComponents.makeButton("Fractal",
        value -> setCanvas(FractalCanvasDisplay.class));
    Node darwinMode = myDisplayComponents.makeButton("Darwin",
        value -> setCanvas(DarwinCanvasDisplay.class));
    panel.getChildren().addAll(turtleMode, fractalMode, darwinMode);

    return panel;
  }


  private Node setupCommandBox() {
    BorderPane panel = new BorderPane();
    panel.setId("CommandBoxPanel");
    myCommandBox = myDisplayComponents.makeCommandBox("CommandBox");
    panel.setLeft(myCommandBox);
    panel.setRight(setCommandBoxButtons());

    return panel;
  }

  private void getCommandBoxInput() {
    String commandText = myCommandBox.getText();
    try {
      compileCommand = myGame.compile(commandText);
      hasCommand = true;
    } catch (Exception e) {
      showErrorMessage();
    }
  }

  private void step(String command, Boolean isFinal) {
    if (hasCommand) {
      myGame.step(command);
      if (myCanvasDisplay instanceof TurtleCanvasDisplay) {
        myGameView.setMyLineWidth(
            ((TurtleCanvasDisplay) myCanvasDisplay).getLineWidthSlider().getValue());
      }
      myGameView.updateCanvas();
      myCanvasDisplay.updateTurtleStatePanel();
        if (myCanvasDisplay instanceof FractalCanvasDisplay && isFinal) {
            ((FractalView) myGameView).drawLeaves();
        }
    }
  }

  /**
   * Method that creates an Error Message that is Command Invalid
   */
  public void showErrorMessage() {
    Alert myError = myDisplayComponents.createErrorMessage("CommandInvalid",
        "CommandInvalidContent", Alert.AlertType.ERROR);
    myError.showAndWait();
  }

  private Node setCommandBoxButtons() {
    VBox panel = new VBox();
    panel.setId("CommandBoxButtonPanel");
    Node runCommand = myDisplayComponents.makeButton("Run", value -> getCommandBoxInput());
    Node clear = myDisplayComponents.makeButton("Clear",
        value -> setCanvas(myCanvasDisplay.getClass()));
    panel.getChildren().addAll(runCommand, clear);
    return panel;
  }

  private void callCommand() {
    String command;
    boolean isFinal = false;
      if (compileCommand != null) {
          if (compileCommand.split("\n").length > commandIndex) {
              command = compileCommand.split("\n")[commandIndex];
              commandIndex++;
              if (commandIndex == compileCommand.split("\n").length - 1) {
                  isFinal = true;
              }
          } else {
              return;
          }
      } else {
          command = "";
      }
    step(command, isFinal);
  }

  private void setCanvas(Class canvas) {
    setGame(canvas);
    myRoot.getChildren().remove(myRoot.lookup("#ChooseGame"));
    if (myRoot.getChildren().contains(myRoot.lookup("#CanvasPanel")) &&
        myRoot.getChildren().contains(myRoot.lookup("#CommandBoxPanel"))) {
      myRoot.getChildren().remove(myRoot.lookup("#CanvasPanel"));
      myRoot.getChildren().remove(myRoot.lookup("#CommandBoxPanel"));
    }
    myRoot.getChildren().addAll(myCanvasDisplay.setupCanvas(), setupCommandBox());
    startAnimation();
  }

  private void setGame(Class canvas) {
    if (canvas == TurtleCanvasDisplay.class) {
      myGame = new TurtleGame(myStartX, myStartY);
      myGameView = new TurtleView((TurtleGame) myGame, myStartX, myStartY);
      myCanvasDisplay = new TurtleCanvasDisplay(myGameView, myGame, myDisplayComponents,
          myAnimation);
    }

    if (canvas == FractalCanvasDisplay.class) {
      myGame = new FractalGame();
      myGameView = new FractalView((FractalGame) myGame);
      myCanvasDisplay = new FractalCanvasDisplay(myGameView, myGame, myDisplayComponents,
          myAnimation);
    }

    if (canvas == DarwinCanvasDisplay.class) {
      myGame = new DarwinGame();
      myGameView = new DarwinView((DarwinGame) myGame, myStartX, myStartY);
      myCanvasDisplay = new DarwinCanvasDisplay(myGameView, myGame, myDisplayComponents,
          myAnimation);
    }
  }

  private void startAnimation() {
    myAnimation = new Timeline();
    myAnimation.setCycleCount(Timeline.INDEFINITE);
    if (myCanvasDisplay instanceof DarwinCanvasDisplay) {
      myAnimation.getKeyFrames().add(new KeyFrame(
          Duration.seconds(1 / myCanvasDisplay.getAnimationSpeedSlider().getValue() * 3),
          e -> callCommand()));
    } else {
      myAnimation.getKeyFrames()
          .add(new KeyFrame(Duration.seconds(secondDelay), e -> callCommand()));
    }
    myAnimation.play();
  }

  /**
   * Getter method to get myCanvasDisplay for testing
   *
   * @return myCanvasDisplay is the CanvasDisplay object
   */
  public CanvasDisplay getMyCanvasDisplay() {
    return myCanvasDisplay;
  }
}
