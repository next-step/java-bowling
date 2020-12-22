package bowling.domain.score;

/**
 * Created : 2020-12-18 오전 8:47
 * Developer : Seo
 */
public class Pins {
    public static final int MAX_PINS = 10;
    public static final int MIN_PINS = 0;

    private final int pins;

    public Pins(int pins) {
        validate(pins);
        this.pins = pins;
    }

    private void validate(int pins) {
        if (pins > MAX_PINS) {
            throw new IllegalArgumentException("볼링 핀은 최대 10개입니다.");
        }
        if (pins < MIN_PINS) {
            throw new IllegalArgumentException("볼링 핀은 최소 0개입니다.");
        }
    }

    public int get() {
        return pins;
    }
}
