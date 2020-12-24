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
    private final States states;

    public FinalFrame() {
        this.states = new States();
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public void bowl(Pins pins) {
        Bowl firstBowl = new FirstBowl();
        State state = firstBowl.stroke(pins);
        states.add(state);
    }

    @Override
    public void secondBowl(int userIndex, State firstState, Pins pins) {
        validateUserIndex(userIndex);
        Score firstScore = firstState.getScore();
        Bowl secondBowl = new SecondBowl(firstScore);
        State secondState = secondBowl.stroke(pins);
        LastState lastState = new LastState(firstState, secondState);
        lastState.setSecondSymbol();
        states.set(userIndex, lastState);
    }

    @Override
    public void thirdBowl(int userIndex, State state, Pins thirdPins) {
        validateUserIndex(userIndex);
        Bowl thirdBowl = new ThirdBowl(state);
        State thirdState = thirdBowl.stroke(thirdPins);
        LastState lastState = new LastState(state, thirdState);
        lastState.setThirdSymbol();
        states.set(userIndex, lastState);
    }

    @Override
    public State getState(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex);
    }

    @Override
    public String getSymbol(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex).getSymbol();
    }

    @Override
    public int getFrameScore(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex).getScore().getFrameScore();
    }

    @Override
    public int getFirstScore(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex).getScore().getFirst().get();
    }

    @Override
    public int getSecondScore(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex).getScore().getSecond().get();
    }

    @Override
    public State getLastState() {
        return this.states.getLast();
    }

    @Override
    public int getLastStateFrameScore() {
        return getLastState().getScore().getFrameScore();
    }

    @Override
    public int getLastStateFirstScore() {
        return getLastState().getScore().getFirst().get();
    }

    @Override
    public int getLastStateSecondScore() {
        return getLastState().getScore().getSecond().get();
    }

    @Override
    public String getLastStateSymbol() {
        return getLastState().getSymbol();
    }

    private void validateUserIndex(int userIndex) {
        if (userIndex >= this.states.size()) {
            throw new IllegalArgumentException(String.format("사용자를 확인해주십시요. 현재 사용자 번호 : %d", userIndex));
        }
    }
}
