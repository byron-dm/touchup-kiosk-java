package com.touchup.kiosk.service;

import com.touchup.kiosk.pojos.Service;
import java.util.List;
import javax.inject.Inject;

public final class ServiceManager {

  private final List<Service> availableServices;

  @Inject
  public ServiceManager() {
    availableServices = List.of(
      new Service("Self Serve", 10),
      new Service("Hand Wash Self Serve", 15),
      new Service("Full Serve", 20));
  }

  public Service find(String serviceName) {
    Service foundService = availableServices.get(0);

    for (Service service : availableServices) {
      if (serviceName.equals(service.getName())) {
        foundService = service;
        break;
      }
    }

    return foundService;
  }
}
