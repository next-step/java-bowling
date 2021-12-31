package bowling.domain;

import java.util.List;
import java.util.stream.Stream;

import bowling.engine.Frame;
import bowling.engine.Score;
import bowling.engine.Sequence;
import bowling.engine.Shot;
import bowling.engine.Shots;

public class NormalFrame implements Frame {
    public static final Frame START_FRAME = new NormalFrame(FrameSequence.FIRST_FRAME, FrameShots.EMPTY_SHOT);

    private final Sequence sequence;
    protected final Shots shots;

    protected NormalFrame(Sequence sequence, Shots shots) {
        this.sequence = sequence;
        this.shots = shots;
    }

    static Frame of(Sequence sequence, Shots shots) {
        if (sequence == null || shots == null) {
            throw new IllegalArgumentException("sequence or shots cannot be null");
        }

        if (sequence.isFinal()) {
            return FinalFrame.of(shots);
        }

        return new NormalFrame(sequence, shots);
    }

    static Frame of(Sequence sequence, List<Shot> shots) {
        if (shots == null) {
            throw new IllegalArgumentException("sequence or shots cannot be null");
        }

        return of(sequence, FrameShots.of(shots));
    }

    public static Frame first(Sequence sequence, Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the first shot cannot be null");
        }
        return of(sequence, List.of(shot));
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (completed()) {
            return NormalFrame.of(sequence.next(), List.of(shot));
        }

        return NormalFrame.of(sequence, shots.nextShot(shot));
    }

    @Override
    public Sequence sequence() {
        return sequence;
    }

    @Override
    public Score score() {
        return shots.score();
    }

    @Override
    public boolean hasThirdChance() {
        return false;
    }

    @Override
    public boolean completed() {
        return shots.size() == Shots.NUMBER_OF_SHOT || shots.isClear();
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public Stream<Shot> stream() {
        return shots.stream();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "sequence=" + sequence +
                ", shots=" + shots +
                '}';
    }
}
