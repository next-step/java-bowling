package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    private final List<Frame> frames;
    private FrameRound currentRound;

    public Frames(List<Frame> frames) {
        this.frames = frames;
        this.currentRound = FrameRound.start();
    }

    public static Frames get(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        List<Frame> frames = createNormalFrames();
        frames.add(FinalFrame.get());

        return get(frames);
    }

    private static List<Frame> createNormalFrames() {
        return IntStream.range(FrameRound.MIN_ROUND, FrameRound.MAX_ROUND)
                .mapToObj(FrameRound::get)
                .map(NormalFrame::get)
                .collect(Collectors.toList());
    }

    public void pitch(int pinPoint) {
        Frame frame = frames.get(getCurrentRound() - 1);
        frame.pitch(pinPoint);

        if (frame.isNextFrame()) {
            currentRound = currentRound.next();
        }
    }

    public boolean isLastFrame() {
        return frames.get(FrameRound.MAX_ROUND - 1).isLast();
    }

    public Frame getCurrentFrame() {
        return frames.get(getCurrentRound() - 1);
    }

    public int getCurrentRound() {
        return currentRound.getRound();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames) && Objects.equals(currentRound, frames1.currentRound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames, currentRound);
    }
}
