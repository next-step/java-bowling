package bowling.domain;

public class Pin {
    public static final int FULL_POINT = 10;
    private final int pin;

    public Pin(int pin) {
        if (pin < 0 || FULL_POINT < pin) {
            throw new IllegalArgumentException("넘어뜨린 핀의 수는 0이상 10이하 이어야 합니다.");
        }
        this.pin = pin;
    }

    public Point pinToPoint() {
        return Point.of(pin);
    }
}
