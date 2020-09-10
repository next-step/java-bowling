package bowling;

public interface State {

  int MIN_PINS = 0;
  int MAX_PINS = 10;

  static State of(int pins) {
    if (MIN_PINS > pins) {
      throw new IllegalArgumentException(
          String.format("%d개핀 이상 입력하세요. 현재 %d핀이 입력되었습니다.", MIN_PINS, pins)
      );
    }

    if (MAX_PINS < pins) {
      throw new IllegalArgumentException(
          String.format("%d개핀을 초과할 수 없습니다. 현재 %d핀이 입력되었습니다.", MAX_PINS, pins)
      );
    }

    if (pins == 10) {
      return new Strike();
    }

    return new Pitching(pins);
  }

  String symbol();

  State roll(int second);

}
