package com.touchup.kiosk.controls;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class RadioToggleButton extends ToggleButton {

  private final BooleanProperty preventClick = new SimpleBooleanProperty(false);
  private final EventHandler<MouseEvent> mousePressedEventHandler = Event::consume;

  public RadioToggleButton() {
    preventClick.addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        addEventFilter(MouseEvent.MOUSE_PRESSED, mousePressedEventHandler);
      } else {
        removeEventFilter(MouseEvent.MOUSE_PRESSED, mousePressedEventHandler);
      }
    });
  }

  public boolean isPreventClick() {
    return preventClick.get();
  }

  public void setPreventClick(boolean shouldPreventClick) {
    preventClick.set(shouldPreventClick);
  }

  @Override
  public void fire() {
    // we don't toggle from selected to not selected if part of a group
    if (getToggleGroup() == null || !isSelected()) {
      super.fire();
    }
  }
}
