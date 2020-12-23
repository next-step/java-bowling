package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.FirstBowl;
import bowling.domain.bowl.SecondBowl;
import bowling.domain.bowl.ThirdBowl;
import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.LastState;
import bowling.domain.state.State;
import bowling.domain.state.States;

/**
 * Created : 2020-12-16 오전 9:09
 * Developer : Seo
 */
public class FinalFrame extends Frame {
    public static final int FINAL_FRAME = 10;

    private final States states;
    private final int frameNo;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.states = new States();
    }

    @Override
    public Frame next(int frameNo) {
        return null;
    }

    @Override
    public int getFrameNo() {
        return FINAL_FRAME;
    }

    @Override
    public void bowl(Pins pins) {
        Bowl firstBowl = new FirstBowl();
        State state = firstBowl.stroke(pins);
        states.add(state);
    }

    @Override
    public void secondBowl(int userIndex, State firstState, Pins pins) {
        Score firstScore = firstState.getScore();
        Bowl secondBowl = new SecondBowl(firstScore);
        State secondState = secondBowl.stroke(pins);
        LastState lastState = new LastState(firstState, secondState);
        lastState.setSecondSymbol();
        states.set(userIndex, lastState);
    }

    @Override
    public void thirdBowl(int userIndex, State state, Pins thirdPins) {
        Bowl thirdBowl = new ThirdBowl();
        State thirdState = thirdBowl.stroke(thirdPins);
        LastState lastState = new LastState(state, thirdState);
        lastState.setThirdSymbol();
        states.set(userIndex, lastState);
    }

    @Override
    public State getState(int userIndex) {
        return this.states.getState(userIndex);
    }

    @Override
    public State getLastState() {
        return this.states.getLast();
    }

    @Override
    public int getScore(int userIndex) {
        State state = this.states.getState(userIndex);
        return state.getScore().getFrameScore();
    }

    @Override
    public int getFirstScore(int userIndex) {
        State state = this.states.getState(userIndex);
        return state.getScore().getFirst().get();
    }
}
