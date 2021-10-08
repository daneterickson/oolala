package oolala.view.canvas;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.Game;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

import java.util.function.Consumer;
import oolala.view.game.TurtleView;

public class TurtleCanvasDisplay extends CanvasDisplay{

  private GameView myTurtleView;
  private ScreenDisplayComponents myDisplayComponents;
  private Label turtleStateX;
  private Label turtleStateY;

  public TurtleCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(gameView, game, components);
    myTurtleView = gameView;
    myDisplayComponents = components;
  }

  /**
   * Creates a border pane where the window that displays the output of the game is on the left and
   * the UI controls for the game are on the left
   * @return BorderPane panel
   */
  @Override
  public Node makeCanvas () {
    BorderPane panel = new BorderPane();
    panel.setId("CanvasPanel");
    panel.setLeft(makeCanvasPanel());
    panel.setRight(makeLogoPanel());
    return panel;
  }

  @Override
  protected Node makeCanvasPanel () {
    StackPane pane = new StackPane();
    pane.setId("CanvasComponentPane");
    Rectangle canvas = myDisplayComponents.makeCanvas();
    pane.getChildren().addAll(canvas, myTurtleView.getMyCreaturePane());
    return pane;
  }

  private Node makeLogoPanel () {
    VBox panel = new VBox();
    panel.setId("LogoPanel");
    panel.getChildren().addAll(makePenThicknessPanel(), makeHomeLocationPanel(), makeTurtleImagePanel(), makeTurtleStatePanel());
    return panel;
  }

  private Node makePenThicknessPanel () {
    VBox panel = new VBox();
    panel.setId("PenThicknessPanel");
    Consumer<String> updateAction = e -> temporary(); // update with model's pen thickness method
    Node sliderLabel = myDisplayComponents.makeLabel("PenThickness");
    Node slider = myDisplayComponents.makeSlider("PenSlider", 1, 1, 4, updateAction);
    panel.getChildren().addAll(sliderLabel, slider);
    return panel;
  }

  private Node makeHomeLocationPanel() {
    VBox panel = new VBox();
    panel.setId("HomeLocationPanel");
    Node homeLocationLabel = myDisplayComponents.makeLabel("HomeLocationLabel");
    Node homeLocationX = myDisplayComponents.makeTextBoxWithLabel("HomeLocationX", "LocationX", e -> temporary());
    Node homeLocationY = myDisplayComponents.makeTextBoxWithLabel("HomeLocationY", "LocationY", e -> temporary());
    panel.getChildren().addAll(homeLocationLabel, homeLocationX, homeLocationY);
    return panel;
  }

  private Node makeTurtleImagePanel() {
    VBox panel = new VBox();
    panel.setId("TurtleImagePanel");
    Node turtleImageLabel = myDisplayComponents.makeLabel("TurtleImageLabel");
    panel.getChildren().addAll(turtleImageLabel, makeTurtleImagePanelButtons());
    return panel;
  }

  private Node makeTurtleImagePanelButtons() {
    HBox panel = new HBox();
    panel.setId("TurtleImagePanelButtons");
    Node catButton = myDisplayComponents.makeButton("CatButton", e -> myTurtleView.setTurtleImage("cat"));
    Node dogButton = myDisplayComponents.makeButton("DogButton", e -> myTurtleView.setTurtleImage("dog"));
    Node turtleButton = myDisplayComponents.makeButton("TurtleButton", e -> myTurtleView.setTurtleImage("turtle"));
    panel.getChildren().addAll(catButton, dogButton, turtleButton);
    return panel;
  }

  private Node makeTurtleStatePanel() {
    VBox panel = new VBox();
    panel.setId("TurtleStatePanel");
    Node turtleStateTitle = myDisplayComponents.makeLabel("TurtleStateTitle");
    turtleStateX = myDisplayComponents.makeLabel("TurtleStateX");
    turtleStateY = myDisplayComponents.makeLabel("TurtleStateY");
    panel.getChildren().addAll(turtleStateTitle, turtleStateX, turtleStateY);
    return panel;
  }

  @Override
  public void updateTurtleStatePanel() {
    turtleStateX.setText(turtleStateX.getText() + " " + myTurtleView.getX());
    turtleStateY.setText(turtleStateY.getText() + " " + myTurtleView.getY());
  }

  private void temporary () {

  }
}
