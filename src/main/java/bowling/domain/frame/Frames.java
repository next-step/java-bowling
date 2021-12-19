package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.FinalBowl;
import bowling.domain.bowl.FirstBowl;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class Frames {

    private static final int MIN_INDEX = 0;
    private static final int INDEX_UNIT = 1;
    private static final int NUMBER_OF_PREVIOUS_FRAMES_TO_CALCULATE = 2;

    public final List<Frame> frames;

    public Frames(Frame firstFrame) {
        this(new ArrayList<>(singletonList(firstFrame)));
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        Frame firstFrame = Frame.firstOf(FirstBowl.bowl());
        return new Frames(firstFrame);
    }

    /**
     * @return 더 투구할 수 있는지
     */
    public boolean pitch(Pin pin) {
        calculateScoreOfPreviousFrame(pin.toScore());

        if (currentFrame().pitch(pin)) {
            return true;
        }
        if (frames.size() == Frame.MAX_FRAME_NUMBER) {
            return false;
        }
        return frames.add(createNextFrame());
    }

    private void calculateScoreOfPreviousFrame(Score score) {
        Frame currentFrame = currentFrame();
        for (Frame frame : framesToCalculate()) {
            currentFrame.calculateScoreOfPreviousFrame(score, frame);
        }
    }

    private List<Frame> framesToCalculate() {
        int sizeOfFrames = frames.size();
        int endIndex = sizeOfFrames - INDEX_UNIT;
        int startIndex = sizeOfFrames - NUMBER_OF_PREVIOUS_FRAMES_TO_CALCULATE - INDEX_UNIT;
        return frames.subList(Math.max(MIN_INDEX, startIndex), endIndex);
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - INDEX_UNIT);
    }

    private Frame createNextFrame() {
        Frame currentFrame = currentFrame();
        if (isNormalFrameOrder()) {
            return currentFrame.nextOf(FirstBowl.bowl());
        }
        return currentFrame.nextOf(new FinalBowl());
    }

    private boolean isNormalFrameOrder() {
        return frames.size() < Frame.MAX_FRAME_NUMBER - INDEX_UNIT;
    }

    public int numberOfFrame() {
        return frames.size();
    }

    public List<Bowl> bowls() {
        return frames.stream()
                .map(Frame::getBowl)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
