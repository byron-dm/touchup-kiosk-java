package com.touchup.kiosk.viewmodel;

import com.touchup.kiosk.service.FormatUtils;
import com.touchup.kiosk.service.ServeManager;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javax.inject.Inject;

public final class ProductsViewModel implements ViewModel {

  private final ServeManager serveManager;

  @Inject
  public ProductsViewModel(ServeManager serveManager) {
    this.serveManager = serveManager;
  }

  public StringBinding productsCostProperty() {
    return serveManager.productsCostProperty().asString(FormatUtils.DECIMAL_FORMAT);
  }

  public StringBinding totalCostProperty() {
    return serveManager.totalCostProperty().asString(FormatUtils.DECIMAL_FORMAT);
  }

  public StringBinding productsCountProperty() {
    return serveManager.productsCountProperty().asString();
  }

  public StringBinding serviceCostProperty() {
    return serveManager.serviceCostProperty().asString();
  }

  public StringProperty serviceNameProperty() {
    return serveManager.serviceNameProperty();
  }

  public void addProduct(String productName) {
    serveManager.addProduct(productName);
  }

  public void removeProduct(String productName) {
    serveManager.removeProduct(productName);
  }
}
