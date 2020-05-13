package kata.alarm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlarmUnitTest {

  @Mock
  private EmailService emailService;

  @Mock
  private TimeProvider timeProvider;

  @Mock
  private SmsService smsService;

  @Mock
  private AlarmAuditService alarmAuditService;

  private Alarm alarm;

  @Before
  public void setUp() {
    alarm = new Alarm(
        timeProvider,
        new FanOutDelegate(
            emailService,
            smsService,
            alarmAuditService));
  }

  @Test
  public void shouldSendEmailAt10h01m() {
    // Given
    when(timeProvider.currentTime())
        .thenReturn(LocalTime.of(10, 1));

    // When
    alarm.checkForAlarm();

    // Then
    verify(emailService)
        .sendWarningEmail(anyInt());

  }

  @Test
  public void shouldSendSmsAt10h01m() {
    // Given
    when(timeProvider.currentTime())
        .thenReturn(LocalTime.of(10, 1));

    // When
    alarm.checkForAlarm();

    // Then
    verify(smsService)
        .sendWarningSms(anyInt());

  }

  @Test
  public void shouldLogAlarmOccurrenceAt10h01m() {
    // Given
    when(timeProvider.currentTime())
        .thenReturn(LocalTime.of(10, 1));

    // When
    alarm.checkForAlarm();

    // Then
    verify(alarmAuditService)
        .logAlarmOccurrence(anyInt());

  }

  @Test
  public void shouldNotSendEmailAt01h01m() {
    // Given
    when(timeProvider.currentTime())
        .thenReturn(LocalTime.of(1, 1));

    // When
    alarm.checkForAlarm();

    // Then
    verify(emailService, never())
        .sendWarningEmail(anyInt());
  }

  @Test
  public void shouldNotSendSmsAt01h01m() {
    // Given
    when(timeProvider.currentTime())
        .thenReturn(LocalTime.of(1, 1));

    // When
    alarm.checkForAlarm();

    // Then
    verify(smsService, never())
        .sendWarningSms(anyInt());
  }

  @Test
  public void shouldReturnElapsedTimeInMinutesSinceMidnight() {
    // Given
    when(timeProvider.currentTime())
        .thenReturn(LocalTime.of(2, 5));

    // When
    int result = alarm.checkForAlarm();

    // Then
    assertThat(result)
        .isEqualTo(125);

  }
}
