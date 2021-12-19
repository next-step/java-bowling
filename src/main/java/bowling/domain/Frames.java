package bowling.domain;

import bowling.strategy.PitchNumberStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int ONE = 1;

    private final List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>();
        this.frames.add(NormalFrame.first());
    }

    public static Frames init() {
        return new Frames();
    }

    public void run(PitchNumberStrategy numberStrategy) {
        Frame frame = currentFrame();
        frame.run(numberStrategy);
        next(frame);
    }

    public List<Frame> frames() {
        return frames;
    }

    private void next(Frame frame) {
        if (frame.isFinal()) {
            return;
        }
        frames.add(frame.next());
    }

    public boolean isGameEnd() {
        return frames.stream().allMatch(Frame::isEnd);
    }

    public List<String> frameResults() {
        return frames.stream()
                .filter(Frame::isEnd)
                .map(results -> String.join("|", results.pitchResults()))
                .collect(Collectors.toList());
    }

    public int currentEndedFrameNo() {
        return currentEndedFrame().no();
    }

    private Frame currentFrame() {
        return frames.get(currentFrameIndex());
    }

    private int currentFrameIndex() {
        return frames.size() - ONE;
    }

    private Frame currentEndedFrame() {
        return frames.get(currentEndedFrameIndex());
    }

    private int currentEndedFrameIndex() {
        return (int) frames.stream()
                .filter(Frame::isEnd)
                .count() - ONE;
    }
}
