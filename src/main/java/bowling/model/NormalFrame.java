package bowling.model;

import java.util.Objects;

import static bowling.model.Pins.DOWN_ALL;
import static bowling.model.Pins.MIN;

public class NormalFrame implements Frame {

    private int index;
    private int round = 0;
    private Pins score = Pins.valueOf(MIN);

    private NormalFrame(int index) {
        this.index = index;
    }

    public static Frame ofFirst() {
        return new NormalFrame(NUMBER_OF_START);
    }

    @Override
    public Frame bowling(Pins pins) {
        round++;
        score = pins;
        if (round == 1) {
            if (pins.equals(DOWN_ALL)) {
                return new NormalFrame(index + 1);
            }
            return this;
        }
        return new NormalFrame(index + 1);
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
