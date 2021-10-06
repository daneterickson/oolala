package oolala.view;

import oolala.games.DarwinGame;
import oolala.games.FractalGame;
import oolala.games.Game;
import oolala.games.TurtleGame;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

public class ScreenDisplay {
    private static final int MY_SPACING = 20;
    private TextArea myCommandBox;
    private TurtleView myGameView;
    private ResourceBundle myResources;
    private int myStartX;
    private int myStartY;
    private TurtleGame myGame;
    private ScreenDisplayComponents myDisplayComponents;

    public static final String DEFAULT_RESOURCE_PACKAGE = "oolala.View.Resources.";
    public static final String DEFAULT_STYLESHEET = "/"+DEFAULT_RESOURCE_PACKAGE.replace(".", "/")+"Default.css";

    public ScreenDisplay (TurtleView gameView, TurtleGame game, String language, int startX, int startY) {
        myGameView = gameView;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myGame = game;
        myStartX = startX;
        myStartY = startY;
        myDisplayComponents = new ScreenDisplayComponents(language);
    }

    public Scene setupDisplay (Paint background) {
        VBox root = new VBox();
        root.setId("Pane");
        root.setSpacing(20);
        root.setPadding(new Insets(MY_SPACING, MY_SPACING, MY_SPACING, MY_SPACING));
        root.getChildren().addAll(makeGameModesPanel(), makeCanvas(), makeCommandBox());

        Scene scene = new Scene(root, background);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        return scene;
    }

    private Node makeGameModesPanel () {
        HBox panel = new HBox();
        panel.setSpacing(5);
        panel.setId("GameModePanel");

        Node turtleMode = myDisplayComponents.makeButton("Turtle", value -> setCanvas(myGame));
        Node fractalMode = myDisplayComponents.makeButton("Fractal", value -> setCanvas(new FractalGame()));
        Node darwinMode = myDisplayComponents.makeButton("Darwin", value -> setCanvas(new DarwinGame()));

        panel.getChildren().addAll(turtleMode, fractalMode, darwinMode);

        return panel;
    }

    private Node makeCommandBox () {
        BorderPane panel = new BorderPane();
        panel.setId("CommandBoxPanel");
        myCommandBox = myDisplayComponents.makeCommandBox("CommandBox", value -> setCanvas(myGame));
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
            myGameView.updateCanvas();
        }
    }

    private Node makeCommandBoxButtons () {
        VBox panel = new VBox();
        panel.setId("CommandBoxButtonPanel");
        Node runCommand = myDisplayComponents.makeButton("Run", value -> getCommandBoxInput());
        Node clear = myDisplayComponents.makeButton("Clear", value -> getCommandBoxInput()); // Clear screen functionality not done
        panel.getChildren().addAll(runCommand, clear);
        panel.setSpacing(MY_SPACING);
        return panel;
    }

    private Node makeCanvas () {
        BorderPane panel = new BorderPane();
        panel.setId("CanvasPanel");
        panel.setLeft(makeCanvasPanel());
        // TODO: Implement other panel views
        // panel.setRight(makeDarwinPanel());
        // panel.setRight(makeFractalPanel());
        // panel.setRight(makeLogoPanel());
        return panel;
    }

    private Node makeCanvasPanel () {
        StackPane pane = new StackPane();
        pane.setId("CanvasComponentPane");
        Rectangle canvas = myDisplayComponents.makeCanvas();
        pane.getChildren().addAll(canvas, myGameView.getMyTurtlePane());
        return pane;
    }
    // TODO: Implement this for other games
    private Node makeDarwinPanel () {
        VBox panel = new VBox();
        return panel;
    }
    // TODO: Implement this for other games
    private Node makeFractalPanel () {
        VBox panel = new VBox();
        return panel;
    }
    // TODO: Implement this for other games
    private Node makeLogoPanel () {
        VBox panel = new VBox();
        return panel;
    }

    // TODO: Set up canvas based on each game
    private void setCanvas (Game game) {

    }
}
