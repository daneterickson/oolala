package oolala.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Alert;
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
    private int myStartX;
    private int myStartY;
    private Game myGame;
    private GameView myGameView;
    private ScreenDisplayComponents myDisplayComponents;
    private CanvasDisplay myCanvasDisplay;
    private String compileCommand;
    private Timeline myAnimation;
    private VBox myRoot;
    private ResourceBundle myResources;

    public static final String DEFAULT_RESOURCE_PACKAGE = "oolala.view.resources.";
    public static final String DEFAULT_STYLESHEET = "/"+DEFAULT_RESOURCE_PACKAGE.replace(".", "/")+"Default.css";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    public ScreenDisplay (String language, int startX, int startY) {
        myDisplayComponents = new ScreenDisplayComponents(language);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        myStartX = startX;
        myStartY = startY;
    }

    public Game getGame() {
        return myGame;
    }

    public GameView getGameView() {
        return myGameView;
    }

    public Scene setupDisplay (Paint background) {
        myRoot = new VBox();
        myRoot.setId("Pane");
        myRoot.setPadding(new Insets(MY_PADDING, MY_PADDING, MY_PADDING, MY_PADDING));
        Node chooseGame = myDisplayComponents.makeLabel("ChooseGame");
        myRoot.getChildren().addAll(chooseGame,setupGameModesPanel());
        Scene scene = new Scene(myRoot, background);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        return scene;
    }

    private Node setupGameModesPanel () {
        HBox panel = new HBox();
        panel.setId("GameModePanel");
        Node turtleMode = myDisplayComponents.makeButton("Turtle", value -> setCanvas(TurtleCanvasDisplay.class));
        Node fractalMode = myDisplayComponents.makeButton("Fractal", value -> setCanvas(FractalCanvasDisplay.class));
        Node darwinMode = myDisplayComponents.makeButton("Darwin", value -> setCanvas(DarwinCanvasDisplay.class));
        panel.getChildren().addAll(turtleMode, fractalMode, darwinMode);

        return panel;
    }

    private Node setupCommandBox () {
        BorderPane panel = new BorderPane();
        panel.setId("CommandBoxPanel");
        myCommandBox = myDisplayComponents.makeCommandBox("CommandBox");
        panel.setLeft(myCommandBox);
        panel.setRight(setCommandBoxButtons());

        return panel;
    }

    public void getCommandBoxInput () {
        String commandText = myCommandBox.getText();
        compileCommand = myGame.compile(commandText);
        if (compileCommand == null) { // compileCommand is null --> Darwin Game
            while (myCanvasDisplay.getPlayingStatus()) {
                myGame.step("");
                myGameView.updateCanvas();
            }
        } else {
            for (String command : compileCommand.split("\n")) {
                if (command == "") {
                    showErrorMessage();
                    break;
                } else {
                    myGame.step(command);
                    if (myCanvasDisplay instanceof TurtleCanvasDisplay) {
                        myGameView.setMyLineWidth(((TurtleCanvasDisplay) myCanvasDisplay).getLineWidthSlider().getValue());
                    }
                    myGameView.updateCanvas();
                    myCanvasDisplay.updateTurtleStatePanel();
                    if (myCanvasDisplay instanceof FractalCanvasDisplay) ((FractalView) myGameView).drawLeaves();
                }
            }
        }
    }
//        if (compileCommand == null) { // compileCommand is null --> Darwin Game
//            while (myCanvasDisplay.getPlayingStatus()) {
//                myGame.step("");
//                myGameView.updateCanvas();
//            }
//            for (int i=0; i<5; i++) {
//                myGame.step("");
//                myGameView.updateCanvas();
//            }
//        }
//        else {
//            for (String command : compileCommand.split("\n")) {
//                myGame.step(command);
//                if (myCanvasDisplay instanceof TurtleCanvasDisplay) {
//                    myGameView.setMyLineWidth(((TurtleCanvasDisplay) myCanvasDisplay).getLineWidthSlider().getValue());
//                }
//                myGameView.updateCanvas();
//                myCanvasDisplay.updateTurtleStatePanel();
//            }
//            if (myCanvasDisplay instanceof FractalCanvasDisplay) ((FractalView)myGameView).drawLeaves();
//        }
//    }
    public void step(String command) {
        myGame.step(command);
        myGameView.updateCanvas();
    }


    public void showErrorMessage() {
        Alert myError = myDisplayComponents.createErrorMessage("CommandInvalid", "CommandInvalidContent", Alert.AlertType.ERROR);
        myError.showAndWait();
    }

    private Node setCommandBoxButtons () {
        VBox panel = new VBox();
        panel.setId("CommandBoxButtonPanel");
        Node runCommand = myDisplayComponents.makeButton("Run", value -> getCommandBoxInput());
        Node clear = myDisplayComponents.makeButton("Clear", value -> setCanvas(myCanvasDisplay.getClass()));
        panel.getChildren().addAll(runCommand, clear);
        return panel;
    }

    private void callCommand(int index) {
        String command;
        if (compileCommand != null) {
            command = compileCommand.split("\n")[index];
        } else
            command = "";
        index++;
        step(command);
    }
    private void setCanvas (Class canvas) {
        setGame(canvas);
        myRoot.getChildren().remove(myRoot.lookup("#ChooseGame"));
        if (myRoot.getChildren().contains(myRoot.lookup("#CanvasPanel")) &&
                myRoot.getChildren().contains(myRoot.lookup("#CommandBoxPanel"))) {
            myRoot.getChildren().remove(myRoot.lookup("#CanvasPanel"));
            myRoot.getChildren().remove(myRoot.lookup("#CommandBoxPanel"));
        }
        myRoot.getChildren().addAll(myCanvasDisplay.setupCanvas(), setupCommandBox());
    }

    private void setGame (Class canvas) {
        startAnimation();
        if (canvas == TurtleCanvasDisplay.class) {
            myGame = new TurtleGame(myStartX, myStartY);
            myGameView = new TurtleView((TurtleGame) myGame, myStartX, myStartY);
            myCanvasDisplay = new TurtleCanvasDisplay(myGameView, myGame, myDisplayComponents, myAnimation);
        }

        if (canvas == FractalCanvasDisplay.class) {
            myGame = new FractalGame();
            myGameView = new FractalView((FractalGame)myGame);
            myCanvasDisplay = new FractalCanvasDisplay(myGameView, myGame, myDisplayComponents, myAnimation);
        }

        if (canvas == DarwinCanvasDisplay.class) {
            myGame = new DarwinGame();
            myGameView = new DarwinView((DarwinGame) myGame, myStartX, myStartY);
            myCanvasDisplay = new DarwinCanvasDisplay(myGameView, myGame, myDisplayComponents, myAnimation);
        }
}
    private void startAnimation() {
        int commandNum = 0;
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> callCommand(commandNum)));
        myAnimation.play();
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
