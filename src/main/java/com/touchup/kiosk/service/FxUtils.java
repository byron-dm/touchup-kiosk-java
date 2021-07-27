package com.touchup.kiosk.service;

import com.touchup.kiosk.view.AddMoreTimeView;
import com.touchup.kiosk.view.CustomWashingMethodView;
import com.touchup.kiosk.view.PayNowView;
import com.touchup.kiosk.view.ProductsView;
import com.touchup.kiosk.view.SelectMethodView;
import com.touchup.kiosk.view.ServicesView;
import com.touchup.kiosk.view.StartView;
import com.touchup.kiosk.view.TouchUpMethodView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;

public final class FxUtils {

  private final Map<Class<? extends FxmlView<?>>, Parent> screens = new HashMap<>();

  private Class<? extends FxmlView<?>> currentViewClass;
  private ResourceBundle resources;
  private Scene mainScene;

  public Parent getView(Class<? extends FxmlView<?>> view) {
    return screens.get(view);
  }

  public void setMainScene(Scene mainScene) {
    this.mainScene = mainScene;
  }

  public void setResources(ResourceBundle resources) {
    this.resources = resources;
  }

  public void setScreen(Class<? extends FxmlView<?>> viewClass) {
    if (currentViewClass != viewClass) {
      currentViewClass = viewClass;
      mainScene.setRoot(screens.get(currentViewClass));
    }
  }

  public void preloadScreens() {
    screens.put(CustomWashingMethodView.class,
      FluentViewLoader.fxmlView(CustomWashingMethodView.class).resourceBundle(resources).load().getView());
    screens.put(PayNowView.class,
      FluentViewLoader.fxmlView(PayNowView.class).resourceBundle(resources).load().getView());
    screens.put(ProductsView.class,
      FluentViewLoader.fxmlView(ProductsView.class).resourceBundle(resources).load().getView());
    screens.put(SelectMethodView.class,
      FluentViewLoader.fxmlView(SelectMethodView.class).resourceBundle(resources).load().getView());
    screens.put(ServicesView.class,
      FluentViewLoader.fxmlView(ServicesView.class).resourceBundle(resources).load().getView());
    screens.put(StartView.class,
      FluentViewLoader.fxmlView(StartView.class).resourceBundle(resources).load().getView());
    screens.put(TouchUpMethodView.class,
      FluentViewLoader.fxmlView(TouchUpMethodView.class).resourceBundle(resources).load().getView());
    screens.put(AddMoreTimeView.class,
      FluentViewLoader.fxmlView(AddMoreTimeView.class).resourceBundle(resources).load().getView());
  }
}
