package kata.alarm;

import java.time.LocalTime;

public class Alarm {

  private final TimeProvider timeProvider;
  private final EmailService emailService;
  private final SmsService smsService;
  private final AlarmAuditService auditService;

  public Alarm(TimeProvider timeProvider,
               EmailService emailService,
               SmsService smsService,
               AlarmAuditService auditService) {
    this.timeProvider = timeProvider;
    this.emailService = emailService;
    this.smsService = smsService;
    this.auditService = auditService;
  }

  public int checkForAlarm() {

    final LocalTime now = timeProvider.currentTime();

    final int elapsed = (now.getHour() * 60 ) + now.getMinute();

    if (elapsed >= 500) {
      emailService.sendWarningEmail(elapsed);
      smsService.sendWarningSms(elapsed);
      auditService.logAlarmOccurrence(elapsed);
    }

    return elapsed;

  }

}
