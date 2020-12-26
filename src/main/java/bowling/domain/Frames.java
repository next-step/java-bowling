package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.FrameNumber.FRAME_START_NUMBER;
import static bowling.domain.FrameNumber.FRAME_LAST_NUMBER;

public class Frames {

    private final List<Frame> frames;
    private FrameNumber currentNumber;

    private Frames(List<Frame> frames) {
        this.frames = frames;
        this.currentNumber = FrameNumber.init();
    }

    public static Frames from(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        List<Frame> frames = makeNormalFrames();
        frames.add(FinalFrame.from());
        return from(frames);
    }

    private static List<Frame> makeNormalFrames() {
        return IntStream.rangeClosed(FRAME_START_NUMBER, (FRAME_LAST_NUMBER - 1))
                .mapToObj(FrameNumber::from)
                .map(NormalFrame::from)
                .collect(Collectors.toList());
    }

    public void pitch(int pinCount) {
        int index = (getCurrentNumber() - 1);
        Frame frame = frames.get(index);
        frame.pitch(pinCount);

        if (frame.isNextFrame()) {
            currentNumber = currentNumber.add();
        }
    }

    public boolean isEnd() {
        return frames.get(FRAME_LAST_NUMBER - 1).isEnd();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public Frame getCurrentFrame() {
        int index = (getCurrentNumber() - 1);
        return frames.get(index);
    }

    public int getCurrentNumber() {
        return currentNumber.getNumber();
    }

    public Boolean isCurrentFinalFrame() {
        return getCurrentNumber() == FRAME_LAST_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames) && Objects.equals(currentNumber, frames1.currentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames, currentNumber);
    }
}
