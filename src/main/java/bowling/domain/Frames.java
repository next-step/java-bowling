package bowling.domain;

import bowling.dto.FramesResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private static final int MIN_TOTAL_NUMBER_OF_FRAME = 2;

    private final List<NormalFrame> normalFrames;

    private final FinalFrame finalFrame;

    private FrameNumber currentFrameNumber;

    public Frames(List<NormalFrame> normalFrames, FinalFrame finalFrame) {
        validateEmptyNormalFrames(normalFrames);

        this.normalFrames = normalFrames.stream()
                .sorted(Comparator.comparing(NormalFrame::number))
                .collect(Collectors.toList());
        this.finalFrame = finalFrame;
        this.currentFrameNumber = frameNumberToStartFrom(this.normalFrames, finalFrame);
    }

    private void validateEmptyNormalFrames(List<NormalFrame> normalFrames) {
        if (normalFrames.isEmpty()) {
            throw new IllegalArgumentException("normalFrame의 갯수는 0보다 커야 합니다.");
        }
    }

    private FrameNumber frameNumberToStartFrom(List<NormalFrame> normalFrames, FinalFrame finalFrame) {
        boolean isAllNormalFramesDone = normalFrames.stream()
                .allMatch(NormalFrame::isDone);

        if (isAllNormalFramesDone) {
            return finalFrame.number();
        }

        return normalFrames.stream()
                .filter(normalFrame -> !normalFrame.isDone())
                .findFirst()
                .map(NormalFrame::number)
                .orElse(FrameNumber.first());
    }

    public static Frames init(int totalNumberOfFrame) {
        if (totalNumberOfFrame < MIN_TOTAL_NUMBER_OF_FRAME) {
            throw new IllegalArgumentException("요청된 프레임수가 너무 적습니다.");
        }
        List<NormalFrame> normalFrames = new ArrayList<>();
        initFirstNormalFrame(normalFrames);
        initRestNormalFrames(normalFrames, totalNumberOfFrame - 2);
        return new Frames(normalFrames, FinalFrame.of(totalNumberOfFrame));
    }

    private static void initFirstNormalFrame(List<NormalFrame> normalFrames) {
        normalFrames.add(NormalFrame.first());
    }

    private static void initRestNormalFrames(List<NormalFrame> normalFrames, int restNormalFrameCount) {
        for (int i = 1; i <= restNormalFrameCount; i++) {
            NormalFrame previousNormalFrame = normalFrames.get(normalFrames.size() - 1);
            normalFrames.add(previousNormalFrame.next());
        }
    }

    public FramesResult result() {
        return new FramesResult(normalFrames, finalFrame);
    }

    public boolean isDone() {
        return isAllNormalFramesDone() && isFinalFrameDone();
    }

    private boolean isAllNormalFramesDone() {
        return normalFrames.stream()
                .allMatch(NormalFrame::isDone);
    }

    private boolean isFinalFrameDone() {
        return finalFrame.isDone();
    }

    public void addPinCount(int pintCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 전체 프레임을 다 play하셨습니다.");
        }
        if (currentFrameNumber.equals(finalFrame.number())) {
            addToFinalFrame(pintCount);
        } else {
            addToNormalFrames(pintCount);
        }

    }

    private void addToNormalFrames(int pintCount) {
        NormalFrame normalFrame = normalFrames.get(currentFrameNumber.number() - 1);
        normalFrame.addPinCount(pintCount);
        if (normalFrame.isDone()) {
            currentFrameNumber = currentFrameNumber.next();
        }
    }

    private void addToFinalFrame(int pintCount) {
        finalFrame.addPinCount(pintCount);
    }

    public int currentFrameNumberInt() {
        return currentFrameNumber.number();
    }
}
