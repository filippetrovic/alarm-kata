package kata.alarm;

public class EmailService {

  public void sendWarningEmail(int elapsed) {
    try {
      Thread.sleep(5000);
      throw new RuntimeException("Can't reach email server.");
    } catch (InterruptedException ignored) {
    }
  }

}
