package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {

    public static final int MIN_NORMAL_FRAME_NUMBER = 0;
    public static final int MAX_NORMAL_FRAME_NUMBER = 8;
    public static final int ADD_FRAME_NUMBER = 1;
    public static final String INVALID_FRAME_NUMBER = "적절하지 못한 프레임 숫자가 들어왔습니다.";
    public static final String INVALID_FRAME = "더 이상 진행할 수 없습니다.";

    private final int frameNumber;
    private State state;
    private Score score;

    private NormalFrame(int frameNumber) {
        this(frameNumber, new Ready());
    }

    private NormalFrame(int frameNumber, State state) {
        if (frameNumber > MAX_NORMAL_FRAME_NUMBER || frameNumber < MIN_NORMAL_FRAME_NUMBER) {
            throw new IllegalArgumentException(INVALID_FRAME_NUMBER);
        }
        this.frameNumber = frameNumber;
        this.state = state;
    }

    public static NormalFrame of(int frameNumber) {
        return new NormalFrame(frameNumber);
    }

    public static NormalFrame first() {
        return new NormalFrame(MIN_NORMAL_FRAME_NUMBER);
    }


    @Override
    public void bowl(int count) {
        if (isEnd()) {
            throw new IllegalArgumentException(INVALID_FRAME);
        }

        this.state = state.bowl(count);
        if (state.isFinish()) {
            this.score = state.getScore();
        }
    }

    @Override
    public boolean isEnd() {
        return state.isFinish();
    }

    @Override
    public Frame next() {
        return NormalFrame.of(frameNumber + ADD_FRAME_NUMBER);
    }

    @Override
    public String getFallenPins() {
        return state.toString();
    }

    @Override
    public int getScore() {
        return score.getScore();
    }

    @Override
    public void calculateScore(int index, int count) {
        if (frameNumber == index || score.isEndCalculate()) {
            return ;
        }

        score.addScore(count);
    }

    @Override
    public boolean hasScore() {
        return Objects.nonNull(score) && score.isEndCalculate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return frameNumber == frame.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
