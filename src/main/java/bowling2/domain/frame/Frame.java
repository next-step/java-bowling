package bowling2.domain.frame;

import bowling2.domain.score.ScoreStrategy;

import java.util.ArrayList;
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

    public void computeScore(ScoreStrategy scoreStrategy) {
        this.score = scoreStrategy.compute(this);
    }

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
        this(index, 10, new ArrayList<>(), prev, next);
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

    public int countOfFallenPins() {
        return fallenPins.size();
    }

    public int sumOfFallenPins() {
        return fallenPins
                .stream()
                .mapToInt(e -> e)
                .sum();
    }

    public boolean isOver() {
        return remainedTryNo == 0 || restOfPins == 0;
    }

    public boolean isCommonScoreType() {
        return scoreType().equals(ScoreType.COMMON);
    }

    public boolean isStrikeScoreType() {
        return scoreType().equals(ScoreType.STRIKE);
    }

    public boolean isSpareScoreType() {
        return scoreType().equals(ScoreType.SPARE);
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
