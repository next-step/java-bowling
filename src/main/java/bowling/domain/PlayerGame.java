package bowling.domain;

import bowling.domain.frame.BasicFrame;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerGame {
    private final Name name;

    private List<Frame> frames = new ArrayList<>();

    public PlayerGame(final String playerName) {
        this.name = new Name(playerName);
        this.frames.add(new BasicFrame(FrameNumber.of(FrameNumber.START_NUMBER)));
    }

    public boolean isGameEnd() {
        Frame currentFrame = getCurrentFrame();
        FrameNumber frameNumber = currentFrame.getFrameNumber();
        if (!frameNumber.isFinalNumber()) {
            return false;
        }
        FinalFrame finalFrame = (FinalFrame) currentFrame;
        return finalFrame.isFinished();
    }

    public Frame play(final int pinCount) {
        Frame currentFrame = getCurrentFrame();
        Frame frame = currentFrame.play(PinCount.of(pinCount));
        if (!Objects.equals(currentFrame, frame)) {
            frames.add(frame);
        }
        return frame;
    }

    public Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    public String getName() {
        return name.getValue();
    }

    public List<FrameResult> getFrameResults() {
        List<FrameResult> frameResults = new ArrayList<>();
        SumScore sumScore = SumScore.ZERO;
        for (Frame frame : frames) {
            FrameResult frameResult = frame.makeResult();
            sumScore = frameResult.plusBeforeFrameSumScore(sumScore);
            frameResults.add(frameResult);
        }
        return frameResults;
    }
}
