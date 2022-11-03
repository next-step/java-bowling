package bowling.domain;

import bowling.exception.NotReadyException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Records {
    private final List<Frame> frames = new ArrayList<>();

    public List<Frame> getFrames() {
        return frames;
    }

    public int getRecordCount() {
        return frames.size();
    }

    public void record(int downPintCount) {
        Frame readyFrame = getReadyFrame();
        readyFrame.record(downPintCount);
    }

    public boolean isLastFrame() {
        return getRecordCount() >= RuleConfig.NUMBER_OF_FRAME;
    }

    public boolean isEndLastFrame() {
        return getLatestFrame().isEndFrame();
    }

    public int getTotalScore() {
        return getTotalScore(frames.size() - 1);
    }

    public int getTotalScore(int targetIndex) {
        int totalScore = 0;
        for (int frameIndex = 0; frameIndex <= targetIndex; frameIndex++) {
            totalScore += getFrameScore(frameIndex);
        }
        return totalScore;
    }

    public boolean isReadyFrameScore(int frameIndex) {
        if (frames.size() <= frameIndex) {
            return false;
        }
        Frame frame = frames.get(frameIndex);
        if (frame.getResult().equals(FrameScore.STRIKE)) {
            return isReadyTwoPitchScore(frameIndex + 1);
        }
        if (frame.getResult().equals(FrameScore.SPARE)) {
            return isReadyOnePitchScore(frameIndex + 1);
        }
        return frame.isEndFrame();
    }

    private boolean isReadyOnePitchScore(int frameIndex) {
        if (frames.size() <= frameIndex) {
            return false;
        }
        Frame frame = frames.get(frameIndex);
        return frame.getTryCount() >= 1;
    }

    private boolean isReadyTwoPitchScore(int frameIndex) {
        if (frames.size() <= frameIndex) {
            return false;
        }
        Frame frame = frames.get(frameIndex);

        if (frame.getResult().equals(FrameScore.STRIKE)) {
            return isReadyOnePitchScore(frameIndex + 1);
        }
        return frame.getTryCount() >= 2;
    }

    private int getFrameScore(int index) {
        checkValidFrameIndex(index);
        Frame frame = frames.get(index);
        return frame.getPinScore() + getBonusScore(index);
    }

    private int getBonusScore(int index) {
        checkValidFrameIndex(index);
        Frame frame = frames.get(index);
        FrameScore frameScore = frame.getResult();

        if (frame.getClass().equals(FinalFrame.class)) {
            return 0;
        }
        if (frameScore.equals(FrameScore.STRIKE)) {
            return getStrikeBonusScore(index + 1);
        }
        if (frameScore.equals(FrameScore.SPARE)) {
            return getSpareBonusScore(index + 1);
        }
        return 0;
    }

    private int getStrikeBonusScore(int index) {
        checkValidFrameIndex(index);
        Frame frame = frames.get(index);
        FrameScore result = frame.getResult();

        int bonusScore = 0;
        bonusScore += getSpareBonusScore(index);

        if (FrameScore.STRIKE.equals(result) && !frame.getClass().equals(FinalFrame.class)) {
            return bonusScore + getSpareBonusScore(index + 1);
        }
        return bonusScore + frame.getSecondPitchScore();
    }

    private int getSpareBonusScore(int index) {
        checkValidFrameIndex(index);
        Frame frame = frames.get(index);
        return frame.getFirstPitchScore();
    }

    private void checkValidFrameIndex(int frameIndex) {
        if (frameIndex >= frames.size()) {
            throw new NotReadyException();
        }
    }

    private Frame getReadyFrame() {
        Frame frame = getLatestFrame();
        if (frame.isEndFrame()) {
            frame = getNewFrame();
            frames.add(frame);
        }
        return frame;
    }

    private Frame getNewFrame() {
        if (frames.size() == RuleConfig.NUMBER_OF_FRAME - 1) {
            return new FinalFrame();
        }
        return new Frame();
    }

    private Frame getLatestFrame() {
        if (CollectionUtils.isEmpty(frames)) {
            Frame frame = new Frame();
            frames.add(frame);
            return frame;
        }
        return frames.get(frames.size() - 1);
    }

}
