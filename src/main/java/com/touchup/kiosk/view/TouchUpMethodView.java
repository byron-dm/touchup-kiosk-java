package com.touchup.kiosk.view;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.touchup.kiosk.controls.RadioToggleButton;
import com.touchup.kiosk.controls.StopWatch;
import com.touchup.kiosk.events.MoreTimeWanted;
import com.touchup.kiosk.events.WashMethod;
import com.touchup.kiosk.events.WashMethodSelected;
import com.touchup.kiosk.service.FxUtils;
import com.touchup.kiosk.viewmodel.TouchUpMethodViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javax.inject.Inject;
import org.tbee.javafx.scene.layout.MigPane;

public class TouchUpMethodView implements FxmlView<TouchUpMethodViewModel> {

  private final EventBus eventBus;
  private final FxUtils fxUtils;

  @InjectViewModel
  private TouchUpMethodViewModel viewModel;

  @FXML private MigPane panelToggles;
  @FXML private RadioToggleButton toggleButtonTouchUpMethod;
  @FXML private ScrollPane scrollPaneToggles;
  @FXML private StopWatch stopWatch;

  private int selectedToggleIndex = 0;

  @Inject
  public TouchUpMethodView(EventBus eventBus, FxUtils fxUtils) {
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

    ((ToggleButton)panelToggles.getChildren().get(0)).setSelected(true);
  }

  @FXML
  private void addMoreTime() {
    eventBus.post(new MoreTimeWanted());
  }

  @FXML
  private void goToCustomWashingMethodScreen() {
    eventBus.post(new WashMethodSelected(WashMethod.CUSTOM));
  }

  @FXML
  private void selectNextMode() {
    selectedToggleIndex = selectedToggleIndex == panelToggles.getChildren().size() - 1 ? 0 : selectedToggleIndex + 1;
    selectProperToggleButton();
  }

  @FXML
  private void selectPreviousMode() {
    selectedToggleIndex = (selectedToggleIndex == 0) ? panelToggles.getChildren().size() - 1 : selectedToggleIndex - 1;
    selectProperToggleButton();
  }

  @Subscribe
  private void onWashMethodSelected(WashMethodSelected event) {
    if (event.getWashMethod() == WashMethod.TOUCH_UP) {
      toggleButtonTouchUpMethod.setSelected(true);
      fxUtils.setScreen(getClass());
      viewModel.setTouchUpMethod();
    }

    viewModel.start();
  }

  private void selectProperToggleButton() {
    ((ToggleButton)panelToggles.getChildren().get(selectedToggleIndex)).fire();
    ensureVisible(panelToggles.getChildren().get(selectedToggleIndex));
  }

  private void ensureVisible(Node node) {
    double viewPortWidth = scrollPaneToggles.getViewportBounds().getWidth();
    double scrollPaneWidth = scrollPaneToggles.getContent().getBoundsInLocal().getWidth();
    double x = node.getBoundsInParent().getMaxX();

    if (x < (viewPortWidth / 2)) {
      scrollPaneToggles.setHvalue(0);
    } else if ((x >= (viewPortWidth / 2)) & (x <= (scrollPaneWidth - viewPortWidth / 2))) {
      scrollPaneToggles.setHvalue((x - (viewPortWidth / 2)) / (scrollPaneWidth - viewPortWidth));
    } else if (x >= (scrollPaneWidth - (viewPortWidth / 2))) {
      scrollPaneToggles.setHvalue(1);
    }
  }
}
