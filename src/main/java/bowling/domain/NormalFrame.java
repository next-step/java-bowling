package bowling.domain;

import java.util.ArrayList;
import java.util.Objects;

public class NormalFrame implements Frame {
    private final Rolls rolls;
    private FrameStatus status;

    NormalFrame(Rolls rolls, FrameStatus status) {
        this.rolls = rolls;
        this.status = status;
    }

    public NormalFrame() {
        this(new Rolls(new ArrayList<>()), FrameStatus.PROGRESS);
    }

    public NormalFrame(Rolls rolls) {
        this(rolls, FrameStatus.PROGRESS);
    }

    @Override
    public boolean isEnd() {
        return !(status == FrameStatus.PROGRESS);
    }

    @Override
    public void addRoll(Pin pin) {
        rolls.add(pin);
    }

    @Override
    public void updateStatus() {
        status = FrameStatus.match(rolls);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return Objects.equals(rolls, frame.rolls) && status == frame.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolls, status);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "scores=" + rolls +
                ", result=" + status +
                '}';
    }

    public Rolls getScores() {
        return rolls;
    }

    public FrameStatus getStatus() {
        return status;
    }
}
