package com.touchup.kiosk.guice;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.touchup.kiosk.service.FxUtils;
import com.touchup.kiosk.service.ProductManager;
import com.touchup.kiosk.service.ServeManager;
import com.touchup.kiosk.service.ServiceManager;
import com.touchup.kiosk.service.TimerService;
import com.touchup.kiosk.service.WashManager;
import javax.inject.Singleton;

public class KioskModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(EventBus.class).in(Singleton.class);
    bind(FxUtils.class).in(Singleton.class);
    bind(ProductManager.class).in(Singleton.class);
    bind(ServeManager.class).in(Singleton.class);
    bind(ServiceManager.class).in(Singleton.class);
    bind(TimerService.class);
    bind(WashManager.class).in(Singleton.class);
  }
}
