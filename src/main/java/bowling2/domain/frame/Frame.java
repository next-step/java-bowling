package bowling2.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Frame {
    protected int index;
    protected int restOfPins = 10;
    protected List<Integer> fallenPins = new ArrayList<>();
    protected Frame prev;
    protected Frame next;
    protected int remainedTryNo = 2;
    protected int score;

    public abstract Frame askCurrentFrame();

    public abstract void handleAfterTry(int fallenPin);

    public abstract ScoreType scoreType();

    public boolean validatePins(int fallenPins) {
        return fallenPins > restOfPins;
    }

    public Frame() {
    }

    public Frame(int index) {
        this.index = index;
    }

    public Frame(int restOfPins, List<Integer> fallenPins) {
        this(1, restOfPins, fallenPins);
    }

    public Frame(int index, int restOfPins, List<Integer> fallenPins) {
        this(index, restOfPins, fallenPins, null, null);
    }

    public Frame(int index, Frame prev, Frame next) {
        this(index, 10, Collections.emptyList(), prev, next);
    }

    public Frame(int index, int restOfPins, List<Integer> fallenPins, Frame prev, Frame next) {
        this(index, restOfPins, fallenPins, prev, next, 0);
    }

    public Frame(int index, int restOfPins, List<Integer> fallenPins, Frame prev, Frame next, int score) {
        this.index = index;
        this.restOfPins = restOfPins;
        this.fallenPins = fallenPins;
        this.prev = prev;
        this.next = next;
        this.score = score;
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

    public int score() {
        return score;
    }

    // TODO(jack.comeback) : 이 메서드 사용안하는 리팩토링 대상있음
    public int countOfFallenPins() {
        return fallenPins.size();
    }

    public int sumOfFallenPins() {
        return fallenPins
                .stream()
                .mapToInt(e -> e)
                .sum();
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
