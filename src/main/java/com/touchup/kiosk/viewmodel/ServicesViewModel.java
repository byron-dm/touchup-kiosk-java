package com.touchup.kiosk.viewmodel;

import com.touchup.kiosk.service.ServeManager;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javax.inject.Inject;

public final class ServicesViewModel implements ViewModel {

  private final ServeManager serveManager;

  @Inject
  public ServicesViewModel(ServeManager serveManager) {
    this.serveManager = serveManager;
  }

  public DoubleProperty totalCostProperty() {
    return serveManager.totalCostProperty();
  }

  public IntegerProperty serviceCostProperty() {
    return serveManager.serviceCostProperty();
  }

  public StringProperty serviceNameProperty() {
    return serveManager.serviceNameProperty();
  }

  public void selectService(String serviceName) {
    serveManager.selectService(serviceName);
  }

  public void unselectService() {
    serveManager.unselectService();
  }
}
