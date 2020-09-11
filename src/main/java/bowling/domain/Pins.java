package bowling.domain;

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

  public static Pins roll(int count) {
    return new Pins(count);
  }

  public boolean isStrike() {
    return count == MAX_PINS;
  }

  public boolean isSpare(int nextCount) {
    return this.count + nextCount == MAX_PINS;
  }

  public boolean isOpen(int nextCount) {
    return this.count + nextCount < MAX_PINS;
  }

  public boolean isPitching() {
    return true;
  }

  public boolean isGutter() {
    return count == MIN_PINS;
  }

  @Override
  public String toString() {
    return String.valueOf(count);
  }
}
