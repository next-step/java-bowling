package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.engine.collection.FirstClassImmutableList;
import bowling.engine.Frame;
import bowling.engine.Score;
import bowling.engine.Sequence;
import bowling.engine.Shot;

public class NormalFrame extends FirstClassImmutableList<Shot> implements Frame {
    public static final Frame START_FRAME = new NormalFrame(FrameSequence.FIRST_FRAME, Collections.emptyList());
    private final Sequence sequence;

    protected NormalFrame(Sequence sequence, List<Shot> collection) {
        super(collection);
        this.sequence = sequence;
    }
    static Frame of(Sequence sequence, List<Shot> shots) {
        if (sequence == null || shots == null) {
            throw new IllegalArgumentException("sequence or shots cannot be null");
        }

        if (sequence.isFinal()) {
            return FinalFrame.of(shots);
        }

        if (sum(shots.stream()) > NUMBER_OF_PINS) {
            throw new IllegalArgumentException("sum of shot results cannot be larger than 10");
        }

        return new NormalFrame(sequence, shots);
    }

    public static Frame first(Sequence sequence, Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("the first shot cannot be null");
        }
        return of(sequence, List.of(shot));
    }

    static int sum(Stream<Shot> shotStream) {
        return shotStream.map(Shot::toInt)
                .reduce(0, Integer::sum);
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (shot == null) {
            throw new IllegalArgumentException("shot results cannot be null");
        }

        if (completed()) {
            throw new IllegalStateException("the frame is already completed");
        }

        if (isSpareChallenge()) {
            shot = SpareShotResult.of(last(), shot);
        }

        return NormalFrame.of(sequence, append(shot).collect());
    }

    public boolean isSpareChallenge() {
        return size() != 0;
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
    public boolean hasThirdChance() {
        return false;
    }

    @Override
    public boolean completed() {
        return isClear() || size() == NUMBER_OF_SHOT;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "sequence=" + sequence +
                ", shots=" + collect().toString() +
                '}';
    }
}
