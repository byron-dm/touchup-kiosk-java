package com.touchup.kiosk.service;

import com.touchup.kiosk.pojos.Product;
import java.util.List;
import javax.inject.Inject;

public final class ProductManager {

  private final List<Product> availableProducts;

  @Inject
  public ProductManager() {
    availableProducts = List.of(
      new Product("3 Pack Microfibers", 2),
      new Product("5 Pack Microfibers", 3),
      new Product("Tire Gel", 1.5),
      new Product("Tire Applicator", 1),
      new Product("Interior Spray", 1),
      new Product("Window Spray", 1),
      new Product("Air Fresheners", 1),
      new Product("Leather Conditioner", 1.5));
  }

  public Product find(String productName) {
    Product foundProduct = availableProducts.get(0);

    for (Product product : availableProducts) {
      if (productName.equals(product.getName())) {
        foundProduct = product;
        break;
      }
    }

    return foundProduct;
  }
}
