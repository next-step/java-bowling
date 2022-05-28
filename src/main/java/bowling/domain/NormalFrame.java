package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {
    private static final int FINAL_NORMAL_FRAME = 9;
    private static final String BLANK = "    ";
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

    public boolean isCalculateScore() {
        return this.state.getScore().isCalculateScore();
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
        try {
            return this.nextFrame.calculateAddScore(beforeScore);
        }catch(NullPointerException e) {
            return Score.ofRelay();
        }
    }

    @Override
    public int getScore() {
        Score score = this.state.getScore();
        if(score.isCalculateScore()) {
            return score.getScore();
        }
        return this.nextFrame.calculateAddScore(score).getScore();
    }

}
