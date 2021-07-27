package com.touchup.kiosk.view;

import com.google.common.eventbus.EventBus;
import com.touchup.kiosk.events.SelectMethodViewShown;
import com.touchup.kiosk.service.FxUtils;
import com.touchup.kiosk.viewmodel.PayNowViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javax.inject.Inject;

public class PayNowView implements FxmlView<PayNowViewModel> {

  private final EventBus eventBus;
  private final FxUtils fxUtils;

  @InjectViewModel
  private PayNowViewModel viewModel;

  @FXML private Label labelTotalCost;

  @Inject
  public PayNowView(EventBus eventBus, FxUtils fxUtils) {
    this.eventBus = eventBus;
    this.fxUtils = fxUtils;
  }

  @FXML
  private void initialize() {
    labelTotalCost.textProperty().bind(viewModel.totalCostProperty().asString());
  }

  @FXML
  private void goToProductsScreen() {
    fxUtils.setScreen(ProductsView.class);
  }

  @FXML
  private void goToSelectMethodScreen() {
    eventBus.post(new SelectMethodViewShown());
    fxUtils.setScreen(SelectMethodView.class);
  }
}
