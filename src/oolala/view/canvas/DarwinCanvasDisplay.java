package oolala.view.canvas;

import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import oolala.games.DarwinGame;
import oolala.games.Game;
import oolala.view.game.DarwinView;
import oolala.view.game.GameView;
import oolala.view.ScreenDisplayComponents;

public class DarwinCanvasDisplay extends CanvasDisplay {

  private DarwinView myDarwinView;
  private Slider animationSpeedSlider;
  private DarwinGame myDarwinGame;

  public DarwinCanvasDisplay(GameView gameView, Game game, ScreenDisplayComponents components, Timeline animation) {
    super(components, animation);
    myDarwinGame = (DarwinGame) game;
    myDarwinView = (DarwinView) gameView;
  }

  /**
   * Creates a border pane where the window that displays the output of the game is on the left and
   * the UI controls for the game are on the left
   *
   * @return BorderPane panel
   */

  @Override
  protected Node addCreature(StackPane pane) {
    pane.getChildren().add(myDarwinView.getMyCreaturePane());
    return pane;
  }

  @Override
  public Node setupGamePanel() {
    VBox panel = new VBox();
    panel.setId("DarwinPanel");
    panel.getChildren().addAll(setupRadiusPanel(), setupHomeLocationPanel(), setupDarwinImagePanel(), animationSettingsPanel());
    return panel;
  }

  private Node setupRadiusPanel() {
    VBox panel = new VBox();
    panel.setId("RadiusPanel");
    Node radius = myDisplayComponents.makeTextBoxWithLabel("RadiusLabel", "RadiusBox");
    Node radiusButton = myDisplayComponents.makeButton("RadiusButton", e -> initializeGame((TextField) panel.lookup("#RadiusBox")));
    panel.getChildren().addAll(radius, radiusButton);
    return panel;
  }

  @Override
  protected Node setupHomeButton() {
    Node renderButton = myDisplayComponents.makeButton("SetHomeLocation", e ->
            updateHomeLocation((TextField) panel.lookup("#LocationX"), (TextField) panel.lookup("#LocationY")));
    return renderButton;
  }

  private void initializeGame (TextField radiusBox){
    myDarwinGame.initialize((int) myPane.getLayoutBounds().getWidth(), (int) myPane.getLayoutBounds().getHeight(), Integer.parseInt(radiusBox.getText()));
  }

  private Node setupDarwinImagePanel () {
      VBox panel = new VBox();
      panel.setId("DarwinImagePanel");
      Node turtleImageLabel = myDisplayComponents.makeLabel("DarwinImageLabel");
      panel.getChildren().addAll(turtleImageLabel, setupDarwinImagePanelButtons());
      return panel;
  }

  private Node setupDarwinImagePanelButtons () {
    HBox panel = new HBox();
    panel.setId("DarwinImagePanelButtons");
    Node catButton = myDisplayComponents.makeButton("CatButton", e -> makeCreature("cat"));
    Node dogButton = myDisplayComponents.makeButton("DogButton", e -> makeCreature("dog"));
    Node turtleButton = myDisplayComponents.makeButton("TurtleButton", e -> makeCreature("turtle"));
    panel.getChildren().addAll(catButton, dogButton, turtleButton);
    return panel;
  }

  private Node animationSettingsPanel () {
    VBox panel = new VBox();
    panel.setId("AnimationSettingsPanel");
    Node sliderLabel = myDisplayComponents.makeLabel("AnimationSpeed");
    animationSpeedSlider = myDisplayComponents.makeSlider("SpeedSlider", 1, 3, 5);
    Node playPauseButton = myDisplayComponents.makeButton("PlayPauseButton", e -> togglePlay());
    panel.getChildren().addAll(sliderLabel, animationSpeedSlider, playPauseButton);
    return panel;
  }

  private void togglePlay() {
    if (isPlaying) myAnimation.stop();
    else myAnimation.play();
    isPlaying = !isPlaying;
  }

  private void makeCreature (String type) {
//    myDarwinView.setTurtleImage(type);
    myDarwinGame.addCreature(type, myDarwinGame.getHomeX(), myDarwinGame.getHomeY());
    myDarwinView.drawCreature(myDarwinGame.getHomeX(), myDarwinGame.getHomeY(), myDarwinView.getMyCreatureMap().size() + 1, type);
  }

  private void updateHomeLocation (TextField x, TextField y){
    if (!isInputValid(x) | !isInputValid(y)) {
      showErrorMessage();
    } else {
      myDarwinGame.updateHome(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
    }
  }

    @Override
    public void updateTurtleStatePanel () {

    }
  }
