package bowling.domain;

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

    private int getFrameScore(int index) {
        if (!isValidFrameIndex(index)) {
            return 0;
        }
        Frame frame = frames.get(index);
        return frame.getPinScore() + getBonusScore(index);
    }

    private int getBonusScore(int index) {
        if (!isValidFrameIndex(index)) {
            return 0;
        }

        Frame frame = frames.get(index);
        FrameScore frameScore = frame.getResult();

        if (frameScore.equals(FrameScore.STRIKE)) {
            return getStrikeBonusScore(index + 1);
        }
        if (frameScore.equals(FrameScore.SPARE)) {
            return getSpareBonusScore(index + 1);
        }
        return 0;
    }

    private int getStrikeBonusScore(int index) {
        if (!isValidFrameIndex(index)) {
            return 0;
        }
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
        if (!isValidFrameIndex(index)) {
            return 0;
        }
        Frame frame = frames.get(index);
        return frame.getFirstPitchScore();
    }

    private boolean isValidFrameIndex(int frameIndex) {
        return frames.size() > frameIndex;
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
