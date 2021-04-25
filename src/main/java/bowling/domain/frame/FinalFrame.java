package bowling.domain.frame;

import bowling.domain.exception.CannotBowlException;
import bowling.domain.exception.NoRemainingFrameException;
import bowling.domain.state.*;

import java.util.LinkedList;

public class FinalFrame implements Frame{
    private static final String STRIKE = "Strike";
    private static final String Spare = "Spare";
    private static final String Miss = "Miss";
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
        return (state.state().equals(STRIKE) || state.state().equals(Spare));
    }

    private void addBonusState(State state) {
        if(isClear(state) && pitchCount < 3){
            states.add(Ready.create());
        }
    }

    @Override
    public Frame bowl(int pins) {
        if(isFinished()) {
            throw new CannotBowlException();
        }
        State state = states.getLast().bowl(pins);
        states.set(states.size()-1,state);
        addBonusState(state);
        pitchCount++;
        return this;
    }

    @Override
    public boolean isFinished() {
        State state = states.getFirst();
        return threePitchFinished(state) || state.state().equals(Miss);
    }

    @Override
    public Frame getNext() {
        throw new NoRemainingFrameException();
    }
}
