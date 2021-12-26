package bowling.domain;

import java.util.List;
import java.util.Objects;

import bowling.engin.FirstClassImmutableList;
import bowling.engin.Frame;
import bowling.engin.Sequence;
import bowling.engin.Shot;

public class NormalFrame extends FirstClassImmutableList<Shot> implements Frame {
    private static final int NUMBER_OF_PINS = 10;
    private static final int NUMBER_OF_SHOT_ON_STRIKE = 1;
    private static final int NUMBER_OF_SHOT = 2;

    private final Sequence sequence;

    protected NormalFrame(Sequence sequence, List<Shot> collection) {
        super(collection);
        this.sequence = sequence;
    }

    public static NormalFrame of(Sequence sequence, List<Shot> shots) {
        if (sequence == null || shots == null) {
            throw new IllegalArgumentException("sequence or shots cannot be null");
        }

        if (shots.size() != NUMBER_OF_SHOT && shots.size() != NUMBER_OF_SHOT_ON_STRIKE) {
            throw new IllegalArgumentException("number of shots must be 1 or 2");
        }

        int sumOfHit = shots.stream()
                .map(Shot::toInt)
                .reduce(0, Integer::sum);
        if (sumOfHit > NUMBER_OF_PINS) {
            throw new IllegalArgumentException("sum of shot results cannot be larger than 10");
        }

        return new NormalFrame(sequence, shots);
    }

    public static NormalFrame strike(Sequence sequence) {
        return of(sequence, List.of(ShotResult.STRIKE));
    }

    public static NormalFrame first(Sequence sequence, Shot first) {
        if (first == null) {
            throw new IllegalArgumentException("shot results cannot be null");
        }

        return of(sequence, List.of(first));
    }

    @Override
    public Frame second(Shot second) {
        if (second == null) {
            throw new IllegalArgumentException("shot results cannot be null");
        }

        if (isClear()) {
            throw new IllegalArgumentException("the frame is already clear");
        }

        return NormalFrame.of(sequence, append(second).collect());
    }

    @Override
    public Sequence sequence() {
        return sequence;
    }

    @Override
    public boolean isClear() {
        return stream().map(Shot::toInt)
                .reduce(0, Integer::sum) == NUMBER_OF_PINS;
    }

    @Override
    public boolean completed() {
        return isClear() || size() == NUMBER_OF_SHOT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(sequence, that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sequence);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "sequence=" + sequence +
                "shots=" + collect().toString() +
                '}';
    }
}
