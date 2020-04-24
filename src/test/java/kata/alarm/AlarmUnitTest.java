package kata.alarm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

  @InjectMocks
  private Alarm alarm;

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
