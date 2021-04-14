package bowling.domain.frame;

import bowling.dto.FrameResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private static final int MIN_TOTAL_NUMBER_OF_FRAME = 2;

    private final List<Frame> frames;

    private Frame currentFrame;

    private Frames(List<Frame> frames) {
        validateEmptyFrames(frames);
        this.frames = frames.stream()
                .sorted(Comparator.comparing(Frame::number))
                .collect(Collectors.toList());
        this.currentFrame = frameToStartFrom(this.frames);
    }

    private void validateEmptyFrames(List<Frame> frames) {
        if (frames.isEmpty()) {
            throw new IllegalArgumentException("frame의 갯수는 0보다 커야 합니다.");
        }
    }

    private Frame frameToStartFrom(List<Frame> frames) {
        return frames.stream()
                .filter(normalFrame -> !normalFrame.isDone())
                .findFirst()
                .orElse(frames.get(frames.size() - 1));
    }

    public static Frames init(int totalNumberOfFrame) {
        if (totalNumberOfFrame < MIN_TOTAL_NUMBER_OF_FRAME) {
            throw new IllegalArgumentException("생성할 프레임 수 가 너무 적습니다.");
        }
        List<Frame> frames = new ArrayList<>();
        initFrames(frames, totalNumberOfFrame);
        return new Frames(frames);
    }

    public static Frames from(List<Frame> frames) {
        return new Frames(frames);
    }

    private static void initFrames(List<Frame> frames, int totalNumberOfFrame) {
        NormalFrame previousFrame = NormalFrame.first();
        frames.add(previousFrame);
        for (int i = 1; i <= totalNumberOfFrame - 2; i++) {
            NormalFrame currentFrame = previousFrame.next();
            frames.add(currentFrame);
            previousFrame = currentFrame;
        }
        frames.add(previousFrame.last());
    }

    public List<FrameResult> result() {
        return frames.stream()
                .map(FrameResult::new)
                .collect(Collectors.toList());
    }

    public boolean isDone() {
        return frames.stream()
                .allMatch(Frame::isDone);
    }

    public void addPinCount(int pintCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 전체 프레임을 다 play하셨습니다.");
        }
        currentFrame.addPinCount(pintCount);
        if (currentFrame.isDone() && !currentFrame.isLast()) {
            currentFrame = currentFrame.nextFrame();
        }
    }

    public FrameNumber currentFrameNumber() {
        return currentFrame.number();
    }
}
