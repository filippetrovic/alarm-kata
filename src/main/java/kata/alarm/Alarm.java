package kata.alarm;

import java.time.LocalTime;

public class Alarm {

  private final TimeProvider timeProvider;
  private final FanOutDelegate alarmListener;

  public Alarm(TimeProvider timeProvider,
               EmailService emailService,
               SmsService smsService,
               AlarmAuditService auditService) {

    this.timeProvider = timeProvider;
    this.alarmListener = new FanOutDelegate(emailService, smsService, auditService);
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
