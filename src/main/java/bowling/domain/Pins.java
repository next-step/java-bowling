package bowling.domain;

public class Pins {
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private static final String PIPE = "|";
    private final int pins;

    public Pins(int pins) {
        validate(pins);
        this.pins = pins;
    }

    private void validate(int pins) {
        if(pins < MIN_PINS|| pins > MAX_PINS) {
            throw new IllegalArgumentException("투구하는 핀의 범위는 0 ~ 10 입니다.");
        }
    }

    public int sum(Pins pins) {
        return this.pins + pins.pins;
    }

    public boolean isStrike() {
        return this.pins == MAX_PINS;
    }

    public boolean isGutter() {
        return this.pins == MIN_PINS;
    }

    public boolean isSpare(Pins secondPins) {
        return this.pins + secondPins.getPins() == MAX_PINS;
    }

    public String expression() {
        return this.pins + PIPE;
    }

    public String expression(Pins secondPins) {
        if(this.pins == MIN_PINS) {
            return "-" + PIPE + secondPins.pins;
        }
        if(secondPins.isGutter()) {
            return this.pins + PIPE + "-";
        }
        return this.pins + PIPE + secondPins.pins;
    }

    public int getPins() {
        return pins;
    }

}
