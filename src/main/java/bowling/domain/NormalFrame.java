package bowling.domain;

import java.util.ArrayList;
import java.util.Objects;

public class NormalFrame implements Frame {
    public static final Pin MAX_PIN = new Pin(10);
    private final Rolls rolls;
    private FrameStatus result;

    public NormalFrame() {
        this.rolls = new Rolls(new ArrayList<>());
    }

    public NormalFrame(Rolls rolls) {
        this.rolls = rolls;
    }

    public Rolls getScores() {
        return rolls;
    }

    @Override
    public boolean end() {
        if (rolls.sum().equals(MAX_PIN) || rolls.size() == 2) {
            result = FrameStatus.match(rolls);
            return true;
        }
        return false;
    }

    @Override
    public void addScore(Pin pin) {
        rolls.add(pin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return Objects.equals(rolls, frame.rolls) && result == frame.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolls, result);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "scores=" + rolls +
                ", result=" + result +
                '}';
    }

    public FrameStatus getResult() {
        return result;
    }
}
