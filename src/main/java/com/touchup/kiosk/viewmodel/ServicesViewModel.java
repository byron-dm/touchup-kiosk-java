package com.touchup.kiosk.viewmodel;

import com.touchup.kiosk.service.FormatUtils;
import com.touchup.kiosk.service.ServeManager;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.StringBinding;
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

  public StringBinding totalCostProperty() {
    return serveManager.totalCostProperty().asString(FormatUtils.DECIMAL_FORMAT);
  }

  public StringBinding serviceCostProperty() {
    return serveManager.serviceCostProperty().asString();
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
