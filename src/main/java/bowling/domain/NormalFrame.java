package bowling.domain;

import bowling.exception.GameOverException;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    public static final int MAX_FRAME_INDEX = 8;
    private static final int MAX_PITCH_COUNT = 2;

    private final int index;
    private final Pins pins;

    public NormalFrame(int index) {
        this.validate(index);

        this.index = index;
        this.pins = new Pins();
    }

    private void validate(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("일반 프레임의 인덱스는 0 이상이어야 합니다.");
        }

        if (index > MAX_FRAME_INDEX) {
            throw new IllegalArgumentException("일반 프레임은 최대 9번 진행할 수 있습니다.");
        }
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(0);
    }

    public NormalFrame next() {
        return new NormalFrame(index + 1);
    }

    @Override
    public List<String> getScore() {
        return pins.getScore();
    }

    public void pitch(int count) {
        if (this.isEnd()) {
            throw new GameOverException();
        }

        pins.pitch(count);
    }

    public boolean isEnd() {
        if (pins.isEmpty()) {
            return false;
        }

        return pins.isEnd() || pins.overPitching(MAX_PITCH_COUNT);
    }

    public List<Pin> getPins() {
        return pins.getPins();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return index == frame.index &&
                Objects.equals(pins, frame.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, pins);
    }
}
