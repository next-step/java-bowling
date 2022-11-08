package bowling.domain;

import bowling.exception.NotReadyException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private final List<Frame> frames = new ArrayList<>();
    private final Player player;

    public Record(Player player) {
        this.player = player;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public boolean isEndFrame(int frameIndex) {
        if (frames.size() <= frameIndex) {
            return false;
        }
        return frames.get(frameIndex).isEndFrame();
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

    public boolean isEndRecord() {
        return isLastFrame() && isEndLastFrame();
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
        if (isValidFrameIndex(frameIndex)) {
            return false;
        }
        Frame frame = frames.get(frameIndex);
        if (!frame.isEndFrame()) {
            return false;
        }
        if (frame.isValidBonusScore()) {
            return isReadyBonusScore(frameIndex);
        }
        return true;
    }

    private boolean isValidFrameIndex(int frameIndex) {
        return frames.size() <= frameIndex;
    }

    private boolean isReadyBonusScore(int frameIndex) {
        Frame frame = frames.get(frameIndex);
        if (frame.isStrike()) {
            return isReadyTwoPitchScore(frameIndex + 1);
        }
        if (frame.isSpare()) {
            return isReadyOnePitchScore(frameIndex + 1);
        }
        return true;
    }

    private boolean isReadyOnePitchScore(int frameIndex) {
        if (isValidFrameIndex(frameIndex)) {
            return false;
        }
        Frame frame = frames.get(frameIndex);
        return frame.getTryCount() >= 1;
    }

    private boolean isReadyTwoPitchScore(int frameIndex) {
        if (isValidFrameIndex(frameIndex)) {
            return false;
        }
        Frame frame = frames.get(frameIndex);

        if (frame.isValidBonusScore() && frame.isStrike()) {
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
        if (!frame.isValidBonusScore()) {
            return 0;
        }
        if (frame.isStrike()) {
            return getStrikeBonusScore(index + 1);
        }
        if (frame.isSpare()) {
            return getSpareBonusScore(index + 1);
        }
        return 0;
    }

    private int getStrikeBonusScore(int index) {
        checkValidFrameIndex(index);
        Frame frame = frames.get(index);

        int bonusScore = 0;
        bonusScore += getSpareBonusScore(index);
        if (frame.isValidBonusScore() && frame.isStrike()) {
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
            throw new NotReadyException("frameIndex must more less than frame size");
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
