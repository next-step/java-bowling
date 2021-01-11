package bowling.domain.frame;

import bowling.domain.PitchResults;
import bowling.domain.Score;

import java.util.Objects;


public abstract class Frame {

    protected final int BOWLING_PIN_COUNT = 10;
    protected final int MAX_FRAME_COUNT = 10;
    protected final int MIN_PITCH_COUNT = 2;
    protected final int MAX_PITCH_COUNT = 3;

    protected final String ILLEGAL_KNOCK_DOWN_PINS = "입력한 투구 결과가 남은 핀의 갯수보다 많습니다.";

    protected int index;
    protected Score score;

    public Frame(int index){
        this.index = index;
    }

    public Score getScore() {return this.score;}

    public abstract void setScore(int previousScore);

    public int getIndex() {
        return this.index;
    }

    public int sumUpCurrentScore(){
        return score.getScore();
    }

    public abstract void start(int knockedDownPins);

    public abstract boolean isEnd();

    public abstract Frame makeNextFrame(int currentFrameIndex);

    public abstract void renewScore(int knockedDownPins);

    public boolean isRenewScore(Frame currentFrame) {
        return !(this.index == currentFrame.getIndex()) && countLeftBonus() > 0;
    }

    public int countLeftBonus(){
        return this.score.getLeftBonusCount();
    }

    public abstract PitchResults getPitchResults();

    public abstract String expressState();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return BOWLING_PIN_COUNT == frame.BOWLING_PIN_COUNT &&
                MAX_FRAME_COUNT == frame.MAX_FRAME_COUNT &&
                MIN_PITCH_COUNT == frame.MIN_PITCH_COUNT &&
                MAX_PITCH_COUNT == frame.MAX_PITCH_COUNT &&
                index == frame.index &&
                Objects.equals(ILLEGAL_KNOCK_DOWN_PINS, frame.ILLEGAL_KNOCK_DOWN_PINS) &&
                Objects.equals(score, frame.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BOWLING_PIN_COUNT, MAX_FRAME_COUNT, MIN_PITCH_COUNT, MAX_PITCH_COUNT, ILLEGAL_KNOCK_DOWN_PINS, index, score);
    }
}
