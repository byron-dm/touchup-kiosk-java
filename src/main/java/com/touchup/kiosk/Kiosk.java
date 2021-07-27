package com.touchup.kiosk;

import com.google.inject.Module;
import com.touchup.kiosk.guice.KioskModule;
import com.touchup.kiosk.service.CombinedResourceBundle;
import com.touchup.kiosk.service.FxUtils;
import com.touchup.kiosk.view.StartView;
import de.saxsys.mvvmfx.guice.MvvmfxGuiceApplication;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.inject.Inject;

public final class Kiosk extends MvvmfxGuiceApplication {

  private final ResourceBundle controls = ResourceBundle.getBundle("i18n/Controls", Locale.US);
  private final ResourceBundle strings = ResourceBundle.getBundle("i18n/Strings", Locale.US);

  @Inject
  private FxUtils fxUtils;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void initGuiceModules(List<Module> modules) throws Exception {
    modules.add(new KioskModule());
    super.initGuiceModules(modules);
  }

  @Override
  public void startMvvmfx(Stage applicationStage) {
    fxUtils.setResources(new CombinedResourceBundle(controls, strings));
    fxUtils.preloadScreens();
    configureApplicationStage(applicationStage);
    applicationStage.show();
  }

  private void configureApplicationStage(Stage applicationStage) {
    applicationStage.getIcons().add(new Image("/images/app-icon.png"));
    applicationStage.setMaximized(true);
    applicationStage.setScene(createApplicationScene());
    //applicationStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    applicationStage.setTitle(strings.getString("Application.Name"));
  }

  private Scene createApplicationScene() {
    Scene scene = new Scene(fxUtils.getView(StartView.class));
    scene.getStylesheets().addAll(getClass().getResource("/css/kiosk.css").toExternalForm());

    return scene;
  }
}