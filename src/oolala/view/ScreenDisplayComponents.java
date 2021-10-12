package oolala.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

import static oolala.view.ScreenDisplay.DEFAULT_RESOURCE_PACKAGE;

/**
 * Class that makes individual components such as TextFields and Buttons for ScreenDisplay and
 * CanvasDisplay
 *
 * @author: Evelyn Cupil-Garcia
 */
public class ScreenDisplayComponents {

  private static final int MY_WIDTH = 1300;
  private static final int CANVAS_X = 350;
  private static final int CANVAS_Y = 300;
  private static final int CANVAS_HEIGHT = 600;
  private static final int COMMAND_BOX_X = 250;
  private static final int COMMAND_BOX_Y = 50;
  private ResourceBundle myResources;

  /**
   * Constructor that initializes ScreenDisplay by taking in a language that determines the
   * properties file to use
   *
   * @param language
   */
  public ScreenDisplayComponents(String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
  }

  /**
   * Method that creates a Button
   *
   * @param label   Button identifier for id/text
   * @param handler ActionHandler when Button is pressed
   * @return Node that has id as label and the button itself
   */
  public Node makeButton(String label, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    result.setText(myResources.getString(label));
    result.setOnAction(handler);
    return setId(label, result);
  }

  /**
   * Method that creates a Command Box
   *
   * @param id CommandBox identifier for id/text
   * @return Node that has id as label and the TextArea
   */
  public TextArea makeCommandBox(String id) {
    TextArea result = new TextArea();
    result.setText(myResources.getString(id));
    result.setLayoutX(COMMAND_BOX_X);
    result.setLayoutY(COMMAND_BOX_Y);
    return (TextArea) setId(id, result);
  }

  /**
   * Method that creates the blank canvas
   *
   * @return Rectangle with id of Canvas
   */
  public Rectangle makeCanvas() {
    Rectangle result = new Rectangle(CANVAS_X, CANVAS_Y, MY_WIDTH, CANVAS_HEIGHT);
    result.setFill(Color.WHITE);
    return (Rectangle) setId("Canvas", result);
  }

  /**
   * Method that creates a slider
   *
   * @param id    slider identifier
   * @param min   number to set tick units
   * @param start min number to start slider at
   * @param max   max number to end slider at
   * @return slider with identifier of id
   */
  public Slider makeSlider(String id, int min, int start, int max) {
    Slider slider = new Slider();
    slider.setMin(min);
    slider.setMax(max);
    slider.setValue(start);
    slider.setMajorTickUnit(min);
    slider.setMinorTickCount(min);
    slider.setSnapToTicks(true);
    slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
    return (Slider) setId(id, slider);
  }

  /**
   * Method that creates a TextField
   *
   * @param id TextField identifier
   * @return TextField with identifier
   */
  public TextField makeTextBox(String id) {
    TextField result = new TextField();
    result.setText(myResources.getString(id));
    return (TextField) setId(id, result);
  }

  /**
   * Method that creates a Label
   *
   * @param id Label identifier
   * @return Label with identifier
   */
  public Label makeLabel(String id) {
    Label label = new Label(myResources.getString(id));
    return (Label) setId(id, label);
  }

  /**
   * Method that creates a TextField and Label
   *
   * @param labelId   Label identifier
   * @param textBoxId TextField identifier
   * @return Node with TextField/Label and its identifiers
   */
  public Node makeTextBoxWithLabel(String labelId, String textBoxId) {
    HBox panel = new HBox();
    Node label = makeLabel(labelId);
    Node textBox = makeTextBox(textBoxId);
    panel.getChildren().addAll(label, textBox);
    return setId(labelId, panel);
  }

  private Node setId(String id, Node node) {
    node.setId(id);
    return node;
  }

  /**
   * Method that creates an Error Alert
   *
   * @param id        Alert id
   * @param content   Alert content to be displayer
   * @param alertType Alert type
   * @return error, which is the Alert
   */
  public Alert createErrorMessage(String id, String content, Alert.AlertType alertType) {
    Alert error = new Alert(Alert.AlertType.ERROR);
    error.setHeaderText(myResources.getString(id));
    error.setContentText(myResources.getString(content));
    error.showAndWait();
    return error;
  }

}
