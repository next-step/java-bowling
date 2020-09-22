package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int MAX_FRAME_INDEX = 8;
    private static final int MAX_PITCH_COUNT = 2;

    private final int index;
    private final List<Pin> pins;

    public NormalFrame(int index) {
        this.validate(index);

        this.index = index;
        this.pins = new ArrayList<>();
    }

    private void validate(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("");

        }

        if (index > MAX_FRAME_INDEX) {
            throw new IllegalArgumentException("");
        }
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(0);
    }

    public NormalFrame next() {
        return new NormalFrame(index + 1);
    }

    public void pitch(int count) {
        if (this.isEnd()) {
            throw new IllegalArgumentException("");
        }

        pins.add(pins.isEmpty() ? new Pin(count) : getLastPin().next(count));
    }

    public boolean isEnd() {
        if (pins.isEmpty()) {
            return false;
        }

        if (getLastPin().isEnd()) {
            return true;
        }

        return pins.size() >= MAX_PITCH_COUNT;
    }

    public List<Pin> getPins() {
        return pins;
    }

    private Pin getLastPin() {
        if (getPins().isEmpty()) {
            throw new IllegalArgumentException("");
        }

        return pins.get(pins.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index &&
                Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, pins);
    }
}
