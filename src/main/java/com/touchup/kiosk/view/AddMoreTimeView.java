package com.touchup.kiosk.view;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.touchup.kiosk.controls.StopWatch;
import com.touchup.kiosk.events.MoreTimeWanted;
import com.touchup.kiosk.events.WashMethodSelected;
import com.touchup.kiosk.service.FxUtils;
import com.touchup.kiosk.viewmodel.AddMoreTimeViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javax.inject.Inject;

public class AddMoreTimeView implements FxmlView<AddMoreTimeViewModel> {

  private final EventBus eventBus;
  private final FxUtils fxUtils;

  @InjectViewModel
  private AddMoreTimeViewModel viewModel;

  @FXML private Button buttonStartNow;
  @FXML private StopWatch stopWatch;
  @FXML private ToggleGroup toggleGroupMoreTime;

  @Inject
  public AddMoreTimeView(EventBus eventBus, FxUtils fxUtils) {
    this.eventBus = eventBus;
    this.fxUtils = fxUtils;

    eventBus.register(this);
  }

  @FXML
  private void initialize() {
    buttonStartNow.disableProperty().bind(toggleGroupMoreTime.selectedToggleProperty().isNull());

    stopWatch.durationProperty().bind(viewModel.durationProperty());
    stopWatch.elapsedSecondsProperty().bind(viewModel.elapsedSecondsProperty());
    stopWatch.formattedTimeProperty().bind(viewModel.formattedTimeProperty());
    stopWatch.reverseProperty().bind(viewModel.reverseProperty());
  }

  @FXML
  private void goToSelectedMethod() {
    eventBus.post(new WashMethodSelected(viewModel.getWashMethod()));
  }

  @FXML
  private void startNow() {
    viewModel.addMoreTime(Integer.parseInt(toggleGroupMoreTime.getSelectedToggle().getUserData().toString()));
  }

  @Subscribe
  private void onMoreTimeWanted(MoreTimeWanted event) {
    fxUtils.setScreen(getClass());
  }
}
