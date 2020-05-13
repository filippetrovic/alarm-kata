package kata.alarm;

import java.time.LocalTime;

public class Alarm {

  private final TimeProvider timeProvider;
  private final EmailService emailService;
  private final SmsService smsService;

  public Alarm(TimeProvider timeProvider, EmailService emailService, SmsService smsService) {
    this.timeProvider = timeProvider;
    this.emailService = emailService;
    this.smsService = smsService;
  }

  public int checkForAlarm() {

    final LocalTime now = timeProvider.currentTime();

    final int elapsed = (now.getHour() * 60 ) + now.getMinute();

    if (elapsed >= 500) {
      emailService.sendWarningEmail(elapsed);
      smsService.sendWarningSms(elapsed);
    }

    return elapsed;

  }

}
