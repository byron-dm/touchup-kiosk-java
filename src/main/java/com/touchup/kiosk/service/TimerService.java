package com.touchup.kiosk.service;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;
import javax.inject.Inject;

public final class TimerService {

  private final BooleanProperty reverse = new SimpleBooleanProperty(false);
  private final IntegerProperty duration = new SimpleIntegerProperty(0);
  private final IntegerProperty elapsedSeconds = new SimpleIntegerProperty(0);
  private final StringProperty formattedTime = new SimpleStringProperty("0");

  private Runnable onFinished;
  private ScheduledService<Void> timer;

  @Inject
  public TimerService() {
    configureTimer();

    elapsedSeconds.addListener((observable, oldValue, newValue) -> {
      if (duration.get() < 60) {
        formattedTime.set(String.valueOf(newValue));
      } else {
        formattedTime.set(String.format("%d:%02d", newValue.intValue() % 3600 / 60, newValue.intValue() % 60));
      }
    });
  }

  public BooleanProperty reverseProperty() {
    return reverse;
  }

  public IntegerProperty durationProperty() {
    return duration;
  }

  public ReadOnlyIntegerProperty elapsedSecondsProperty() {
    return elapsedSeconds;
  }

  public ReadOnlyStringProperty formattedTimeProperty() {
    return formattedTime;
  }

  public void addDuration(int minutes) {
    if (reverse.get()) {
      duration.set(elapsedSeconds.get() == 0 ? minutes * 60 : duration.get() + minutes * 60);
      elapsedSeconds.set(elapsedSeconds.get() + minutes * 60);
    }
  }

  public void setOnFinished(Runnable onFinished) {
    this.onFinished = onFinished;
  }

  public void start() {
    if (timer != null && !timer.isRunning()) {
      elapsedSeconds.set(reverse.get() ? duration.get() : 0);
      timer.start();
    }
  }

  public void stop() {
    if (timer != null && timer.isRunning()) {
      timer.cancel();
    }
  }

  private void configureTimer() {
    timer = new ScheduledService<>() {
      @Override
      protected Task<Void> createTask() {
        return new Task<>() {
          @Override
          protected Void call() {
            if (reverse.get()) {
              updateIfReverse(this);
            } else {
              update(this);
            }

            return null;
          }
        };
      }
    };

    timer.setOnCancelled(event -> {
      if (onFinished != null) {
        onFinished.run();
        timer.reset();
      }
    });

    timer.setDelay(Duration.seconds(1));
    timer.setPeriod(Duration.seconds(1));
  }

  private void update(Task<Void> task) {
    elapsedSeconds.set(elapsedSeconds.get() + 1);

    if (elapsedSeconds.get() == duration.get() + 1) {
      task.cancel();
    }
  }

  private void updateIfReverse(Task<Void> task) {
    elapsedSeconds.set(elapsedSeconds.get() - 1);

    if (elapsedSeconds.get() == 0) {
      task.cancel();
    }
  }
}
