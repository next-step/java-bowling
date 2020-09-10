package bowling;

public class Pins {

  int MIN_PINS = 0;
  int MAX_PINS = 10;

  private int count;

  private Pins(int count) {
    if (MIN_PINS > count) {
      throw new IllegalArgumentException(
          String.format("%d개핀 이상 입력하세요. 현재 %d핀이 입력되었습니다.", MIN_PINS, count)
      );
    }

    if (MAX_PINS < count) {
      throw new IllegalArgumentException(
          String.format("%d개핀을 초과할 수 없습니다. 현재 %d핀이 입력되었습니다.", MAX_PINS, count)
      );
    }

    this.count = count;
  }

  public static Pins roll(int pins) {
    return new Pins(pins);
  }

  public boolean isStrike() {
    return count == 10;
  }

  public boolean isPitching() {
    return true;
  }
}
