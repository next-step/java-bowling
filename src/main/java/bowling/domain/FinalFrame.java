package bowling.domain;

import bowling.domain.state.*;

public class FinalFrame implements Frame{
    private State state;
    private int finalRound;

    public FinalFrame() {
        this.state = new Ready();
        this.finalRound = 2;
    }

    @Override
    public State bowl(int countOfPins) {
        this.state = this.state.bowl(countOfPins);
        if(this.finalRound > 0 && this.state instanceof Strike) {
            this.state = new Ready();
            this.finalRound--;
        }
        if(this.finalRound > 0 && this.state instanceof Spare) {
            this.state = new Ready();
            this.finalRound -= 2;
        }
        finalRound--;
        return this.state;
    }

    @Override
    public Frame nextFrame() {
        return null;
    }

    @Override
    public State getState() {
        return this.state;
    }
}
