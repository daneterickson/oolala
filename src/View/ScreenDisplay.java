package View;

import Games.DarwinGame;
import Games.FractalGame;
import Games.Game;
import Games.TurtleGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

public class ScreenDisplay {
    private final int MY_WIDTH = 200;
    private final int MY_HEIGHT = 45;
    private final int MY_SPACING = 20;
    private TextArea myCommandBox;
    private TurtleView myGame;
    private ResourceBundle myResources;

    public static final String DEFAULT_RESOURCE_PACKAGE = "View.Resources.";
    public static final String DEFAULT_STYLESHEET = "/"+DEFAULT_RESOURCE_PACKAGE.replace(".", "/")+"Default.css";

    public ScreenDisplay (TurtleView game, String language) {
        myGame = game;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    }

    public Scene setupDisplay (Paint background) {
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(MY_SPACING, MY_SPACING, MY_SPACING, MY_SPACING));
        root.getChildren().addAll(makeGameModesPanel(), makeCanvas(), makeCommandBox());

        Scene scene = new Scene(root, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        return scene;
    }

    private Node makeGameModesPanel () {
        HBox panel = new HBox();
        panel.setSpacing(5);
        ScreenDisplayComponents displayComponents = new ScreenDisplayComponents();

        Node turtleMode = displayComponents.makeButton("Turtle", value -> setCanvas(new TurtleGame()));
        Node fractalMode = displayComponents.makeButton("Fractal", value -> setCanvas(new FractalGame()));
        Node darwinMode = displayComponents.makeButton("Darwin", value -> setCanvas(new DarwinGame()));

        panel.getChildren().addAll(turtleMode, fractalMode, darwinMode);

        return panel;
    }

    private Node makeCommandBox() {
        BorderPane panel = new BorderPane();
        ScreenDisplayComponents displayComponents = new ScreenDisplayComponents();
        myCommandBox = displayComponents.makeCommandBox(value -> setCanvas(new TurtleGame()));
        panel.setLeft(myCommandBox);
        panel.setRight(makeCommandBoxButtons());

        return panel;
    }

    // Trying to figure out the connection from ScreenDisplay to model
    public String getCommandBoxInput() {
        System.out.println(myCommandBox.getText());
        return myCommandBox.getText();
    }

    private Node makeCommandBoxButtons() {
        VBox panel = new VBox();
        Game game = new Game();
//        panel.getStyleClass().add("buttonDefault");
        ScreenDisplayComponents displayComponents = new ScreenDisplayComponents();
        Node runCommand = displayComponents.makeButton("Run", value -> game.step(getCommandBoxInput()));
        Node clear = displayComponents.makeButton("Clear", value -> setCanvas(new TurtleGame()));
//        runCommand.setPrefWidth(MY_WIDTH);
//        runCommand.setPrefHeight(MY_HEIGHT);
//        clear.setPrefWidth(MY_WIDTH);
//        clear.setPrefHeight(MY_HEIGHT);
        panel.getChildren().addAll(runCommand, clear);
        panel.setSpacing(MY_SPACING);
        return panel;
    }

    private Node makeCanvas() {
        BorderPane panel = new BorderPane();
        panel.setLeft(makeCanvasPanel());
        // panel.setRight(makeDarwinPanel());
        // panel.setRight(makeFractalPanel());
        return panel;
    }

    private Node makeCanvasPanel() {
        StackPane pane = new StackPane();
        ScreenDisplayComponents displayComponents = new ScreenDisplayComponents();
        Rectangle canvas = displayComponents.makeCanvas();
        pane.getChildren().addAll(canvas, myGame.getMyTurtlePane());
        return pane;
    }

    private Node makeDarwinPanel() {
        VBox panel = new VBox();
        return panel;
    }

    private Node makeFractalPanel() {
        VBox panel = new VBox();
        return panel;
    }

    private void setCanvas (Game game) {
        if (game.equals(new TurtleGame())) {
            // initialize Turtle Game
        }

        if (game.equals(new DarwinGame())) {
            // initialize Darwin Game

        }

        if (game.equals(new FractalGame())) {
            // initialize Fractal Game
        }
    }

    private void handleKeyInput (KeyCode code) {
        // NEW Java 14 syntax that some prefer (but watch out for the many special cases!)
        //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
        switch (code) {
//            case N -> newMaze();
//            case S -> step();
//            case SPACE -> togglePause();
        }
    }
}
