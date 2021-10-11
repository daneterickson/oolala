package oolala.view.canvas;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.Game;
import oolala.view.ScreenDisplay;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

public abstract class CanvasDisplay {
  protected BorderPane myPane;
  protected VBox panel;
  protected ScreenDisplayComponents myDisplayComponents;

  protected CanvasDisplay(ScreenDisplayComponents components) {
    myDisplayComponents = components;
  }

  /**
   * Creates a border pane where the window that displays the output of the game is on the left and
   * the UI controls for the game are on the left
   * @return BorderPane panel
   */
  public Node setupCanvas () {
    myPane = new BorderPane();
    myPane.setId("CanvasPanel");
    myPane.setLeft(setupCanvasPanel());
    myPane.setRight(setupGamePanel());
    return myPane;
  }

  protected StackPane setupCanvasStackPane() {
    StackPane pane = new StackPane();
    pane.setId("CanvasComponentPane");
    Rectangle canvas = myDisplayComponents.makeCanvas();
    pane.getChildren().add(canvas);
    return pane;
  }

  protected abstract Node setupGamePanel();

  protected Node setupCanvasPanel () {
    StackPane myPane = setupCanvasStackPane();
    return addCreature(myPane);
  }

  protected Node setupHomeLocationPanel() {
    panel = new VBox();
    panel.setId("HomeLocationPanel");
    Node homeLocationLabel = myDisplayComponents.makeLabel("HomeLocationLabel");
    Node homeLocationX = myDisplayComponents.makeTextBoxWithLabel("HomeLocationX", "LocationX");
    Node homeLocationY = myDisplayComponents.makeTextBoxWithLabel("HomeLocationY", "LocationY");
    panel.getChildren().addAll(homeLocationLabel, homeLocationX, homeLocationY, setupHomeButton());
    return panel;
  }

  protected boolean isInputValid(TextField text) {
    String regex = "^[a-zA-Z]*$";
    if (text.getText().equals("") || text.getText().matches(regex) || text.getText() == null) {
      return false;
    }
    return true;
  }

  protected void showErrorMessage() {
    Alert myError = myDisplayComponents.createErrorMessage("ValuesNull", "ValuesNullContent", Alert.AlertType.ERROR);
    myError.showAndWait();
  }

  protected abstract Node setupHomeButton();

  protected abstract Node addCreature(StackPane pane);

  public abstract void updateTurtleStatePanel();

  }
