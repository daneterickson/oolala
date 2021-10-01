package oolala.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

import static oolala.view.ScreenDisplay.DEFAULT_RESOURCE_PACKAGE;

// Class that creates components for the ScreenDisplay class
public class ScreenDisplayComponents {
    private final int MY_WIDTH = 1300;
    private final int CANVAS_X = 350;
    private final int CANVAS_Y = 300;
    private final int CANVAS_HEIGHT = 600;
    private final int COMMAND_BOX_HEIGHT = 100;
    private final int COMMAND_BOX_X = 250;
    private final int COMMAND_BOX_Y = 50;
    private ResourceBundle myResources;

    public ScreenDisplayComponents(String language) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    }

    public Node makeButton (String label, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        result.setText(label);
        result.setOnAction(handler);
        return setId(label, result);
    }

    public TextArea makeCommandBox(String id, EventHandler handler) {
        TextArea result = new TextArea();
        result.setOnKeyPressed(handler);
        result.setText(myResources.getString(id));
        result.setPrefWidth(MY_WIDTH);
        result.setPrefHeight(COMMAND_BOX_HEIGHT);
        result.setLayoutX(COMMAND_BOX_X);
        result.setLayoutY(COMMAND_BOX_Y);
        return (TextArea)setId(id, result);
    }

    public Rectangle makeCanvas() {
        Rectangle result = new Rectangle(CANVAS_X, CANVAS_Y, MY_WIDTH, CANVAS_HEIGHT);
        result.setFill(Color.WHITE);

        return (Rectangle)setId("Canvas", result);
    }

    private Node setId (String id, Node node) {
        node.setId(id);
        return node;
    }

}
