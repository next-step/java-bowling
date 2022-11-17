package bowling.step4.domain;

import java.util.HashMap;
import java.util.Map;

public class Frames {
    private static final int START_FRAME_INDEX = 1;
    private static final int LAST_FRAME_INDEX = 10;

    private final Map<Integer, Frame> frameMap;

    public Frames() {
        Map<Integer, Frame> frameMap = new HashMap<>();
        for (int i = START_FRAME_INDEX; i <= LAST_FRAME_INDEX; i++) {
            frameMap.put(i, getNewFrame(i));
        }
        this.frameMap = frameMap;
    }

    private Frame getNewFrame(int i) {
        if (i == LAST_FRAME_INDEX) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    public boolean isEndedFrame(int frameNum) {
        return getFrame(frameNum).isEndedFrame();
    }

    public void bowl(int frameNum, int count) {
        getFrame(frameNum).add(count);
        calculateFrameScore(frameNum);
    }

    private void calculateFrameScore(int frameNum) {
        if (isEndedFrame(frameNum)) {
            addFrameScore(frameNum);
            return;
        }
        if (frameNum > START_FRAME_INDEX && getFrame(frameNum - 1).pitches.hasSpare()) {
            getFrame(frameNum - 1).addPoint(getFrame(frameNum).firstPitch(), getPreviousScore(frameNum - 1));
        }

        if (frameNum > START_FRAME_INDEX + 1 && getFrame(frameNum - 2).pitches.hasStrike()) {
            getFrame(frameNum - 2).addPoint(getFrame(frameNum - 1).pitches.sum(), getPreviousScore(frameNum - 2));
        }
    }

    private int getPreviousScore(int index) {
        if (index - 1 < START_FRAME_INDEX) {
            return 0;
        }
        return getFrame(index - 1).score().value();
    }

    private void addFrameScore(int frameNum) {
        if (frameNum > START_FRAME_INDEX + 1 && getFrame(frameNum - 1).pitches.hasStrike()) {
            checkDoubleStrike(frameNum);
        }
        if (frameNum > START_FRAME_INDEX && getFrame(frameNum - 1).pitches.hasSpare() && getFrame(frameNum).pitches.hasStrike()) {
            getFrame(frameNum - 1).addPoint(10, getPreviousScore(frameNum - 1));
        }

        if (frameNum == START_FRAME_INDEX + 1 && !getFrame(frameNum).pitches.hasStrike() && getFrame(frameNum - 1).pitches.hasStrike()) {
            getFrame(frameNum - 1).addPoint(getStrikeBonusPoint(frameNum), getPreviousScore(frameNum - 1));
        }
        if (getFrame(frameNum).isFinalFrame() || (!getFrame(frameNum).pitches.hasStrike() && !getFrame(frameNum).pitches.hasSpare())) {
            getFrame(frameNum).addPoint(0, getPreviousScore(frameNum));
        }
    }

    private void checkDoubleStrike(int frameNum) {
        if (getFrame(frameNum).pitches.hasStrike() && getFrame(frameNum - 2).pitches.hasStrike()) {
            getFrame(frameNum - 2).addPoint(20, getPreviousScore(frameNum - 2));
        }

        if (!getFrame(frameNum).pitches.hasStrike() || getFrame(frameNum).isFinalFrame()) {
            getFrame(frameNum - 1).addPoint(getStrikeBonusPoint(frameNum), getPreviousScore(frameNum - 1));
        }
    }

    private int getStrikeBonusPoint(int frameNum) {
        if (getFrame(frameNum).isFinalFrame()) {
            return getFrame(frameNum).firstPitch() + getFrame(frameNum).secondPitch();
        }
        return getFrame(frameNum).pitches.sum();
    }

    private Frame getFrame(int index) {
        return this.frameMap.get(index);
    }

    public Map<Integer, Frame> frameMap() {
        return new HashMap<>(this.frameMap);
    }
}
