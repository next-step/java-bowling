package bowling.step3.domain.frame;

import bowling.step3.domain.state.Pins;
import bowling.step3.domain.score.Score;
import bowling.step3.domain.state.LastState;

import java.util.Objects;

public class FinalFrame extends Frame {
    private static final int BONUS_PITCHES_END = 3;
    private static final int FINAL_FRAME_NUMBER = 10;
    private static final String FINAL_FRAME_EXCEPTION = "마지막 프레임 번호는 10만 가능합니다.";

    private LastState lastState;
    private int pitchCount = 0;

    public FinalFrame(int frameNo) {
        super(frameNo);
        lastState = new LastState();
    }

    protected void validateFrameNo(int frameNo) {
        if (frameNo != FINAL_FRAME_NUMBER){
            throw new IllegalArgumentException(FINAL_FRAME_EXCEPTION);
        }
    }

    @Override
    public Frame pitch(int pitch) {
        if(isFrameEnd()){
            throw new RuntimeException("게임 끝");
        }
        pitchCount++;
        lastState = lastState.pitch(new Pins(pitch));
        return this;
    }

    @Override
    public boolean isFrameEnd() {
        if (lastState.isMissState()){
            return true;
        }
        if (pitchCount == BONUS_PITCHES_END){
            return true;
        }
        return false;
    }

    @Override
    public Score getScore() {
        if(!isFrameEnd()){
            return Score.readyScore();
        }
        return new Score(lastState.getScore(), 0);
    }

    @Override
    public Score caculateAdditionalScore(Score beforScore) {
        return lastState.addAdditionalScore(beforScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return frameNo == that.frameNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo);
    }

    @Override
    public String toString() {
        return lastState.disPlay();
    }
}
