package bowling.step2.domain.frame;

import bowling.step2.domain.LeftOverPins;
import bowling.step2.domain.Score;
import bowling.step2.domain.state.Ready;
import bowling.step2.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FinalFrame implements Frame {
    private static final int COUNT_OF_MAX_BOWL = 3;
    
    private final LinkedList<State> states;
    private LeftOverPins leftOverPins;
    
    public FinalFrame() {
        this.states = new LinkedList<>(List.of(new Ready()));
        this.leftOverPins = new LeftOverPins();
    }
    
    @Override
    public Frame bowl(final int fallenPins) {
        State state = states.getLast();
        state = state.bowl(fallenPins);
        
        states.set(statesLastIndex(), state);
        leftOverPins = leftOverPins.knockDown(fallenPins);
        
        return checkGameOver(state);
    }
    
    private int statesLastIndex() {
        return states.size() - 1;
    }
    
    private FinalFrame checkGameOver(final State state) {
        if (isCurrentFrameFinished(state)) {
            return new FinalFrame();
        }
        
        if (state.isFinished()) {
            states.add(new Ready());
            leftOverPins = new LeftOverPins();
        }
        return this;
    }
    
    private boolean isCurrentFrameFinished(final State state) {
        return isMiss(state) || isFinalBonusBowl();
    }
    
    private boolean isFinalBonusBowl() {
        return (states.size() == 2 && containsSpare()) || (states.size() >= COUNT_OF_MAX_BOWL);
    }
    
    private boolean containsSpare() {
        return states.stream()
                .anyMatch(State::isSpare);
    }
    
    private boolean isMiss(final State state) {
        return state.isFinished() && leftOverPins.isExistLeftOverPins();
    }
    
    @Override
    public boolean isNormalFrame() {
        return false;
    }
    
    @Override
    public List<Score> getScores() {
        List<Score> scores = new ArrayList<>();
        for (State state : states) {
            final List<Score> stateScores = state.getScores();
            scores.addAll(stateScores);
        }
        
        return Collections.unmodifiableList(scores);
    }
    
    @Override
    public int getOneNextScore() {
        return getScores().get(0).getFallenPins();
    }
}
