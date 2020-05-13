package kata.alarm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SnoozeAlarmUnitTest {

  @Mock
  private AlarmListener listener;

  @Mock
  private TimeProvider timeProvider;

  private Alarm alarm;

  @Before
  public void setUp() {
    alarm = new Alarm(timeProvider, listener);
  }

  @Test
  public void shouldNotGoOffWhenSnoozeIsActive() {
    // Given
    when(timeProvider.currentTime())
        .thenReturn(LocalTime.of(12, 0));

    alarm.snooze();

    // When
    alarm.checkForAlarm();

    // Then
    verify(listener, never())
        .notify(anyInt());
  }
}