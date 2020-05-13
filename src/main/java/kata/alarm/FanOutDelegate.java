package kata.alarm;

public class FanOutDelegate implements AlarmListener {

  final EmailService emailService;
  final SmsService smsService;
  final AlarmAuditService auditService;

  public FanOutDelegate(EmailService emailService,
                        SmsService smsService,
                        AlarmAuditService auditService) {
    this.emailService = emailService;
    this.smsService = smsService;
    this.auditService = auditService;
  }

  @Override
  public void notify(int elapsed) {
    emailService.sendWarningEmail(elapsed);
    smsService.sendWarningSms(elapsed);
    auditService.logAlarmOccurrence(elapsed);
  }
}