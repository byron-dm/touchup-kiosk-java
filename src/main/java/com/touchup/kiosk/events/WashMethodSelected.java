package com.touchup.kiosk.events;

public final class WashMethodSelected {
  private final WashMethod washMethod;

  public WashMethodSelected(WashMethod washMethod) {
    this.washMethod = washMethod;
  }

  public WashMethod getWashMethod() {
    return washMethod;
  }
}
