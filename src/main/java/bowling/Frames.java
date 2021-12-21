package bowling;

import bowling.domain.factory.BowlingScoresFactory;
import bowling.domain.factory.ScoresFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    private static final int START_FRAME_NUMBER = 0;
    private static final int ONE_NUMBER = 1;

    private final ScoresFactory scoresFactory;
    private final List<Frame> frames;

    public Frames() {
        this(new BowlingScoresFactory(), new ArrayList<>());
    }

    public Frames(ScoresFactory scoresFactory, int... numbers) {
        this(scoresFactory, toFrame(scoresFactory, numbers));
    }


    public Frames(ScoresFactory scoresFactory, Frame... frames) {
        this(scoresFactory, Arrays.asList(frames));
    }


    public Frames(ScoresFactory scoresFactory, List<Frame> frames) {
        this.scoresFactory = scoresFactory;
        this.frames = frames;
    }

    public boolean lastFrameStrokeIsClosed() {
        return getLastFrame().isClosedStroke();
    }

    public Frames add(int hitCount) {
        if (isNewFrame()) { // lastFrameStrokeIsClosed() 마지막 프레임이 닫혀있으면, 신규 프레임에 추가.
            frames.add(new Frame(scoresFactory.create(frames.size())));
        }

        Frame updatedFrame = getLastFrame().updateScore(hitCount);
        frames.set(frames.size() - ONE_NUMBER, updatedFrame);

        return new Frames(scoresFactory, frames);
    }

    private boolean isNewFrame() {
        return frames.isEmpty() || lastFrameStrokeIsClosed();
    }

    public int size() {
        return frames.size();
    }

    public Frame getLastFrame() {
        if (frames.isEmpty()) {
            return new Frame(scoresFactory.create(START_FRAME_NUMBER));
        }

        return frames.get(frames.size() - ONE_NUMBER);
    }

    private static List<Frame> toFrame(ScoresFactory scoresFactory, int[] numbers) {
        return IntStream.range(0, numbers.length)
            .boxed()
            .map(round -> new Frame(scoresFactory.create(round, numbers[round])))
            .collect(Collectors.toList());
    }
}
