package com.touchup.kiosk.service;

import com.touchup.kiosk.pojos.Product;
import com.touchup.kiosk.pojos.Service;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javax.inject.Inject;

public final class ServeManager {

  private final DoubleProperty productsCost = new SimpleDoubleProperty(0);
  private final DoubleProperty totalCost = new SimpleDoubleProperty(0);
  private final IntegerProperty productsCount = new SimpleIntegerProperty(0);
  private final IntegerProperty serviceCost = new SimpleIntegerProperty(0);
  private final ObjectProperty<Service> selectedService = new SimpleObjectProperty<>();
  private final ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
  private final ProductManager productManager;
  private final ServiceManager serviceManager;
  private final StringProperty serviceName = new SimpleStringProperty("");

  @Inject
  public ServeManager(ProductManager productManager, ServiceManager serviceManager) {
    this.productManager = productManager;
    this.serviceManager = serviceManager;

    selectedProducts.addListener((ListChangeListener<Product>) change -> {
      productsCost.set(selectedProducts.stream().mapToDouble(Product::getCost).sum());
      productsCount.set(selectedProducts.size());
      totalCost.set(productsCost.get() + serviceCost.get());
    });

    selectedService.addListener((observable, oldSelectedService, newSelectedService) -> {
      serviceCost.set(newSelectedService == null ? 0 : newSelectedService.getCost());
      serviceName.set(newSelectedService == null ? "" : newSelectedService.getName());
      totalCost.set(productsCost.get() + serviceCost.get());
    });
  }

  public IntegerProperty serviceCostProperty() {
    return serviceCost;
  }

  public DoubleProperty productsCostProperty() {
    return productsCost;
  }

  public DoubleProperty totalCostProperty() {
    return totalCost;
  }

  public IntegerProperty productsCountProperty() {
    return productsCount;
  }

  public StringProperty serviceNameProperty() {
    return serviceName;
  }

  public void addProduct(String productName) {
    selectedProducts.add(productManager.find(productName));
  }

  public void removeProduct(String productName) {
    selectedProducts.remove(productManager.find(productName));
  }

  public void selectService(String serviceName) {
    selectedService.set(serviceManager.find(serviceName));
  }

  public void unselectService() {
    selectedService.set(null);
  }
}
