package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// Class that creates components for the ScreenDisplay class
public class ScreenDisplayComponents {
    private final int MY_WIDTH = 1300;
    private final int CANVAS_X = 350;
    private final int CANVAS_Y = 300;
    private final int CANVAS_HEIGHT = 600;
    private final int COMMAND_BOX_HEIGHT = 100;
    private final int COMMAND_BOX_X = 250;
    private final int COMMAND_BOX_Y = 50;

    public Button makeButton (String label, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }

    public TextArea makeCommandBox(EventHandler handler) {
        TextArea result = new TextArea();
        result.setOnKeyPressed(handler);
        result.setText("Type your code here...");
        result.setPrefWidth(MY_WIDTH);
        result.setPrefHeight(COMMAND_BOX_HEIGHT);
        result.setLayoutX(COMMAND_BOX_X);
        result.setLayoutY(COMMAND_BOX_Y);
        return result;
    }

    public Rectangle makeCanvas() {
        Rectangle result = new Rectangle(CANVAS_X, CANVAS_Y, MY_WIDTH, CANVAS_HEIGHT);
        result.setFill(Color.WHITE);
        return result;
    }

}