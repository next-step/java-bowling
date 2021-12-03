package bowling.domain.value;

import bowling.annotations.GetterForUI;
import bowling.domain.frame.Frame;
import bowling.utils.Preconditions;

import java.util.List;
import java.util.Objects;

public class BowlingClub {
    private static final String EMPTY_SCORE = "";

    private int currentIndex;
    private final List<Frame> frames;

    private BowlingClub(List<Frame> frames) {
        Preconditions.checkEmpty(frames, "frames은 필수값입니다.");

        this.frames = frames;
    }

    public static BowlingClub from(List<Frame> frames) {
        return new BowlingClub(frames);
    }

    public void knockedDown(Pins pins) {
        Frame frame = getCurrentFrame();
        frame.knockedDown(pins);

        calculateAccumulationScore(pins);

        if (frame.isFrameOver()) {
            frame.accumulateScore();

            currentIndex++;
        }

        if (frame.isFinalFrameOver()) {
            frame.accumulateScore();
        }
    }

    private void calculateAccumulationScore(Pins pins) {
        frames.forEach(frame -> {
            if (frame.isAccumulateScore()) {
                frame.accumulateScore(pins.getPins());
            }
        });
    }

    private Frame getCurrentFrame() {
        return frames.get(currentIndex);
    }

    public boolean isGameOver() {
        return getCurrentFrame().isFinalFrameOver();
    }

    @GetterForUI
    public FrameNumber getCurrentFrameNumber() {
        return getCurrentFrame().getCurrentFrameNumber();
    }

    @GetterForUI
    public FramePins getPins(int frameNumber) {
        return getFrame(frameNumber).getPins();
    }

    @GetterForUI
    public String getScore(int frameNumber) {
        Frame currentFrame = getFrame(frameNumber);

        if (Objects.nonNull(currentFrame.getScore()) && currentFrame.getScore().canCalculateScore()) {
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
