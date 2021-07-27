package com.touchup.kiosk.pojos;

public final class Service {

  private final String name;
  private final int cost;

  public Service(String name, int cost) {
    this.name = name;
    this.cost = cost;
  }

  public int getCost() {
    return cost;
  }

  public String getName() {
    return name;
  }
}
