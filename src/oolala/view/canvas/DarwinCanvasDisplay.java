package oolala.view.canvas;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.Game;
import oolala.view.game.DarwinView;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

public class DarwinCanvasDisplay extends CanvasDisplay{

  private DarwinView myDarwinView;
  private Game myDarwinGame;

  public DarwinCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(components);
    myDarwinGame = game;
    myDarwinView = (DarwinView) gameView;
  }

  /**
   * Creates a border pane where the window that displays the output of the game is on the left and
   * the UI controls for the game are on the left
   * @return BorderPane panel
   */

  @Override
  protected Node addCreature(StackPane pane) {
    pane.getChildren().add(myDarwinView.getMyCreaturePane());
    return pane;
  }

  @Override
  public Node setupGamePanel () {
    VBox panel = new VBox();
    panel.setId("DarwinPanel");
    panel.getChildren().addAll(setupRadiusPanel(), setupHomeLocationPanel(), setupDarwinImagePanel());
    return panel;
  }

  private Node setupRadiusPanel() {
    VBox panel = new VBox();
    panel.setId("RadiusPanel");
    Node radius = myDisplayComponents.makeTextBoxWithLabel("RadiusLabel", "RadiusBox");
    Node radiusButton = myDisplayComponents.makeButton("RadiusButton", e-> temporary());
    panel.getChildren().addAll(radius, radiusButton);
    return panel;
  }

  @Override
  protected Node setupHomeButton() {
    Node renderButton = myDisplayComponents.makeButton("SetHomeLocation", e ->
            updateHomeLocation((TextField)panel.lookup("#LocationX"), (TextField)panel.lookup("#LocationY")));
    return renderButton;
  }

  private Node setupDarwinImagePanel() {
    VBox panel = new VBox();
    panel.setId("DarwinImagePanel");
    Node turtleImageLabel = myDisplayComponents.makeLabel("DarwinImageLabel");
    panel.getChildren().addAll(turtleImageLabel, setupDarwinImagePanelButtons());
    return panel;
  }

  private Node setupDarwinImagePanelButtons() {
    HBox panel = new HBox();
    panel.setId("DarwinImagePanelButtons");
    Node catButton = myDisplayComponents.makeButton("CatButton", e -> temporary());
    Node dogButton = myDisplayComponents.makeButton("DogButton", e -> temporary());
    Node turtleButton = myDisplayComponents.makeButton("TurtleButton", e -> temporary());
    panel.getChildren().addAll(catButton, dogButton, turtleButton);
    return panel;
  }

  private void updateHomeLocation (TextField x, TextField y) {
    if (!isInputValid(x) | !isInputValid(y)) {
      showErrorMessage();
    } else {
      myDarwinGame.updateHome(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
    }
  }

  private void temporary() {}
  
  @Override
  public void updateTurtleStatePanel() {

  }
}
