package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {
    private State state;
    private static final int FINAL_NORMAL_FRAME = 9;
    private static final String BLANK = "    ";
    private final int round;

    public NormalFrame(int round) {
        this.round = round;
        this.state = new Ready();
    }

    @Override
    public Frame bowl(int countOfPins) {
        this.state = this.state.bowl(countOfPins);
        if(round == FINAL_NORMAL_FRAME && this.state.isFinish()) {
            return new FinalFrame();
        }
        if(this.state.isFinish()) {
            return new NormalFrame(this.round + 1);
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

}
