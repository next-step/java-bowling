package bowling.step4.domain.frame;

import bowling.step4.domain.score.Score;
import bowling.step4.domain.state.Pins;
import bowling.step4.domain.state.Ready;
import bowling.step4.domain.state.State;

import java.util.Objects;

public class NormalFrame extends Frame {
    private static final int ADD_ONE = 1;
    private static final int NORMAL_FRAME_MIN = 1;
    private static final int NORMAL_FRAME_MAX = 9;
    private static final String NORMAL_FRAME_EXCEPTION = "일반 프레임의 범위를 벗어났습니다.";

    private State state;
    private Frame nextFrame;

    public NormalFrame(int frameNo) {
        super(frameNo);
        this.state = new Ready();
    }

    protected void validateFrameNo(int frameNo) {
        if (frameNo < NORMAL_FRAME_MIN || frameNo > NORMAL_FRAME_MAX){
            throw new IllegalArgumentException(NORMAL_FRAME_EXCEPTION);
        }
    }

    @Override
    public Frame pitch(int pitch) {
        state = state.pitch(new Pins(pitch));
        if (state.isFinish()){
            return next();
        }
        return this;
    }

    private Frame next() {
        int nextFrameNo = frameNo + ADD_ONE;
        if (frameNo == NORMAL_FRAME_MAX){
            nextFrame = new FinalFrame(nextFrameNo);
            return nextFrame;
        }
        nextFrame = new NormalFrame(nextFrameNo);
        return nextFrame;
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        if (score.isFinishedScore()){
            return score;
        }
        if (Objects.isNull(nextFrame)){
            return Score.readyScore();
        }
        return nextFrame.calculateAdditionalScore(score);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.addAdditionalScore(beforeScore);
        if (score.isFinishedScore()){
            return score;
        }
        if (Objects.isNull(nextFrame)){
            return Score.readyScore();
        }
        return nextFrame.calculateAdditionalScore(score);
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(state, that.state) &&
                Objects.equals(nextFrame, that.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, nextFrame);
    }

    @Override
    public String toString() {
        return state.display();
    }
}
