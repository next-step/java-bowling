package bowling.domain.frame;

import bowling.domain.score.Scores;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    private static final int FRAME_COUNT = 10;
    private static final int FIRST_INDEX = 0;
    private static final int LAST_INDEX = 9;

    private final List<Frame> frames;
    private int currentFrameNumber;
    private Scores scores;

    private Frames(List<Frame> frames) {
        validate(frames);
        this.scores = Scores.create();
        this.frames = frames;
    }

    public static Frames create() {
        List<Frame> frames = IntStream.range(FIRST_INDEX, LAST_INDEX)
            .mapToObj(index -> Frame.of(FrameNumber.create(index), new NormalPins()))
            .collect(Collectors.toCollection(LinkedList::new));

        frames.add(LAST_INDEX, Frame.of(FrameNumber.create(LAST_INDEX), FinalFramePins.create()));
        return new Frames(frames);
    }

    private void validate(List<Frame> frames) {
        if (frames.size() != FRAME_COUNT) {
            throw new IllegalArgumentException("10개 프레임이 아닙니다.");
        }
    }

    public void roll(int downPin) {
        Frame frame = this.frames.get(this.currentFrameNumber);
        frame.roll(downPin, scores);

        if (!frame.hasTurn()) {
            this.currentFrameNumber++;
        }
    }

    public List<FrameResult> getFrameResults() {
        List<FrameResult> frameResults = IntStream.rangeClosed(FIRST_INDEX, LAST_INDEX)
            .mapToObj(index -> frames.get(index).getFrameResult(scores))
            .collect(Collectors.toList());
        return frameResults;
    }


    public int getCurrentFrameNumber() {
        return this.currentFrameNumber;
    }

    public boolean isFinished() {
        return !this.frames.get(this.frames.size() - 1).hasTurn();
    }
}
