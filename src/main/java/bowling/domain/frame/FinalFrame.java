package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.FinalBowl;
import bowling.domain.bowl.FirstBowl;
import bowling.domain.bowl.SecondBowl;
import bowling.domain.score.Pins;
import bowling.domain.state.FinalState;
import bowling.domain.state.State;
import bowling.domain.state.States;

/**
 * Created : 2020-12-16 오전 9:09
 * Developer : Seo
 */
public class FinalFrame implements Frame {
    private final States states;

    public FinalFrame() {
        this.states = new States();
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public State stroke(int playerIndex, Pins pins) {
        Bowl firstBowl = new FirstBowl();
        State state = firstBowl.stroke(pins);
        FinalState finalState = new FinalState(state);
        states.add(playerIndex, finalState);
        return finalState;
    }

    @Override
    public State spare(int playerIndex, Pins pins) {
        State state = getLastState();

        if (isBonus(state)) {
            FinalBowl finalBowl = new FinalBowl(state);
            State thirdState = finalBowl.stroke(pins);
            FinalState finalState = new FinalState(state, thirdState);
            finalState.setThirdSymbol();
            states.set(playerIndex, finalState);
            return finalState;
        }

        Bowl secondBowl = new SecondBowl(state);
        State secondState = secondBowl.stroke(pins);
        FinalState finalState = new FinalState(state, secondState);
        finalState.setSecondSymbol();
        states.set(playerIndex, finalState);
        return finalState;
    }

    private boolean isBonus(State state) {
        FinalState finalState = (FinalState) state;
        return finalState.isFirstStateStrike()
                && (finalState.isSecondStateStrike() || finalState.isSecondStateSpare());
    }

    @Override
    public State getState(int userIndex) {
        validatePlayerIndex(userIndex);
        return this.states.getState(userIndex);
    }

    @Override
    public State getLastState() {
        return this.states.getLast();
    }

    @Override
    public String getSymbol(int userIndex) {
        validatePlayerIndex(userIndex);
        return this.states.getState(userIndex).getSymbol();
    }

    @Override
    public String getLastStateSymbol() {
        return getLastState().getSymbol();
    }

    @Override
    public int getFrameScore(int userIndex) {
        validatePlayerIndex(userIndex);
        return this.states.getState(userIndex).getScore().getFrameScore();
    }

    @Override
    public int getLastStateFrameScore() {
        return getLastState().getScore().getFrameScore();
    }

    @Override
    public int getFirstScore(int userIndex) {
        validatePlayerIndex(userIndex);
        return this.states.getState(userIndex).getScore().getFirst().get();
    }

    @Override
    public int getLastStateFirstScore() {
        return getLastState().getScore().getFirst().get();
    }

    @Override
    public int getSecondScore(int userIndex) {
        validatePlayerIndex(userIndex);
        return this.states.getState(userIndex).getScore().getSecond().get();
    }

    @Override
    public int getLastStateSecondScore() {
        return getLastState().getScore().getSecond().get();
    }

    private void validatePlayerIndex(int userIndex) {
        if (userIndex >= this.states.size()) {
            throw new IllegalArgumentException(String.format("사용자를 확인해주십시요. 현재 사용자 번호 : %d", userIndex));
        }
    }
}
