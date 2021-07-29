package com.touchup.kiosk.service;

import com.touchup.kiosk.events.WashMethod;
import javax.inject.Inject;

public final class WashManager {

  public static final int WASH_DURATION = 1200;

  private final TimerService timerService;

  private WashMethod washMethod = WashMethod.TOUCH_UP;

  @Inject
  public WashManager(TimerService timerService) {
    this.timerService = timerService;
  }

  public WashMethod getWashMethod() {
    return washMethod;
  }

  public TimerService getTimerService() {
    return timerService;
  }

  public void setWashMethod(WashMethod washMethod) {
    this.washMethod = washMethod;
  }
}
