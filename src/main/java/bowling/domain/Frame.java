package bowling.domain;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Frame {

    protected final int BOWLING_PIN_COUNT = 10;
    protected final int MAX_FRAME_COUNT = 10;
    protected final String ILLEGAL_KNOCK_DOWN_PINS = "입력한 투구 결과가 남은 핀의 갯수보다 많습니다.";

    protected int index;
    protected PitchResults pitchResults;

    public Frame(int index){
        this.index = index;
        this.pitchResults = PitchResults.from(new ArrayList<>());
    }

    public int getIndex() {
        return this.index;
    }

    public PitchResults getPitchResults() {
        return pitchResults;
    }

    public int sumCurrentPitchResults() {
        return pitchResults.sumUpCurrentResult();

    }

    public abstract void start(int knockedDownPins);

    public abstract boolean isEnd();

    public abstract Frame makeNextFrame(int currentFrameIndex);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return BOWLING_PIN_COUNT == frame.BOWLING_PIN_COUNT &&
                MAX_FRAME_COUNT == frame.MAX_FRAME_COUNT &&
                index == frame.index &&
                Objects.equals(pitchResults, frame.pitchResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BOWLING_PIN_COUNT, MAX_FRAME_COUNT, index, pitchResults);
    }
}
