package bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import bowling.engin.FirstClassImmutableList;
import bowling.engin.Frame;
import bowling.engin.Score;
import bowling.engin.Sequence;
import bowling.engin.Shot;

public class NormalFrame extends FirstClassImmutableList<Shot> implements Frame {
    public static final int NUMBER_OF_PINS = 10;
    public static final int NUMBER_OF_SHOT = 2;

    private final Sequence sequence;

    protected NormalFrame(Sequence sequence, List<Shot> collection) {
        super(collection);
        this.sequence = sequence;
    }

    static int sum(Stream<Shot> shotStream) {
        return shotStream.map(Shot::toInt)
                .reduce(0, Integer::sum);
    }

    static Frame of(Sequence sequence, List<Shot> shots) {
        if (sequence == null || shots == null) {
            throw new IllegalArgumentException("sequence or shots cannot be null");
        }

        if (sum(shots.stream()) > NUMBER_OF_PINS) {
            throw new IllegalArgumentException("sum of shot results cannot be larger than 10");
        }

        if (sequence.isFinal()) {
            return FinalFrame.of(shots);
        }

        return new NormalFrame(sequence, shots);
    }

    public static Frame strike(Sequence sequence) {
        return of(sequence, List.of(ShotResult.STRIKE));
    }

    public static Frame first(Sequence sequence, Shot first) {
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

        if (size() >= NUMBER_OF_SHOT) {
            throw new IllegalStateException("the second shot is allowed after first shot.");
        }

        return NormalFrame.of(sequence, append(second).collect());
    }

    @Override
    public Frame third(Shot third) {
        throw new IllegalStateException("the third shot is not allowed in normal frame");
    }

    @Override
    public Sequence sequence() {
        return sequence;
    }

    @Override
    public boolean isClear() {
        return score().toInt() == NUMBER_OF_PINS;
    }

    @Override
    public Score score() {
        return FrameScore.of(sum(stream()));
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
                ", shots=" + collect().toString() +
                '}';
    }
}
