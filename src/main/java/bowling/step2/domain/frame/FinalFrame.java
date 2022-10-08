package bowling.step2.domain.frame;

import bowling.step2.domain.LeftOverPins;
import bowling.step2.domain.state.Ready;
import bowling.step2.domain.state.State;

import java.util.LinkedList;
import java.util.List;

public class FinalFrame implements Frame {
    private static final int COUNT_OF_MAX_BOWL = 2;
    
    private final LinkedList<State> states;
    private final LeftOverPins leftOverPins;
    
    public FinalFrame() {
        this.states = new LinkedList<>(List.of(new Ready()));
        this.leftOverPins = new LeftOverPins();
    }
    
    @Override
    public Frame bowl(final int fallenPins) {
        State state = parseState(fallenPins);
        
        states.set(statesLastIndex(), state);
        leftOverPins.knockDown(fallenPins);
    
        return checkGameOver(state);
    }
    
    private int statesLastIndex() {
        return states.size() - 1;
    }
    
    private FinalFrame checkGameOver(final State state) {
        if (isMiss(state) || isBonusBowl()) {
            return new FinalFrame();
        }
    
        if (state.isFinished()) {
            states.add(new Ready());
        }
        
        return this;
    }
    
    private boolean isBonusBowl() {
        return states.size() >= COUNT_OF_MAX_BOWL;
    }
    
    private boolean isMiss(final State state) {
        return state.isFinished() && leftOverPins.isExistLeftOverPins();
    }
    
    private State parseState(final int fallenPins) {
        State state = states.getLast();
        return state.bowl(fallenPins);
    }
}
