package bowling.domain.frame;

import bowling.domain.result.FrameResult;
import bowling.domain.FrameSequence;
import bowling.engine.Frame;
import bowling.engine.Result;
import bowling.engine.Sequence;
import bowling.engine.Shot;

public abstract class BowlingFrame implements Frame {
    protected final Sequence sequence;
    protected final Result result;

    protected BowlingFrame(Sequence sequence, Result result) {
        this.sequence = sequence;
        this.result = result;
    }

    static Frame of(Sequence sequence, Result result) {
        if (sequence == null || result == null) {
            throw new IllegalArgumentException("sequence or result cannot be null");
        }

        if (sequence.isFinal()) {
            return ofFinal(result);
        }

        return new NormalFrame(sequence, result);
    }

    public static Frame ofFinal(Result result) {
        return new FinalFrame(result);
    }

    public static Frame ready(Sequence sequence, Shot shot) {
        return of(sequence, FrameResult.of(shot));
    }

    public static Frame startFrame() {
        return of(FrameSequence.FIRST_FRAME, FrameResult.emptyResult());
    }

    @Override
    public Sequence sequence() {
        return sequence;
    }

    @Override
    public Result result() {
        return result;
    }

    @Override
    public String toString() {
        return "BowlingFrame{" +
                "sequence=" + sequence +
                ", result=" + result +
                '}';
    }
}
