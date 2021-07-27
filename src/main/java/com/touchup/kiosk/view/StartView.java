package com.touchup.kiosk.view;

import com.touchup.kiosk.service.FxUtils;
import com.touchup.kiosk.viewmodel.StartViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javax.inject.Inject;
import org.tbee.javafx.scene.layout.MigPane;

public final class StartView implements FxmlView<StartViewModel> {

  private final FxUtils fxUtils;

  @InjectViewModel
  private StartViewModel viewModel;

  @FXML private MigPane panelStart;

  @Inject
  public StartView(FxUtils fxUtils) {
    this.fxUtils = fxUtils;
  }

  @FXML
  private void initialize() {
    panelStart.sceneProperty().addListener((observable, oldScene, newScene) -> {
      if (newScene != null) {
        fxUtils.setMainScene(newScene);
      }
    });
  }

  @FXML
  private void goToServicesScreen() {
    fxUtils.setScreen(ServicesView.class);
  }
}
