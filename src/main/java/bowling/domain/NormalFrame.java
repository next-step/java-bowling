package bowling.domain;

public class NormalFrame {

  private final Pins pins = new Pins();

  public NormalFrame() {
  }

  public void play(int countOfHitPin) {
    validateHitPin(countOfHitPin);
    pins.add(new Pin(countOfHitPin));
  }

  public boolean isStrike() {
    return score() == Score.STRIKE;
  }

  public Score score() {
    return Score.score(pins.totalHitPin(), pins.size() < 2);
  }

  public boolean isEnd() {
    return pins.size() >= 2 || isStrike();
  }

  public boolean isEmpty() {
    return pins.size() == 0;
  }

  public Pins pins() {
    return pins;
  }

  private void validateHitPin(int countOfHitPin) {
    if (pins.totalHitPin() + countOfHitPin > 10) {
      throw new IllegalArgumentException("핀의 갯수는 10개를 넘을 수 없습니다.");
    }
  }
}
