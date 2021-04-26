package bowling.domain.state;

import bowling.domain.exception.CannotBowlException;
import bowling.dto.statedto.FinalStateDTO;
import bowling.dto.statedto.StateDTO;

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

    private State addBonusState(State state) {
        if(isClear(state)){
            states.add(Ready.create());
        }
        return states.getLast();
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
        State state = states.getLast();
        state = addBonusState(state);
        states.set(states.size()-1,state.bowl(pitch));
        pitchCount++;
        return this;
    }

    @Override
    public StateDTO exportStateDTO() {
        List<StateDTO> stateDTOList = new ArrayList<>();
        for(State state : states) {
            stateDTOList.add(state.exportStateDTO());
        }
        return new FinalStateDTO(state(), stateDTOList);
    }
}
