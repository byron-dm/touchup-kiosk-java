package com.touchup.kiosk.view;

import com.touchup.kiosk.service.FxUtils;
import com.touchup.kiosk.viewmodel.ServicesViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javax.inject.Inject;

public final class ServicesView implements FxmlView<ServicesViewModel> {

  private final FxUtils fxUtils;

  @InjectViewModel
  private ServicesViewModel viewModel;

  @FXML private Button buttonNext;
  @FXML private Label labelServiceCost;
  @FXML private Label labelServiceName;
  @FXML private Label labelTotalCost;
  @FXML private ToggleGroup toggleGroupService;

  @Inject
  public ServicesView(FxUtils fxUtils) {
    this.fxUtils = fxUtils;
  }

  @FXML
  private void initialize() {
    buttonNext.disableProperty().bind(toggleGroupService.selectedToggleProperty().isNull());
    labelServiceCost.textProperty().bind(viewModel.serviceCostProperty());
    labelServiceName.textProperty().bind(viewModel.serviceNameProperty());
    labelTotalCost.textProperty().bind(viewModel.totalCostProperty());
  }

  @FXML
  private void goToProductsScreen() {
    fxUtils.setScreen(ProductsView.class);
  }

  @FXML
  private void goToStartScreen() {
    fxUtils.setScreen(StartView.class);
  }

  @FXML
  private void selectService(ActionEvent event) {
    ToggleButton toggleButton = (ToggleButton) event.getSource();

    if (toggleButton.isSelected()) {
      viewModel.selectService(toggleButton.getUserData().toString());
    } else {
      viewModel.unselectService();
    }
  }
}
