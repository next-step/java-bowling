package bowling;

public class Pitching implements State {

  private int first;

  public Pitching(int first) {
    this.first = first;
  }

  @Override
  public State roll(int second) {
    if (MIN_PINS > second) {
      throw new IllegalArgumentException(
          String.format("%d개핀 이상 입력하세요. 현재 %d핀이 입력되었습니다.", MIN_PINS, second)
      );
    }

    if (MAX_PINS < second) {
      throw new IllegalArgumentException(
          String.format("%d개핀을 초과할 수 없습니다. 현재 %d핀이 입력되었습니다.", MAX_PINS, second)
      );
    }

    if (first + second == 10) {
      return new Spare(first, second);
    }
    return new Open(first, second);
  }

  @Override
  public String symbol() {
    if (first == 0) {
      return "-";
    }
    return String.valueOf(first);
  }

}