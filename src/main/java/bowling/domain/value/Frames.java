package bowling.domain.value;

import bowling.annotations.GetterForUI;
import bowling.domain.frame.Frame;
import bowling.utils.Preconditions;

import java.util.List;

public class Frames {
    private static final String EMPTY_SCORE = "";

    private int currentFrameNumber;
    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        Preconditions.checkEmpty(frames, "frames은 필수값입니다.");

        this.frames = frames;
    }

    public static Frames from(List<Frame> frames) {
        return new Frames(frames);
    }

    public void pitch(Pins pins) {
        Frame frame = getCurrentFrame();

        accumulateTotalScore(pins);

        frame.pitch(pins);

        checkFrameOver(frame);
    }

    private void accumulateTotalScore(Pins pins) {
        frames.stream()
                .filter(frame -> !frame.canCalculateScore())
                .forEach(frame -> frame.accumulateScore(pins.getPins()));
    }

    private void checkFrameOver(Frame frame) {
        if (frame.isFrameOver()) {
            currentFrameNumber++;
        }
    }

    public Frame getCurrentFrame() {
        return frames.get(currentFrameNumber);
    }

    public boolean isGameOver() {
        return frames.get(frames.size() - 1).isGameOver();
    }

    public int currentFrameNumber() {
        if (isGameOver() || getCurrentFrame().isFrameOver()) {
            return currentFrameNumber + 1;
        }

        return currentFrameNumber;
    }

    @GetterForUI
    public String getScore(int frameNumber) {
        Frame currentFrame = getFrame(frameNumber);

        if (currentFrame.canCalculateScore()) {
            int score = frames.stream()
                    .limit(frameNumber)
                    .map(Frame::getScore)
                    .map(Score::getScore)
                    .reduce(0, Integer::sum);
            return String.valueOf(score);
        }

        return EMPTY_SCORE;
    }

    @GetterForUI
    public Frame getFrame(int frameNumber) {
        return frames.get(frameNumber - 1);
    }
}
