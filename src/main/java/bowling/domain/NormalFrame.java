package bowling.domain;

import java.util.stream.Stream;

import bowling.engine.Frame;
import bowling.engine.Result;
import bowling.engine.Score;
import bowling.engine.Sequence;
import bowling.engine.Shot;

public class NormalFrame implements Frame {
    private final Sequence sequence;
    protected final Result result;

    protected NormalFrame(Sequence sequence, Result result) {
        this.sequence = sequence;
        this.result = result;
    }

    static Frame of(Sequence sequence, Result result) {
        if (sequence == null || result == null) {
            throw new IllegalArgumentException("sequence or result cannot be null");
        }

        if (sequence.isFinal()) {
            return FinalFrame.of(result);
        }

        return new NormalFrame(sequence, result);
    }

    public static Frame ready(Sequence sequence, Shot shot) {
        return of(sequence, FrameResult.of(shot));
    }

    public static Frame startFrame() {
        return new NormalFrame(FrameSequence.FIRST_FRAME, FrameResult.empty());
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (completed()) {
            return of(sequence.next(), result.next(shot));
        }

        return of(sequence, result.next(shot));
    }

    @Override
    public Sequence sequence() {
        return sequence;
    }

    @Override
    public Score score() {
        return result.score();
    }

    @Override
    public boolean completedBonus() {
        return result.completedBonus();
    }

    @Override
    public boolean completed() {
        return result.completed();
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public Stream<Shot> stream() {
        return result.stream();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "sequence=" + sequence +
                ", result=" + result +
                '}';
    }
}
