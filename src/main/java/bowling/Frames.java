package bowling;

import bowling.domain.factory.BowlingHitScoresFactory;
import bowling.domain.factory.HitScoresFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    private static final int START_FRAME_NUMBER = 0;
    private static final int ONE_NUMBER = 1;

    private final HitScoresFactory hitScoresFactory;
    private final List<Frame> frames;

    public Frames() {
        this(new BowlingHitScoresFactory(), new ArrayList<>());
    }

    public Frames(HitScoresFactory hitScoresFactory, int... numbers) {
        this(hitScoresFactory, toFrame(hitScoresFactory, numbers));
    }


    public Frames(HitScoresFactory hitScoresFactory, Frame... frames) {
        this(hitScoresFactory, Arrays.asList(frames));
    }


    public Frames(HitScoresFactory hitScoresFactory, List<Frame> frames) {
        this.hitScoresFactory = hitScoresFactory;
        this.frames = frames;
    }

    public boolean lastFrameStrokeIsClosed() {
        return getLastFrame().isClosedStroke();
    }

    public Frames add(int hitCount) {
        if (isNewFrame()) { // lastFrameStrokeIsClosed() 마지막 프레임이 닫혀있으면, 신규 프레임에 추가.
            frames.add(new Frame(hitScoresFactory.create(frames.size())));
        }

        Frame updatedFrame = getLastFrame().updateScore(hitCount);
        frames.set(frames.size() - ONE_NUMBER, updatedFrame);

        return new Frames(hitScoresFactory, frames);
    }

    private boolean isNewFrame() {
        return frames.isEmpty() || lastFrameStrokeIsClosed();
    }

    public int size() {
        return frames.size();
    }

    public Frame getLastFrame() {
        if (frames.isEmpty()) {
            return new Frame(hitScoresFactory.create(START_FRAME_NUMBER));
        }

        return frames.get(frames.size() - ONE_NUMBER);
    }

    private static List<Frame> toFrame(HitScoresFactory hitScoresFactory, int[] numbers) {
        return IntStream.range(0, numbers.length)
            .boxed()
            .map(round -> new Frame(hitScoresFactory.create(round, numbers[round])))
            .collect(Collectors.toList());
    }
}
