package oolala.view.canvas;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.Game;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

public class DarwinCanvasDisplay extends CanvasDisplay{

  private GameView myDarwinView;
  private ScreenDisplayComponents myDisplayComponents;

  public DarwinCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components) {
    super(gameView, game, components);
    myDarwinView = gameView;
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
    panel.setRight(makeDarwinPanel());
    return panel;
  }

  @Override
  protected Node makeCanvasPanel () {
    StackPane pane = new StackPane();
    pane.setId("CanvasComponentPane");
    Rectangle canvas = myDisplayComponents.makeCanvas();
//    pane.getChildren().addAll(canvas, myDarwinView.getMyTurtlePane());
    return pane;
  }

  private Node makeDarwinPanel () {
    VBox panel = new VBox();
    panel.setId("DarwinPanel");
    panel.getChildren().addAll(makeRadiusPanel(), makeSpeciesPanel(), makeDarwinImagePanel());
    return panel;
  }

  private Node makeRadiusPanel() {
    VBox panel = new VBox();
    panel.setId("RadiusPanel");
    Node radius = myDisplayComponents.makeTextBoxWithLabel("RadiusLabel", "RadiusBox", e -> temporary());
    panel.getChildren().add(radius);
    return panel;
  }

  private Node makeSpeciesPanel() {
    VBox panel = new VBox();
    panel.setId("SpeciesPanel");
    Node speciesLabel = myDisplayComponents.makeLabel("SpeciesLabel");
    Node speciesX = myDisplayComponents.makeTextBoxWithLabel("HomeLocationX", "LocationX", e -> temporary());
    Node speciesY = myDisplayComponents.makeTextBoxWithLabel("HomeLocationY", "LocationY", e -> temporary());
    panel.getChildren().addAll(speciesLabel, speciesX, speciesY);
    return panel;
  }

  private Node makeDarwinImagePanel() {
    VBox panel = new VBox();
    panel.setId("DarwinImagePanel");
    Node turtleImageLabel = myDisplayComponents.makeLabel("DarwinImageLabel");
    panel.getChildren().addAll(turtleImageLabel, makeDarwinImagePanelButtons());
    return panel;
  }

  private Node makeDarwinImagePanelButtons() {
    HBox panel = new HBox();
    panel.setId("DarwinImagePanelButtons");
    Node catButton = myDisplayComponents.makeButton("CatButton", e -> temporary());
    Node dogButton = myDisplayComponents.makeButton("DogButton", e -> temporary());
    Node turtleButton = myDisplayComponents.makeButton("TurtleButton", e -> temporary());
    panel.getChildren().addAll(catButton, dogButton, turtleButton);
    return panel;
  }

  private void temporary() {}
  
  @Override
  public void updateTurtleStatePanel() {

  }
}
