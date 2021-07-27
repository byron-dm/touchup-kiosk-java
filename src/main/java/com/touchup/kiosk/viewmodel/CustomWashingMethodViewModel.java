package com.touchup.kiosk.viewmodel;

import com.google.common.eventbus.EventBus;
import com.touchup.kiosk.events.WashMethod;
import com.touchup.kiosk.events.WashingComplete;
import com.touchup.kiosk.service.WashManager;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javax.inject.Inject;

public class CustomWashingMethodViewModel implements ViewModel {

  private final WashManager washManager;

  @Inject
  public CustomWashingMethodViewModel(EventBus eventBus, WashManager washManager) {
    this.washManager = washManager;

    washManager.getTimerService().reverseProperty().set(true);
    washManager.getTimerService().durationProperty().set(WashManager.WASH_DURATION);
    washManager.getTimerService().setOnFinished(() -> eventBus.post(new WashingComplete()));
  }

  public ReadOnlyBooleanProperty reverseProperty() {
    return washManager.getTimerService().reverseProperty();
  }

  public ReadOnlyIntegerProperty durationProperty() {
    return washManager.getTimerService().durationProperty();
  }

  public ReadOnlyIntegerProperty elapsedSecondsProperty () {
    return washManager.getTimerService().elapsedSecondsProperty();
  }

  public ReadOnlyStringProperty formattedTimeProperty() {
    return washManager.getTimerService().formattedTimeProperty();
  }

  public void setCustomWashingMethod() {
    washManager.setWashMethod(WashMethod.CUSTOM);
  }

  public void start() {
    washManager.getTimerService().start();
  }
}
