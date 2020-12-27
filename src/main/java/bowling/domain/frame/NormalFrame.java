package bowling.domain.frame;

import bowling.domain.Bowling;
import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.FirstBowl;
import bowling.domain.bowl.SecondBowl;
import bowling.domain.score.Pins;
import bowling.domain.state.State;
import bowling.domain.state.States;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class NormalFrame implements Frame {
    private final int frameNo;
    private final States states;

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.states = new States();
    }

    @Override
    public Frame next() {
        return this.frameNo + Bowling.INDEX_ONE == Bowling.FINAL_FRAME ?
                new FinalFrame() : new NormalFrame(this.frameNo + Bowling.INDEX_ONE);
    }

    @Override
    public State stroke(int playerIndex, Pins pins) {
        Bowl firstBowl = new FirstBowl();
        State state = firstBowl.stroke(pins);
        states.add(playerIndex, state);
        return state;
    }

    @Override
    public State spare(int playerIndex, Pins pins) {
        Bowl secondBowl = new SecondBowl(states.getState(playerIndex));
        State state = secondBowl.stroke(pins);
        states.set(playerIndex, state);
        return state;
    }

    @Override
    public State getState(int playerIndex) {
        validatePlayerIndex(playerIndex);
        return this.states.getState(playerIndex);
    }

    @Override
    public State getLastState() {
        return this.states.getLast();
    }

    @Override
    public String getSymbol(int playerIndex) {
        validatePlayerIndex(playerIndex);
        return this.states.getState(playerIndex).getSymbol();
    }

    @Override
    public String getLastStateSymbol() {
        return getLastState().getSymbol();
    }

    @Override
    public int getFrameScore(int playerIndex) {
        validatePlayerIndex(playerIndex);
        return this.states.getState(playerIndex).getScore().getFrameScore();
    }

    @Override
    public int getLastStateFrameScore() {
        return getLastState().getScore().getFrameScore();
    }

    @Override
    public int getFirstScore(int playerIndex) {
        validatePlayerIndex(playerIndex);
        return this.states.getState(playerIndex).getScore().getFirst().get();
    }

    @Override
    public int getLastStateFirstScore() {
        return getLastState().getScore().getFirst().get();
    }

    @Override
    public int getSecondScore(int playerIndex) {
        validatePlayerIndex(playerIndex);
        return this.states.getState(playerIndex).getScore().getSecond().get();
    }

    @Override
    public int getLastStateSecondScore() {
        return getLastState().getScore().getSecond().get();
    }

    private void validatePlayerIndex(int playerIndex) {
        if (playerIndex >= this.states.size()) {
            throw new IllegalArgumentException(String.format("사용자를 확인해주십시요. 현재 사용자 번호 : %d", playerIndex));
        }
    }
}
