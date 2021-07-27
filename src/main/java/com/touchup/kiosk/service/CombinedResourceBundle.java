package com.touchup.kiosk.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CombinedResourceBundle extends ResourceBundle {

  private final Map<String, String> combinedResources = new HashMap<>();

  public CombinedResourceBundle(ResourceBundle...resourceBundles) {
    for (ResourceBundle resourceBundle : resourceBundles) {
      Enumeration<String> keysEnumeration = resourceBundle.getKeys();
      ArrayList<String> keysList = Collections.list(keysEnumeration);
      keysList.forEach(key -> combinedResources.put(key, resourceBundle.getString(key)));
    }
  }

  @Override
  public Object handleGetObject(String key) {
    return combinedResources.get(key);
  }

  @Override
  public Enumeration<String> getKeys() {
    return Collections.enumeration(combinedResources.keySet());
  }
}