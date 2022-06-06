package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.exception.BowlingGameException;

import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int NOT_SCORE = -1;
    private static final int LAST_NORMAL_FRAME = 9;
    private static final String BLANK = "    ";
    private State state;
    private Frame nextFrame;
    private final int round;

    public NormalFrame(int round) {
        this.state = new Ready();
        this.round = round;
    }

    @Override
    public Frame bowl(int pins) {
        this.state = this.state.bowl(pins);
        if(this.round == LAST_NORMAL_FRAME && this.state.isFinish()) {
            this.nextFrame = new FinalFrame();
            return this.nextFrame;
        }
        if(this.state.isFinish()) {
            this.nextFrame = new NormalFrame(this.round + 1);
            return this.nextFrame;
        }

        return this;
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        Score score = this.state.calculateScore(beforeScore);
        if(score.isCalculable()) {
            return score;
        }
        return this.nextFrame.calculateScore(score);
    }

    @Override
    public int score() {
        if(!this.state.isFinish()) {
            return NOT_SCORE;
        }
        try {
            Score score = this.state.getScore();
            if (score.isCalculable()) {
                return score.getScore();
            }
            return this.nextFrame.calculateScore(score).getScore();
        }catch(BowlingGameException b) {
            return NOT_SCORE;
        }
    }

    @Override
    public String frameExpression() {
        String stateExpression = this.state.expression();
        return stateExpression + BLANK.substring(stateExpression.length());
    }

    @Override
    public boolean isFinish() {
        return this.state.isFinish();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return round == that.round && Objects.equals(state, that.state) && Objects.equals(nextFrame, that.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, nextFrame, round);
    }
}
