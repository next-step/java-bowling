package bowling.domain;

public class Pins {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;
    private final int pins;

    public Pins(int countOfPins) {
        validate(countOfPins);
        this.pins = countOfPins;
    }

    private void validate(int countOfPins) {
        if(countOfPins < MIN_PINS) {
            throw new IllegalArgumentException("볼링 핀은 0개 미만일 수 없습니다.");
        }
        if(countOfPins > MAX_PINS) {
            throw new IllegalArgumentException("볼링 핀은 최대 10개를 넘을 수 없습니다.");
        }
    }
    public int getPins() {
        return pins;
    }

}
