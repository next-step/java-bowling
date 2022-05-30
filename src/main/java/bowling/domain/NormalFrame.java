package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final FrameNumber frameNumber;
    private State state;
    private Frame next;

    private NormalFrame(FrameNumber frameNumber, State state) {
        this.frameNumber = frameNumber;
        this.state = state;
    }

    private static NormalFrame create(FrameNumber frameNumber, State state) {
        return new NormalFrame(frameNumber, state);
    }

    public static NormalFrame create(FrameNumber frameNumber) {
        return create(frameNumber, Ready.create());
    }

    public static NormalFrame initialize() {
        return create(FrameNumber.first());
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public int score() {
        if(!isEnd()) {
            return Score.INCALCULABLE_SCORE;
        }

        Score score = state.score();

        if(score.isCalculatorScore()) {
            return score.getValue();
        }

        return next.calculationScore(score);
    }

    @Override
    public int calculationScore(Score before) {
        try {
            return calculationFinalScore(before);
        } catch (IllegalStateException e) {
            return Score.INCALCULABLE_SCORE;
        }
    }

    private int calculationFinalScore(Score before) {
        Score score = state.calculatorScore(before);

        if(score.isCalculatorScore()) {
            return score.getValue();
        }

        if(Objects.isNull(next)) {
            throw new IllegalStateException("다음 프레임이 존재하지 않습니다.");
        }

        return next.calculationScore(score);
    }

    @Override
    public int getFrameNumber() {
        return frameNumber.getValue();
    }

    @Override
    public Frame bowl(Pitching pitching) {
        this.state = state.bowl(pitching);

        if (state.isEnd()) {
            return createNextFrame();
        }

        return this;
    }

    private Frame createNextFrame() {
        if (frameNumber.next().isMax()) {
            this.next = new LastFrame();
            return this.next;
        }

        this.next = create(frameNumber.next());
        return this.next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }

    @Override
    public String toString() {
        return state.symbol();
    }
}
