package bowling.domain.frame;

import bowling.domain.exception.CannotBowlException;
import bowling.domain.exception.NoRemainingFrameException;
import bowling.domain.state.*;
import bowling.dto.FrameDTO;
import bowling.dto.StateDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FinalFrame implements Frame{
    private static final String STRIKE = "STRIKE";
    private static final String SPARE = "SPARE";
    private static final String MISS = "MISS";
    private int pitchCount = 0;
    private LinkedList<State> states = new LinkedList<>();

    private FinalFrame() {
        states.add(Ready.create());
    }

    public static FinalFrame of(){
        return new FinalFrame();
    }

    private boolean threePitchFinished(State state) {
        return isClear(state) && pitchCount == 3;
    }

    private boolean isClear(State state) {
        return (state.state().equals(STRIKE) || state.state().equals(SPARE));
    }

    private void addBonusState() {
        if(isClear(states.getLast())){
            states.add(Ready.create());
        }
    }

    @Override
    public Frame bowl(int pitch) {
        if(isFinished()) {
            throw new CannotBowlException();
        }
        addBonusState();
        State state = states.getLast();
        states.set(states.size()-1,state.bowl(pitch));
        pitchCount++;
        return this;
    }

    @Override
    public boolean isFinished() {
        State state = states.getFirst();
        return threePitchFinished(state) || state.state().equals(MISS);
    }

    @Override
    public Frame getNext() {
        throw new NoRemainingFrameException();
    }

    @Override
    public FrameDTO exportFrameDTO() {
        List<StateDTO> stateDTOList = new ArrayList<>();
        for(State state : states) {
            stateDTOList.add(state.exportStateDTO());
        }
        return new FrameDTO(stateDTOList);
    }
}
