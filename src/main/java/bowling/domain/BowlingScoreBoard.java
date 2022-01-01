package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bowling.engine.Frame;
import bowling.engine.Name;
import bowling.engine.Score;
import bowling.engine.ScoreBoard;
import bowling.engine.Sequence;
import bowling.engine.Shot;
import bowling.engine.collection.FirstClassMutableList;

public class BowlingScoreBoard extends FirstClassMutableList<Frame> implements ScoreBoard {
    private static final int FRAME_INDEX_DIFF = 1;

    private final Name name;

    protected BowlingScoreBoard(Name name, List<Frame> frames) {
        super(frames);
        this.name = name;
    }

    public static BowlingScoreBoard of(String name) {
        List<Frame> frames = new ArrayList<>(NUMBER_OF_FRAME);
        frames.add(NormalFrame.startFrame());
        return new BowlingScoreBoard(Player.of(name), frames);
    }

    public static BowlingScoreBoard of(String name, List<Frame> frames) {
        return new BowlingScoreBoard(Player.of(name), frames);
    }

    @Override
    public Optional<Score> score(Sequence sequence) {
        if (sequence == null) {
            throw new IllegalArgumentException("sequence cannot be null");
        }

        if (size() < sequence.toInt()) {
            return Optional.empty();
        }

        return Optional.of(elementOf(sequenceToIndex(sequence))
                .score());
    }

    @Override
    public Frame nextShot(Shot shot) {
        Sequence sequence = last().sequence();
        Frame frame = last().nextShot(shot);
        if (!sequence.equals(frame.sequence())) {
            append(frame);
        }

        return setElement(sequenceToIndex(frame.sequence()), frame);
    }

    private int sequenceToIndex(Sequence sequence) {
        return sequence.toInt() - FRAME_INDEX_DIFF;
    }

    @Override
    public Sequence current() {
        Frame last = last();
        if (last.isFinal() || !last().completed()) {
            return last.sequence();
        }

        return last.sequence()
                .next();
    }

    @Override
    public Name name() {
        return name;
    }

    @Override
    public boolean isEnded() {
        Frame last = last();
        return last.isFinal() && last.completed();
    }
}
