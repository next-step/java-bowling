package bowling2.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Frame {
    protected int index;
    protected int restOfPins = 10;
    protected List<Integer> fallenPins = new ArrayList<>();
    protected Frame prev;
    protected Frame next;

    public abstract Frame askCurrentFrame();

    public abstract void handleAfterTry(int fallenPin);

    public boolean validatePins(int fallenPins) {
        return fallenPins > restOfPins;
    }

    public Frame() {
    }

    public Frame(int index) {
        this.index = index;
    }

    public Frame(int restOfPins, List<Integer> fallenPins) {
        this.restOfPins = restOfPins;
        this.fallenPins = fallenPins;
    }

    public Frame(int index, int restOfPins, List<Integer> fallenPins) {
        this.index = index;
        this.restOfPins = restOfPins;
        this.fallenPins = fallenPins;
    }

    public Frame(int index, Frame prev, Frame next) {
        this.index = index;
        this.prev = prev;
        this.next = next;
    }

    public int getIndex() {
        return index;
    }

    public int getRestOfPins() {
        return restOfPins;
    }

    public List<Integer> getFallenPins() {
        return fallenPins;
    }

    public Frame getPrev() {
        return prev;
    }

    public Frame getNext() {
        return next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return restOfPins == frame.restOfPins && Objects.equals(fallenPins, frame.fallenPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restOfPins, fallenPins);
    }
}
