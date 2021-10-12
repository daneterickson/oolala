package oolala.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;
import java.util.function.Consumer;

import static oolala.view.ScreenDisplay.DEFAULT_RESOURCE_PACKAGE;

public class ScreenDisplayComponents {
    private static final int MY_WIDTH = 1300;
    private static final int CANVAS_X = 350;
    private static final int CANVAS_Y = 300;
    private static final int CANVAS_HEIGHT = 600;
    private static final int COMMAND_BOX_X = 250;
    private static final int COMMAND_BOX_Y = 50;
    private ResourceBundle myResources;

    public ScreenDisplayComponents (String language) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    }

    public Node makeButton (String label, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        result.setText(myResources.getString(label));
        result.setOnAction(handler);
        return setId(label, result);
    }

    public TextArea makeCommandBox (String id) {
        TextArea result = new TextArea();
        result.setText(myResources.getString(id));
        result.setLayoutX(COMMAND_BOX_X);
        result.setLayoutY(COMMAND_BOX_Y);
        return (TextArea)setId(id, result);
    }

    public Rectangle makeCanvas () {
        Rectangle result = new Rectangle(CANVAS_X, CANVAS_Y, MY_WIDTH, CANVAS_HEIGHT);
        result.setFill(Color.WHITE);
        return (Rectangle)setId("Canvas", result);
    }

    public Slider makeSlider (String id, int min, int start, int max) {
        Slider slider = new Slider();
        slider.setMin(min);
        slider.setMax(max);
        slider.setValue(start);
        slider.setMajorTickUnit(min);
        slider.setMinorTickCount(min);
        slider.setSnapToTicks(true);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        return (Slider)setId(id, slider);
    }

    public TextField makeTextBox (String id) {
        TextField result = new TextField();
        result.setText(myResources.getString(id));
        return (TextField)setId(id, result);
    }

    public Label makeLabel (String id) {
        Label label = new Label(myResources.getString(id));
        return (Label)setId(id, label);
    }

    public Node makeTextBoxWithLabel (String labelId, String textBoxId) {
        HBox panel = new HBox();
        Node label = makeLabel(labelId);
        Node textBox = makeTextBox(textBoxId);
        panel.getChildren().addAll(label, textBox);
        return setId(labelId, panel);
    }

    public Node makeTextBoxWithLabelAndButton (String labelId, String textBoxId, String buttonId, EventHandler<ActionEvent> handler) {
        HBox panel = new HBox();
        Node label = makeLabel(labelId);
        Node textBox = makeTextBox(textBoxId);
        Node button = makeButton(buttonId, handler);
        panel.getChildren().addAll(label, textBox, button);
        return setId(labelId, panel);
    }

    private Node setId (String id, Node node) {
        node.setId(id);
        return node;
    }

    public Alert createErrorMessage(String id, String content, Alert.AlertType alertType) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText(myResources.getString(id));
        error.setContentText(myResources.getString(content));
        error.showAndWait();
        return error;
    }

}
