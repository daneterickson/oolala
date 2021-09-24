package View;

import Games.DarwinGame;
import Games.FractalGame;
import Games.Game;
import Games.TurtleGame;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
// Goals: create white screen and textbox
public class ScreenDisplay {

    public ScreenDisplay () {}


    public Scene setupDisplay (Paint background) {
        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(makeGameModesPanel());

        Scene scene = new Scene(root, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    private Node makeGameModesPanel () {
        HBox panel = new HBox();
        panel.setSpacing(5);

        Button turtleMode = new Button("Turtle");
        turtleMode.setOnAction(value ->  setCanvas(new TurtleGame()));
        Button fractalMode = new Button("Fractal");
        turtleMode.setOnAction(value ->  setCanvas(new FractalGame()));
        Button darwinMode = new Button("Darwin");
        turtleMode.setOnAction(value ->  setCanvas(new DarwinGame()));
        panel.getChildren().addAll(turtleMode, fractalMode, darwinMode);

        return panel;
    }

    private Node makeCommandBox () { return null; }

    private void setCanvas (Game game) {}

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
