package bowling.refactoring.domain.frame;

import java.util.*;

public class Frames {
    private static final int START_FRAME_INDEX = 0;
    private static final int LAST_FRAME_INDEX = 9;

    private final List<Frame> frameList;
    private int nowFrameNum;


    public Frames() {
        this.frameList = new ArrayList<>();
        this.frameList.add(getNewFrame());
        this.nowFrameNum = START_FRAME_INDEX;
    }

    public void bowl(int fallenPinCount) {
        getNowFrame().bowl(fallenPinCount);

        for (int i = 0; i < nowFrameNum; i++) {
            calculateBonusScore(i);
        }

        if (getNowFrame().isEnd() && this.nowFrameNum < LAST_FRAME_INDEX) {
            this.nowFrameNum++;
            frameList.add(getNewFrame());
        }
    }

    private Frame getNewFrame() {
        if (this.nowFrameNum == LAST_FRAME_INDEX) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    private void calculateBonusScore(int i) {
        if (frameList.get(i).isEnd() && !frameList.get(i).isEndedCalculateScore()) {
            frameList.get(i).calculateBonusScore(getNowFrame());
        }
    }

    public boolean isEndedFrame(int frameNum) {
        return this.frameList.get(frameNum).isEnd();
    }

    private Frame getNowFrame() {
        return this.frameList.get(this.nowFrameNum);
    }

    public List<Frame> frameList() {
        return new ArrayList<>(frameList);
    }

    public int nowFrameNum() {
        return this.nowFrameNum;
    }
}
