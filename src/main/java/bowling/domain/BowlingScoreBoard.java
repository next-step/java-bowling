package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bowling.engine.Frame;
import bowling.engine.Name;
import bowling.engine.Score;
import bowling.engine.ScoreBoard;
import bowling.engine.Sequence;
import bowling.engine.Shot;

public class BowlingScoreBoard implements ScoreBoard {
    private final Name name;
    private final Map<Sequence, Frame> frames;
    // todo final
    private Sequence current;

    protected BowlingScoreBoard(Name name, Map<Sequence, Frame> frames) {
        this.name = name;
        this.frames = frames;
        this.current = FrameSequence.of(Sequence.FIRST);
    }

    public static BowlingScoreBoard of(String name) {

        return new BowlingScoreBoard(Player.of(name), new HashMap<>());
    }

    @Override
    public Score score(Sequence sequence) {
        if (sequence == null) {
            throw new IllegalArgumentException("sequence cannot be null");
        }

        if (!frames.containsKey(sequence)) {
            throw new IllegalStateException(sequence + " frame is not started.");
        }

        return frames.get(sequence)
                .score();
    }

    @Override
    public Frame nextShot(Shot shot) {
        Frame frame = Optional.ofNullable(frames.get(current))
                .orElse(NormalFrame.ready(current))
                .nextShot(shot);
        frames.put(current, frame);

        // todo refactoring next와 shot을 나누자
        if (frame.completed()) {
            // todo next
            current = FrameSequence.of(current.toInt() + 1);
        }

        return frame;
    }

    @Override
    public Sequence current() {
        return current;
    }

    @Override
    public Name name() {
        return name;
    }
}
