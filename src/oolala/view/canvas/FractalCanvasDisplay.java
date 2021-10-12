package oolala.view.canvas;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.FractalGame;
import oolala.games.Game;
import oolala.view.game.FractalView;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

/**
 * Class for Fractal Display on the Canvas, displays specific UI components for Fractal
 *
 * @author: Evelyn Cupi-Garcia, Dane Erickson
 */
public class FractalCanvasDisplay extends CanvasDisplay {

  private static final int MY_LEVEL_SPAN = 100;

  private FractalView myFractalView;
  private FractalGame myFractalGame;

  public FractalCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(components);
    myFractalView = (FractalView) gameView;
    myFractalGame = (FractalGame) game;
  }


  @Override
  protected Node addCreature(StackPane pane) {
    pane.getChildren().add(myFractalView.getMyCreaturePane());
    return pane;
  }

  @Override
    protected Node setupGamePanel () {
    VBox panel = new VBox();
    panel.setId("FractalPanel");
    panel.getChildren().addAll(setupAngleAndLengthPanel(), setupLevelPanel(), setupHomeLocationPanel(), setupImagePanel());
    return panel;
  }

  private Node setupAngleAndLengthPanel() {
    VBox panel = new VBox();
    panel.setId("AngleAndLevelPanel");
    Node angle = myDisplayComponents.makeTextBoxWithLabel("AngleLabel", "AngleBox");
    Node length = myDisplayComponents.makeTextBoxWithLabel("LengthLabel", "LengthBox");
    panel.getChildren().addAll(angle, length);
    return panel;
  }

  private Node setupLevelPanel() {
    VBox panel = new VBox();
    panel.setId("LevelPanel");
    Node level = myDisplayComponents.makeTextBoxWithLabel("LevelLabel", "LevelBox");
    panel.getChildren().addAll(level);
    return panel;
  }

  private Node setupImagePanel() {
    VBox panel = new VBox();
    HBox images = new HBox();
    panel.setId("ImagePanel");
    Node imageLabel = myDisplayComponents.makeLabel("LeafLabel");
    Node catButton = myDisplayComponents.makeButton("CatButton", e -> myFractalView.setCreatureImage("cat"));
    Node dogButton = myDisplayComponents.makeButton("DogButton", e -> myFractalView.setCreatureImage("dog"));
    Node turtleButton = myDisplayComponents.makeButton("TurtleButton", e -> myFractalView.setCreatureImage("turtle"));
    images.getChildren().addAll(catButton, dogButton,turtleButton);
    panel.getChildren().addAll(imageLabel, images);
    return panel;
  }

  @Override
  protected Node setupHomeButton() {
    Node renderButton = myDisplayComponents.makeButton("SetButton", e -> renderFractal((TextField)myPane.lookup("#LevelBox"),
            (TextField)myPane.lookup("#LengthBox"), (TextField)myPane.lookup("#AngleBox"), (TextField)myPane.lookup("#LocationX")
    , (TextField)myPane.lookup("#LocationY")));
    return renderButton;
  }

  private void renderFractal(TextField level, TextField length, TextField angle, TextField startX, TextField startY) {
    if (!isInputValid(level) || !isInputValid(length) || !isInputValid(angle) || !isInputValid(startX) || !isInputValid(startY)) {
      showErrorMessage();
    } else {
      int levelNum = Integer.parseInt(level.getText());
      int lengthNum = Integer.parseInt(length.getText());
      int angleNum = Integer.parseInt(angle.getText());
      int xNum = Integer.parseInt(startX.getText());
      int yNum = Integer.parseInt(startY.getText());
      myFractalGame.initialize(levelNum, lengthNum, angleNum, xNum, yNum, MY_LEVEL_SPAN);
    }

  }

  @Override
  public void updateTurtleStatePanel() {

  }
}
