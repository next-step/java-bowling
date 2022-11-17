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
            frameMap.put(i, getFrame(i));
        }
        this.frameMap = frameMap;
    }

    private Frame getFrame(int i) {
        if (i == LAST_FRAME_INDEX) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    public boolean isEndedFrame(int frameNum) {
        return this.frameMap.get(frameNum).isEndedFrame();
    }

    public void bowl(int frameNum, int count) {
        this.frameMap.get(frameNum).add(count);
        calculateFrameScore(frameNum);
    }

    private void calculateFrameScore(int frameNum) {
        if (isEndedFrame(frameNum)) {
            addFrameScore(frameNum);
            return;
        }
        if (frameNum > START_FRAME_INDEX && this.frameMap.get(frameNum - 1).pitches.hasSpare()) {
            this.frameMap.get(frameNum - 1).addPoint(this.frameMap.get(frameNum).first(), getPreviousScore(frameNum - 1));
        }

        if(frameNum > START_FRAME_INDEX + 1 && this.frameMap.get(frameNum - 2).pitches.hasStrike()){
            this.frameMap.get(frameNum - 2).addPoint(this.frameMap.get(frameNum-1).pitches.sum(), getPreviousScore(frameNum - 2));
        }
    }

    private int getPreviousScore(int index) {
        if (index - 1 < START_FRAME_INDEX) {
            return 0;
        }
        return this.frameMap.get(index - 1).score().value();
    }

    private void addFrameScore(int frameNum) {
        if (frameNum > START_FRAME_INDEX+1 && this.frameMap.get(frameNum - 1).pitches.hasStrike()) {
            checkDoubleStrike(frameNum);
        }

        if (frameNum == START_FRAME_INDEX + 1 && !this.frameMap.get(frameNum).pitches.hasStrike() && this.frameMap.get(frameNum - 1).pitches.hasStrike()) {
            this.frameMap.get(frameNum - 1).addPoint(getStrikeBonusPoint(frameNum), getPreviousScore(frameNum - 1));
        }
        if (this.frameMap.get(frameNum).isFinalFrame() || (!this.frameMap.get(frameNum).pitches.hasStrike() && !this.frameMap.get(frameNum).pitches.hasSpare())) {
            this.frameMap.get(frameNum).addPoint(0, getPreviousScore(frameNum));
        }
    }

    private void checkDoubleStrike(int frameNum) {
        if(this.frameMap.get(frameNum).pitches.hasStrike() && this.frameMap.get(frameNum - 2).pitches.hasStrike()){
            this.frameMap.get(frameNum - 2).addPoint(20, getPreviousScore(frameNum - 2));
        }

        if(!this.frameMap.get(frameNum).pitches.hasStrike() || this.frameMap.get(frameNum).isFinalFrame()) {
            this.frameMap.get(frameNum - 1).addPoint(getStrikeBonusPoint(frameNum), getPreviousScore(frameNum - 1));
        }
    }

    private int getStrikeBonusPoint(int frameNum) {
        if(this.frameMap.get(frameNum).isFinalFrame()){
            return this.frameMap.get(frameNum).first()+this.frameMap.get(frameNum).second();
        }
        return this.frameMap.get(frameNum).pitches.sum();
    }

    public Map<Integer, Frame> frameMap() {
        return new HashMap<>(this.frameMap);
    }
}
