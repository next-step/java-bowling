package bowling.domain;

import bowling.strategy.PitchNumberStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int ONE = 1;
    private static final int NOT_FOUND_INDEX = -1;

    private final List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>();
        this.frames.add(NormalFrame.first());
    }

    public static Frames init() {
        return new Frames();
    }

    public Frame run(PitchNumberStrategy numberStrategy) {
        Frame frame = currentFrame();
        frame.run(numberStrategy);
        if (frame.isEnd()) {
            next(frame);
        }
        return frame;
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
                .map(results -> String.join("|", results.pitchResults()))
                .collect(Collectors.toList());
    }

    public int noOf(Frame frame) {
        return frames.get(indexOf(frame)).no();
    }

    public int fallDownPinsCountOf(Frame frame) {
        return frames.get(indexOf(frame)).fallDownPinsCount();
    }

    private int indexOf(Frame frame) {
        int index = frames.indexOf(frame);
        if (index == NOT_FOUND_INDEX) {
            throw new IllegalArgumentException("존재하지 않는 프레임입니다.");
        }
        return index;
    }

    private Frame currentFrame() {
        return frames.get(currentFrameIndex());
    }

    private int currentFrameIndex() {
        return currentSize() - ONE;
    }

    private int currentSize() {
        int size = size();
        if (size == 0) {
            throw new IllegalArgumentException("생성된 프레임이 존재하지 않습니다");
        }
        return size;
    }

    private int size() {
        return frames.size();
    }
}
