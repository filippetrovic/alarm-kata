package kata.alarm;

public class EmailService implements AlarmListener {

  public void notify(int elapsed) {
    System.out.println("Warning! Elapsed minutes: " + elapsed);
  }

}
