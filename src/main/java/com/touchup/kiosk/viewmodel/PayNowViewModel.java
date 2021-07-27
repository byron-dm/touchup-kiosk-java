package com.touchup.kiosk.viewmodel;

import com.touchup.kiosk.service.ServeManager;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javax.inject.Inject;

public class PayNowViewModel implements ViewModel {

  private final ServeManager serveManager;

  @Inject
  public PayNowViewModel(ServeManager serveManager) {
    this.serveManager = serveManager;
  }

  public DoubleProperty totalCostProperty() {
    return serveManager.totalCostProperty();
  }
}
