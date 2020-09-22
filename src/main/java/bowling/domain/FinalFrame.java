package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private static final int MAX_PITCH_COUNT = 3;

    private final int index;
    private final List<Pin> pins;

    public FinalFrame() {
        this.index = 9;
        this.pins = new ArrayList<>();
    }

    public void pitch(int count) {
        if (this.isEnd()) {
            throw new IllegalArgumentException("");
        }

        pins.add(pins.isEmpty() || getLastPin().isEnd()
                ? new Pin(count) : getLastPin().next(count));
    }

    public boolean isEnd() {
        if (pins.isEmpty()) {
            return false;
        }

        if (pins.size() == MAX_PITCH_COUNT - 1 && pins.stream().mapToInt(Pin::getCount).sum() < 10) {
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
    public Frame next() {
        throw new IllegalArgumentException("");
    }

    @Override
    public List<String> getScore() {
        return pins.stream()
                .map(Pin::getSymbolValue)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return index == that.index &&
                Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, pins);
    }
}
