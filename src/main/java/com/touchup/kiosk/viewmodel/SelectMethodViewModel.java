package com.touchup.kiosk.viewmodel;

import com.google.common.eventbus.EventBus;
import com.touchup.kiosk.events.WashMethod;
import com.touchup.kiosk.events.WashMethodSelected;
import com.touchup.kiosk.service.TimerService;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javax.inject.Inject;

public final class SelectMethodViewModel implements ViewModel {

  private static final int AUTOSTART_DURATION = 10;

  private final BooleanProperty touchUpMethodSelected = new SimpleBooleanProperty(true);
  private final EventBus eventBus;
  private final TimerService timerService;

  @Inject
  public SelectMethodViewModel(EventBus eventBus, TimerService timerService) {
    this.eventBus = eventBus;
    this.timerService = timerService;

    timerService.durationProperty().set(AUTOSTART_DURATION);
  }

  public BooleanProperty touchUpMethodSelectedProperty() {
    return touchUpMethodSelected;
  }

  public ReadOnlyIntegerProperty durationProperty() {
    return timerService.durationProperty();
  }

  public ReadOnlyIntegerProperty elapsedSecondsProperty () {
    return timerService.elapsedSecondsProperty();
  }

  public ReadOnlyStringProperty formattedTimeProperty() {
    return timerService.formattedTimeProperty();
  }

  public void goToWashMethodScreen() {
    timerService.setOnFinished(null);
    timerService.stop();

    eventBus.post(touchUpMethodSelected.get()
      ? new WashMethodSelected(WashMethod.TOUCH_UP)
      : new WashMethodSelected(WashMethod.CUSTOM));
  }

  public void waitBeforeGoingToWashScreen() {
    timerService.setOnFinished(this::goToWashMethodScreen);
    timerService.start();
  }
}
