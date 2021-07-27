package com.touchup.kiosk.viewmodel;

import com.touchup.kiosk.service.ServeManager;
import de.saxsys.mvvmfx.ViewModel;
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

  public DoubleProperty productsCostProperty() {
    return serveManager.productsCostProperty();
  }

  public DoubleProperty totalCostProperty() {
    return serveManager.totalCostProperty();
  }

  public IntegerProperty productsCountProperty() {
    return serveManager.productsCountProperty();
  }

  public IntegerProperty serviceCostProperty() {
    return serveManager.serviceCostProperty();
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
