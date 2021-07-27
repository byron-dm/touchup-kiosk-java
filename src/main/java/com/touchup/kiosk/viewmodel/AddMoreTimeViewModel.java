package com.touchup.kiosk.viewmodel;

import com.google.common.eventbus.EventBus;
import com.touchup.kiosk.events.WashMethodSelected;
import com.touchup.kiosk.service.WashManager;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javax.inject.Inject;

public class AddMoreTimeViewModel implements ViewModel {

  private final EventBus eventBus;
  private final WashManager washManager;

  @Inject
  public AddMoreTimeViewModel(EventBus eventBus, WashManager washManager) {
    this.eventBus = eventBus;
    this.washManager = washManager;
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

  public void addMoreTime(int minutes) {
    washManager.getTimerService().addDuration(minutes);
    eventBus.post(new WashMethodSelected(washManager.getWashMethod()));
  }
}
