package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.domain.state.Pins;
import bowling.exception.NoActionBowlException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.subtractExact;
import static java.util.Collections.unmodifiableList;

public final class Frames {

    private static final int FIRST_INDEX = 0;
    private static final int OPERATION_UNIT = 1;

    private final List<Frame> frames;
    private Frame current;

    public Frames() {
        this.frames = generateFrames();
        this.current = frames.get(FIRST_INDEX);
    }

    private final List<Frame> generateFrames() {
        final List<Frame> frames = IntStream.range(Frame.START_SEQUENCE, Frame.LAST_SEQUENCE)
                .mapToObj(NormalFrame::new)
                .collect(Collectors.toList());
        frames.add(new FinalFrame());
        return frames;
    }

    public final int sequence() {
        return current.sequence();
    }

    public final boolean isFinish() {
        return current.isFinish();
    }

    public final void bowl(final int pins) {
        bowl(Pins.valueOf(pins));
    }

    public final void bowl(final Pins pins) {
        validateFinish();
        current = current.bowl(pins);
        frames.set(sequenceToIndex(current), current);
    }

    private final void validateFinish() {
        if (isFinish()) {
            throw new NoActionBowlException();
        }
    }

    private final int sequenceToIndex(final Frame frame) {
        return subtractExact(frame.sequence(), OPERATION_UNIT);
    }

    public final Scores scores() {
        List<Score> scores = frames.stream()
                .filter(Frame::isFinish)
                .map(this::score)
                .collect(Collectors.toList());
        return new Scores(scores);
    }

    private final Score score(final Frame frame) {
        Frame now = frame;
        Score score = now.score();
        while (!score.isFinish()) {
            now = nextFrame(now);
            score = now.calculateAdditionalScore(score);
        }
        return score;
    }

    private final Frame nextFrame(final Frame frame) {
        final int nextIndex = frame.sequence();
        if (nextIndex == Frame.LAST_SEQUENCE) {
            return frame;
        }
        return frames.get(nextIndex);
    }

    public final Frame get(final int index) {
        return unmodifiableList(frames).get(index);
    }
}
