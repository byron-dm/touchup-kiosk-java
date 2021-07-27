package com.touchup.kiosk.view;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.touchup.kiosk.controls.RadioToggleButton;
import com.touchup.kiosk.controls.StopWatch;
import com.touchup.kiosk.events.SelectMethodViewShown;
import com.touchup.kiosk.viewmodel.SelectMethodViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javax.inject.Inject;

public final class SelectMethodView implements FxmlView<SelectMethodViewModel> {

  @InjectViewModel
  private SelectMethodViewModel viewModel;

  @FXML private RadioToggleButton toggleButtonTouchUpMethod;
  @FXML private StopWatch stopWatch;

  @Inject
  public SelectMethodView(EventBus eventBus) {
    eventBus.register(this);
  }

  @FXML
  private void initialize() {
    stopWatch.durationProperty().bind(viewModel.durationProperty());
    stopWatch.elapsedSecondsProperty().bind(viewModel.elapsedSecondsProperty());
    stopWatch.formattedTimeProperty().bind(viewModel.formattedTimeProperty());
    viewModel.touchUpMethodSelectedProperty().bind(toggleButtonTouchUpMethod.selectedProperty());
  }

  @FXML
  private void goToWashMethodScreen() {
    viewModel.goToWashMethodScreen();
  }

  @Subscribe
  private void onSelectMethodViewShown(SelectMethodViewShown event) {
    viewModel.waitBeforeGoingToWashScreen();
  }
}
