package bowling.domain.state;

import bowling.domain.exception.CannotBowlException;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FinalState implements State{
    private static final String state = "FinalState";
    private static final String STRIKE = "Strike";
    private static final String Spare = "Spare";
    private static final String Miss = "Miss";
    private LinkedList<State> states;
    private int pitchCount = 0;

    private FinalState() {
        states = new LinkedList<>();
        states.add(Ready.create());
    }

    public static FinalState create() {
        return new FinalState();
    }

    private boolean threePitchFinished(State state) {
        return isClear(state) && pitchCount == 3;
    }

    private boolean isClear(State state) {
        return (state.state().equals(STRIKE) || state.state().equals(Spare));
    }

    private void addBonusState(State state) {
        if(isClear(state) && pitchCount < 3){
            states.add(Ready.create());
        }
    }

    @Override
    public boolean isFinished() {
        State state = states.getFirst();
        return threePitchFinished(state) || state.state().equals(Miss);
    }

    @Override
    public String state() {
        return state;
    }

    @Override
    public State bowl(int pitch) {
        if(isFinished()) {
            throw new CannotBowlException();
        }
        State state = states.getLast().bowl(pitch);
        states.set(states.size()-1,state);
        addBonusState(state);
        pitchCount++;
        return this;
    }

    @Override
    public StateDTO exportStateDTO() {
        List<Integer> pins = new ArrayList<>();
        for(State state : states) {
            pins.addAll(state.exportStateDTO().pins());
        }
        return new StateDTO(state(),pins);
    }
}
