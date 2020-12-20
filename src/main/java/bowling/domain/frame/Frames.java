package bowling.domain.frame;

import bowling.domain.score.Pitch;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static Frames of() {
        return init();
    }

    private static Frames init() {
        List<Frame> frames = IntStream.of(START_INDEX, NORMAL_FRAME_SIZE)
                .mapToObj(NormalFrame::of)
                .collect(Collectors.toList());
        frames.add(FinalFrame.of(FINAL_FRAME_INDEX));
        return new Frames(frames);
    }

    public void bowling(Pitch score) {
        Frame currentFrame = getFrameByIndex(currentIndex);
        currentFrame.pitch(score);
        if (!currentFrame.isPlayable()) {
            this.currentIndex = currentIndex + 1;
        }
    }

    public Frame getFrameByIndex(int index) {
        return this.frames.get(index);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
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
