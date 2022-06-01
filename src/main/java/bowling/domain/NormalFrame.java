package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;
import bowling.exception.BowlingGameException;

public class NormalFrame implements Frame {
    private static final int FINAL_NORMAL_FRAME = 9;
    private static final String BLANK = "    ";
    private static final int NOT_SCORE = -1;
    private Frame nextFrame;
    private State state;
    private final int round;

    public NormalFrame(int round) {
        this.round = round;
        this.state = new Ready();
    }

    @Override
    public Frame bowl(int countOfPins) {
        this.state = this.state.bowl(countOfPins);
        if(round == FINAL_NORMAL_FRAME && this.state.isFinish()) {
            this.nextFrame = new FinalFrame();
            return this.nextFrame;
        }
        if(this.state.isFinish()) {
            this.nextFrame = new NormalFrame(this.round + 1);
            return this.nextFrame;
        }
        return this;
    }

    public State getState() {
        return this.state;
    }

    @Override
    public String expression() {
        String stateExpression = this.state.expression();
        return stateExpression + BLANK.substring(stateExpression.length());
    }

    @Override
    public Score calculateAddScore(Score beforeScore) {
        Score score = this.state.calculateAddScore(beforeScore);
        if(score.isCalculateScore()) {
            return score;
        }
        return this.nextFrame.calculateAddScore(score);
    }

    @Override
    public FrameScore getFrameScore() {
        if(!this.state.isFinish()) {
            return new FrameScore(NOT_SCORE);
        }
        try {
            return new FrameScore(getScore());
        }catch(BowlingGameException b) {
            return new FrameScore(NOT_SCORE);
        }
    }

    public int getScore() {
        Score score = this.state.getScore();
        if(score.isCalculateScore()) {
            return score.getScore();
        }
        return this.nextFrame.calculateAddScore(score).getScore();
    }

}
