package com.touchup.kiosk.view;

import com.touchup.kiosk.service.FxUtils;
import com.touchup.kiosk.viewmodel.ProductsViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javax.inject.Inject;

public final class ProductsView implements FxmlView<ProductsViewModel> {

  private final FxUtils fxUtils;

  @InjectViewModel
  private ProductsViewModel viewModel;

  @FXML private Button buttonPayNow;
  @FXML private Label labelProductsCost;
  @FXML private Label labelProductsCount;
  @FXML private Label labelServiceCost;
  @FXML private Label labelServiceName;
  @FXML private Label labelTotalCost;

  @Inject
  public ProductsView(FxUtils fxUtils) {
    this.fxUtils = fxUtils;
  }

  @FXML
  private void initialize() {
    labelProductsCost.textProperty().bind(viewModel.productsCostProperty());
    labelProductsCount.textProperty().bind(viewModel.productsCountProperty());
    labelServiceName.textProperty().bind(viewModel.serviceNameProperty());
    labelServiceCost.textProperty().bind(viewModel.serviceCostProperty());
    labelTotalCost.textProperty().bind(viewModel.totalCostProperty());
  }

  @FXML
  private void goToPayNowScreen() {
    fxUtils.setScreen(PayNowView.class);
  }

  @FXML
  private void selectProduct(ActionEvent event) {
    ToggleButton toggleButton = (ToggleButton) event.getSource();

    if (toggleButton.isSelected()) {
      viewModel.addProduct(toggleButton.getUserData().toString());
    } else {
      viewModel.removeProduct(toggleButton.getUserData().toString());
    }
  }

  @FXML
  private void goToServicesScreen() {
    fxUtils.setScreen(ServicesView.class);
  }
}
