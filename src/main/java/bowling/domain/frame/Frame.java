package bowling.domain.frame;

public class Frame {
    private final int pins;

    public Frame(int pins) {
        this.pins = pins;
    }

    public int score() {
        return pins;
    }
}
