package com.touchup.kiosk.view;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.touchup.kiosk.controls.RadioToggleButton;
import com.touchup.kiosk.controls.StopWatch;
import com.touchup.kiosk.events.WashMethod;
import com.touchup.kiosk.events.WashMethodSelected;
import com.touchup.kiosk.events.WashingComplete;
import com.touchup.kiosk.service.FxUtils;
import com.touchup.kiosk.viewmodel.CustomWashingMethodViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javax.inject.Inject;

public class CustomWashingMethodView implements FxmlView<CustomWashingMethodViewModel> {

  private final EventBus eventBus;
  private final FxUtils fxUtils;

  @InjectViewModel
  private CustomWashingMethodViewModel viewModel;

  @FXML private RadioToggleButton toggleButtonCustomMethod;
  @FXML private StopWatch stopWatch;

  @Inject
  public CustomWashingMethodView(EventBus eventBus, FxUtils fxUtils) {
    this.eventBus = eventBus;
    this.fxUtils = fxUtils;
    eventBus.register(this);
  }

  @FXML
  private void initialize() {
    stopWatch.durationProperty().bind(viewModel.durationProperty());
    stopWatch.elapsedSecondsProperty().bind(viewModel.elapsedSecondsProperty());
    stopWatch.formattedTimeProperty().bind(viewModel.formattedTimeProperty());
    stopWatch.reverseProperty().bind(viewModel.reverseProperty());
  }

  @FXML
  private void addMoreTime() {
    eventBus.post(new WashingComplete());
  }

  @FXML
  private void goToTouchUpMethodScreen() {
    eventBus.post(new WashMethodSelected(WashMethod.TOUCH_UP));
  }

  @Subscribe
  private void onWashMethodSelected(WashMethodSelected event) {
    if (event.getWashMethod() == WashMethod.CUSTOM) {
      toggleButtonCustomMethod.setSelected(true);
      fxUtils.setScreen(getClass());
      viewModel.setCustomWashingMethod();
    }

    viewModel.start();
  }
}
