package bowling.step2_1.domain.frame;

import bowling.step2_1.domain.score.Scores;

import java.util.Objects;

public class FinalFrame implements Frame {
    private static final int NORMAL_PITCHES_END = 2;
    private static final int BONUS_PITCHES_END = 3;
    private static final int STRIKE_COUNT = 10;
    private static final int FINAL_FRAME_NUMBER = 10;
    private static final String FINAL_FRAME_EXCEPTION = "마지막 프레임 번호는 10만 가능합니다.";

    private final int frameNo;
    private final Scores scores;

    public FinalFrame(int frameNo) {
        validateFrameNo(frameNo);
        this.frameNo = frameNo;
        this.scores = new Scores();
    }

    private void validateFrameNo(int frameNo) {
        if (frameNo != FINAL_FRAME_NUMBER){
            throw new IllegalArgumentException(FINAL_FRAME_EXCEPTION);
        }
    }

    @Override
    public Frame pitch(int pitch) {
        scores.addScore(pitch, frameNo);
        return this;
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public int frameNo() {
        return frameNo;
    }

    @Override
    public boolean isFrameEnd() {
        if (scores.size() == NORMAL_PITCHES_END && pitchSum() < STRIKE_COUNT){
            return true;
        }
        if (scores.size() == BONUS_PITCHES_END){
            return true;
        }
        return false;
    }

    private int pitchSum() {
        return scores.pitchSum();
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
        return scores.toString();
    }
}
