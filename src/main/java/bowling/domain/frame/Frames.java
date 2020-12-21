package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.ScoreResult;
import bowling.state.BowlingState;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Frames {

    public static final int START_INDEX = 1;
    public static final int NORMAL_FRAME_SIZE = 9;
    public static final int FINAL_FRAME_INDEX = 10;

    private int currentIndex;
    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.currentIndex = START_INDEX;
        this.frames = frames;
    }

    private static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        List<Frame> frames = IntStream.rangeClosed(START_INDEX, NORMAL_FRAME_SIZE)
                .mapToObj(NormalFrame::of)
                .collect(Collectors.toList());
        frames.add(FinalFrame.init());
        return of(frames);
    }

    public void bowling(Pin knockDownPin) {
        Frame currentFrame = getFrameByIndex(currentIndex);
        currentFrame.pitch(knockDownPin);
        if (!currentFrame.isPlayable()) {
            this.currentIndex = currentIndex + 1;
        }
    }

    public boolean isFrameEnd() {
        return getFrameByIndex(FINAL_FRAME_INDEX).isEnd();
    }

    private Frame getFrameByIndex(int index) {
        return this.frames.get(index-1);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public List<ScoreResult> getScoreBoard() {
        return this.frames.stream()
                .map(Frame::getState)
                .map(BowlingState::getScoreBoard)
                .collect(Collectors.collectingAndThen(toList(), Collections::unmodifiableList));

    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(getFrames(), frames1.getFrames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrames());
    }
}
