package kata.alarm;

import java.time.LocalTime;

public class Alarm {

  private final TimeProvider timeProvider;
  private final AlarmListener alarmListener;

  public Alarm(TimeProvider timeProvider, AlarmListener alarmListener) {
    this.timeProvider = timeProvider;
    this.alarmListener = alarmListener;
  }

  public int checkForAlarm() {

    final int elapsed = getInputs();

    if (shouldGoOff(elapsed)) {
      alarmListener.notify(elapsed);
    }

    return elapsed;

  }

  private int getInputs() {
    final LocalTime now = timeProvider.currentTime();

    return (now.getHour() * 60 ) + now.getMinute();
  }

  private boolean shouldGoOff(int elapsed) {
    return elapsed >= 500;
  }

}
