package com.touchup.kiosk.pojos;

public final class Product {

  private final String name;
  private final double cost;

  public Product(String name, double cost) {
    this.name = name;
    this.cost = cost;
  }

  public double getCost() {
    return cost;
  }

  public String getName() {
    return name;
  }
}
