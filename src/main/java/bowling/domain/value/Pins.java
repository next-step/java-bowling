package bowling.domain.value;

public class Pins {
    private static final String MIN_ERROR_MSG = "쓰러트린 볼링 핀의 갯수는 0보다 작을 수 없습니다!!!";
    private static final String MAX_ERROR_MSG = "쓰러트린 볼링 핀의 갯수는 10보다 클 수 없습니다!!!";

    private static final int MIN_COUNT = 0;
    private static final int MAX_COUNT = 10;

    private final int pins;

    public Pins(int pins) {

        if(pins < MIN_COUNT) {
            throw new IllegalArgumentException(MIN_ERROR_MSG);
        }

        if(pins > MAX_COUNT) {
            throw new IllegalArgumentException(MAX_ERROR_MSG);
        }

        this.pins = pins;
    }

    public int getPins() {
        return pins;
    }
}
