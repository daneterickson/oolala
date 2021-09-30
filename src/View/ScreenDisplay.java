package View;

import Games.DarwinGame;
import Games.FractalGame;
import Games.Game;
import Games.TurtleGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScreenDisplay {
    private final int MY_WIDTH = 200;
    private final int MY_HEIGHT = 45;
    private final int MY_SPACING = 20;
    private TextArea myCommandBox;
    private TurtleView myGameView;
    private ResourceBundle myResources;
    private int myStartX;
    private int myStartY;
    private TurtleGame myGame;
    private Timeline myAnimation;
    private double SECONDS_DELAY = 0.01;

    public static final String DEFAULT_RESOURCE_PACKAGE = "View.Resources.";
    public static final String DEFAULT_STYLESHEET = "/"+DEFAULT_RESOURCE_PACKAGE.replace(".", "/")+"Default.css";

    public ScreenDisplay (TurtleView game, String language, int startX, int startY) {
        myGameView = game;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myGame = new TurtleGame(startX, startY);
        myStartX = startX;
        myStartY = startY;

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

        Node turtleMode = displayComponents.makeButton("Turtle", value -> setCanvas(myGame));
        Node fractalMode = displayComponents.makeButton("Fractal", value -> setCanvas(new FractalGame()));
        Node darwinMode = displayComponents.makeButton("Darwin", value -> setCanvas(new DarwinGame()));

        panel.getChildren().addAll(turtleMode, fractalMode, darwinMode);

        return panel;
    }

    private Node makeCommandBox() {
        BorderPane panel = new BorderPane();
        ScreenDisplayComponents displayComponents = new ScreenDisplayComponents();
        myCommandBox = displayComponents.makeCommandBox(value -> setCanvas(myGame));
        panel.setLeft(myCommandBox);
        panel.setRight(makeCommandBoxButtons());

        return panel;
    }

    // Trying to figure out the connection from ScreenDisplay to model
    public void getCommandBoxInput() {
        String commandText = myCommandBox.getText();
        String[] splitCommand = commandText.split("\n");
        startAnimation();
        for (String command : splitCommand) {
            myGame.step(command);
            myGameView.updateCanvas();
        }
    }

    private void startAnimation () {
        if (myAnimation != null) {
            myAnimation.stop();
        }
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECONDS_DELAY)));
        myAnimation.play();
    }

    private Node makeCommandBoxButtons() {
        VBox panel = new VBox();
//        panel.getStyleClass().add("buttonDefault");
        ScreenDisplayComponents displayComponents = new ScreenDisplayComponents();
        Node runCommand = displayComponents.makeButton("Run", value -> getCommandBoxInput());
        Node clear = displayComponents.makeButton("Clear", value -> getCommandBoxInput()); // Clear screen functionality not done
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
        pane.getChildren().addAll(canvas, myGameView.getMyTurtlePane());
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
        if (game.equals(myGame)) {
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
