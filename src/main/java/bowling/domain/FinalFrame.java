package bowling.domain;

import bowling.domain.turn.TurnState;
import bowling.domain.turn.Turns;

public class FinalFrame implements Frame {

    private final Turns turns;
    private final int frameNumber = 10;
    private final TurnState turnState;

    public FinalFrame() {
        this.turns = new Turns();
        this.turnState = TurnState.READY;
    }

    public FinalFrame(Turns turns, TurnState turnState) {
        this.turns = turns;
        this.turnState = turnState;
    }

    @Override
    public Frame bowl(int pinCount) {
        return null;
    }

    @Override
    public Turns getTurns() {
        return null;
    }
}
