package bowling.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public class Pins {

    private static final int MAX_PIN_COUNT = 10;
    private static final int MIN_PIN_COUNT = 0;

    private static final String MIN_ERROR_MSG = "쓰러트린 볼링 핀의 갯수는 0보다 작을 수 없습니다!!!";
    private static final String MAX_ERROR_MSG = "쓰러트린 볼링 핀의 갯수는 10보다 클 수 없습니다!!!";
    private static final String MAX_BOWL_ERROR_MSG = "쓰러트린 볼링 핀의 갯수는 10보다 클 수 없습니다!!!";
    private static final String NOT_NUMBER_MESSAGE = "숫자만 입력 할 수 있습니다!!!";

    private static final Pattern PATTERN = Pattern.compile("-?\\d+");

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

    public Pins(String pins) {
        if(!PATTERN.matcher(pins).matches()) {
            throw new IllegalArgumentException(NOT_NUMBER_MESSAGE);
        }

        this.pins = Integer.parseInt(pins);
    }

    private Pins(int firstPins, int secondPins) {

        if(firstPins + secondPins > MAX_COUNT) {
            throw new IllegalArgumentException(MAX_BOWL_ERROR_MSG);
        }

        this.pins = firstPins + secondPins;
    }

    public boolean isStrike() {
        return this.pins == MAX_PIN_COUNT;
    }

    public boolean isGutter() {
        return this.pins == MIN_PIN_COUNT;
    }

    public boolean isSpare(Pins secondPins) {
        return new Pins(this.pins, secondPins.pins).isStrike();
    }

    public String count() {
        return String.valueOf(this.pins);
    }

    public int getPins() {
        return pins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pins pins1 = (Pins) o;
        return pins == pins1.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }

}
