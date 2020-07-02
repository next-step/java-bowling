package bowling.step2_1.domain.frame;

import bowling.step2_1.domain.score.Scores;

import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int ADD_ONE = 1;
    private static final int SECOND_PITCH = 2;
    private static final int NORMAL_FRAME_MIN = 1;
    private static final int NORMAL_FRAME_MAX = 9;
    private static final String NORMAL_FRAME_EXCEPTION = "일반 프레임의 범위를 벗어났습니다.";

    private final int frameNo;
    private final Scores scores;

    public NormalFrame(int frameNo) {
        validateFrameNo(frameNo);
        this.frameNo = frameNo;
        this.scores = new Scores();
    }

    private void validateFrameNo(int frameNo) {
        if (frameNo < NORMAL_FRAME_MIN || frameNo > NORMAL_FRAME_MAX){
            throw new IllegalArgumentException(NORMAL_FRAME_EXCEPTION);
        }
    }

    @Override
    public Frame pitch(int pitch) {
        scores.addScore(pitch, frameNo);
        return this;
    }

    @Override
    public Frame next() {
        int nextFrameNo = frameNo + ADD_ONE;
        if (frameNo == NORMAL_FRAME_MAX){
            return new FinalFrame(nextFrameNo);
        }
        return new NormalFrame(nextFrameNo);
    }

    @Override
    public int frameNo() {
        return frameNo;
    }

    @Override
    public boolean isFrameEnd() {
        return scores.hasStrikeorSpare() || scores.size() == SECOND_PITCH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNo == that.frameNo &&
                Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, scores);
    }

    @Override
    public String toString() {
        return scores.toString();
    }
}
