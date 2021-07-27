package com.touchup.kiosk.controls;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.tbee.javafx.scene.layout.MigPane;

public final class StopWatch extends MigPane {

  private final Arc arc = new Arc();
  private final Circle circle = new Circle();
  private final BooleanProperty reverse = new SimpleBooleanProperty(false);
  private final IntegerProperty duration = new SimpleIntegerProperty(0);
  private final IntegerProperty elapsedSeconds = new SimpleIntegerProperty(0);
  private final IntegerProperty size = new SimpleIntegerProperty(100);
  private final ObjectProperty<Paint> arcColor = new SimpleObjectProperty<>(Color.valueOf("0x0076FF"));
  private final ObjectProperty<Paint> circleColor = new SimpleObjectProperty<>(Color.LIGHTGRAY);
  private final ObjectProperty<Paint> fontColor = new SimpleObjectProperty<>(Color.valueOf("0x27296C"));
  private final StringProperty message = new SimpleStringProperty("");
  private final StringProperty formattedTime = new SimpleStringProperty("0");

  public StopWatch() {
    super("insets 0");

    configureArc();
    configureCircle();

    Group group = new Group();
    group.getChildren().addAll(circle, arc);

    add(group);
    add(createLabelPanel(), "pos 0.5al 0.5al");

    elapsedSeconds.addListener((observable, oldValue, newValue) -> {
      if (reverse.get()) {
        arc.setLength(((double)elapsedSeconds.get()/duration.get() * 360) - 360);
      } else {
        arc.setLength((double)elapsedSeconds.get()/duration.get() * -360);
      }

      arcColor.set(newValue.intValue() == 0 ? Color.valueOf("0xFE4901") :
        reverse.get() ? Color.LIGHTGRAY : arcColor.get());
      fontColor.set(Color.valueOf(newValue.intValue() == 0 ? "0xFE4901" : "0x27296C"));
    });

    reverse.addListener((observable, wasReverse, isReverse) -> {
      arcColor.set(isReverse ? Color.LIGHTGRAY : arcColor.get());
      circleColor.set(isReverse ? Color.valueOf("0x0076FF") : circleColor.get());
    });
  }

  public void setArcColor(Paint arcColor) {
    this.arcColor.set(arcColor);
  }

  public Paint getArcColor() {
    return arcColor.get();
  }

  public void setCircleColor(Paint circleColor) {
    this.circleColor.set(circleColor);
  }

  public Paint getCircleColor() {
    return circleColor.get();
  }

  public BooleanProperty reverseProperty() {
    return reverse;
  }

  public IntegerProperty durationProperty() {
    return duration;
  }

  public IntegerProperty elapsedSecondsProperty() {
    return elapsedSeconds;
  }

  public void setFontColor(Paint fontColor) {
    this.fontColor.set(fontColor);
  }

  public Paint getFontColor() {
    return fontColor.get();
  }

  public void setMessage(String messageText) {
    message.set(messageText);
  }

  public String getMessage() {
    return message.get();
  }

  public void setSize(int size) {
    this.size.set(size);
  }

  public int getSize() {
    return size.get();
  }

  public StringProperty formattedTimeProperty() {
    return formattedTime;
  }

  private void configureArc() {
    arc.radiusXProperty().bind(size.subtract(1));
    arc.radiusYProperty().bind(size.subtract(1));
    arc.setFill(Color.TRANSPARENT);
    arc.setStartAngle(90);
    arc.setStrokeWidth(10);
    arc.strokeProperty().bind(arcColor);
  }

  private void configureCircle() {
    circle.setFill(Color.TRANSPARENT);
    circle.radiusProperty().bind(size);
    circle.setStrokeWidth(10);
    circle.strokeProperty().bind(circleColor);
  }

  private MigPane createLabelPanel() {
    Text textMessage = new Text();
    textMessage.fillProperty().bind(fontColor);
    textMessage.setStyle("-fx-font-size: 2em; -fx-font-weight: bold");
    textMessage.textProperty().bind(message);

    Text textTimer = new Text();
    textTimer.fillProperty().bind(fontColor);
    textTimer.setStyle("-fx-font-size: 6em; -fx-font-weight: bold");
    textTimer.textProperty().bind(formattedTime);

    MigPane migPane = new MigPane("gap 0, insets 0, wrap", "center");
    migPane.add(textTimer);
    migPane.add(textMessage);

    return migPane;
  }
}