package oolala.view;

import oolala.games.DarwinGame;
import oolala.games.FractalGame;
import oolala.games.Game;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
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

public class ScreenDisplay {
    private static final int MY_PADDING = 20;
    private TextArea myCommandBox;
    private ResourceBundle myResources;
    private int myStartX;
    private int myStartY;
    private Game myGame;
    private GameView myGameView;
    private ScreenDisplayComponents myDisplayComponents;
    private CanvasDisplay myCanvasDisplay;

    public static final String DEFAULT_RESOURCE_PACKAGE = "oolala.view.resources.";
    public static final String DEFAULT_STYLESHEET = "/"+DEFAULT_RESOURCE_PACKAGE.replace(".", "/")+"Default.css";

    public ScreenDisplay (GameView gameView, Game game, String language, int startX, int startY) {
        myGameView = gameView;
        myGame = game;
        myDisplayComponents = new ScreenDisplayComponents(language);
//        myCanvasDisplay = new TurtleCanvasDisplay(myGameView, myGame, myDisplayComponents); // Default is turtle Logo Game
        myCanvasDisplay = new FractalCanvasDisplay(myGameView, myGame, myDisplayComponents); // Default is turtle Logo Game
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myStartX = startX;
        myStartY = startY;
    }

    public Scene setupDisplay (Paint background) {
        VBox root = new VBox();
        root.setId("Pane");
        root.setPadding(new Insets(MY_PADDING, MY_PADDING, MY_PADDING, MY_PADDING));
        root.getChildren().addAll(makeGameModesPanel(), myCanvasDisplay.makeCanvas(), makeCommandBox());
        Scene scene = new Scene(root, background);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        return scene;
    }

    private Node makeGameModesPanel () {
        HBox panel = new HBox();
        panel.setId("GameModePanel");

        Node turtleMode = myDisplayComponents.makeButton("Turtle", value -> setCanvas(new TurtleCanvasDisplay(myGameView, myGame, myDisplayComponents)));
        Node fractalMode = myDisplayComponents.makeButton("Fractal", value -> setCanvas(new FractalCanvasDisplay(myGameView, myGame, myDisplayComponents)));
        Node darwinMode = myDisplayComponents.makeButton("Darwin", value -> setCanvas(new DarwinCanvasDisplay(myGameView, myGame, myDisplayComponents)));

        panel.getChildren().addAll(turtleMode, fractalMode, darwinMode);

        return panel;
    }

    private Node makeCommandBox () {
        BorderPane panel = new BorderPane();
        panel.setId("CommandBoxPanel");
        myCommandBox = myDisplayComponents.makeCommandBox("CommandBox");
        panel.setLeft(myCommandBox);
        panel.setRight(makeCommandBoxButtons());

        return panel;
    }

    // Trying to figure out the connection from ScreenDisplay to model
    public void getCommandBoxInput () {
        String commandText = myCommandBox.getText();
        String[] splitCommand = myGame.compile(commandText).split("\n");
        for (String command : splitCommand) {
            myGame.step(command);
            if (myCanvasDisplay instanceof TurtleCanvasDisplay) {
                myGameView.setMyLineWidth(((TurtleCanvasDisplay) myCanvasDisplay).getLineWidthSlider().getValue());
            }
            myGameView.updateCanvas();
            myCanvasDisplay.updateTurtleStatePanel();
        }
    }

    private Node makeCommandBoxButtons () {
        VBox panel = new VBox();
        panel.setId("CommandBoxButtonPanel");
        Node runCommand = myDisplayComponents.makeButton("Run", value -> getCommandBoxInput());
        Node clear = myDisplayComponents.makeButton("Clear", value -> getCommandBoxInput()); // Clear screen functionality not done
        panel.getChildren().addAll(runCommand, clear);
        return panel;
    }

    protected void setCanvas (CanvasDisplay canvas) {
        myCanvasDisplay = canvas;
    }

    /**
     * Getter method to get myCanvasDisplay for testing
     *
     * @return myCanvasDisplay is the CanvasDisplay object
     */
    public CanvasDisplay getMyCanvasDisplay () {
        return myCanvasDisplay;
    }

}
