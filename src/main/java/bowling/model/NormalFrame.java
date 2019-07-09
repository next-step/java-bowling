package bowling.model;

import java.util.Objects;

import static bowling.model.Pins.MIN;

public class NormalFrame implements Frame {

    private int index;
    private Pins score = Pins.valueOf(MIN);
    private FrameState state = null;

    private NormalFrame(int index) {
        this.index = index;
    }

    static Frame ofFirst() {
        return of(NUMBER_OF_START);
    }

    static Frame of(int index) {
        return new NormalFrame(index);
    }

    @Override
    public Frame bowling(Pins pins) {
        score = pins;
        if (state == null) {
            state = new FirstBowl(pins);
            return moveNextFrame();
        }
        state = new SecondBowl(score, pins);
        return moveNextFrame();
    }

    private Frame moveNextFrame() {
        if (state.isFinished()) {
            return Frame.generate(index + 1);
        } else {
            return this;
        }
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, score);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", score=" + score +
                '}';
    }
}
